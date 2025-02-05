package de.ems.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"de.ems.controllers", "de.ems"})
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class ErasmusManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ErasmusManagementSystemApplication.class, args);
		//das ist ein Testkommentar 
	}

}
