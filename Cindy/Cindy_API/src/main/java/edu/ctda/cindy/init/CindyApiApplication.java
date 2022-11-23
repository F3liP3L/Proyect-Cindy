package edu.ctda.cindy.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"edu.ctda.cindy"})
public class CindyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CindyApiApplication.class, args);
	}

}
