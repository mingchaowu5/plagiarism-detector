package edu.northeastern.cs5500.models.submission;


/** 
 * 
 * @author varunnandu
 *
 */
public class Submission {
	private String name;
	private int id;
	private int assignment;
	private int student;
	private String dateTime;
	
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
	public int getAssignment() {
		return assignment;
	}
	
	/** 
	 * 
	 * @param assignment
	 */
	public void setAssignment(int assignment) {
		this.assignment = assignment;
	}
	
	/** 
	 * 
	 * @return
	 */
	public int getStudent() {
		return student;
	}
	
	/** 
	 * 
	 * @param student
	 */
	public void setStudent(int student) {
		this.student = student;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getDateTime() {
		return dateTime;
	}
	
	/** 
	 * 
	 * @param dateTime
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	/** 
	 * 
	 * @param id
	 * @param assignment
	 * @param student
	 * @param dateTime
	 */
	public Submission(int id, int assignment, int student, String dateTime) {
		this.id = id;
		this.assignment = assignment;
		this.student = student;
		this.dateTime = dateTime;
	}
	
	/** 
	 * 
	 */
	public Submission() {
	}
	
	

}
