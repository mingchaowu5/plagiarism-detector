package edu.northeastern.cs5500.models.submission;

public class Submission {
	private String name;
	private int id;
	private int assignment;
	private int student;
	private String dateTime;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAssignment() {
		return assignment;
	}
	public void setAssignment(int assignment) {
		this.assignment = assignment;
	}
	public int getStudent() {
		return student;
	}
	public void setStudent(int student) {
		this.student = student;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public Submission(int id, int assignment, int student, String dateTime) {
		this.id = id;
		this.assignment = assignment;
		this.student = student;
		this.dateTime = dateTime;
	}
	public Submission() {
		// TODO Auto-generated constructor stub
	}
	
	

}
