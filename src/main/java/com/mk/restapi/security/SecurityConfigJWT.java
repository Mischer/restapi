package com.mk.restapi.security;

import com.mk.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*@Configuration
@EnableWebSecurity
@Profile("development")
public class SecurityConfigJWT {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("-----------START SecurityFilterChain DEV-----------------");
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("-----------START PasswordEncoder-----------------");
        return new BCryptPasswordEncoder();
    }
}*/

@Configuration
@EnableWebSecurity // Enables Spring Security’s web security support
@EnableMethodSecurity // Enables method-level security annotations like @PreAuthorize
@Profile("dev")
public class SecurityConfigJWT {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    public SecurityConfigJWT(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    // Bean for password encoding
    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("-----------START PasswordEncoder-----------------");
        return new BCryptPasswordEncoder();
    }

    // Authentication manager bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    // Security filter chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF as we're using JWT
                .csrf(csrf -> csrf.disable())
                // No session management
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // Set unauthorized requests exception handler (optional)
                //.exceptionHandling(exception -> exception
                //    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                //)
                // Configure authorization
                .authorizeHttpRequests(authorize -> authorize
                        // Permit all for authentication endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                );

        // Add JWT authentication filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}