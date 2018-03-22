package edu.northeastern.cs5500.controller.professor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.northeastern.cs5500.models.Person.Professor;

@RestController
@RequestMapping("/rest/professor")
public class ProfessorController {

	@Autowired
	private ProfessorService professorService;
	
	/**
	 * Get all the semesters when an user logs in.
	 * @return
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<Professor>> professor() {
		List<Professor> semester = this.professorService.getAllProfessors();
		return new ResponseEntity<>(semester, HttpStatus.OK);
	}

	
}
