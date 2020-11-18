package org.sltb.transportmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.sltb.transportmanagement")
@SpringBootApplication
public class TransportManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(TransportManagementApplication.class, args);
	}
}
