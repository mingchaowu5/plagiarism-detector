package edu.northeastern.cs5500.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.Results.Results;

@Transactional
@Repository
public class ResultsDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Results> findAllResultsForAssignment(int assignmentId){
		String sql = "Select * from Results join StudentAssignmentMapping on Results.studentAssignmentId = StudentAssignmentMapping.id where StudentAssignmentMapping.assignment = ?";
		RowMapper<Results> rowMapper = new BeanPropertyRowMapper<>(Results.class);
		List<Results> results = this.jdbcTemplate.query(sql, rowMapper, assignmentId);
		return results;
	}
}
