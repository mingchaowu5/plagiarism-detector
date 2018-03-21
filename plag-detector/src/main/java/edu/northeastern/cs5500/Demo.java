package edu.northeastern.cs5500;

import java.io.File;
import java.util.Collection;
import java.net.URL;
import org.apache.commons.io.FileUtils;
import it.zielke.moji.SocketClient;

public class Demo {
	private static final String sFiles = "/Users/varunnandu/Desktop/NLP/HW01";
	private static final String sBase = "/Users/varunnandu/Desktop/NLP/HW01";
	
	public static void main(String[] args) throws Exception {
		// a list of students' source code files located in the prepared
		// directory.
		Collection<File> files = FileUtils.listFiles(new File(sFiles), new String[] { "py" }, true);

		// a list of base files that was given to the students for this
		// assignment.
		Collection<File> baseFiles = FileUtils.listFiles(new File(sBase), new String[] { "py" }, true);
		
		//get a new socket client to communicate with the Moss server
		//and set its parameters.
		SocketClient socketClient = new SocketClient();
		
		//set your Moss user ID
		socketClient.setUserID("864716792");
		//socketClient.setOpt...
		
		//set the programming language of all student source codes
		socketClient.setLanguage("python");
		
		//initialize connection and send parameters
		socketClient.run();
		
		// upload all base files
		for (File f : baseFiles) {
			socketClient.uploadBaseFile(f);
		}
		
		//upload all source files of students
		for (File f : files) {
			socketClient.uploadFile(f);
		}
		
		//finished uploading, tell server to check files
		socketClient.sendQuery();
		
		//get URL with Moss results and do something with it
		URL results = socketClient.getResultURL();
		System.out.println("Results available at " + results.toString());
	}
}
