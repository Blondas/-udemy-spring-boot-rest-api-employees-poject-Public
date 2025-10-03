package com.chacaroja.udemy.springbootrestapi.employees.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/h2/**") .permitAll()
                        .requestMatchers(HttpMethod.POST, "/h2/**") .permitAll()
                        .requestMatchers("/swagger", "swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html") .permitAll()
                        .requestMatchers("/swagger", "swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html") .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        http.httpBasic(AbstractHttpConfigurer::disable);

        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);

        http.exceptionHandling(exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(authenticationEntryPoint())
        );

        http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));

        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {

        return (_, response, _) -> {
            // don't send WWW-Authenticate header
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");

            // Clear the WWW-Authenticate header to prevent browser pop-up
            response.setHeader("WWW-Authenticate", "");
            response.getWriter().write("{\"error\": \"Unauthorized access\"}");
        };
    }
}
