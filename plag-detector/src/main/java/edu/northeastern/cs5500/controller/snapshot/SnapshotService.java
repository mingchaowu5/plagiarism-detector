package edu.northeastern.cs5500.controller.snapshot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.northeastern.cs5500.dao.SnapshotDao;
import edu.northeastern.cs5500.models.snapshot.Snapshot;
import edu.northeastern.cs5500.models.submission.Submission;

@Service
public class SnapshotService {

	@Autowired
	private SnapshotDao snapshotDao;
	
	/**
	 * Connect to the database layer and get the semesters
	 * @return
	 */
	public List<Snapshot> getAllSnapshotsForAssignment(int id){
		return this.snapshotDao.findAllSnapshotsForAssignment(id);
	}
	
	
	public List<Snapshot> getAllSnapshotsToBePerformed(){
		return this.snapshotDao.findAllSnapshotsToBePerformed();
	}
	
	public void updateStatus(int snapShotId) {
		this.snapshotDao.updateStatus(snapShotId);
	}
	
	public List<Submission> getAllSubmissionsForSnapshot(int snapShotId){
		return this.snapshotDao.findAllSubmissionFromSnapshot(snapShotId);
	}
}
