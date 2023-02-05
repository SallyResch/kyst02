package com.sillysally.kyst02.configurations;

import com.sillysally.kyst02.user.UserModelService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.concurrent.TimeUnit;

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
        http.csrf().disable()
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers("/kyst","/login","/error", "/register","/static/**","/rest/**", "/logout").permitAll()
                            .requestMatchers("/admin").hasRole("ADMIN")
                            .requestMatchers("/user").hasRole("USER")
                            .requestMatchers("/delete").authenticated()
                            .anyRequest()
                            .authenticated();
                })
                .formLogin(formlogin -> {
                    formlogin.loginPage("/login");
                })
                .rememberMe(rememberMe ->{
                    rememberMe
                            .rememberMeParameter("remember-me")
                            .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                            .key("someSecureKey")
                            .userDetailsService(userModelService);
                })
                .logout(logout ->{
                    logout
                            .logoutUrl("/logout")
                            .logoutSuccessUrl("/login")
                            .clearAuthentication(true)
                            .invalidateHttpSession(true)
                            .deleteCookies("remember-me", "JSESSIONID");
                })
                .authenticationProvider(authenticationOverride());

        return http.build();
    }

    //Här säger vi till Spring att använda våran implementation istället
    public DaoAuthenticationProvider authenticationOverride(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userModelService);
        provider.setPasswordEncoder(bcrypt.bCryptEncoder());

        return provider;
    }
}
