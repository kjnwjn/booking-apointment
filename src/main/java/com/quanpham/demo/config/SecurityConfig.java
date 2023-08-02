package com.quanpham.demo.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.quanpham.demo.config.filters.JwtFilter;

@Configuration
@EnableMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)

public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authorize -> authorize.antMatchers("/api/v1/auth/")
                                .permitAll()
                                .antMatchers("/api/v1/**")
                                .authenticated()
                                .anyRequest().authenticated())
                // .formLogin(form ->
                // form.loginProcessingUrl("/login")).httpBasic(withDefaults())
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        ;

        return http.build();

    }

    @Bean
    PasswordEncoder bcryptEncoder() {
        return new BCryptPasswordEncoder();
    }

}
