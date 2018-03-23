package edu.northeastern.cs5500.models.file;

public class FileStructure {
	
	private int id;
	private int studentAssignment;
	private String file;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentAssignment() {
		return studentAssignment;
	}
	public void setStudentAssignment(int studentAssignment) {
		this.studentAssignment = studentAssignment;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public FileStructure(int id, int studentAssignment, String file) {
		this.id = id;
		this.studentAssignment = studentAssignment;
		this.file = file;
	}
	public FileStructure() {
	}
	
	
	
	
}
