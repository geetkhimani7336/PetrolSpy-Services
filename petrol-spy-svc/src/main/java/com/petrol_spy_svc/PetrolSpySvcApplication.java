package com.petrol_spy_svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.petro_spy_svc.entity")
@EnableJpaRepositories(basePackages = "com.petro_spy_svc.repository")
@ComponentScan(basePackages = "com.petro_spy_svc")
public class PetrolSpySvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetrolSpySvcApplication.class, args);
	}

}
