package edu.northeastern.cs5500;

/**
 * Class to store constants used across the database
 * @author takyon
 *
 */
public interface Constants {
	//public static final String ASSIGNMENTURL = "/home/ec2-user/assignments/";
	//public static final String TEMPURL = "/home/ec2-user/temp/";
	//public static final String RESULTURL = "/home/ec2-user/result/";
	public static final String ASSIGNMENTURL = "/Users/takyon/Documents/homework2/assignments/";
	public static final String TEMPURL = "/Users/takyon/Documents/homework2/temp/";
	public static final String RESULTURL = "/Users/takyon/Documents/homework2/result/";
	
	
	/**
	 * Get current date
	 * @return String: which represents the current date
	 */
	public static String getCurrentDate() {
		java.util.Date dt = new java.util.Date();

		java.text.SimpleDateFormat sdf = 
		     new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return sdf.format(dt);
	}
}
