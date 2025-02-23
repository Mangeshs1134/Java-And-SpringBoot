package com.myproject.myJournalProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurity   {

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
    //     auth
    //         .
    // }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests(auth -> auth.requestMatchers("/journal/**")
                .authenticated().anyRequest().permitAll()
            )
            .httpBasic(null);

        return http.build();
    }
    
}
