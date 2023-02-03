package com.sillysally.kyst02.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class KystPasswordConfig {

    @Bean
    public PasswordEncoder bCryptEncoder(){
        return new BCryptPasswordEncoder(14);
    }
}
