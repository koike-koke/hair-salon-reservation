package com.udemy.spring3Item.reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.spring3Item.user.UserEntity;

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
			,LocalDate date,LocalTime time,UserEntity user) {
		
		LocalDateTime startTime=LocalDateTime.of(date, time);
		Integer menuTime = switch (reservation.getMenuName()) {
        case "カット" -> 30;
        case "カラー＆カット" -> 90;
        case "パーマ" -> 120;
        default -> 30;
         };
		LocalDateTime endTime=startTime.plusMinutes(menuTime);
		
		reservation.setUser(user);
		reservation.setEndTime(endTime);
		reservation.setMenuTime(menuTime);
		reservation.setStartTime(startTime);
		
		repo.save(reservation);
	}
	
	/**
	 * 営業時間３０分刻みに作成
	 * 開始時間、終了時間変えると自動計算
	 * @return　３０分間隔で区切られた営業時間のリスト
	 */
	public List<LocalTime> generateTimeSlots(){
		List<LocalTime> timeSlots = new ArrayList<>();
		
		//ここ営業時間
		LocalTime staetTime = LocalTime.of(10, 0);
		LocalTime endTime = LocalTime.of(19, 0);
		
		while (!staetTime.isAfter(endTime)) {
            timeSlots.add(staetTime);
            staetTime = staetTime.plusMinutes(30);
        }
		return timeSlots;
	}

	
}
