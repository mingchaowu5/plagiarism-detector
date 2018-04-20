package edu.northeastern.cs5500.models.submission;


/** 
 * 
 * @author varunnandu
 *
 */
public class Sub1 {

	private String course, student, assignment,date;
	private int submission;
	
	
	/** 
	 * 
	 * @return
	 */
	public String getCourse() {
		return course;
	}
	
	/** 
	 * 
	 * @param course
	 */
	public void setCourse(String course) {
		this.course = course;
	}
	
	
	/** 
	 * 
	 * @return
	 */
	public String getStudent() {
		return student;
	}
	
	/** 
	 * 
	 * @param student
	 */
	public void setStudent(String student) {
		this.student = student;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getAssignment() {
		return assignment;
	}
	
	
	/** 
	 * 
	 * @param assignment
	 */
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	/**
	 * 
	 * @return
	 */
	public int getSubmission() {
		return submission;
	}

	/**
	 * 
	 * @param submission
	 */
	public void setSubmission(int submission) {
		this.submission = submission;
	}

	/** 
	 * 
	 * @return
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/** 
	 * 
	 */
	public Sub1() {
		
	}
	
	
	/** 
	 * 
	 * @param course
	 * @param student
	 * @param assignment
	 * @param submission
	 * @param date
	 */
	public Sub1(String course, String student, String assignment, int submission, String date) {
		super();
		this.course = course;
		this.student = student;
		this.assignment = assignment;
		this.submission = submission;
		this.date = date;
	}
	
}
