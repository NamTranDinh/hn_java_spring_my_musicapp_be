package com.aptech.mymusic.config;

import com.aptech.mymusic.domain.entities.Enums;
import com.aptech.mymusic.domain.entities.Permission;
import com.aptech.mymusic.domain.entities.Role;
import com.aptech.mymusic.domain.repository.RoleRepository;
import com.aptech.mymusic.domain.repository.UserRepository;
import com.aptech.mymusic.presentation.service.UserDetailService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthenticationConfig(UserRepository userRepository,
                                RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/raw/**", "/images/**", "/public/**", "/api/**")
                .permitAll();
        grantPermission(http).authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin((frm) -> frm
                        .loginPage("/auth/login")
                        .successForwardUrl("/auth/redirect")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll())
                .logout((logout) -> logout.clearAuthentication(true).permitAll())
                .csrf().disable();

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




