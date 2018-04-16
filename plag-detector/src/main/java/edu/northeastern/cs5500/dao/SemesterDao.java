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

import edu.northeastern.cs5500.models.semester.Semester;

@Transactional
@Repository
public class SemesterDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger log = Logger.getAnonymousLogger();
	
	/**
	 * 
	 * @return
	 */
	public List<Semester> getAllSemester(){
		try {
			String sql = "Select * from Semester";
			RowMapper<Semester> rowMapper = new BeanPropertyRowMapper<>(Semester.class);
			return this.jdbcTemplate.query(sql, rowMapper);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	/**
	 * 
	 * @param semesterId
	 * @return
	 */
	public Semester findSemesterById(int semesterId) {
		try {
			String sql = "select * from Semester WHERE Semester.id = ?";
			RowMapper<Semester> rowMapper = new BeanPropertyRowMapper<>(Semester.class);
			return jdbcTemplate.queryForObject(sql, rowMapper, semesterId);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public int addSemester(String name) {
		try {
			String sql = "INSERT INTO Semester (name) VALUES (?)";
			return jdbcTemplate.update(sql, new Object[] {name});
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
		}
		return 0;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int deleteSemester(final int id) {
		try {
			String sql = "DELETE FROM Semester WHERE id = ?";
			return jdbcTemplate.update(sql, new Object[] {id});
		}
		catch(Exception e){
			log.log(Level.INFO, e.getMessage());
		}
		return 0;
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public int updateSemester(int id, String name) {
		try {
			String sql = "UPDATE Semester SET name = ? WHERE id = ?";
			return jdbcTemplate.update(sql, new Object[] {name, id});
		}
		catch(Exception e){
			log.log(Level.INFO, e.getMessage());
		}
		return 0;
	}

}
