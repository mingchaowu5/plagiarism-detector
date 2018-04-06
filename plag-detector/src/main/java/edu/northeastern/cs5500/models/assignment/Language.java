package edu.northeastern.cs5500.models.assignment;

public class Language {

	private String name;
	private String code;
	private int id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Language(String name, String code, int id) {
		super();
		this.name = name;
		this.code = code;
		this.id = id;
	}
	public Language() {
		
	}
}
