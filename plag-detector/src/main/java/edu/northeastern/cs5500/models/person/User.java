package edu.northeastern.cs5500.models.person;


/** 
 * 
 * @author varunnandu
 *
 */
public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String userName;
	private String password;
	private int type;
	
	/** 
	 * 
	 * @return
	 */
	public int getType() {
		return type;
	}
	
	/** 
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/** 
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/** 
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/** 
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}
	
	/** 
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}
	
	/** 
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getUsername() {
		return userName;
	}
	
	/** 
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.userName = username;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	/** 
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/** 
	 * 
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param username
	 * @param password
	 */
	public User(int id, String firstName, String lastName, String email, String username, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userName = username;
		this.password = password;
	}
	
	
	/** 
	 * 
	 */
	public User() {

	}	
}
