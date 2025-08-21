package com.Restaurante.GestorDeRestaurante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GestorDeRestauranteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestorDeRestauranteApplication.class, args);
	}

}
