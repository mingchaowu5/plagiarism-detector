package edu.northeastern.cs5500.controller.submission;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.northeastern.cs5500.models.submission.Submission;

/**
 * Submission Controller Class, 
 * handle submission related operations.
 * @author takyon
 *
 */
@RestController
@RequestMapping("/rest/submission")
public class SubmissionController {

	@Autowired
	private SubmissionService submissionService;
	
	/**
	 * Get latest submissions for an assignment
	 * @return	List: of all the submissions ids in the database
	 */
	@GetMapping(value = "/assignment")
	public ResponseEntity<List<Submission>> latestSubmissionsByAssignment(@RequestParam(value = "id") int id) {
		List<Submission> submissions = this.submissionService.getLatestSubmissionsForAssignment(id);
		return new ResponseEntity<>(submissions, HttpStatus.OK);
	}
	
	/**
	 * Get all the submissions for an assignment
	 * @return	List: of all the submissions ids in the database
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<Submission>> allSubmissionsByAssignment(@RequestParam(value = "assignment_id") int assignmentId, 
			@RequestParam(value = "student_id") int studentId) {
		List<Submission> submissions = this.submissionService.getAllSubmissionsForAssignment(assignmentId, studentId);
		return new ResponseEntity<>(submissions, HttpStatus.OK);
	}
	
	/**
	 * Get all the submissions for an assignment
	 * @return	List: of all the submissions ids in the database
	 */
	@GetMapping(value = "/insert")
	public ResponseEntity<Integer> insert(@RequestParam(value = "assignment_id") int assignmentId, 
			@RequestParam(value = "student_id") int studentId) {
		Integer i = this.submissionService.insert(assignmentId, studentId);
		return new ResponseEntity<>(i, HttpStatus.OK);
	}
	
}
