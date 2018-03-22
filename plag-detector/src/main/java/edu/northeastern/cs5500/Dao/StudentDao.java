package edu.northeastern.cs5500.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.Person.Professor;
import edu.northeastern.cs5500.models.Person.StudentAssignmentMap;

@Transactional
@Repository
public class StudentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer findStudentAssignmentId(int studentId, int assignmentId) {
		
		try {
			String sql = "SELECT * FROM StudentAssignmentMapping WHERE StudentAssignmentMapping.student = ? AND StudentAssignmentMapping.assignment = ?";
			RowMapper<StudentAssignmentMap> mapper = new BeanPropertyRowMapper<>(StudentAssignmentMap.class);
			StudentAssignmentMap user =  jdbcTemplate.queryForObject(sql, mapper, studentId, assignmentId);
			return user.getId();
			
		}
		catch(Exception e) {
			return null;
		}
	}

}
