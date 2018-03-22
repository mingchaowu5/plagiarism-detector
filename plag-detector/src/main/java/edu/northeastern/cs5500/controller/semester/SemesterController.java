package edu.northeastern.cs5500.controller.semester;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5500.models.Course.Course;
import edu.northeastern.cs5500.models.Semester.Semester;

@RestController
@RequestMapping("/rest/semester")
public class SemesterController {

	@Autowired
	private SemesterService semesterService;
	
	/**
	 * Get all the semesters when an user logs in.
	 * @return
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<Semester>> semesters() {
		List<Semester> semester = this.semesterService.getAllSemesters();
		return new ResponseEntity<>(semester, HttpStatus.OK);
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<Semester> semesterById(@RequestParam(value = "id") int id) {
		Semester semester = this.semesterService.getSemesterById(id);
		return new ResponseEntity<>(semester, HttpStatus.OK);
	}
	
}
