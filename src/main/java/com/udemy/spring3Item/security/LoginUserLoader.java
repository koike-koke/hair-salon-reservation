package com.udemy.spring3Item.security;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.udemy.spring3Item.user.UserEntity;
import com.udemy.spring3Item.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserLoader extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	
	@Override 
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        String lineUserId = oAuth2User.getName();
       
        UserEntity user = userRepository.findByLineUserId(lineUserId)
            .orElseThrow(() -> new OAuth2AuthenticationException("ユーザーが見つかりません: " + lineUserId));

        return new LoginUser(user);
    }
}
