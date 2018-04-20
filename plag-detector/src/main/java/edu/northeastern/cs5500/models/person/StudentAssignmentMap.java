package edu.northeastern.cs5500.models.person;


/** 
 * 
 * @author varunnandu
 *
 */
public class StudentAssignmentMap {
	
	int id;
	
	int student;
	int assignment;
	
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
	 * @param id
	 * @param student
	 * @param assignment
	 */
	public StudentAssignmentMap(int id, int student, int assignment) {
		this.id = id;
		this.student = student;
		this.assignment = assignment;
	}
	
	/** 
	 * 
	 */
	public StudentAssignmentMap() {
		
	}
	
	

}
