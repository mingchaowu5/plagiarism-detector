package edu.northeastern.cs5500.models.snapshot;


/** 
 * 
 * @author varunnandu
 *
 */
public class Snapshot {
	private Integer id;
	private int type;
	private int submission;
	private String dateTime;
	
	/** 
	 * 
	 * @return
	 */
	public int getType() {
		return type;
	}
	
	/** 
	 * 
	 * @param type
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/** 
	 * 
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	
	/** 
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/** 
	 * 
	 * @return
	 */
	public int getSubmission() {
		return submission;
	}
	
	/** 
	 * 
	 * @param submission
	 */
	public void setSubmission(int submission) {
		this.submission = submission;
	}
	
	/** 
	 * 
	 * @return
	 */
	public String getDateTime() {
		return dateTime;
	}
	
	/** 
	 * 
	 * @param dateTime
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	/** 
	 * 
	 */
	public Snapshot() {
	}
	
	/** 
	 * 
	 * @param id
	 * @param submission
	 * @param dateTime
	 */
	public Snapshot(Integer id, int submission, String dateTime) {
		this.id = id;
		this.submission = submission;
		this.dateTime = dateTime;
	}
	
	

}
