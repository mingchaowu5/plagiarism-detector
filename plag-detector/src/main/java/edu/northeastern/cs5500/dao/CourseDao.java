package edu.northeastern.cs5500.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.course.Course;

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
		}catch(Exception e) {
			return new ArrayList<>();
		}
	}
	
	public void assignProfessorToCourse(int professorId, int courseId) {
		try {
			String sql = "INSERT INTO ProfessorCourseMapping (professor, course) VALUES (?, ?)";
			jdbcTemplate.update(sql, new Object[] {professorId, courseId});
		}catch(Exception e) {
		}
	}
	
	public void registerStudentForCourse(int studentId, int courseId){
		try {
			String sql = "INSERT INTO StudentCourseMapping (student, course) VALUES (?, ?)";
			jdbcTemplate.update(sql, new Object[] {studentId, courseId});
		}catch(Exception e) {
		}
	}
	
	public void addCourse(final Course course, int semesterId) {
		try {
			String sql = "INSERT INTO Courses (name, semester) VALUES (?, ?)";
			jdbcTemplate.update(sql, new Object[] {course.getName(), semesterId});
		}catch(Exception e) {
		}
	}
	
	public void updateCourse(final Course course) {
		try {
			
			String sql = "UPDATE Courses SET name = ?, semester = ? WHERE id = ?";
			jdbcTemplate.update(sql, new Object[] {course.getName(), course.getSemester(), course.getId()});
		}
		catch(Exception e){
			
		}
	}
	
	public void deleteCourse(final Course course) {
		try {
			
			String sql = "DELETE FROM Courses WHERE id = ?";
			jdbcTemplate.update(sql, new Object[] {course.getId()});
		}
		catch(Exception e){
			
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
			return new ArrayList<>();
		}
	}

	public List<Course> getAllCoursesForProfessor(int id) {
		try {
			String sql = "Select Courses.id, Courses.name, Courses.semester from Courses join ProfessorCourseMapping on Courses.id = ProfessorCourseMapping.course WHERE ProfessorCourseMapping.professor = ?";
			RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
			List<Course> results = this.jdbcTemplate.query(sql, rowMapper, id);
			return results;
		}catch(Exception e) {
			return new ArrayList<>();
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
			return new ArrayList<>();
		}
	}

}
