package com.aptech.mymusic.presentation.security;

import com.aptech.mymusic.presentation.security.jwt.*;
import com.aptech.mymusic.presentation.security.voter.CustomWebExpressionVoter;
import com.aptech.mymusic.presentation.service.UserDetailService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailService userDetailService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationHandler jwtAuthenticationHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(UserDetailService userDetailService,
                          JwtAuthorizationFilter jwtAuthorizationFilter,
                          JwtAccessDeniedHandler jwtAccessDeniedHandler,
                          JwtAuthenticationHandler jwtAuthenticationHandler,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.userDetailService = userDetailService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationHandler = jwtAuthenticationHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
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
                new CustomWebExpressionVoter(userDetailService),
                new RoleVoter(),
                new AuthenticatedVoter());
        return new UnanimousBased(decisionVoters);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        // permit all public url
        http.cors().and().csrf().disable()
                .authorizeRequests(req -> req
                        .accessDecisionManager(accessDecisionManager())
                        .antMatchers(SecurityConstant.PUBLIC_URL).permitAll()
                        .antMatchers(HttpMethod.GET, SecurityConstant.PUBLIC_GET_URL).permitAll()
                        .anyRequest().denyAll())
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

}
