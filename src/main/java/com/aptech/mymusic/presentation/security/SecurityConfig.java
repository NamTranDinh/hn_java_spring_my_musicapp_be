package com.aptech.mymusic.presentation.security;

import com.aptech.mymusic.domain.entities.Enums;
import com.aptech.mymusic.domain.entities.Permission;
import com.aptech.mymusic.domain.repository.PermissionRepository;
import com.aptech.mymusic.presentation.security.jwt.*;
import com.aptech.mymusic.presentation.security.voter.CustomWebExpressionVoter;
import com.aptech.mymusic.presentation.service.UserDetailService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class SecurityConfig {
    private final ApplicationContext context;
    private final PermissionRepository permissionRepository;
    private final UserDetailService userDetailService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationHandler jwtAuthenticationHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(ApplicationContext context,
                          PermissionRepository permissionRepository,
                          UserDetailService userDetailService,
                          JwtAuthorizationFilter jwtAuthorizationFilter,
                          JwtAccessDeniedHandler jwtAccessDeniedHandler,
                          JwtAuthenticationHandler jwtAuthenticationHandler,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.context = context;
        this.permissionRepository = permissionRepository;
        this.userDetailService = userDetailService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationHandler = jwtAuthenticationHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    public void updateConfiguration() {
        try {
            WebSecurityConfiguration webSecurityConfiguration = (WebSecurityConfiguration) context.getBean("org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration");
            HttpSecurity httpSecurity = (HttpSecurity) context.getBean("org.springframework.security.config.annotation.web.configuration.HttpSecurityConfiguration.httpSecurity");
            SecurityFilterChain chain = securityFilterChain(httpSecurity);
            Method method = WebSecurityConfiguration.class.getDeclaredMethod("setFilterChains", List.class);
            method.setAccessible(true);
            method.invoke(webSecurityConfiguration, Collections.singletonList(chain));
        } catch (Exception e) {
            System.out.println("updateConfiguration error:");
            e.printStackTrace();
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setUserDetailsPasswordService(userDetailService);
        return authProvider;
    }

    @Bean
    public AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> decisionVoters = Arrays.asList(
                new CustomWebExpressionVoter(),
                new RoleVoter(),
                new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }

    @Bean
    @Scope("prototype")
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        System.out.println("Security filter chain " + http);

        // permit all public url
        http.authorizeRequests(req -> req
                .accessDecisionManager(accessDecisionManager())
                .antMatchers(SecurityConstant.PUBLIC_URL).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstant.PUBLIC_GET_URL).permitAll());

        // load all permission
        permissionRepository.findAll().stream()
                .filter(p -> p.getStatus() == Enums.Status.ACTIVE)          // get active
                .sorted((p1, p2) -> p2.getSlug().compareTo(p1.getSlug()))   // sort by slug z -> a
                .forEach(p -> matchPermission(http, p));

        http.authorizeRequests(req -> req.anyRequest().denyAll())
                .cors().and().csrf().disable()
                .formLogin(frm -> frm
                        .loginPage(SecurityConstant.LOGIN_PAGE)
                        .failureHandler(jwtAuthenticationHandler)
                        .successHandler(jwtAuthenticationHandler)
                        .permitAll())
                .logout(out -> out
                        .logoutUrl(SecurityConstant.LOGOUT_PAGE)
                        .logoutSuccessUrl(SecurityConstant.LOGIN_PAGE)
                        .deleteCookies(JwtCookiesManager.JWT_LOGIN_SESSION_TOKEN_NAME)
                        .clearAuthentication(true)
                        .permitAll())
                .exceptionHandling(cf -> cf
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private void matchPermission(HttpSecurity http, Permission permission) {
        try {
            http.authorizeRequests().antMatchers(permission.getSlug()).hasAuthority(permission.getAuthority());
            System.out.println(permission.getTitle() + " -> " + permission.getAuthority() + " : " + permission.getSlug());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
