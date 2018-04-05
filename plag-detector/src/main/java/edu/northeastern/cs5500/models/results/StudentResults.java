package edu.northeastern.cs5500.models.results;

public class StudentResults {
	
	private int id;
	private int studentAssignment1;
	private int studentAssignment2;
	private int result;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentAssignment1() {
		return studentAssignment1;
	}
	public void setStudentAssignment1(int studentAssignment1) {
		this.studentAssignment1 = studentAssignment1;
	}
	public int getStudentAssignment2() {
		return studentAssignment2;
	}
	public void setStudentAssignment2(int studentAssignment2) {
		this.studentAssignment2 = studentAssignment2;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public StudentResults() {

	}
	public StudentResults(int id, int studentAssignment1, int studentAssignment2, int result) {
		this.id = id;
		this.studentAssignment1 = studentAssignment1;
		this.studentAssignment2 = studentAssignment2;
		this.result = result;
	}
	
	
	
	

}
