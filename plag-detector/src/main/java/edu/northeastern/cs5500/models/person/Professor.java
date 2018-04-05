package edu.northeastern.cs5500.models.person;

public class Professor extends User{
	private int id;
	private String officeLocation;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOfficeLocation() {
		return officeLocation;
	}
	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}
	public Professor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Professor(int id, String firstName, String lastName, String email, String username, String password, String offloc) {
		super(id, firstName, lastName, email, username, password);
		setOfficeLocation(offloc);
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
