package edu.northeastern.cs5500.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.assignment.Assignment;
import edu.northeastern.cs5500.models.assignment.Language;

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
			return new ArrayList<>();
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
			return new ArrayList<>();
		}
	}
	
	public int addAssignment(String assignmentName, int courseId, int langId) {
		try {
				String sql = "INSERT INTO Assignment (name, course, language) VALUES (?, ?, ?)";
				return jdbcTemplate.update(sql, new Object[] {assignmentName, courseId, langId});
			}
			catch(Exception e) {
				return 0;
			}
	}
	
	public int updateAssignment(int assignmentId, String assignmentName, int courseId, int langId) {
		try {
			
			String sql = "UPDATE Assignment SET name = ?, course = ?, language = ? WHERE id = ?";
			return jdbcTemplate.update(sql, new Object[] {assignmentName, courseId, langId, assignmentId});
		}
		catch(Exception e){
			return 0;
		}
	}
	
	public int deleteAssignment(final int id) {
		try {
			String sql = "DELETE FROM Assignment WHERE id = ?";
			return jdbcTemplate.update(sql, new Object[] {id});
		}
		catch(Exception e){
			return 0;
		}
	}
	
	public void assignAssignmentToStudents(List<Integer> listOfStudents, int assignmentId) {
		for(int s: listOfStudents) {
			try {
				String sql = "INSERT INTO StudentAssignmentMapping (student, assignment) VALUES (?, ?)";
				jdbcTemplate.update(sql, new Object[] {s, assignmentId});
			}
			catch(Exception e) {
			}
		}
	}
	
	public String getLanguage(int id) {
		try {
			String sql = "SELECT l.name, l.code, l.id FROM Submission AS s JOIN Assignment AS a ON s.assignment = a.id JOIN Languages AS l ON a.language = l.id WHERE s.id = ?";
			RowMapper<Language> rowMapper = new BeanPropertyRowMapper<>(Language.class);
			Language l = this.jdbcTemplate.queryForObject(sql, rowMapper, id);
			if(l == null || l.getCode() == null)
				return "python3";
			return l.getCode();
		}catch(Exception e) {
			return "python3";
		}
	}
}
