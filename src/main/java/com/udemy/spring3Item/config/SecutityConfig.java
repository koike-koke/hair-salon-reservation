package com.udemy.spring3Item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecutityConfig { 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            
	            .requestMatchers("/css/**", "/js/**", "/images/**", "/error").permitAll()
	            
	            .anyRequest().authenticated()
	        )
	        .oauth2Login(oauth2 -> oauth2
	            // ログインが成功したら自動的に "/" のコントローラーへ飛ばす
	            .defaultSuccessUrl("/", true)
	        );
	    
	    return http.build();
	}
	
}
	