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

import edu.northeastern.cs5500.models.assignment.Assignment;
import edu.northeastern.cs5500.models.assignment.Language;

/** 
 * 
 * @author varunnandu
 *
 */

@Transactional
@Repository
public class AssignmentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger log = Logger.getAnonymousLogger();
	
	/**
	 * Find all assignments
	 * @return
	 */
	public List<Assignment> findAllAssignments(){
		try {
			String sql = "Select * from Assignment ORDER BY id";
			RowMapper<Assignment> rowMapper = new BeanPropertyRowMapper<>(Assignment.class);
			return this.jdbcTemplate.query(sql, rowMapper);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	/**
	 * Find assignment for a given ID
	 * @param id
	 * @return
	 */
	public Assignment findAssignmentById(int id){
		try {
			String sql = "Select * from Assignment WHERE id = ?";
			RowMapper<Assignment> rowMapper = new BeanPropertyRowMapper<>(Assignment.class);
			return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get all assignments for a course
	 * @param courseId
	 * @return
	 */
	public List<Assignment> findAllAssignmentsForCourse(int courseId){
		try {
			String sql = "Select Assignment.id, Assignment.name, Assignment.course from Assignment where Assignment.course = ?";
			RowMapper<Assignment> rowMapper = new BeanPropertyRowMapper<>(Assignment.class);
			return this.jdbcTemplate.query(sql, rowMapper, courseId);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	/**
	 * Add Assignment
	 * @param assignmentName
	 * @param courseId
	 * @param langId
	 * @return
	 */
	public int addAssignment(String assignmentName, int courseId, int langId) {
		try {
				String sql = "INSERT INTO Assignment (name, course, language) VALUES (?, ?, ?)";
				return jdbcTemplate.update(sql, new Object[] {assignmentName, courseId, langId});
		}catch(Exception e) {
				log.log(Level.INFO, e.getMessage());
				return 0;
		}
	}
	
	/**
	 * Update Assignment
	 * @param assignmentId
	 * @param assignmentName
	 * @param courseId
	 * @param langId
	 * @return
	 */
	public int updateAssignment(int assignmentId, String assignmentName, int courseId, int langId) {
		try {
			
			String sql = "UPDATE Assignment SET name = ?, course = ?, language = ? WHERE id = ?";
			return jdbcTemplate.update(sql, new Object[] {assignmentName, courseId, langId, assignmentId});
		}catch(Exception e){
			log.log(Level.INFO, e.getMessage());
			return 0;
		}
	}
	
	/**
	 * Delete Assignment
	 * @param id
	 * @return
	 */
	public int deleteAssignment(final int id) {
		try {
			String sql = "DELETE FROM Assignment WHERE id = ?";
			return jdbcTemplate.update(sql, new Object[] {id});
		}catch(Exception e){
			log.log(Level.INFO, e.getMessage());
			return 0;
		}
	}
	
	/**
	 * Get Language of an Assignment
	 * @param id
	 * @return
	 */
	public String getLanguage(int id) {
		try {
			String sql = "SELECT l.name, l.code, l.id FROM Submission AS s JOIN Assignment AS a ON s.assignment = a.id JOIN Languages AS l ON a.language = l.id WHERE s.id = ?";
			RowMapper<Language> rowMapper = new BeanPropertyRowMapper<>(Language.class);
			Language l = this.jdbcTemplate.queryForObject(sql, rowMapper, id);
			if(l == null || l.getCode() == null)
				return "python3";
			return l.getCode();
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return "python3";
		}
	}
}
