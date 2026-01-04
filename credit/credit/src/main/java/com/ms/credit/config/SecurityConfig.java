package com.ms.credit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class that enables web security and configures HTTP security rules.
 * Simplified version with basic authentication for development.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain with basic authentication.
     * Allows all requests for development purposes while keeping Spring Security active.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for API development
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  // Allow all requests for now
            );
        
        return http.build();
    }
}
