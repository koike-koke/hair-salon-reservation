package com.hairsalon.user;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // こちらが推奨

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository repo;
	
	@Transactional
	public void updateUser(OAuth2User principal) {  
		
	String lineUserId=principal.getAttribute("userId");
	String name = principal.getAttribute("displayName");
	
	Optional<UserEntity> userOpt 
	=repo.findByLineUserId(lineUserId); 
	
	if (userOpt.isPresent()) {
		UserEntity existingUser=userOpt.get();
		if(!Objects.equals(existingUser.getName(), name)) {
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
