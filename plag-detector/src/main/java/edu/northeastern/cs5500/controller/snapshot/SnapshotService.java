package edu.northeastern.cs5500.controller.snapshot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.Constants;
import edu.northeastern.cs5500.dao.ResultsDao;
import edu.northeastern.cs5500.dao.SnapshotDao;
import edu.northeastern.cs5500.dao.SubmissionDao;
import edu.northeastern.cs5500.dao.UserDao;
import edu.northeastern.cs5500.models.extras.Edge;
import edu.northeastern.cs5500.models.extras.Graph;
import edu.northeastern.cs5500.models.extras.Node;
import edu.northeastern.cs5500.models.results.Result;
import edu.northeastern.cs5500.models.snapshot.Snapshot;
import edu.northeastern.cs5500.models.submission.Submission;

@Service
public class SnapshotService {

	@Autowired
	private SnapshotDao snapshotDao;
	
	@Autowired
	private ResultsDao resultsDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SubmissionDao submissionDao;
	
	/**
	 * Connect to the database layer and get the semesters
	 * @return
	 */
	public List<Snapshot> getAllSnapshotsForAssignment(int id){
		return this.snapshotDao.findAllSnapshotsForAssignment(id);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Snapshot> getAllSnapshotsToBePerformed(){
		return this.snapshotDao.findAllSnapshotsToBePerformed();
	}
	
	/**
	 * 
	 * @param snapShotId
	 * @param type
	 */
	public void updateStatus(int snapShotId, int type) {
		this.snapshotDao.updateStatus(snapShotId, type);
	}
	
	/**
	 * 
	 * @param snapShotId
	 * @return
	 */
	public List<Submission> getAllSubmissionsForSnapshot(int snapShotId){
		return this.snapshotDao.findAllSubmissionFromSnapshot(snapShotId);
	}
	
	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 */
	public List<Result> getResults(int id1, int id2){
		return this.resultsDao.findAllResultsForSubmission(id1, id2);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean runSnapshotForAssignment(int id) {
		List<Submission> submissions = this.submissionDao.getLatestSubmissionIdByAssignment(id);
		List<Integer> submissionIds = new ArrayList<>();
		for(Submission s : submissions) {
			submissionIds.add(s.getId());
		}
		return this.snapshotDao.addAllSnapshots(submissionIds, Constants.getCurrentDate(), 1) == 1?true:false;
	}
	
	/**
	 * 
	 * @param snapShotId
	 * @return
	 */
	public Graph getResultsForSnapshot(int snapShotId) {
		List<Submission> submissions = this.getAllSubmissionsForSnapshot(snapShotId);
		List<Edge> edges = new ArrayList<>();
		List<Node> nodes = new ArrayList<>();
		for(int i = 0;i<submissions.size();++i) {
			nodes.add(new Node(submissions.get(i).getId(), 
					resultsDao.findAverageResultForOneSubmission(submissions.get(i).getId()).getPercentage(),
					userDao.getNameOfStudent(submissions.get(i).getId())));
			for(int j = i + 1;j<submissions.size();++j) {
				Result result = this.resultsDao.findAverageResultSubmission(submissions.get(i).getId(), submissions.get(j).getId());
				edges.add(new Edge(result.getSubmission1(), result.getSubmission2(), result.getPercentage()));
			}
		}
		return new Graph(nodes, edges);
	}
}
