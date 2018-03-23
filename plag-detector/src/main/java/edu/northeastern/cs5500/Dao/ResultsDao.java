package edu.northeastern.cs5500.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.Results.FileResults;
import edu.northeastern.cs5500.models.Results.StudentResults;
import edu.northeastern.cs5500.models.file.FileStructure;

@Transactional
@Repository
public class ResultsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void addFileResults(int saId1, int saId2, int result) {
		try {
		String sql = "INSERT INTO FileFileResults (studentAssignmentFile1, studentAssignmentFile2, result) VALUES(?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] {saId1, saId2, result});
		}
		catch(Exception e) {
		}
	
	}
	
	public List<FileResults> findAllFileResultsForSTudentAssignment(int fileId) {
		try {
		String sql = "Select * from FileFileResults WHERE FileFileResults.studentAssignmentFile1 = ? or FileFileResults.studentAssignmentFile2=?";
		RowMapper<FileResults> rowMapper = new BeanPropertyRowMapper<>(FileResults.class);
		List<FileResults> results = this.jdbcTemplate.query(sql, rowMapper, fileId, fileId);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public void addStudentResults(int sa1, int sa2, int result) {
		try {
		String sql = "INSERT INTO StudentStudentResults (studentAssignment1, studentAssignment2, result) VALUES(?, ?, ?)";
		jdbcTemplate.update(sql, new Object[] {sa1, sa2, result});
		}
		catch(Exception e) {
		}
	
	}
	
	public List<StudentResults> findAllStudentResultsForSTudentAssignment(int saId) {
		try {
		String sql = "Select * from StudentStudentResults WHERE StudentStudentResults.studentAssignment1 = ? or StudentStudentResults.studentAssignment2=?";
		RowMapper<StudentResults> rowMapper = new BeanPropertyRowMapper<>(StudentResults.class);
		List<StudentResults> results = this.jdbcTemplate.query(sql, rowMapper, saId, saId);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}
}
