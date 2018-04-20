package edu.northeastern.cs5500;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class to store constants used across the database
 * @author takyon
 *
 */
public interface Constants {
	
	public static final String ASSIGNMENTURL = "/home/ec2-user/assignments/";
	public static final String TEMPURL = "/home/ec2-user/temp/";
	public static final String RESULTURL = "/home/ec2-user/result/";
	//public static final String ASSIGNMENTURL = "jplag_results/assignments/";
	//public static final String TEMPURL = "jplag_results/temp/";
	//public static final String RESULTURL = "jplag_results/result/";
	
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
	
	/**
	 * Create Folder
	 */
	public static void createFolders() {
		try{File f1 = new File(ASSIGNMENTURL);

		if(f1.exists()) {
			Files.delete(f1.toPath());
		}
		f1.mkdir();
		f1 = new File(TEMPURL);
		if(f1.exists()) {
			Files.delete(f1.toPath());
		}
		f1.mkdir();
		
		
		f1 = new File(RESULTURL);
		if(f1.exists()) {
			Files.delete(f1.toPath());
		}
		f1.mkdir();
		}catch(IOException e) {
			Logger log = Logger.getAnonymousLogger();
			log.log(Level.INFO, "Unable to add/delete folders");
		}
	}
}
