package com.canteen.CanteenManagement;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class CanteenManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanteenManagementApplication.class, args);
	}
}

