package edu.northeastern.cs5500.dao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.semester.Semester;

@Transactional
@Repository
public class SemesterDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Semester> getAllSemester(){
		try {
		String sql = "Select * from Semester";
		RowMapper<Semester> rowMapper = new BeanPropertyRowMapper<>(Semester.class);
		List<Semester> results = this.jdbcTemplate.query(sql, rowMapper);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public Semester findSemesterById(int semesterId) {
		try {
		String sql = "select * from Semester WHERE Semester.id = ?";
		RowMapper<Semester> rowMapper = new BeanPropertyRowMapper<>(Semester.class);
		Semester sem = jdbcTemplate.queryForObject(sql, rowMapper, semesterId);
		return sem;
		}
		catch(Exception e) {
			return null;
		}
		
	}

}
