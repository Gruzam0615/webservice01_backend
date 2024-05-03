package com.gruzam0615.webservice01.utils.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gruzam0615.webservice01.utils.security.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    private final String[] PERMIT_URL_LIST = {
        "/api/sign/**",
        "/api/user/findByUsername",
        "/api/user/test01",

    };

    private final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable());
        http.cors((cors) -> cors.disable());

        http.httpBasic((basic) -> basic.disable());
        http.formLogin((form) -> form.disable());

        http.sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        http.authorizeHttpRequests((request) -> request
            .requestMatchers(PERMIT_URL_LIST).permitAll()
            .anyRequest().authenticated()
        );

        // http.addFilterBefore(new JwtFil, null)

        return http.build();
    }

}
