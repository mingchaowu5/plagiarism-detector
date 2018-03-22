package edu.northeastern.cs5500.controller.assignment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.northeastern.cs5500.models.Assignment.Assignment;

@RestController
@RequestMapping("/rest/assignment")
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentService;
	
	/**
	 * Get all the courses when an user logs in.
	 * @return
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<Assignment>> allAssigments() {
		List<Assignment> assignments = this.assignmentService.getAllAssignments();
		return new ResponseEntity<>(assignments, HttpStatus.OK);
	}
	
	@GetMapping(value = "/course")
	public ResponseEntity<List<Assignment>> assignmentsForCourse(@RequestParam(value = "id") int id) {
		List<Assignment> assignments = this.assignmentService.getAllAssignmentsForCourse(id);
		return new ResponseEntity<>(assignments, HttpStatus.OK);
	}
		
}
