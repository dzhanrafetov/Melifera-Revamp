package com.dzhanrafetov.melifera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MeliferaRevampApplication {



	public static void main(String[] args) {
		SpringApplication.run(MeliferaRevampApplication.class, args);
	}


}
