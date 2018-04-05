package edu.northeastern.cs5500.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.northeastern.cs5500.models.notification.Notification;

@Transactional
@Repository
public class NotificationDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Notification> findAllNotifications(){
		try {
			String sql = "Select * from Notification ORDER BY date DESC";
			RowMapper<Notification> rowMapper = new BeanPropertyRowMapper<>(Notification.class);
			List<Notification> results = this.jdbcTemplate.query(sql, rowMapper);
			return results;
		}
		catch(Exception e) {
			return new ArrayList<>();
		}
	}

	public int addNotification(String text, int snapshotId, String date) {
		try {
				String sql = "INSERT INTO Notification (text, snapshot, date) VALUES (?, ?, ?)";
				return jdbcTemplate.update(sql, new Object[] {text, snapshotId, date});
			}
			catch(Exception e) {
				return 0;
			}
	}

}
