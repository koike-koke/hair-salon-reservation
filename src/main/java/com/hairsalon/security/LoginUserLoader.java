package com.hairsalon.security;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.hairsalon.user.UserEntity;
import com.hairsalon.user.UserRepository;
import com.hairsalon.user.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserLoader extends DefaultOAuth2UserService{
	
	private final UserRepository userRepository;
	private final UserService userService;
	
	@Override 
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        		
        OAuth2User oAuth2User = super.loadUser(userRequest);
        
        userService.updateUser(oAuth2User);
        
        String lineUserId = oAuth2User.getAttribute("userId");
       
        UserEntity user = userRepository.findByLineUserId(lineUserId)
            .orElseThrow(() -> new OAuth2AuthenticationException("ユーザーが見つかりません: " + lineUserId));

        return new LoginUser(user,oAuth2User.getAttributes());
    }
}
