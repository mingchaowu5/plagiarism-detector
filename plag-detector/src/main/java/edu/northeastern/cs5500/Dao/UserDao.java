package edu.northeastern.cs5500.Dao;

import java.util.List;

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
		try {
		String sql = "INSERT INTO User(firstName, lastName, email, userName, password) VALUES(?, ?, ?, ?, ?)";
		//jdbcTemplate.update(sql, user, user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword());
		jdbcTemplate.update(sql, new Object[] {user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword()});
		}
		catch(Exception e) {
			return null;
		}
		int rId = findId(user);
		if(user instanceof Student) {
			str = "Student";
			try {
				String sql2 = "INSERT INTO Student (id, universityID) VALUES(?, ?)";
				//jdbcTemplate.update(sql2, user, rId, ((Student) user).getUniversityId());
				jdbcTemplate.update(sql2, new Object[] {rId, ((Student) user).getUniversityId()});
			}
			catch(Exception e) {
				return null;
			}
		
		}
		else if(user instanceof Professor){
			str = "Professor";
			String sql2 = "INSERT INTO Professor (id, officeLocation) VALUES(?, ?)";
			//jdbcTemplate.update(sql2, user, rId, ((Professor) user).getOfficeLocation());	
			jdbcTemplate.update(sql2, new Object[] {rId, ((Professor) user).getOfficeLocation()});	
		}
		
		
		return this.login(user.getUsername(), user.getPassword(), str);
	}
	
	public User login(final String username, final String password, String type) {
		if(type.equals("Professor")) {
			try {
				String sql = "SELECT * FROM User join Professor on  Professor.id = User.id WHERE User.username = ? AND User.password = ?";
				RowMapper<Professor> mapper = new BeanPropertyRowMapper<>(Professor.class);
				Professor user =  jdbcTemplate.queryForObject(sql, mapper, username, password);
				return user;
				
			}
			catch(Exception e) {
				return null;
			}
			
		} else if(type.equals("Student")) {
			
			try {
			String sql = "SELECT * FROM User join Student on  Student.id = User.id WHERE User.username = ? AND User.password = ?";
			RowMapper<Student> mapper = new BeanPropertyRowMapper<>(Student.class);
			Student user =  jdbcTemplate.queryForObject(sql, mapper, username, password);
			return user;
			}
			catch(Exception e) {
				return null;
			}
			
		}
		else {
			return null;
		}
		
		
		
	
	}
	
	public List<User> findAllUsers(){
		try {
		String sql = "Select * from User";
		RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
		List<User> results = this.jdbcTemplate.query(sql, rowMapper);
		return results;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	public Integer getTypeFromUsernamePassword(String username, String password) {
		try {
			String sql = "Select * from User where User.username = ? AND User.password = ?";
			RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
			User user =  jdbcTemplate.queryForObject(sql, rowMapper, username, password);
			try {
				String sql2 = "Select * from Student where Student.id = ?";
				RowMapper<Student> map1 = new BeanPropertyRowMapper<>(Student.class);
				Student s =  jdbcTemplate.queryForObject(sql2, map1, user.getId());
				return 1;
			} catch(Exception e) {
				try {
					String sql3 = "Select * from Professor where Professor.id = ?";
					RowMapper<Professor> map2 = new BeanPropertyRowMapper<>(Professor.class);
					Professor p =  jdbcTemplate.queryForObject(sql3, map2, user.getId());
					return 0;
				} catch(Exception ee) {
					return -1;
				}
				
			}
			
			
			}
			catch(Exception e) {
				return null;
			}
	}
	
	
	
public Integer findId(final User user) {
		
		try {
			String sql = "SELECT * FROM User WHERE User.username = ? AND User.password = ?";
			RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
			User u =  jdbcTemplate.queryForObject(sql, mapper, user.getUsername(), user.getPassword());
			return u.getId();
			
		}
		catch(Exception e) {
			return null;
		}
	}
}