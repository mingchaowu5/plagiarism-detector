package edu.northeastern.cs5500;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import edu.northeastern.cs5500.Dao.SemesterDao;
import edu.northeastern.cs5500.models.Semester.Semester;

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
		
		SemesterDao sem1 = SemesterDao.getInstance();
		
		List<Semester> seml = sem1.findAllSemesters();
		
		for(Semester s:seml) {
			System.out.println(s.getName());
		}
	}
}
