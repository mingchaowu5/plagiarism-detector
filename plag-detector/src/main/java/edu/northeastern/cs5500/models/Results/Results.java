package edu.northeastern.cs5500.models.Results;

public class Results {
	private int id;
	private int studentAssignmentId;
	private int comparator;
	private int result;
	private String type;
	private String path;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStudentAssignmentId() {
		return studentAssignmentId;
	}
	public void setStudentAssignmentId(int studentAssignmentId) {
		this.studentAssignmentId = studentAssignmentId;
	}
	public int getComparator() {
		return comparator;
	}
	public void setComparator(int comparator) {
		this.comparator = comparator;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Results(int id, int studentAssignmentId, int comparator, int result, String type, String path) {
		super();
		this.id = id;
		this.studentAssignmentId = studentAssignmentId;
		this.comparator = comparator;
		this.result = result;
		this.type = type;
		this.path = path;
	}
	public Results() {
	}
	
	

}
