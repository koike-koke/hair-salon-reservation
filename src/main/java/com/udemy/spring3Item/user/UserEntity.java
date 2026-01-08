package com.udemy.spring3Item.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
	@Column(nullable = false, unique = true)//ライン認証番号
	private String lineUserId;
	
	@Column(nullable = false)//ラインの名前
    private String name;
	
	@Column(nullable = false)//管理者権限
	private String role;
	

}
