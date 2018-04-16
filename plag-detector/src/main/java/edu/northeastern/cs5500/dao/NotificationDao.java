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

import edu.northeastern.cs5500.models.notification.Notification;

@Transactional
@Repository
public class NotificationDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger log = Logger.getAnonymousLogger();
	
	/**
	 * 
	 * @return
	 */
	public List<Notification> findAllNotifications(){
		try {
			String sql = "Select * from Notification ORDER BY date DESC";
			RowMapper<Notification> rowMapper = new BeanPropertyRowMapper<>(Notification.class);
			return this.jdbcTemplate.query(sql, rowMapper);
		}
		catch(Exception e) {
			log.log(Level.INFO, e.getMessage());
			return new ArrayList<>();
		}
	}

	/**
	 * 
	 * @param text
	 * @param snapshotId
	 * @param date
	 * @return
	 */
	public int addNotification(String text, int snapshotId, String date) {
		try {
				String sql = "INSERT INTO Notification (text, snapshot, date) VALUES (?, ?, ?)";
				return jdbcTemplate.update(sql, new Object[] {text, snapshotId, date});
			}
			catch(Exception e) {
				log.log(Level.INFO, e.getMessage());
				return 0;
			}
	}

}
