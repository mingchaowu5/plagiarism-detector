package edu.northeastern.cs5500.controller.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5500.models.course.Course;

/**
 * Course Controller Class, 
 * handle course related operations.
 * @author takyon
 *
 */
@RestController
@RequestMapping("/rest/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	/**
	 * Get all the courses when an user logs in.
	 * @return	List: of all the courses in the database which have not been deleted
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<Course>> allCourses() {
		List<Course> courses = this.courseService.getAllCourses();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	
	/**
	 * Get list of courses for a semester
	 * @param id: of a particular semester
	 * @return	List: of courses for a semester 
	 */
	@GetMapping(value = "/semester")
	public ResponseEntity<List<Course>> coursesForSemester(@RequestParam(value = "id") int id) {
		List<Course> courses = this.courseService.getAllCoursesForSemester(id);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	/**
	 * Get list of courses for a professor
	 * @param id: of a particular professor
	 * @return	List: of courses for a professor 
	 */
	@GetMapping(value = "/professor")
	public ResponseEntity<List<Course>> coursesForProfessor(@RequestParam(value = "id") int id) {
		List<Course> courses = this.courseService.getAllCoursesForProfessor(id);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	/**
	 * Get list of courses for a student
	 * @param id: of a particular student
	 * @return	List: of courses for a student 
	 */
	@GetMapping(value = "/student")
	public ResponseEntity<List<Course>> coursesForStudent(@RequestParam(value = "id") int id) {
		List<Course> courses = this.courseService.getAllCoursesForStudent(id);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	/**
	 * Delete the given course
	 * @param id:	of a course
	 * @return	Boolean:		true iff and only if the course was successfully deleted.
	 */
	@GetMapping(value = "/delete")
	public ResponseEntity<Boolean> deleteCourse(@RequestParam(value = "id") int id){
		boolean flag = this.courseService.deleteCourse(id);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
	/**
	 * Insert a new course
	 * @param	semester_id:	of the semester where course is to be added
	 * 			name	:		of the course to be added
	 * @return	Boolean:		true iff and only if the course was successfully inserted.
	 */
	@GetMapping(value = "/insert")
	public ResponseEntity<Boolean> insertCourse(@RequestParam(value = "semester_id") int semesterId, @RequestParam(value = "name") String name){
		boolean flag = this.courseService.insertCourse(semesterId, name);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
}
