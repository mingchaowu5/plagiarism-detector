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

	public List<Course> findAllCourses() {
		String sql = "Select * from Courses";
		RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
		List<Course> results = this.jdbcTemplate.query(sql, rowMapper);
		return results;
	}

	public List<Course> findAllCoursesForSemester(int id) {
		String sql = "Select * from Courses WHERE Courses.semester = ?";
		RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
		List<Course> results = this.jdbcTemplate.query(sql, rowMapper, id);
		return results;
	}

	public List<Course> findAllCoursesForProfessor(int id) {
		String sql = "Select Courses.* from Courses join ProfessorCourseMapping on Courses.id = ProfessorCourseMapping.course WHERE ProfessorCourseMapping.professor = ?";
		RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
		List<Course> results = this.jdbcTemplate.query(sql, rowMapper, id);
		return results;
	}

	public List<Course> findAllCoursesForStudent(int studentId) {
		String sql = "Select Courses.* from Courses join StudentCourseMapping on Courses.id = StudentCourseMapping.course WHERE StudentCourseMapping.student = ?";
		RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
		List<Course> results = this.jdbcTemplate.query(sql, rowMapper, studentId);
		return results;
	}

}
