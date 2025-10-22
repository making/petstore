package com.example.petstore;

import org.springframework.boot.SpringApplication;

public class TestPetstoreApplication {

	public static void main(String[] args) {
		SpringApplication.from(PetstoreApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
