package edu.northeastern.cs5500.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;

import edu.northeastern.cs5500.Config;
import edu.northeastern.cs5500.models.Semester.Semester;

public class SemesterDao {
	
	private static final String URL = "jdbc:mysql://plag-detector.c05al3v5c9ha.us-east-2.rds.amazonaws.com:3306/cs5500";
	private static final String USERNAME = Config.getUsername();
	private static final String PASSWORD = Config.getPassword();
	private String jdbcConnect = "com.mysql.jdbc.Driver";
	private String idString = "id";
	private String nameString = "name";
	private static SemesterDao instance = null;
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
			Class.forName(jdbcConnect);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			statement = connection.createStatement();
			String sql = "select * from Semester";
			results = statement.executeQuery(sql);
			while(results.next()) {
				int id = results.getInt(idString);
				String name = results.getString(nameString);
				Semester sem = new Semester(id, name);
				listOfSemesters.add(sem);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.getLogger("context").log(Level.ERROR, e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger("context").log(Level.ERROR, e);
		} finally {
			if (results != null) {
		        try {
		        	results.close();
		        } catch (SQLException e) { 
		        	Logger.getLogger("context").log(Level.ERROR, e);}
		    }
		    if (statement != null) {
		        try {
		        	statement.close();
		        } catch (SQLException e) { 
		        	Logger.getLogger("context").log(Level.ERROR, e);
		        	}
		    }
		    if (connection != null) {
		        try {
		            connection.close();
		        } catch (SQLException e) { 
		        	Logger.getLogger("context").log(Level.ERROR, e);
		        }
		    }
		}
		return listOfSemesters;
		
	}
	
	public Semester findSemesterById(int semesterId) {
		Semester sem = null;
	
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		try {
			Class.forName(jdbcConnect);
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			String sql = "select * from Semester WHERE Semester.id = ?";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, semesterId);
			results = statement.executeQuery();
			if(results.next()) {
				int id = results.getInt(idString);
				String name = results.getString(nameString);
				sem = new Semester(id, name);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.getLogger("context").log(Level.ERROR, e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger("context").log(Level.ERROR, e);
		} finally {
			if (results != null) {
		        try {
		        	results.close();
		        } catch (SQLException e) { 
		        	Logger.getLogger("context").log(Level.ERROR, e);}
		    }
		    if (statement != null) {
		        try {
		        	statement.close();
		        } catch (SQLException e) { 
		        	Logger.getLogger("context").log(Level.ERROR, e);
		        	}
		    }
		    if (connection != null) {
		        try {
		            connection.close();
		        } catch (SQLException e) { 
		        	Logger.getLogger("context").log(Level.ERROR, e);
		        }
		    }
		}
		return sem;
	}

}
