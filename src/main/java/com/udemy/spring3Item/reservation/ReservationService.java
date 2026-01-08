package com.udemy.spring3Item.reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
	
	private final ReservationRepository repo;
	
	
	/**
	 * 日にちと時間を組み合わせて予約終了時間を計算し、予約情報を保存するメソッド
	 */
	@Transactional
	public void saveReservation(ReservationEntity reservation
			,LocalDate date,LocalTime time) {
		
		LocalDateTime startTime=LocalDateTime.of(date, time);
		reservation.setStartTime(startTime);
		 
		//メニューの時間施術ごとに変えたかったらここ修正
		Integer menuTime=reservation.getMenuTime();
		
		LocalDateTime endTime=startTime.plusMinutes(menuTime);
		
		reservation.setEndTime(endTime);
		
		repo.save(reservation);
	}
	
	/**
	 * 営業時間３０分刻みに作成
	 * 開始時間、終了時間変えると自動計算
	 * @return　３０分間隔で区切られた営業時間のリスト
	 */
	public List<LocalTime> generateTimeSlots(){
		List<LocalTime> timeSlots = new ArrayList<>();
		LocalTime staetTime = LocalTime.of(10, 0);
		LocalTime endTime = LocalTime.of(19, 0);
		
		while (!staetTime.isAfter(endTime)) {
            timeSlots.add(staetTime);
            staetTime = staetTime.plusMinutes(30);
        }
		return timeSlots;
	}

	
}
