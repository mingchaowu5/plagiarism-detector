package edu.northeastern.cs5500.controller.assignment;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.Dao.AssignmentDao;
import edu.northeastern.cs5500.models.Assignment.Assignment;

@Service
public class AssignmentService {

	@Autowired
	private AssignmentDao assignmentConnection;
	
	/**
	 * Connect to the database layer and get the semesters
	 * @return
	 */
	public List<Assignment> getAllAssignments(){
		return this.assignmentConnection.findAllAssignments();
	}
	
	public List<Assignment> getAllAssignmentsForCourse(int id){
		return this.assignmentConnection.findAllAssignmentsForCourse(id);
	}
	
	
}
