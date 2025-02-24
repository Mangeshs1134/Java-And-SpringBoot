package com.myproject.myJournalProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.myproject.myJournalProject.services.CustomUserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurity   {

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private CustomUserServiceImpl customUserServiceImpl;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth
            .userDetailsService(customUserServiceImpl)
            .passwordEncoder(passwordEncoder());
            
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests(auth -> auth.requestMatchers("/journal/**", "/users/**")
                .authenticated().anyRequest().permitAll()
            )
            .httpBasic(httpBasic -> {})  // Enable HTTP Basic Authentication
            // .httpBasic(withDefaults())  // Enable HTTP Basic Authentication
            .csrf(csrf-> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
            
            return http.build();
        }
        
    }
    
    // http
    //    