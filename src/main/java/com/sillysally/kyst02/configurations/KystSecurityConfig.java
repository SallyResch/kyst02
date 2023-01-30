package com.sillysally.kyst02.configurations;

import com.sillysally.kyst02.user.UserModelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class KystSecurityConfig {

    private final KystPasswordConfig bcrypt;
    private final UserModelService userModelService;

    public KystSecurityConfig(KystPasswordConfig bcrypt, UserModelService userModelService) {
        this.bcrypt = bcrypt;
        this.userModelService = userModelService;
    }
//finns mer att göra från kristoffers template
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests()
                .requestMatchers("/","login","/error", "/kyst").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();


        return http.build();
    }

    //Här säger vi till Spring att använda våran implementation istället
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userModelService);
        provider.setPasswordEncoder(bcrypt.bCryptEncoder());

        return provider;
    }
}
