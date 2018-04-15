package edu.northeastern.cs5500.jobs;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.northeastern.cs5500.Constants;
import edu.northeastern.cs5500.controller.result.ResultsService;
import edu.northeastern.cs5500.controller.snapshot.SnapshotService;
import edu.northeastern.cs5500.controller.user.UserService;
import edu.northeastern.cs5500.mail.MailClient;
import edu.northeastern.cs5500.models.person.User;
import edu.northeastern.cs5500.models.snapshot.Snapshot;
import edu.northeastern.cs5500.models.submission.Submission;


@Component
public class ScheduleController {
	
	@Autowired
	private SnapshotService snapshotService;
	
	@Autowired
	private ResultsService resultsService;

	@Autowired
	private MailClient mail;

	@Autowired
	private UserService userService;

	
	private Logger log;
	
	
	/**
	 * Run method
	 */
	@Scheduled(cron = "0 0/2 * 1/1 * ?")
	public void run() {
		log = Logger.getAnonymousLogger();
		log.log(Level.INFO, "Running");
		List<Snapshot> list = this.snapshotService.getAllSnapshotsToBePerformed();
		log.log(Level.INFO, "Size: "+list.size());
		boolean flag = false;
		for(Snapshot snap : list) {
			this.snapshotService.updateStatus(snap.getId(), -1);
			log.log(Level.INFO, "Type: " + snap.getType());
			if(snap.getType() == 0) {
				flag = calculateIncrementalResult(snap.getId());
				sendMail(2);
			}else {
				flag = calculateTotalResult(snap.getId());
				log.log(Level.INFO, "Sending Mail");
				sendMail(snap.getId());
			}
			if(flag)
				this.snapshotService.updateStatus(snap.getId(), 1);
		}
	}
	
	
	/**
	 * 
	 * @param snapId
	 */
	private void sendMail(int snapId) {
		User user = this.userService.getUserId(snapId);
		if(user == null)
			return;
		mail.sendSimpleMessage(user.getEmail(), "Plag Detector Report", "Hello\nThe plag report was generated on " + 
			Constants.getCurrentDate() + ".\nYou can log in now and check the report.\nThank You!\nAdmin");
	}
	
	/**
	 * 
	 * @param list
	 * @return
	 */
	private int findMaxSubmission(List<Submission> list) {
		int max = -1;
		for(Submission s: list) {
			if(max < s.getId()) {
				max = s.getId();
			}
		}
		return max;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	private boolean calculateIncrementalResult(int id) {
		List<Submission> submissions = this.snapshotService.getAllSubmissionsForSnapshot(id);
		if(submissions.size() == 1)
			return false;
		int max = this.findMaxSubmission(submissions);
		if(max == -1)
			return false;

		for(Submission s : submissions) {
			if(s.getId() != max) {
				this.resultsService.deleteEntries(max, s.getId());
				resultsService.findResults(max, s.getId());
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	private boolean calculateTotalResult(int id) {
		List<Submission> submissions = this.snapshotService.getAllSubmissionsForSnapshot(id);
		try {
			for(int i = 0;i<submissions.size();++i) {
				for(int j = i + 1;j<submissions.size();++j) {
					this.resultsService.deleteEntries(submissions.get(i).getId(), submissions.get(j).getId());
					resultsService.findResults(submissions.get(i).getId(), submissions.get(j).getId());
				}
			}
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return false;
		}
		return true;
	}
	
}
