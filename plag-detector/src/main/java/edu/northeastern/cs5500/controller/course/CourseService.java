package edu.northeastern.cs5500.controller.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.Dao.CourseDao;
import edu.northeastern.cs5500.models.Course.Course;

@Service
public class CourseService {

	@Autowired
	private CourseDao courseConnection;
	
	/**
	 * Connect to the database layer and get the semesters
	 * @return
	 */
	public List<Course> getAllCourses(){
		return this.courseConnection.findAllCourses();
	}
	
	public List<Course> getAllCoursesForSemester(int id){
		return this.courseConnection.findAllCoursesForSemester(id);
	}
	
	public List<Course> getAllCoursesForProfessor(int id){
		return this.courseConnection.findAllCoursesForProfessor(id);
	}
	
}
