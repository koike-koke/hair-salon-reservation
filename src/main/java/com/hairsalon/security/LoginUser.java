package com.hairsalon.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.hairsalon.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUser implements UserDetails,OAuth2User {
	
    private final UserEntity user; 
    private final java.util.Map<String, Object> attributes;
    
    @Override
    public String getUsername() {
        return user.getLineUserId();
    }   
    @Override
    public String getName() {
        return user.getLineUserId();
    }
    @Override
    public java.util.Map<String, Object> getAttributes() {
        return attributes; 
    }  
    @Override 
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    @Override
    public String getPassword() {
        return null;
    }    
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}