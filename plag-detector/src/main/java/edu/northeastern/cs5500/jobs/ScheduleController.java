package edu.northeastern.cs5500.jobs;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.northeastern.cs5500.controller.result.ResultsService;
import edu.northeastern.cs5500.controller.snapshot.SnapshotService;
import edu.northeastern.cs5500.models.snapshot.Snapshot;
import edu.northeastern.cs5500.models.submission.Submission;


@Component
public class ScheduleController {
	
	@Autowired
	private SnapshotService snapshotService;
	
	@Autowired
	private ResultsService resultsService;

	@Scheduled(cron = "0 0 0/1 1/1 * ?")
	public void run() {
		List<Snapshot> list = this.snapshotService.getAllSnapshotsToBePerformed();
		boolean flag = true;
		for(Snapshot snap : list) {
			if(snap.getType() == 0) {
				flag = findIncrementalResult(snap.getId());
			}
			if(flag)
				this.snapshotService.updateStatus(snap.getId());
		}
	}
	
	private int findMaxSubmission(List<Submission> list) {
		int max = -1;
		for(Submission s: list) {
			if(max < s.getId()) {
				max = s.getId();
			}
		}
		return -1;
	}
	
	private boolean findIncrementalResult(int id) {
		List<Submission> submissions = this.snapshotService.getAllSubmissionsForSnapshot(id);
		if(submissions.size() == 1)
			return false;
		int max = this.findMaxSubmission(submissions);
		if(max == -1)
			return false;
		for(Submission s : submissions) {
			if(s.getId() != max) {
				resultsService.setSubmissionIds(max, s.getId());
				resultsService.findResults();
			}
		}
		return true;
	}
	
}
