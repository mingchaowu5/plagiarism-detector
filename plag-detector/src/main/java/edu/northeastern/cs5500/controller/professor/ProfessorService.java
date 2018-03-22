package edu.northeastern.cs5500.controller.professor;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.northeastern.cs5500.Dao.ProfessorDao;
import edu.northeastern.cs5500.models.Person.Professor;


@Service
public class ProfessorService {

	@Autowired
	private ProfessorDao professorConnection;
	
	/**
	 * Connect to the database layer and get the semesters
	 * @return
	 */
	public List<Professor> getAllProfessors(){
		return this.professorConnection.findAllProfessors();
	}
	
	
}
