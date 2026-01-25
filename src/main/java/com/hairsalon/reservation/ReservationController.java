package com.hairsalon.reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hairsalon.menu.MenuRepository;
import com.hairsalon.security.LoginUser;
import com.hairsalon.user.UserEntity;
import com.hairsalon.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ReservationController {
	
	private final ReservationService reservationService;
	private final UserRepository repo;
	private final MenuRepository menuRepo;
	
	@GetMapping("/form")//http://localhost:8083/form
	public String form(Model model) {
		model.addAttribute("timeSlots", reservationService.generateTimeSlots());
	    model.addAttribute("reservation", new ReservationEntity());
	    model.addAttribute("menus", menuRepo.findAll());
	    
	     return "form";
	}
	
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
	
	@GetMapping("/mypage")
	public String showMypage(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		List<ReservationEntity> myReservations = 
	            reservationService.findMyReservations(loginUser.getUser().getId());
		
		model.addAttribute("reservations", myReservations);
		model.addAttribute("userName", loginUser.getUser().getName());
		
		return "mypage";
	}
	
	@PostMapping("/reservations/cancel/{id}")
	public String cancel(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
		try {
	reservationService.cancelReservation(id);
	
	redirectAttributes.addFlashAttribute("successMessage", "予約をキャンセルしました。");
		}catch (ReservationException e) {
			
			redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
		}
		
		return "redirect:/mypage";
	}
	
	
	
}
