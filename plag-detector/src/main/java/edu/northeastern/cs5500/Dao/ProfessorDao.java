package edu.northeastern.cs5500.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.Course.Course;
import edu.northeastern.cs5500.models.Person.Professor;


@Transactional
@Repository

public class ProfessorDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Professor> findAllProfessors(){
		String sql = "Select * from User join Professor on User.id = Professor.id";
		RowMapper<Professor> rowMapper = new BeanPropertyRowMapper<>(Professor.class);
		List<Professor> results = this.jdbcTemplate.query(sql, rowMapper);
		return results;
	}
	
	
	
	
}
