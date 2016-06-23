package com.luxuriem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories({"com.luxuriem.repository"})
public class MicroserviceBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBootApplication.class, args);
	}
}
