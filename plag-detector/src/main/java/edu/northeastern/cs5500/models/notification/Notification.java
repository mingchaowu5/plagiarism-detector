package edu.northeastern.cs5500.models.notification;


public class Notification {
	private int id;
	private String text;
	private int snapshot;
	private String date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getSnapshot() {
		return snapshot;
	}
	public void setSnapshot(int snapshot) {
		this.snapshot = snapshot;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Notification(int id, String text, int snapshot, String date) {
		this.id = id;
		this.text = text;
		this.snapshot = snapshot;
		this.date = date;
	}
	public Notification() {
		
	}
	
	

}
