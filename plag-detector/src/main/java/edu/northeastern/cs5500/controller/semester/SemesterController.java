package edu.northeastern.cs5500.controller.semester;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.northeastern.cs5500.models.semester.Semester;

/**
 * Semester Controller Class, 
 * handle semester related operations.
 * @author takyon
 *
 */
@RestController
@RequestMapping("/rest/semester")
public class SemesterController {

	@Autowired
	private SemesterService semesterService;
	
	/**
	 * Get all the semester when an user logs in.
	 * @return	List: of all the semester in the database which have not been deleted
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<Semester>> semesters() {
		List<Semester> semester = this.semesterService.getAllSemesters();
		return new ResponseEntity<>(semester, HttpStatus.OK);
	}
	
	/**
	 * Get details of a semester by ID
	 * @param id: of a particular semester
	 * @return	Semester: contains all the details of a semester
	 */
	@GetMapping(value = "/")
	public ResponseEntity<Semester> semesterById(@RequestParam(value = "id") int id) {
		Semester semester = this.semesterService.getSemesterById(id);
		if(semester == null)
			return ResponseEntity.noContent().build();
		return new ResponseEntity<>(semester, HttpStatus.OK);
	}
	
	/**
	 * Delete the given semester
	 * @param id:	of a semester
	 * @return	Boolean:		true iff and only if the semester was successfully deleted.
	 */
	@GetMapping(value = "/delete")
	public ResponseEntity<Boolean> deleteSemester(@RequestParam(value = "id") int id){
		boolean flag = this.semesterService.deleteSemester(id);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
	/**
	 * Insert a new semester
	 * @param	name	:		of the semester to be added
	 * @return	Boolean:		true iff and only if the semester was successfully inserted.
	 */
	@GetMapping(value = "/insert")
	public ResponseEntity<Boolean> insertSemester(@RequestParam(value = "name") String name){
		boolean flag = this.semesterService.insertSemester(name);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
	/**
	 * Update a semester
	 * @param	name	:		of the semester to be updated
	 * @return	Boolean:		true iff and only if the semester was successfully inserted.
	 */
	@GetMapping(value = "/update")
	public ResponseEntity<Boolean> updateSemester(@RequestParam(value = "name") String name, @RequestParam(value = "id") int id){
		boolean flag = this.semesterService.editSemester(id, name);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
}
