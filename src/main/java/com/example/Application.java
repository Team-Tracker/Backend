package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;

@SpringBootApplication
public class Application {

	public static Gson GSON;

	public static void main(String[] args) {
		Application.GSON = new Gson();

		SpringApplication.run(Application.class, args);
	}
}