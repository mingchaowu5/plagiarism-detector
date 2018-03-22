package edu.northeastern.cs5500.database;

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
public class CourseConnection {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Course> getAllCourses(){
		String sql = "Select * from Courses";
		RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
		List<Course> results = this.jdbcTemplate.query(sql, rowMapper);
		return results;
	}
	
	public List<Course> getAllCoursesForSemester(int id){
		String sql = "Select * from Courses WHERE Courses.semester = ?";
		RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
		List<Course> results = this.jdbcTemplate.query(sql, rowMapper, id);
		return results;
	}
	
	public List<Course> getAllCoursesForProfessor(int id){
		String sql = "Select Courses.* from Courses join ProfessorCourseMapping on Courses.id = ProfessorCourseMapping.course WHERE ProfessorCourseMapping.professor = ?";
		RowMapper<Course> rowMapper = new BeanPropertyRowMapper<>(Course.class);
		List<Course> results = this.jdbcTemplate.query(sql, rowMapper, id);
		return results;
	}

}
