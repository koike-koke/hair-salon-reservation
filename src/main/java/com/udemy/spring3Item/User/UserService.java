package com.udemy.spring3Item.User;

import jakarta.transaction.Transactional;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository repo;
	
	@Transactional
	public void updateUser(OAuth2User principal) {
		
	String lineUserId=principal.getAttribute("userId");
	String name = principal.getAttribute("displayName");
	
	Optional<UserEntity>
	
		
	}
	
	
	

}
