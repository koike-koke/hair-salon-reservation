package com.udemy.spring3Item.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/")
	public String index(@AuthenticationPrincipal OAuth2User principal, Model model) {
		
		userService.updateUser(principal);
		
		model.addAttribute("userName", principal.getAttribute("displayName"));
		return "index";
		
		
	}
	

	
}
