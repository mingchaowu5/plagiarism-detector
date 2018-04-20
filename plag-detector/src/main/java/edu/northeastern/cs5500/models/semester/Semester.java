package edu.northeastern.cs5500.models.semester;

/** 
 * 
 * @author varunnandu
 *
 */

public class Semester {
	
	private int id;
	private String name;
	
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
	public String getName() {
		return name;
	}
	
	/** 
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * 
	 * @param id
	 * @param name
	 */
	public Semester(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	/** 
	 * 
	 */
	public Semester() {
	}
	
	
	
	

}
