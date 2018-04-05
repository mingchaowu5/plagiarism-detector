package edu.northeastern.cs5500.models.snapshot;

public class Snapshot {
	private Integer id;
	private int type;
	private int submission;
	private String dateTime;
	
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getSubmission() {
		return submission;
	}
	public void setSubmission(int submission) {
		this.submission = submission;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public Snapshot() {
		// TODO Auto-generated constructor stub
	}
	public Snapshot(Integer id, int submission, String dateTime) {
		this.id = id;
		this.submission = submission;
		this.dateTime = dateTime;
	}
	
	

}
