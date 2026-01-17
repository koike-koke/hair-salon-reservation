package com.udemy.spring3Item.reservation;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udemy.spring3Item.security.LoginUser;
import com.udemy.spring3Item.user.UserEntity;
import com.udemy.spring3Item.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
	
	private final ReservationService reservationService;
	private final UserRepository repo;
	
	@GetMapping("/form")//http://localhost:8083/form
	public String form(Model model) {
		model.addAttribute("timeSlots", reservationService.generateTimeSlots());
	    model.addAttribute("reservation", new ReservationEntity());
	    
	     return "form";
	}
	
	//TODO
	@PostMapping("/reservations/save")
    public String save
   (@ModelAttribute ReservationEntity reservation,
    @RequestParam("date") LocalDate date,
    @RequestParam("time") LocalTime time,
    @AuthenticationPrincipal LoginUser loginUser
    ) {
		
		UserEntity user = loginUser.getUser();
	
	reservationService.saveReservation(reservation, date, time,user);
	
	return "redirect:/success";
	
	}
	
	@GetMapping("/success")
	public String success(Model model) {
		 return "success";
	}
	
}
