package edu.northeastern.cs5500.models.notification;

/** 
 * 
 * @author varunnandu
 *
 */
public class Notification {
	private int id;
	private String text;
	private int snapshot;
	private String date;
	
	/** 
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/** 
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getText() {
		return text;
	}
	
	/**  
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/** 
	 * 
	 * @return
	 */
	public int getSnapshot() {
		return snapshot;
	}
	
	/** 
	 * 
	 * @param snapshot
	 */
	public void setSnapshot(int snapshot) {
		this.snapshot = snapshot;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getDate() {
		return date;
	}
	
	/** 
	 * 
	 * @param date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/** 
	 * 
	 * @param id
	 * @param text
	 * @param snapshot
	 * @param date
	 */
	public Notification(int id, String text, int snapshot, String date) {
		this.id = id;
		this.text = text;
		this.snapshot = snapshot;
		this.date = date;
	}
	
	/** 
	 * 
	 */
	public Notification() {
		
	}
	
	

}
