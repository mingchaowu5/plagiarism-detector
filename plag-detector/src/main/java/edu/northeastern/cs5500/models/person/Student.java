package edu.northeastern.cs5500.models.person;

public class Student extends User{

	private int id;
	private int universityID;
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int id, String firstName, String lastName, String email, String username, String password, int uniId) {
		super(id, firstName, lastName, email, username, password);
		setUniversityId(uniId);
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUniversityId() {
		return universityID;
	}
	public void setUniversityId(int universityId) {
		this.universityID = universityId;
	}
	
	
}