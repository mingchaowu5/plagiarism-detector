package edu.northeastern.cs5500.controller.assignment;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.northeastern.cs5500.models.assignment.Assignment;

/**
 * Assignment Controller Class, 
 * handle assignment related operations.
 * @author takyon
 *
 */
@RestController
@RequestMapping("/rest/assignment")
public class AssignmentController {

	@Autowired
	private AssignmentService assignmentService;
	
	/**
	 * Get all the assignments when an user logs in.
	 * @return	List: of all the assignments in the database which have not been deleted
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<Assignment>> allAssigments() {
		List<Assignment> assignments = this.assignmentService.getAllAssignments();
		return new ResponseEntity<>(assignments, HttpStatus.OK);
	}
	
	/**
	 * Get list of assignments for a course
	 * @param id: of a particular course
	 * @return	List: of assignments for a course
	 */
	@GetMapping(value = "/course")
	public ResponseEntity<List<Assignment>> assignmentsForCourse(@RequestParam(value = "id") int id) {
		List<Assignment> assignments = this.assignmentService.getAllAssignmentsForCourse(id);
		return new ResponseEntity<>(assignments, HttpStatus.OK);
	}
	
	/**
	 * Delete the given assignment
	 * @param id:	of an assignment
	 * @return	Boolean:		true iff and only if the assignment was successfully deleted.
	 */
	@GetMapping(value = "/delete")
	public ResponseEntity<Boolean> deleteAssignment(@RequestParam(value = "id") int id){
		boolean flag = this.assignmentService.deleteAssignment(id);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
	/**
	 * Insert a new assignment into the database.
	 * @param	course_id:	of the course where assignment is to be added
	 * 			name	:		of the assignment to be added
	 * 			language_id:	selected for the new assignment
	 * @return	Boolean:		true iff and only if the assignment was successfully inserted.
	 */
	@GetMapping(value = "/insert")
	public ResponseEntity<Boolean> insertAssignment(@RequestParam(value = "course_id") int courseId, @RequestParam(value = "name") String name){
			//@RequestParam(value = "language_id") int langId){
		boolean flag = this.assignmentService.insertAssignment(name, courseId);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
	/**
	 * Update an assignment present in the database.
	 * @param	assignment_id: of the assignment which is being edited
	 * 			course_id:	of the course to which this assignment belongs
	 * 			name	:		of the assignment
	 * 			language_id:	selected for the assignment
	 * @return	Boolean:		true iff and only if the assignment was successfully updated.
	 */
	@GetMapping(value = "/update")
	public ResponseEntity<Boolean> updateAssignment(@RequestParam(value = "assignment_id") int assignmentId, @RequestParam(value = "course_id") int courseId, @RequestParam(value = "name") String name){
			//@RequestParam(value = "language_id") int langId){
		boolean flag = this.assignmentService.updateAssignment(assignmentId, name, courseId);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
	/**
	 * Get a list of languages supported by the system.
	 * @return
	 */
	@GetMapping(value = "/language")
	public ResponseEntity<List<String>> getAllLanguages(){
		List<String> list = new ArrayList<>();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * Upload zip file for a particular student and assignment
	 * @param	studentId:	id of the student who is uploading the assignment
	 * 			assignmentId:	id of the assignment for which files are being uploaded
	 * @return	Boolean:		true iff and only if the assignment was successfully uploaded and unzipped.
	 */
	@PostMapping(value = "/upload")
	public ResponseEntity<Boolean> uploadAssignment(@RequestPart(value = "file") MultipartFile multiPartFile, 
			@RequestParam(value = "student_id") int studentId, @RequestParam(value = "assignment_id") int assignmentId){
		boolean flag = this.assignmentService.uploadFile(multiPartFile, studentId, assignmentId);
		if(flag) {
			this.assignmentService.insertIntoQueue(studentId, assignmentId);
		}
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
}
