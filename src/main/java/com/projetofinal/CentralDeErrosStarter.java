package com.projetofinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CentralDeErrosStarter {

	public static void main(String[] args) {
		SpringApplication.run(CentralDeErrosStarter.class, args);
	}

}
