package edu.northeastern.cs5500.controller.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.northeastern.cs5500.database.CourseConnection;
import edu.northeastern.cs5500.models.Course.Course;

@Service
public class CourseService {

	@Autowired
	private CourseConnection courseConnection;
	
	/**
	 * Connect to the database layer and get the semesters
	 * @return
	 */
	public List<Course> getAllCourses(){
		return this.courseConnection.getAllCourses();
	}
	
	public List<Course> getAllCoursesForSemester(int id){
		return this.courseConnection.getAllCoursesForSemester(id);
	}
	
	public List<Course> getAllCoursesForProfessor(int id){
		return this.courseConnection.getAllCoursesForProfessor(id);
	}
	
}
