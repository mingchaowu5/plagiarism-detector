package edu.northeastern.cs5500.models.person;

/**
 * 
 * @author varunnandu
 *
 */
public class Professor extends User{
	private int id;
	private String officeLocation;
	
	/** 
	 * 
	 */
	public int getId() {
		return id;
	}
	/** 
	 * 
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getOfficeLocation() {
		return officeLocation;
	}
	
	/** 
	 * 
	 * @param officeLocation
	 */
	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}
	
	/** 
	 * 
	 */
	public Professor() {
		super();
	}
	
	/** 
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param username
	 * @param password
	 * @param offloc
	 */
	public Professor(int id, String firstName, String lastName, String email, String username, String password, String offloc) {
		super(id, firstName, lastName, email, username, password);
		setOfficeLocation(offloc);
	}
	
	
	
	

}
