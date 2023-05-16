package com.github.jumagaliev1.backendAssignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BackendAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendAssignmentApplication.class, args);
	}

}
