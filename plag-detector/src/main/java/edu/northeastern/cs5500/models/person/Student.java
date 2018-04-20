package edu.northeastern.cs5500.models.person;


/** 
 * 
 * @author varunnandu
 *
 */
public class Student extends User{

	private int id;
	private int universityID;
	
	/** 
	 * 
	 */
	public Student() {
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
	 * @param uniId
	 */
	public Student(int id, String firstName, String lastName, String email, String username, String password, int uniId) {
		super(id, firstName, lastName, email, username, password);
		setUniversityId(uniId);
	}
	
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
	public int getUniversityId() {
		return universityID;
	}
	
	/** 
	 * 
	 * @param universityId
	 */
	public void setUniversityId(int universityId) {
		this.universityID = universityId;
	}
	
	
}
