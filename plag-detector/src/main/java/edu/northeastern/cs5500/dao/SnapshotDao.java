package edu.northeastern.cs5500.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.snapshot.Snapshot;
import edu.northeastern.cs5500.models.submission.Submission;


/** 
 * 
 * @author varunnandu
 *
 */
@Transactional
@Repository
public class SnapshotDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger log = Logger.getAnonymousLogger();
	
	/**
	 * add prof and snap mapping for emailing
	 * @param snapId
	 * @param prof
	 * @return
	 */
	public int addAssignmentSnapshot(int snapId, int prof) {
		try {
				String sql = "INSERT INTO ProfessorSnapshotMapping (professor, snapshot) VALUES (?, ?)";
				return jdbcTemplate.update(sql, new Object[] {prof, snapId});
			}
			catch(Exception e) {
				log.log(Level.INFO, e.getMessage());
				return 0;
			}
	}
	
	/**
	 * find all snapshots for a assignment
	 * @param assignmentId
	 * @return
	 */
	public List<Snapshot> findAllSnapshotsForAssignment(int assignmentId){
		try {
			String sql = "Select DISTINCT Snapshot.id, Snapshot.dateTime, Snapshot.type from Snapshot join Submission on Snapshot.submission = Submission.id where Submission.assignment = ? AND Snapshot.status = 1 AND Snapshot.type <> 2";
			RowMapper<Snapshot> rowMapper = new BeanPropertyRowMapper<>(Snapshot.class);
			return this.jdbcTemplate.query(sql, rowMapper, assignmentId);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	/**
	 * find all manually run snapshots, from different submissions
	 * @return
	 */
	public List<Snapshot> getAllManualSnapshots(){
		try {
			String sql = "Select DISTINCT Snapshot.id, Snapshot.dateTime, Snapshot.type from Snapshot where Snapshot.type = 2 AND Snapshot.status = 1";
			RowMapper<Snapshot> rowMapper = new BeanPropertyRowMapper<>(Snapshot.class);
			return this.jdbcTemplate.query(sql, rowMapper);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	/**
	 * Find all pending snapshots
	 * @return
	 */
	public List<Snapshot> findAllSnapshotsToBePerformed(){
		try {
			String sql = "Select DISTINCT(Snapshot.id), Snapshot.type from Snapshot join Submission on Snapshot.submission = Submission.id where Snapshot.status = 0";
			RowMapper<Snapshot> rowMapper = new BeanPropertyRowMapper<>(Snapshot.class);
			return this.jdbcTemplate.query(sql, rowMapper);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	/**
	 * update status
	 * @param snapShotId
	 * @param type
	 * @return
	 */
	public int updateStatus(int snapShotId, int type){
		try {
			String sql = "UPDATE Snapshot SET status = ? WHERE id = ?";
			return jdbcTemplate.update(sql, new Object[] {type, snapShotId});
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return 0;
		}
	}

	/**
	 * Find all submissions from snapshot
	 * @param snapShotId
	 * @return
	 */
	public List<Submission> findAllSubmissionFromSnapshot(int snapShotId){
		try {
			String sql = "Select Snapshot.submission as id from Snapshot where Snapshot.id = ?";
			RowMapper<Submission> rowMapper = new BeanPropertyRowMapper<>(Submission.class);
			return this.jdbcTemplate.query(sql, rowMapper, snapShotId);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	
	/**
	 * add all the submission ids to a snapshot id
	 * @param submissionList
	 * @param date
	 * @param type
	 * @return
	 */
	public int addAllSnapshots(List<Integer> submissionList, String date, int type) {
		int id = getLatestSnapId();
		for(Integer i: submissionList){
		try {
				String sql = "INSERT INTO Snapshot (id, submission, dateTime, type) VALUES (?, ?, ?,?)";
				jdbcTemplate.update(sql, new Object[] {id, i, date, type});
			}
			catch(Exception e) {
				log.log(Level.INFO, e.getMessage());
				return 0;
			}
		}
		return id;
	}
		
	/**
	 * Get a new snapshot id
	 * @return
	 */
	public int getLatestSnapId() {
		int d = 0;
		try {
			String sql = "SELECT MAX(id) AS id FROM Snapshot";
			RowMapper<Snapshot> mapper = new BeanPropertyRowMapper<>(Snapshot.class);
			Snapshot u =  jdbcTemplate.queryForObject(sql, mapper);
			if(u.getId() == null) return d + 1;
			else return d + u.getId() + 1;
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return -1;
		}
	}
}
