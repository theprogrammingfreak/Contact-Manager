package com.scm20.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm20.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    // user create and login using java code within memory service

    // @Bean
    // public UserDetailsService userDetailsService(){

    //     UserDetails user1 = User
    //     .withDefaultPasswordEncoder()
    //     .username("admin123")
    //     .password("admin123")
    //     .roles("ADMIN","USER")
    //     .build();

    //     var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
    //     return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    // configuration of authentication provider

    @Bean
    public AuthenticationProvider authencticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // user detail object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // password encoder object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        //configuration of urls for deciding the public and private urls

        httpSecurity.authorizeHttpRequests(authorize->{
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        }); 

        //default login page
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
        
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
