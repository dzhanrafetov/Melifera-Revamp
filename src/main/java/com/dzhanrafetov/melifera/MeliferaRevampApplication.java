package com.dzhanrafetov.melifera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@EnableFeignClients
public class MeliferaRevampApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeliferaRevampApplication.class, args);


	}

}
