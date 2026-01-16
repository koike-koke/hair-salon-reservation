package com.udemy.spring3Item.user;

import java.util.Optional;

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
		
	//ライン内部IDと名前 直すとこからデバックで
	String lineUserId=principal.getAttribute("userId");
	String name = principal.getAttribute("displayName");
	
	Optional<UserEntity> userOpt 
	=repo.findByLineUserId(lineUserId); 
	
	if (userOpt.isPresent()) {
		UserEntity existingUser=userOpt.get();
		if(!existingUser.getName().equals(name)) {
			existingUser.setName(name);  
		}
		
		
	}else {
		UserEntity newUser=new UserEntity();
		newUser.setLineUserId(lineUserId);
		newUser.setName(name);
		newUser.setRole("ROLE_USER");
		repo.save(newUser);
	}	
	}    
}
