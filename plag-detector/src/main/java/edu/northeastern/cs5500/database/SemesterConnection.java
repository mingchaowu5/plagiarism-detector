package edu.northeastern.cs5500.database;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import edu.northeastern.cs5500.models.Semester.Semester;

@Transactional
@Repository
public class SemesterConnection {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Semester> getAllSemester(){
		String sql = "Select * from Semester";
		RowMapper<Semester> rowMapper = new BeanPropertyRowMapper<>(Semester.class);
		List<Semester> results = this.jdbcTemplate.query(sql, rowMapper);
		return results;
	}
}
