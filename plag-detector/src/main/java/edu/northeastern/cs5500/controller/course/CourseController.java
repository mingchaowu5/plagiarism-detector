package edu.northeastern.cs5500.controller.course;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.northeastern.cs5500.models.Course.Course;

@RestController
@RequestMapping("/rest/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	/**
	 * Get all the courses when an user logs in.
	 * @return
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<Course>> allCourses() {
		List<Course> courses = this.courseService.getAllCourses();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	@GetMapping(value = "/semester")
	public ResponseEntity<List<Course>> coursesForSemester(@RequestParam(value = "id") int id) {
		List<Course> courses = this.courseService.getAllCoursesForSemester(id);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	@GetMapping(value = "/professor")
	public ResponseEntity<List<Course>> coursesForProfessor(@RequestParam(value = "id") int id) {
		List<Course> courses = this.courseService.getAllCoursesForProfessor(id);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
	@GetMapping(value = "/student")
	public ResponseEntity<List<Course>> coursesForStudent(@RequestParam(value = "id") int id) {
		List<Course> courses = this.courseService.getAllCoursesForStudent(id);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}
	
}
