package com.udemy.spring3Item.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.udemy.spring3Item.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginUser implements UserDetails,OAuth2User {

	
    private final UserEntity user;
    
    private final java.util.Map<String, Object> attributes;
    

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getLineUserId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
    @Override
    public java.util.Map<String, Object> getAttributes() {
        // 空のMapではなく、保持している情報を返す
        return attributes; 
    }
    
    @Override//管理者画面作りたい場合ここ変える
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return java.util.Collections.emptyList();
    }
    
    @Override
    public String getName() {
        return user.getLineUserId();
    }
    
    
    
    
}