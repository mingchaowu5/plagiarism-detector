package edu.northeastern.cs5500.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.northeastern.cs5500.models.Semester.Semester;

public class SemesterDao {
	
	private static final String URL = "jdbc:mysql://plag-detector.c05al3v5c9ha.us-east-2.rds.amazonaws.com:3306/cs5500";
	private static final String USERNAME = "varunnandu";
	private static final String PASSWORD = "varun123";
	public static SemesterDao instance = null;
	public static SemesterDao getInstance() {
		if (instance == null) {
			instance = new SemesterDao();
		}
		return instance;
	}
	private SemesterDao() {}
	
	public List<Semester> findAllSemesters(){
		List<Semester> listOfSemesters= new ArrayList<Semester>();
		Connection connection = null;
		Statement statement = null;
		ResultSet results = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			String sql = "select * from Semester";
			results = statement.executeQuery(sql);
			while(results.next()) {
				int id = results.getInt("id");
				String name = results.getString("name");
				Semester sem = new Semester(id, name);
				listOfSemesters.add(sem);
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
		return listOfSemesters;
		
	}

}
