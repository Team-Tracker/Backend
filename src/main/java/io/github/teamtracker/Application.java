package io.github.teamtracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.gson.Gson;

@SpringBootApplication
public class Application {

	public static final Gson GSON = new Gson();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}