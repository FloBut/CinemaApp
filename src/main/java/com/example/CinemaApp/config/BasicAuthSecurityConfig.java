package com.example.CinemaApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class BasicAuthSecurityConfig {
    //benul care ma ajuta sa imi cripteze parola in baza de date
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //este o subclasa a clasei encoder care encodeaza parola
    }

    //configurare de baza
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //au voie sa adauge o proiectie a unui film doar administratorii salii de cinema
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        //pe endpointurile care incep cu movie si orice dupa ele, permite la toata lumea
        //indiferent de rol sa le faca
        // pe metodele de post pentru un film cu mai multe proiectii
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/movie/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/projection/**").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();


    }

}
