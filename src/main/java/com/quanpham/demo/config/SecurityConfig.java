package com.quanpham.demo.config;

// import java.beans.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.antMatchers("/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                // .oauth2ResourceServer(oauth2 -> oauth2
                // .jwt(jwt -> jwt
                // .jwtAuthenticationConverter(myConverter())))
                .build();
        // SecurityContextHolder.getContext().getAuthentication().isAuthenticated()

        // @Bean
        // public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // return http
        // .authorizeHttpRequests(auth -> {
        // auth.antMatchers("/").permitAll();
        // auth.anyRequest().authenticated();
        // })
        // .oauth2Login(withDefaults())
        // // .formLogin(withDefaults())
        // .build();
    }

    @Bean
    PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
