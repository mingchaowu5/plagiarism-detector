package edu.northeastern.cs5500.controller.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.dao.CourseDao;
import edu.northeastern.cs5500.models.course.Course;

/**
 * Course Service class,
 * makes several calls to the Data Layer to fetch or insert the course data
 * @author takyon
 *
 */
@Service
public class CourseService {

	@Autowired
	private CourseDao courseDao;
	
	/**
	 * Connect to the database layer and get all the courses
	 * @return	List: of all the courses in the database(except the deleted ones)
	 */
	public List<Course> getAllCourses(){
		return this.courseDao.getAllCourses();
	}
	
	/**
	 * Connect to the database layer and get all the courses for a 
	 * particular semester
	 * @param	id:	of a particular semester
	 * @return	List:	of all the courses in the database for a semester
	 */
	public List<Course> getAllCoursesForSemester(int id){
		return this.courseDao.getAllCoursesForSemester(id);
	}
	
	/**
	 * Connect to the database layer and get all the courses for a 
	 * particular professor
	 * @param	id:	of a particular professor
	 * @return	List:	of all the courses in the database for a professor
	 */
	public List<Course> getAllCoursesForProfessor(int id){
		return this.courseDao.getAllCoursesForProfessor(id);
	}
	
	/**
	 * Connect to the database layer and get all the courses for a 
	 * particular student
	 * @param	id:	of a particular student
	 * @return	List:	of all the courses in the database for a student
	 */
	public List<Course> getAllCoursesForStudent(int id){
		return this.courseDao.getAllCoursesForStudent(id);
	}
	
	/**
	 * Delete a particular course from the system
	 * @param	id:	of the course to be deleted
	 * @return	boolean:	true if and only iff the course is successfully deleted
	 */
	public boolean deleteCourse(int id) {
		return true;
	}
	
	/**
	 * Insert a new course in the system
	 * @param	semesterId:	is the id of the semester to which this course is begin added
	 * 			name:	of the course to be added
	 * @return	boolean:	true if and only iff the assignment is successfully inserted
	 */
	public boolean insertCourse(int semesterId, String name) {
		return true;
	}
	
}
