package edu.northeastern.cs5500.models.results;

public class FileResults {
	private int id;
	private int studentAssignmentFile1;
	private int studentAssignmentFile2;
	private int result;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentAssignmentFile1() {
		return studentAssignmentFile1;
	}
	public void setStudentAssignmentFile1(int studentAssignmentFile1) {
		this.studentAssignmentFile1 = studentAssignmentFile1;
	}
	public int getStudentAssignmentFile2() {
		return studentAssignmentFile2;
	}
	public void setStudentAssignmentFile2(int studentAssignmentFile2) {
		this.studentAssignmentFile2 = studentAssignmentFile2;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public FileResults(int id, int studentAssignmentFile1, int studentAssignmentFile2, int result) {
		this.id = id;
		this.studentAssignmentFile1 = studentAssignmentFile1;
		this.studentAssignmentFile2 = studentAssignmentFile2;
		this.result = result;
	}
	public FileResults() {

	}
	
	
	
	
	

}
