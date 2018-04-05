package edu.northeastern.cs5500.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.snapshot.Snapshot;
import edu.northeastern.cs5500.models.submission.Submission;

@Transactional
@Repository
public class SnapshotDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Snapshot> findAllSnapshotsForAssignment(int assignmentId){
		try {
			String sql = "Select DISTINCT(Snapshot.id), Snapshot.submission, Snapshot.dateTime, Snapshot.type from Snapshot join Submission on Snapshot.submission = Submission.id where Submission.assignment = ? AND Snapshot.status = 1";
			RowMapper<Snapshot> rowMapper = new BeanPropertyRowMapper<>(Snapshot.class);
			List<Snapshot> results = this.jdbcTemplate.query(sql, rowMapper, assignmentId);
			return results;
		}
		catch(Exception e) {
			return new ArrayList<>();
		}
	}
	
	public List<Snapshot> findAllSnapshotsToBePerformed(){
		try {
			String sql = "Select DISTINCT(Snapshot.id), Snapshot.submission, Snapshot.dateTime from Snapshot join Submission on Snapshot.submission = Submission.id where Snapshot.status = 0";
			RowMapper<Snapshot> rowMapper = new BeanPropertyRowMapper<>(Snapshot.class);
			List<Snapshot> results = this.jdbcTemplate.query(sql, rowMapper);
			return results;
		}
		catch(Exception e) {
			return new ArrayList<>();
		}
	}
	
	public int updateStatus(int snapShotId){
		try {
			String sql = "UPDATE Snapshot SET status = 1 WHERE id = ?";
			return jdbcTemplate.update(sql, new Object[] {snapShotId});
		}
		catch(Exception e) {
			return 0;
		}
	}

	
	public List<Submission> findAllSubmissionFromSnapshot(int snapShotId){
		try {
			String sql = "Select Snapshot.submission as id from Snapshot where Snapshot.id = ?";
			RowMapper<Submission> rowMapper = new BeanPropertyRowMapper<>(Submission.class);
			List<Submission> results = this.jdbcTemplate.query(sql, rowMapper, snapShotId);
			return results;
		}
		catch(Exception e) {
			return new ArrayList<>();
		}
	}
	
	
	
	public int addAllSnapshots(List<Integer> submissionList, String date, int type) {
		int id = getLatestSnapId();
		for(Integer i: submissionList){
		try {
				String sql = "INSERT INTO Snapshot (id, submission, dateTime, type) VALUES (?, ?, ?,?)";
				jdbcTemplate.update(sql, new Object[] {id, i, date, type});
			}
			catch(Exception e) {
				return 0;
			}
		}
		return 1;
	}
		
	private int getLatestSnapId() {
		int d = 0;
		try {
		String sql = "SELECT MAX(id) AS id FROM Snapshot";
		RowMapper<Snapshot> mapper = new BeanPropertyRowMapper<>(Snapshot.class);
		Snapshot u =  jdbcTemplate.queryForObject(sql, mapper);
		if(u.getId() == null) return d;
		else return d + u.getId() + 1;
		
	}
	catch(Exception e) {
		return -1;
	}
	}
}
