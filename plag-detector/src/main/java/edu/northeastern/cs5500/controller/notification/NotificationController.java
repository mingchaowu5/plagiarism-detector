package edu.northeastern.cs5500.controller.notification;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.northeastern.cs5500.models.notification.Notification;

/**
 * Notification Controller Class, 
 * handle notification related operations.
 * @author takyon
 *
 */
@RestController
@RequestMapping("/rest/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	/**
	 * Get all the Notifications for the professor
	 * @return	List: of all the semester in the database which have not been deleted
	 */
	@GetMapping(value = "/all")
	public ResponseEntity<List<Notification>> nofitications() {
		List<Notification> list = notificationService.getAllNotifications();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * Add the notification to the database
	 * @param text
	 * @param snapshotId
	 * @return
	 */
	@GetMapping(value = "/insert")
	public ResponseEntity<Boolean> addNotification(@RequestParam(value = "text") String text, @RequestParam(value = "id") int snapshotId){
		boolean flag = notificationService.addNotification(text, snapshotId);
		return new ResponseEntity<>(flag, HttpStatus.OK);
	}
	
}
