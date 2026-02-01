package com.hairsalon.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity
@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
	@Column(nullable = false, unique = true)//ライン認証番号
	private String lineUserId;
	
	@Column(nullable = false)
    private String name;
	
	@Column(nullable = false)
	private String role;
	

}
