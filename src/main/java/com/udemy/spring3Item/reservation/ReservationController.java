package com.udemy.spring3Item.reservation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
	
	private final ReservationService reservationService;
	
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("timeSlots", reservationService.generateTimeSlots());
	    model.addAttribute("reservation", new ReservationEntity());
	    
	     return "form";
	}
	//HTMLから返ってきたデータを保存するコントローラー書くところから
	
	
	

}
