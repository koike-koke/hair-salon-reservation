package com.hairsalon.reservation;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository 
extends JpaRepository<ReservationEntity, Long>{
	
	boolean existsByEndTimeAfterAndStartTimeBeforeAndActiveTrue( LocalDateTime startTime,LocalDateTime endTime);
	
	List<ReservationEntity> findByUserIdAndActiveTrueOrderByStartTimeDesc(Long userId);
	
	
}
