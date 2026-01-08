package com.udemy.spring3Item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing//自動で日時入る
public class HairSalonReservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(HairSalonReservationApplication.class, args);
	}

}
