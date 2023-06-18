package com.user.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import jakarta.servlet.http.HttpServletResponse;

import static org.springframework.security.config.Customizer.withDefaults;



@Configuration
@EnableMethodSecurity


public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        // http = http.cors(withDefaults()).csrf(csrf -> csrf.disable());
        http = http.cors(cs -> cs.disable());

        // Set session management to stateless
        // http = http
        //         .sessionManagement(management -> management
        //                 .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //                 .and());

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(
                                (request, response, ex) -> {
                                    response.sendError(
                                            HttpServletResponse.SC_UNAUTHORIZED,
                                            ex.getMessage());
                                }));

        // Set permissions on endpoints
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/auth/**").permitAll()
                .anyRequest().authenticated())
                .httpBasic(withDefaults());

        // Add JWT token filter
        // http.addFilterBefore(
        //         jwtTokenFilter,
        //         UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Used by Spring Security if CORS is enabled.
   
}