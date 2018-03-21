package edu.northeastern.cs5500;

public class Config {
	
	private static final String username = getUser();
	private static final String password = getPass();
	public static String getUsername() {
		return username;
	}
	public static String getPassword() {
		return password;
	}
	
	public static String getUser() {
		return "varunnandu";
	}
	
	public static String getPass() {
		return "varun123";
	}
	

}
