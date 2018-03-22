package edu.northeastern.cs5500.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.Assignment.Assignment;

@Transactional
@Repository
public class AssignmentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Assignment> findAllAssignments(){
		try {
		String sql = "Select * from Assignment";
		RowMapper<Assignment> rowMapper = new BeanPropertyRowMapper<>(Assignment.class);
		List<Assignment> results = this.jdbcTemplate.query(sql, rowMapper);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public List<Assignment> findAllAssignmentsForCourse(int courseId){
		try {
		String sql = "Select Assignment.id, Assignment.name, Assignment.course from Assignment where Assignment.course = ?";
		RowMapper<Assignment> rowMapper = new BeanPropertyRowMapper<>(Assignment.class);
		List<Assignment> results = this.jdbcTemplate.query(sql, rowMapper, courseId);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}
}
