package com.udemy.spring3Item.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository 
extends JpaRepository<UserEntity, Long>{
	
	Optional<UserEntity> findByLineUserId(String lineUserId);
	
	

}
