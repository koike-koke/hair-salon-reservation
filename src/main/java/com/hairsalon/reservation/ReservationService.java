package com.hairsalon.reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hairsalon.menu.MenuEntity;
import com.hairsalon.menu.MenuRepository;
import com.hairsalon.user.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservationService {
	
	private final ReservationRepository repo;
	
	private final MenuRepository menuRepo;
	
	
	/**
	 * 日にちと時間を組み合わせて予約終了時間を計算し、予約情報を保存するメソッド
	 */
	@Transactional
	public void saveReservation(ReservationEntity reservation
			,LocalDate date,LocalTime time,UserEntity user) {
		
		LocalDateTime startTime=LocalDateTime.of(date, time);
		MenuEntity menu = menuRepo.findByName(reservation.getMenuName())
	            .orElseThrow(() -> new RuntimeException("メニュー「" + reservation.getMenuName() + "」が見つかりません。"));
		
		Integer menuTime = menu.getTime();
		LocalDateTime endTime=startTime.plusMinutes(menuTime);
		
		//営業時間
		LocalTime startLimit = LocalTime.of(10, 0);
	    LocalTime endLimit = LocalTime.of(20, 0);
	    
	    if (time.isBefore(startLimit) || time.isAfter(endLimit)) {
	        throw new ReservationException("営業時間は10:00から19:00までです。");
	    }
	    
		if (startTime.isBefore(LocalDateTime.now())) {
	        throw new ReservationException("過去の日時は予約できません。");
		}
		boolean overlap = repo.overlap(startTime, endTime);
	    if (overlap) {
	        throw new ReservationException("ご希望の時間は、他の予約と重なっているため予約できません。");
	    }
	    
	    
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
	
	@Transactional(readOnly = true)
	public List<ReservationEntity> findMyReservations(Long userId) {
		
        return repo.findByUserIdOrderByStartTimeDesc(userId);
    }
}
