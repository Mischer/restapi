package com.mk.restapi.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("prod")
public class SecurityConfig {
    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()  // Разрешить доступ ко всем запросам
                )
                .csrf(csrf -> csrf.disable())  // Отключаем CSRF
                .headers(headers -> headers.frameOptions().disable())  // Отключаем дополнительные заголовки безопасности, если необходимо
                .httpBasic(httpBasic -> httpBasic.disable());  // Отключаем базовую аутентификацию

        return http.build();
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Отключаем CSRF для REST API
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api*").permitAll()  // Разрешаем доступ к путям аутентификации
                        .requestMatchers("/actuator*").permitAll()  // Разрешаем доступ к Actuator без авторизации
                        .anyRequest().authenticated()  // Все остальные запросы требуют аутентификации
                )
                .httpBasic(httpBasic -> {});  // Отключаем базовую аутентификацию, если необходимо

        return http.build();
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("-----------START SecurityFilterChain PROD-----------------");
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth*").permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {});
        return http.build();


    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        System.out.println("-----------START PasswordEncoder-----------------");
        return new BCryptPasswordEncoder();
    }
}