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

import edu.northeastern.cs5500.models.course.Course;

/** 
 * 
 * @author varunnandu
 *
 */
@Transactional
@Repository

public class CourseDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger log = Logger.getAnonymousLogger();

	/**
	 * 
	 * @return
	 */
	public List<Course> getAllCourses() {
		try {
			String sql = "Select * from Courses";
			RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
			return this.jdbcTemplate.query(sql, rowMapper);
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	/**
	 * 
	 * @param professorId
	 * @param courseId
	 */
	public void assignProfessorToCourse(int professorId, int courseId) {
		try {
			String sql = "INSERT INTO ProfessorCourseMapping (professor, course) VALUES (?, ?)";
			jdbcTemplate.update(sql, new Object[] {professorId, courseId});
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	public int registerStudentForCourse(int studentId, int courseId){
		if(checkRegister(studentId, courseId) == null) {
			try {
				String sql = "INSERT INTO StudentCourseMapping (student, course) VALUES (?, ?)";
				return jdbcTemplate.update(sql, new Object[] {studentId, courseId});
			}catch(Exception e) {
				log.log(Level.INFO, e.getMessage());
			}
		}
		return 0;
	}
	
	/**
	 * 
	 * @param course
	 * @param semesterId
	 * @return
	 */
	public int addCourse(final Course course, int semesterId) {
		try {
			String sql = "INSERT INTO Courses (name, semester) VALUES (?, ?)";
			return jdbcTemplate.update(sql, new Object[] {course.getName(), semesterId});
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
		}
		return 0;
	}
	
	/**
	 * 
	 * @param course
	 * @return
	 */
	public int updateCourse(final Course course) {
		try {
			String sql = "UPDATE Courses SET name = ?, semester = ? WHERE id = ?";
			return jdbcTemplate.update(sql, new Object[] {course.getName(), course.getSemester(), course.getId()});
		}
		catch(Exception e){
			log.log(Level.INFO, e.getMessage());
		}
		return 0;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public int deleteCourse(final int id) {
		try {
			
			String sql = "DELETE FROM Courses WHERE id = ?";
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
	 * @return
	 */
	public List<Course> getAllCoursesForSemester(int id) {
		try {
			String sql = "Select Courses.id, Courses.name, Courses.semester from Courses WHERE Courses.semester = ?";
			RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
			return this.jdbcTemplate.query(sql, rowMapper, id);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<Course> getAllCoursesForProfessor(int id) {
		try {
			String sql = "Select Courses.id, Courses.name, Courses.semester from Courses join ProfessorCourseMapping on Courses.id = ProfessorCourseMapping.course WHERE ProfessorCourseMapping.professor = ?";
			RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
			return this.jdbcTemplate.query(sql, rowMapper, id);
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	
	/**
	 * 
	 * @param studentId
	 * @return
	 */
	public List<Course> getAllCoursesForStudent(int studentId) {
		try {
			String sql = "Select Courses.id, Courses.name, Courses.semester from Courses join StudentCourseMapping on Courses.id = StudentCourseMapping.course WHERE StudentCourseMapping.student = ?";
			RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
			return this.jdbcTemplate.query(sql, rowMapper, studentId);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}
	/** 
	 * 
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	
	public Course checkRegister(int studentId, int courseId) {
		try {
			String sql = "Select id, course as semester from StudentCourseMapping where student = ? and course = ?";
			RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
			return jdbcTemplate.queryForObject(sql, rowMapper, studentId, courseId);
		}catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
		}
		
		return null;
		
	}

}
