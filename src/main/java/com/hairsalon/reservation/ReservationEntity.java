package com.hairsalon.reservation;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hairsalon.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "reservations")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ReservationEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
	@ManyToOne(fetch = FetchType.LAZY)//UserからもらったメインID
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
	
	@Column(nullable = false)//電話番号
    private String phoneNumber;
	
	@Column(columnDefinition = "TEXT")//備考
    private String memo;
	
	@Column(nullable = false)//何時から予約するか
    private LocalDateTime startTime;
	
	@CreatedDate   //予約した日時
	@Column(nullable = false, updatable = false)
	private LocalDateTime sentAt;
	
	@Column(nullable = false)  //施術の種類
	private String menuName;
	
	@Column(nullable = false) //所要時間
	private Integer menuTime;
	
	@Column(nullable = false)//予約終了時間
	private LocalDateTime endTime;

	@Column(nullable = false)//予約有効か判定
	private boolean active = true;
	

} 
