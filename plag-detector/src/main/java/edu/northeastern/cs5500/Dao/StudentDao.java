package edu.northeastern.cs5500.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.Person.Student;
import edu.northeastern.cs5500.models.Person.StudentAssignmentMap;
import edu.northeastern.cs5500.models.Person.User;
import edu.northeastern.cs5500.models.file.FileStructure;

@Transactional
@Repository
public class StudentDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Integer addFileForStudentAssignment(int studentId, int assignmentId, String fname) {
		int saId = findStudentAssignmentId(studentId, assignmentId);
		try {
		String sql = "INSERT INTO StudentAssignmentFileMapping (studentAssignment, file) VALUES(?, ?)";
		jdbcTemplate.update(sql, new Object[] {saId, fname});
		}
		catch(Exception e) {
		}
		int myId = findStudentFileId(saId,fname);
		return myId;
		
	}
	
public Integer findStudentFileId(int saId, String fname) {
		
		try {
			String sql = "SELECT * FROM StudentAssignmentFileMapping WHERE StudentAssignmentFileMapping.studentAssignment = ? AND StudentAssignmentFileMapping.file = ?";
			RowMapper<FileStructure> mapper = new BeanPropertyRowMapper<>(FileStructure.class);
			FileStructure u =  jdbcTemplate.queryForObject(sql, mapper, saId, fname);
			return u.getId();
			
		}
		catch(Exception e) {
			return null;
		}
	}
	
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
	
	public List<Student> findAllStudentsForAssignment(int assignmentId) {
		try {
		String sql = "Select Student.id, Student.universityID from StudentAssignmentMapping join Student on Student.id = StudentAssignmentMapping.student WHERE StudentAssignmentMapping.assignment = ?";
		RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
		List<Student> results = this.jdbcTemplate.query(sql, rowMapper, assignmentId);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public List<FileStructure> findAllFileStructuresStudent(int studentId, int assignmentId) {
		int saId = findStudentAssignmentId(studentId, assignmentId);
		try {
		String sql = "Select * from StudentAssignmentFileMapping WHERE StudentAssignmentFileMapping.studentAssignment = ?";
		RowMapper<FileStructure> rowMapper = new BeanPropertyRowMapper<>(FileStructure.class);
		List<FileStructure> results = this.jdbcTemplate.query(sql, rowMapper, saId);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}


}
