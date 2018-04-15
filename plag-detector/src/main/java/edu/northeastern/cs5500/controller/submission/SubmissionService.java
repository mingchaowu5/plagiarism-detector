package edu.northeastern.cs5500.controller.submission;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.Constants;
import edu.northeastern.cs5500.dao.SubmissionDao;
import edu.northeastern.cs5500.models.submission.Sub1;
import edu.northeastern.cs5500.models.submission.Submission;

@Service
public class SubmissionService {

	@Autowired
	private SubmissionDao submissionDao;
	
	public List<Sub1> getAllSubmission(){
		return this.submissionDao.getAllSubmission();
	}
	
	/**
	 * Connect to the database layer and get the semesters
	 * @return
	 */
	public List<Submission> getLatestSubmissionsForAssignment(int id){
		return this.submissionDao.getLatestSubmissionIdByAssignment(id);
	}
	
	public List<Submission> getAllSubmissionsForAssignment(int assignmentId, int studentId){
		return this.submissionDao.getAllSubmissionsIdByAssignment(studentId, assignmentId);
	}
	
	public Integer insert(int assignmentId, int studentId) {
		return this.submissionDao.addSubmission(assignmentId, studentId, Constants.getCurrentDate());
	}
	
}
