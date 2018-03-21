package edu.northeastern.cs5500.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.northeastern.cs5500.models.Course.Course;
import edu.northeastern.cs5500.models.Semester.Semester;

public class CourseDao {
	
	private static final String URL = "jdbc:mysql://plag-detector.c05al3v5c9ha.us-east-2.rds.amazonaws.com:3306/cs5500";
	private static final String USERNAME = "varunnandu";
	private static final String PASSWORD = "varun123";
	public static CourseDao instance = null;
	public static CourseDao getInstance() {
		if (instance == null) {
			instance = new CourseDao();
		}
		return instance;
	}
	private CourseDao() {}
	
	public List<Course> findAllCourses(){
		List<Course> listOfCourses= new ArrayList<Course>();
		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			String sql = "select * from Courses";
			results = statement.executeQuery(sql);
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				int semester = results.getInt("semester");
				Course c = new Course(id, name, semester);
				listOfCourses.add(c);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listOfCourses;
		
	}
	
	public List<Course> findCoursesForSemester(int semesterId){
		List<Course> listOfCourses= new ArrayList<Course>();
		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			String sql = "select * from Courses WHERE Courses.semester = '"+ semesterId +"'";
			results = statement.executeQuery(sql);
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				int semester = results.getInt("semester");
				Course c = new Course(id, name, semester);
				listOfCourses.add(c);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listOfCourses;
		
	}
	
	public List<Course> findCoursesForProfessor(int professorId){
		List<Course> listOfCourses= new ArrayList<Course>();
		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			String sql = "select Courses.id, Courses.name, Courses.semester from Courses join ProfessorCourseMapping on Courses.id = ProfessorCourseMapping.course WHERE ProfessorCourseMapping.professor = '"+ professorId +"'";
			results = statement.executeQuery(sql);
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				int semester = results.getInt("semester");
				Course c = new Course(id, name, semester);
				listOfCourses.add(c);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listOfCourses;
		
	}

}
