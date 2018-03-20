package edu.northeastern.cs5500;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PlagDetectorApplication extends SpringBootServletInitializer{
	
	@Override
	 protected SpringApplicationBuilder
	  configure(SpringApplicationBuilder application) {
	  return application.sources(PlagDetectorApplication.class);
	 }

	public static void main(String[] args) {
		SpringApplication.run(PlagDetectorApplication.class, args);
	}
}
