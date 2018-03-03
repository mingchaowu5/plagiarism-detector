package edu.northeastern.cs5500.database;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.controller.user.User;

@Transactional
@Repository
public class UserConnection {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 
	 * @param user
	 */
	public User addNewUser(final User user) {
		String sql = "INSERT INTO User(email, username, password, timestamp) VALUES(?, ?, ?, ?)";
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); 
		jdbcTemplate.update(sql, user.getEmail(), user.getUsername(), user.getPassword(), timestamp);
		return this.login(user.getUsername(), user.getPassword());
	}
	
	public User login(final String username, final String password) {
		String sql = "SELECT email, username, password, timestamp FROM User WHERE username = ? AND password = ?";
		RowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);
		User user =  jdbcTemplate.queryForObject(sql, mapper, username, password);
		return user;
	}
}