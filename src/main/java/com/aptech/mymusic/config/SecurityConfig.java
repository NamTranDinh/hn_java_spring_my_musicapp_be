package com.aptech.mymusic.config;

import com.aptech.mymusic.config.component.JwtAccessDeniedHandler;
import com.aptech.mymusic.config.component.JwtAuthenticationEntryPoint;
import com.aptech.mymusic.config.component.JwtAuthorizationFilter;
import com.aptech.mymusic.domain.entities.Enums;
import com.aptech.mymusic.domain.entities.Permission;
import com.aptech.mymusic.domain.entities.Role;
import com.aptech.mymusic.domain.repository.RoleRepository;
import com.aptech.mymusic.presentation.service.UserDetailService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final RoleRepository roleRepository;
    private final UserDetailService userDetailService;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    public SecurityConfig(RoleRepository roleRepository,
                          UserDetailService userDetailService,
                          JwtAuthorizationFilter jwtAuthorizationFilter,
                          JwtAccessDeniedHandler jwtAccessDeniedHandler,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.roleRepository = roleRepository;
        this.userDetailService = userDetailService;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {

//        grantPermission(http.authorizeRequests(req -> req
//                .antMatchers(SecurityConstant.PUBLIC_URL).permitAll()
//                .antMatchers(HttpMethod.GET, SecurityConstant.PUBLIC_GET_URL).permitAll()))
//                .authorizeRequests(req -> req.anyRequest().authenticated())
//                .formLogin((frm) -> frm
//                        .loginPage("/auth/login")
//                        .successForwardUrl("/auth/redirect")
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .permitAll())
//                .logout((logout) -> logout.clearAuthentication(true).permitAll())
//                .csrf().disable();

        http.authorizeRequests(req -> req
                .antMatchers(SecurityConstant.PUBLIC_URL).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstant.PUBLIC_GET_URL).permitAll());
        grantPermission(http)
                .authorizeRequests(req -> req.anyRequest().authenticated())
                .formLogin(frm -> frm
                        .loginPage("/admin/auth/login")
                        .successForwardUrl("/admin/auth/redirect")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll())
                .logout(out -> out
                        .logoutSuccessUrl("/admin/auth/login")
                        .clearAuthentication(true)
                        .permitAll())
                .exceptionHandling(cf -> cf
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                ;

        return http.build();
    }

    private HttpSecurity grantPermission(HttpSecurity http) throws Exception {
        List<Role> roles = roleRepository.findAll();

        for (Role role : roles) {
            if (role.getStatus() == Enums.Status.INACTIVE) {
                continue;
            }
            List<String> permissionList = new ArrayList<>();
            for (Permission permission : role.getPermissions()) {
                if (permission.getStatus() == Enums.Status.INACTIVE) {
                    continue;
                }
                permissionList.add(permission.getSlug());
            }
            String[] authorities = permissionList.toArray(new String[0]);
            http.authorizeRequests()
                    .antMatchers(authorities)
                    .hasAuthority(role.getAuthority());
        }
        return http;
    }

}




