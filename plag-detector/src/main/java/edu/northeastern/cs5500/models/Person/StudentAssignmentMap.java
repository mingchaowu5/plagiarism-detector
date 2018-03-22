package edu.northeastern.cs5500.models.Person;

public class StudentAssignmentMap {
	
	int id;
	
	int student;
	int assignment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudent() {
		return student;
	}
	public void setStudent(int student) {
		this.student = student;
	}
	public int getAssignment() {
		return assignment;
	}
	public void setAssignment(int assignment) {
		this.assignment = assignment;
	}
	public StudentAssignmentMap(int id, int student, int assignment) {
		this.id = id;
		this.student = student;
		this.assignment = assignment;
	}
	public StudentAssignmentMap() {
		
	}
	
	

}
