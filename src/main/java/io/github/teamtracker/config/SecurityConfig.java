package io.github.teamtracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.github.teamtracker.filter.JwtFilter;

@Configuration
public class SecurityConfig {

    /*
     * @SuppressWarnings("deprecation")
     * 
     * @Bean
     * SecurityFilterChain securityFilterChain(HttpSecurity http) throws
     * Exception {
     * http.csrf(csrf -> csrf.disable())
     * .authorizeRequests(requests -> requests
     * .anyRequest().permitAll())
     * .httpBasic(basic -> basic.disable());
     * 
     * return http.build();
     * }
     */

    @SuppressWarnings("deprecation")
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests(requests -> requests
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class));

        return http.build();
    }
}