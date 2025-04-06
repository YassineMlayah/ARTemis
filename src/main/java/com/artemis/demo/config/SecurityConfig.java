package com.artemis.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Password Encoder Bean for hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security Filter Chain allowing session-based authentication
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF since it's a local application
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/login", "/auth/session", "/auth/logout"
                                            , "/", "/login", "/signup", "/contact", "/aboutus", "/artist-signup", "/client-signup").permitAll() // Allow login & signup
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated() // Restrict other endpoints to authenticated users
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // Use session if available
            )
            .formLogin(form -> form.disable()); // Disable Spring’s default login form

        return http.build();
    }
}

/*
// OLD CODE
package com.artemis.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Password Encoder Bean for hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security Filter Chain allowing full access
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF since it's a local application
                .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // Allow full access to all endpoints
                );

        return http.build();
    }
}
*/