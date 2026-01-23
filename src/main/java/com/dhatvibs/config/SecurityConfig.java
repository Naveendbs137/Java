/*
 * package com.dhatvibs.config;
 * 
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.web.SecurityFilterChain;
 * 
 * @Configuration public class SecurityConfig {
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception {
 * 
 * http .csrf(csrf -> csrf.disable()) .authorizeHttpRequests(auth -> auth
 * .requestMatchers( "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html"
 * ).permitAll() .anyRequest().permitAll() ) .formLogin(form -> form.disable())
 * .httpBasic(basic -> basic.disable());
 * 
 * return http.build(); } }
 * 
 */ 

/*
 * package com.dhatvibs.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.web.SecurityFilterChain;
 * 
 * @Configuration public class SecurityConfig {
 * 
 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
 * throws Exception {
 * 
 * http // 🔴 Disable CSRF (mandatory for Swagger & mobile apps) .csrf(csrf ->
 * csrf.disable())
 * 
 * // 🔐 Authorization rules .authorizeHttpRequests(auth -> auth // Swagger
 * .requestMatchers( "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html"
 * ).permitAll()
 * 
 * // ✅ Onboarding APIs .requestMatchers("/api/onboarding/**").permitAll()
 * 
 * // 🔓 Everything else (temporary) .anyRequest().permitAll() )
 * 
 * // Disable default login mechanisms .formLogin(form -> form.disable())
 * .httpBasic(basic -> basic.disable());
 * 
 * return http.build(); } }
 */ 




package com.dhatvibs.config;

import com.dhatvibs.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                // Swagger
                .requestMatchers(
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html"
                ).permitAll()

                // OTP / Auth APIs
                .requestMatchers("/api/onboarding/**").permitAll()

                // 🔐 Everything else secured
                .anyRequest().authenticated()
            )

            .addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
            )

            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
