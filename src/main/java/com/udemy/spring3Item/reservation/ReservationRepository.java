package com.udemy.spring3Item.reservation;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository 
extends JpaRepository<ReservationEntity, Long>{
	@Query("SELECT COUNT(r) > 0 FROM ReservationEntity r " +
	           "WHERE r.startTime < :endTime AND r.endTime > :startTime")
	boolean overlap(LocalDateTime startTime, LocalDateTime endTime);
}
