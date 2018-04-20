package edu.northeastern.cs5500.models.course;


/** 
 * 
 * @author varunnandu
 *
 */
public class Course {
	
	private int id;
	private String name;
	private int semester;
	
	/** 
	 * 
	 */
	public Course() {
	}
	
	/** 
	 * 
	 * @param id
	 * @param name
	 * @param semester
	 */
	public Course(int id, String name, int semester) {
		this.id = id;
		this.name = name;
		this.semester = semester;
	}
	
	/** 
	 * 
	 * @return
	 */
	public int getId() {
		return this.id;
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
		return this.name;
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
	 * @return
	 */
	public int getSemester() {
		return this.semester;
	}
	
	/** 
	 * 
	 * @param semester
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	

}
