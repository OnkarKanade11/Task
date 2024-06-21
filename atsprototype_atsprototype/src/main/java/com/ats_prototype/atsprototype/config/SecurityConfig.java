package com.ats_prototype.atsprototype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, UserDetailsService userDetailsService) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers("/candidate/register", "/login", "/css/**", "/js/**").permitAll()
                .requestMatchers("/index").permitAll()
                .requestMatchers("/candidate/**").hasRole("CANDIDATE")
                .requestMatchers("/employer/**").hasRole("EMPLOYER")
                .requestMatchers("/recruiter/**").hasRole("RECRUITER")
                .requestMatchers("/coordinator/**").hasRole("COORDINATOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/candidate/dashboard", true)
                .failureUrl("/login?error")
                .and()
                .logout()
                .logoutSuccessUrl("/index")
                .and()
                .userDetailsService(userDetailsService)
                .build();
    }
}