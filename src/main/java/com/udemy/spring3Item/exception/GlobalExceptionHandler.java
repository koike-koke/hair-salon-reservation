package com.udemy.spring3Item.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.udemy.spring3Item.reservation.ReservationException;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ReservationException.class)
	public String handleReservationError(ReservationException e, Model model) {
		
		model.addAttribute("errorMessage", e.getMessage());
		
		return "form"; 
    }
	
}
	


