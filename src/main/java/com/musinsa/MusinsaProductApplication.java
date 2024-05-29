package com.musinsa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.musinsa"})
public class MusinsaProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusinsaProductApplication.class, args);
	}

}
