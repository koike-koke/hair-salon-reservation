package com.hairsalon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.hairsalon.security.LoginUserLoader;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig { 
	
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
	        )
	        .logout(logout -> logout
	                .logoutUrl("/logout")            
	                .logoutSuccessUrl("/") 
	                .invalidateHttpSession(true)   
	                .deleteCookies("JSESSIONID")     
	            ); 
	    
	    return http.build();
	}
	
}
	