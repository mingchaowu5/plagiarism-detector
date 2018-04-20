package edu.northeastern.cs5500.controller.result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.northeastern.cs5500.Constants;
import edu.northeastern.cs5500.dao.AssignmentDao;
import edu.northeastern.cs5500.dao.ResultsDao;

@Service
public class ResultsService {
	
	private int sub1;
	private int sub2;
	private Set<String> set1;
	private Set<String> set2;
	private Logger log;
	private Map<String, String> map;
	private List<FileResult> results;
	private String language = "python3";
	
	@Autowired
	private ResultsDao resultsDao;
	
	@Autowired
	private AssignmentDao assignmentDao;
	
	/**
	 * initialize the instance variables
	 * @param snap
	 * @param sub1
	 * @param sub2
	 */
	private void init(int sub1, int sub2) {
		this.sub1 = sub1;
		this.sub2 = sub2;
		set1 = new HashSet<>();
		set2 = new HashSet<>();
		map = new HashMap<>();
		results = new ArrayList<>();
		this.language = this.assignmentDao.getLanguage(sub1);
	}
	
	/**
	 * Find and store results for the two submissions
	 * @param snap
	 * @param sub1
	 * @param sub2
	 */
	public void findResults(int sub1, int sub2){
		init(sub1, sub2);
		try{
			log = Logger.getAnonymousLogger();
			if(!this.createFileIfNotPresent(Constants.TEMPURL))
				return;
			if(!this.createFileIfNotPresent(Constants.RESULTURL))
				return;
			FileUtils.cleanDirectory(new File(Constants.TEMPURL));
			FileUtils.cleanDirectory(new File(Constants.RESULTURL));
			findFiles(sub1, set1);
			findFiles(sub2, set2);
			runJplag();
			readResults();
			saveToDatabase();
		}catch(IOException e) {
			log.log(Level.INFO, e.getMessage());
		}
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private void saveToDatabase() throws IOException{
		for(FileResult fr : results) {
			this.saveFiles(fr.getFile1(), map.get(fr.getFile1()), fr.getFile2(), map.get(fr.getFile2()), fr.getResult());
		}
	}
	
	/**
	 * Read the results given out by the Jplag library
	 */
	private void readResults() throws IOException{
		File file = new File(Constants.RESULTURL + "index.html");
		if(!file.exists()) {
			return;
		}
		Document doc = Jsoup.parse(file, "UTF-8");
		Element table = doc.select("table").get(2); //select the first table.
		Elements rows = table.select("tr");
		int k = 0;
		for (int i = 0; i < rows.size(); i++) { //first row is the col names so skip it.
		    Element row = rows.get(i);
		    Elements cols = row.select("td");
		    String mFile = cols.get(0).text();
		    for(int j = 2;j<cols.size();++j) {
		    		String tFile = cols.get(j).select("a").text();
		    		String per = cols.get(j).select("font").text();
		    		double d = Double.parseDouble(per.substring(1, per.length() - 2));	
		    		String mMatch = "match" + k + "-0.html";
		    		String tMatch = "match" + k + "-1.html";
		    		saveFiles(mFile, mMatch, tFile, tMatch, d);
		    		++k;
		    }
		}
	}
	
	
	/**
	 * Save the two files to the database
	 * @param file1
	 * @param match1
	 * @param file2
	 * @param match2
	 */
	private void saveFiles(String file1, String match1, String file2, String match2, double plag) throws IOException{
		try(InputStream is1 = new FileInputStream(new File(Constants.RESULTURL + match1)); 
				InputStream is2 = new FileInputStream(new File(Constants.RESULTURL + match2))){
			if(set1.contains(file1) && set2.contains(file2)) {
				this.resultsDao.addResults(sub1, sub2, file1, file2, is1, is2, plag);
			}else if(set1.contains(file2) && set2.contains(file1)) {
				this.resultsDao.addResults(sub2, sub1, file1, file2, is1, is2, plag);			
			}
		}catch(IOException e) {
			log.log(Level.CONFIG, e.getMessage());
		}
	}
	
	/**
	 * Run JPlag library and find the results
	 */
	public void runJplag() throws IOException, MalformedURLException {
        FileUtils.copyURLToFile(new URL("https://github.com/jplag/jplag/releases/download/v2.11.9-SNAPSHOT/jplag-2.11.9-SNAPSHOT-jar-with-dependencies.jar"), new File("jplag-2.11.9-SNAPSHOT-jar-with-dependencies.jar"));
        String command="java -jar jplag-2.11.9-SNAPSHOT-jar-with-dependencies.jar "+ Constants.TEMPURL +" -l "+ language +" -r "+ Constants.RESULTURL +" -s  ";
        StringBuilder output = new StringBuilder();
        Process p;
        try {
                p = Runtime.getRuntime().exec(command);
                p.waitFor();
                BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

                String line = "";
                while ((line = reader.readLine())!= null) {
                        output.append(line + "\n");
                }

        } catch (Exception e) {
                log.log(Level.INFO,"Context : "+ "No directories found to parse");
        }
     }
	
	/**
	 * Find and copy all the files for this submission
	 * @param sub
	 * @param set
	 * @throws IOException
	 */
	private void findFiles(int sub, Set<String> set) throws IOException{
		File file = new File(Constants.ASSIGNMENTURL + sub);
		if(!file.exists())
			return;
		List<File> subdirs = this.getSubdirs(file);
		subdirs.add(file);
		for(File sFile : subdirs) {
			File[] allFiles = sFile.listFiles();
			if(allFiles == null || allFiles.length == 0)
				continue;
			for(File tFile : allFiles) {
				if(!tFile.isDirectory()) {
					set.add(sub + "-" + tFile.getName());
					this.copyFile(tFile, sub);
				}
			}
		}
	}
	
	/**
	 * Copy file from source to temp location
	 * @param source
	 * @param sub
	 * @throws IOException
	 */
	private void copyFile(File source, int sub) throws IOException {
		log.log(Level.INFO, "File: " + source.getAbsolutePath());
		Files.copy(Paths.get(source.getAbsolutePath()), Paths.get(Constants.TEMPURL + sub + "-" + source.getName()),
                StandardCopyOption.REPLACE_EXISTING);
	}
	
	/**
	 * Get all the sub-directories in the folder
	 * @param file
	 * @return List: of sub-directories
	 */
	private List<File> getSubdirs(File file) {
		if(file == null || !file.exists())
			return new ArrayList<>();
	    List<File> subdirs = Arrays.asList(file.listFiles(new FileFilter() {
	        public boolean accept(File f) {
	            return f.isDirectory();
	        }
	    }));
	    
	    subdirs = new ArrayList<>(subdirs);
	    
	    List<File> deepSubdirs = new ArrayList<>();
	    for(File subdir : subdirs) {
	        deepSubdirs.addAll(getSubdirs(subdir));  
	    }
	    subdirs.addAll(deepSubdirs);
	    return subdirs;
	}
	
	/**
	 * creates a file/folder given as the input to the function if not exists
	 * @param filePath: path and name of the file to created
	 * @return	boolean:	true iff and only if the file/folder is successfully created
	 * @throws IOException
	 */
	private boolean createFileIfNotPresent(String filePath){
		File file = new File(filePath);
		if(!file.exists()) {
			return file.mkdir();
		}
		return true;
	}
	
	/**
	 * 
	 */
	public void deleteEntries(int sub1, int sub2) {
		this.resultsDao.deleteResults(sub1, sub2);
	}
	
	/**
	 * File result class to temporially store the result of two files.
	 * @author takyon
	 *
	 */
	private class FileResult{
		private String file1;
		private String file2;
		private double result;
		
		public FileResult(String file1, String file2, double result) {
			this.setFile1(file1);
			this.setFile2(file2);
			this.setResult(result);
		}
		
		public String getFile1() {
			return file1;
		}
		public void setFile1(String file1) {
			this.file1 = file1;
		}
		public String getFile2() {
			return file2;
		}
		public void setFile2(String file2) {
			this.file2 = file2;
		}
		public double getResult() {
			return result;
		}
		public void setResult(double result) {
			this.result = result;
		}
	}

}
