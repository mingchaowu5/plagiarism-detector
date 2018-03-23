package edu.northeastern.cs5500.models.Course;

public class Course {
	
	private int id;
	private String name;
	private int semester;
	
	
	public Course() {
	}
	public Course(int id, String name, int semester) {
		this.id = id;
		this.name = name;
		this.semester = semester;
	}
	public int getId() {
		return this.id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSemester() {
		return this.semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	

}
