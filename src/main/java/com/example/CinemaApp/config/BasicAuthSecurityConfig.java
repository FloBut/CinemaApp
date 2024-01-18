package com.example.CinemaApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration

public class BasicAuthSecurityConfig {
    //benul care ma ajuta sa imi cripteze parola in baza de date
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //este o subclasa a clasei encoder care encodeaza parola
    }

}
