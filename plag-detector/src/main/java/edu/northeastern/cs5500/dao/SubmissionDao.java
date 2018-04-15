package edu.northeastern.cs5500.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.submission.Sub1;
import edu.northeastern.cs5500.models.submission.Submission;


@Transactional
@Repository
public class SubmissionDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int addSubmission(int assignmentId, int studentId, String date) {
		try {
				String sql = "INSERT INTO Submission (assignment, student, dateTime) VALUES (?, ?, ?)";
				int i = jdbcTemplate.update(sql, new Object[] {assignmentId, studentId, date});
				if(i == 1) {
					return getLatestSubmissionIdByStudent(studentId, assignmentId);
				} else {
					return 0;
				}
			}
			catch(Exception e) {
				return 0;
			}
	}
	
	
	public int getLatestSubmissionIdByStudent(int studentId, int assignmentId) {
		try {
			String sql = "SELECT MAX(id) AS id FROM Submission WHERE student = ? and assignment = ?";
			RowMapper<Submission> mapper = new BeanPropertyRowMapper<>(Submission.class);
			Submission submission =  jdbcTemplate.queryForObject(sql, mapper, studentId, assignmentId);
			return submission.getId();
		}
		catch(Exception e){
			return -1;
		}
		
	}
	
	public List<Submission> getAllSubmissionsIdByAssignment(int studentId, int assignmentId) {
		List<Submission> submissions = new ArrayList<>();
		try {
			String sql = "SELECT * FROM Submission WHERE assignment = ? AND student = ?";
			RowMapper<Submission> mapper = new BeanPropertyRowMapper<>(Submission.class);
			submissions =  jdbcTemplate.query(sql, mapper, assignmentId, studentId);
		}
		catch(Exception e){
			
		}
		return submissions;
	}
	
	public List<Sub1> getAllSubmission() {
		List<Sub1> submissions = new ArrayList<>();
		try {
			String sql = "SELECT u.firstName AS student,a.name AS assignment, s.id AS submission, s.dateTime AS date FROM Submission AS s JOIN Assigment AS a ON s.assignment = a.id JOIN Student AS st ON s.student = st.id JOIN User AS u ON s.id = u.id";
			RowMapper<Sub1> mapper = new BeanPropertyRowMapper<>(Sub1.class);
			submissions =  jdbcTemplate.query(sql, mapper);
		}
		catch(Exception e){
			
		}
		return submissions;
	}
	
	public List<Submission> getLatestSubmissionIdByAssignment(int assignmentId) {
		List<Submission> submissions = new ArrayList<>();
		try {
			String sql = "SELECT MAX(id) AS id FROM Submission WHERE assignment = ? GROUP BY student";
			RowMapper<Submission> mapper = new BeanPropertyRowMapper<>(Submission.class);
			submissions =  jdbcTemplate.query(sql, mapper, assignmentId);
		}
		catch(Exception e){
			
		}
		return submissions;
	}
}
