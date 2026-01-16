package com.udemy.spring3Item.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.udemy.spring3Item.security.LoginUserLoader;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecutityConfig { 
	
	private final LoginUserLoader loginUserLoader;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeHttpRequests(auth -> auth
	            
	            .requestMatchers("/css/**", "/js/**", "/images/**", "/error").permitAll()
	            
	            .anyRequest().authenticated()
	        )
	        .oauth2Login(oauth2 -> oauth2
	        		
	            .defaultSuccessUrl("/", true)
	            .userInfoEndpoint(userInfo -> userInfo
	                    .userService(loginUserLoader)
	                )
	        );
	    
	    return http.build();
	}
	
}
	