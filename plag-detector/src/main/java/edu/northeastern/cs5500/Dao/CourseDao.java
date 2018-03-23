package edu.northeastern.cs5500.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.Course.Course;

@Transactional
@Repository

public class CourseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Course> getAllCourses() {
		try {
		String sql = "Select * from Courses";
		RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
		List<Course> results = this.jdbcTemplate.query(sql, rowMapper);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}

	public List<Course> getAllCoursesForSemester(int id) {
		try {
		String sql = "Select Courses.id, Courses.name, Courses.semester from Courses WHERE Courses.semester = ?";
		RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
		List<Course> results = this.jdbcTemplate.query(sql, rowMapper, id);
		
		return results;
		
		}
		catch(Exception e) {
			return null;
		}
	}

	public List<Course> getAllCoursesForProfessor(int id) {
		try {
		String sql = "Select Courses.id, Courses.name, Courses.semester from Courses join ProfessorCourseMapping on Courses.id = ProfessorCourseMapping.course WHERE ProfessorCourseMapping.professor = ?";
		RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
		List<Course> results = this.jdbcTemplate.query(sql, rowMapper, id);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}

	public List<Course> getAllCoursesForStudent(int studentId) {
		try {
		String sql = "Select Courses.id, Courses.name, Courses.semester from Courses join StudentCourseMapping on Courses.id = StudentCourseMapping.course WHERE StudentCourseMapping.student = ?";
		RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
		List<Course> results = this.jdbcTemplate.query(sql, rowMapper, studentId);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}

}
