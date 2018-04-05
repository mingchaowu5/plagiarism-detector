package edu.northeastern.cs5500.controller.notification;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.northeastern.cs5500.Constants;
import edu.northeastern.cs5500.dao.NotificationDao;
import edu.northeastern.cs5500.models.notification.Notification;


@Service
public class NotificationService {

	@Autowired
	private NotificationDao notificationDao;
	
	/**
	 * Connect to the database layer and get the notifications
	 * @return
	 */
	public List<Notification> getAllNotifications(){
		return this.notificationDao.findAllNotifications();
	}
	
	/**
	 * Add a notification to the database
	 * @param text
	 * @param snapshotId
	 * @return boolean: true iff and only if the notification is successfully added to the database
	 */
	public boolean addNotification(String text, int snapshotId) {
		return this.notificationDao.addNotification(text, snapshotId, Constants.getCurrentDate()) == 1 ? true: false;
	}
	
}
