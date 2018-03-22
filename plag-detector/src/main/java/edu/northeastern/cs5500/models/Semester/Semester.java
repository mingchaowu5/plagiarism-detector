package edu.northeastern.cs5500.models.Semester;

import java.util.List;

import edu.northeastern.cs5500.models.Course.Course;

public class Semester {
	
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public Semester(int id, String name) {
		this.id = id;
		this.name = name;
	}
	public Semester() {
	}
	
	
	
	

}
