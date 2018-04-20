package edu.northeastern.cs5500.controller.submission;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.Constants;
import edu.northeastern.cs5500.dao.SnapshotDao;
import edu.northeastern.cs5500.dao.SubmissionDao;
import edu.northeastern.cs5500.models.submission.Sub1;
import edu.northeastern.cs5500.models.submission.Submission;

/** 
 * 
 * @author varunnandu
 *
 */
@Service
public class SubmissionService {

	@Autowired
	private SubmissionDao submissionDao;
	
	@Autowired
	private SnapshotDao snapshotDao;
	
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
	
	/**
	 * Get all the submissions of the assignment
	 * @param assignmentId
	 * @param studentId
	 * @return
	 */
	public List<Submission> getAllSubmissionsForAssignment(int assignmentId, int studentId){
		return this.submissionDao.getAllSubmissionsIdByAssignment(studentId, assignmentId);
	}
	
	/**
	 * Insert a new submission
	 * @param assignmentId
	 * @param studentId
	 * @return
	 */
	public Integer insert(int assignmentId, int studentId) {
		return this.submissionDao.addSubmission(assignmentId, studentId, Constants.getCurrentDate());
	}
	
	/**
	 * Compare multiple submissions of the different students
	 * @param subs
	 * @param profId
	 */
	public void compareMultipleSubmissions(int []subs, int profId) {
		List<Integer> list = Arrays.stream(subs).boxed().collect(Collectors.toList());
		int snapshotId = this.snapshotDao.addAllSnapshots(list, Constants.getCurrentDate(), 2);
		this.snapshotDao.addAssignmentSnapshot(snapshotId, profId);
	}
	
}
