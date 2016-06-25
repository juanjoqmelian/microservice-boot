package co.uk.dragosolutions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories({"co.uk.dragosolutions.repository"})
public class MicroserviceBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBootApplication.class, args);
	}
}
