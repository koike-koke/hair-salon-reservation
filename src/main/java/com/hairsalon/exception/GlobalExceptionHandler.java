package com.hairsalon.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hairsalon.menu.MenuRepository;
import com.hairsalon.reservation.ReservationEntity;
import com.hairsalon.reservation.ReservationException;
import com.hairsalon.reservation.ReservationService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private final ReservationService reservationService;
	private final MenuRepository menuRepo;

	@ExceptionHandler(ReservationException.class)
	public String handleReservationError(ReservationException e, Model model) {
		
	
		model.addAttribute("errorMessage", e.getMessage());
		
		model.addAttribute("menus", menuRepo.findAll());
		model.addAttribute("timeSlots", reservationService.generateTimeSlots());
		model.addAttribute("reservation", new ReservationEntity());
		
		return "form"; 
    }
	
}
	


