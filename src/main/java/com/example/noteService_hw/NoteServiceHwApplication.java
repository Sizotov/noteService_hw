package com.example.noteService_hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class NoteServiceHwApplication {

	public static void main(String[] args) {

		SpringApplication.run(NoteServiceHwApplication.class, args);
	}

}
