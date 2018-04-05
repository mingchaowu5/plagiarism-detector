package edu.northeastern.cs5500.models.assignment;

public class Assignment {
	
	private int id;
	private String name;
	private int course;
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
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}
	public Assignment(int id, String name, int course) {
		super();
		this.id = id;
		this.name = name;
		this.course = course;
	}
	public Assignment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
