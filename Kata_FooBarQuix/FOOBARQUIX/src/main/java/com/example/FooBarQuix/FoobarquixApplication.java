package com.example.FooBarQuix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class FoobarquixApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoobarquixApplication.class, args);
	}

}
