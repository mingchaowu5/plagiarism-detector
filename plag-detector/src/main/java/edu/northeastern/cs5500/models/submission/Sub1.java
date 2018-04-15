package edu.northeastern.cs5500.models.submission;

public class Sub1 {

	private String course, student, assignment,date;
	private int submission;

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public int getSubmission() {
		return submission;
	}

	public void setSubmission(int submission) {
		this.submission = submission;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public Sub1() {
		
	}

	public Sub1(String course, String student, String assignment, int submission, String date) {
		super();
		this.course = course;
		this.student = student;
		this.assignment = assignment;
		this.submission = submission;
		this.date = date;
	}
	
}
