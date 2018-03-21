package edu.northeastern.cs5500.models.Course;

public class Course {
	
	private int courseId;
	private String name;
	private int semester;
	
	
	public Course(int courseId, String name, int semester) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.semester = semester;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	
	

}
