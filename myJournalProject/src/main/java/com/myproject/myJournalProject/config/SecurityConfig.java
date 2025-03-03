package com.myproject.myJournalProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.myproject.myJournalProject.services.UserSecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public final UserSecurityService userSecurityService;

    public SecurityConfig(UserSecurityService userSecurityService){
        this.userSecurityService = userSecurityService;
    }

    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .csrf(csrf-> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/users/**", "/journal/**").authenticated()
                .requestMatchers("/admin/**" ).hasRole("ADMIN")
                // .requestMatchers("/admin/**" ).hasAnyRole("ADMIN")
                // .requestMatchers("/admin/**" ).hasAnyAuthority("ROLE_ADMIN") //this can also work 
                .anyRequest().permitAll()
                )
            .httpBasic(httpBasic-> {}); // Enable basic auth
            return http.build();
        }
        
        @Bean
        public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder){
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setUserDetailsService(userDetailsService);
            authProvider.setPasswordEncoder(passwordEncoder);
            return new ProviderManager(authProvider);
        }
    
}