package edu.northeastern.cs5500.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.Person.Professor;
import edu.northeastern.cs5500.models.Person.Student;
import edu.northeastern.cs5500.models.Person.User;

@Transactional
@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @param user
	 */
	public User addNewUser(final User user) {
		String str = null;
		String sql = "INSERT INTO User(id, firstName, lastName, lastName, userName, password) VALUES(?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, user, user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword());
		if(user instanceof Student) {
			str = "Student";
			String sql2 = "INSERT INTO Student (id, universityID) VALUES(?, ?)";
			jdbcTemplate.update(sql2, user, user.getId(), ((Student) user).getUniversityId());	
		
		}
		else if(user instanceof Professor){
			str = "Professor";
			String sql2 = "INSERT INTO Professor (id, officeLocation) VALUES(?, ?)";
			jdbcTemplate.update(sql2, user, user.getId(), ((Professor) user).getOfficeLocation());	
		}
		
		
		return this.login(user.getUsername(), user.getPassword(), str);
	}
	
	public User login(final String username, final String password, String type) {
		if(type.equals("Professor")) {
			String sql = "SELECT * FROM User join Professor on  Professor.id = User.id WHERE User.username = ? AND User.password = ?";
			RowMapper<Professor> mapper = new BeanPropertyRowMapper<>(Professor.class);
			Professor user =  jdbcTemplate.queryForObject(sql, mapper, username, password);
			return user;
		} else if(type.equals("Student")) {
			String sql = "SELECT * FROM User join Student on  Student.id = User.id WHERE User.username = ? AND User.password = ?";
			RowMapper<Student> mapper = new BeanPropertyRowMapper<>(Student.class);
			Student user =  jdbcTemplate.queryForObject(sql, mapper, username, password);
			return user;
		}
		else {
			return null;
		}
		
		
		
	
	}
}