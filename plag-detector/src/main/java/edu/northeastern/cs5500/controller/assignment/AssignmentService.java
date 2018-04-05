package edu.northeastern.cs5500.controller.assignment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import edu.northeastern.cs5500.Constants;
import edu.northeastern.cs5500.dao.AssignmentDao;
import edu.northeastern.cs5500.dao.SubmissionDao;
import edu.northeastern.cs5500.models.assignment.Assignment;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

/**
 * Assignment Service class,
 * makes several calls to the Data Layer to fetch or insert the assignment data
 * @author takyon
 *
 */
@Service
public class AssignmentService {

	@Autowired
	private AssignmentDao assignmentDao;
	
	@Autowired
	private SubmissionDao submissionDao;
	
	
	/**
	 * Connect to the database layer and get all the assignments
	 * @return	List:	of all the assignments in the database
	 */
	public List<Assignment> getAllAssignments(){
		return this.assignmentDao.findAllAssignments();
	}
	
	/**
	 * Connect to the database layer and get all the assignments for a 
	 * particular course
	 * @param	id:	of a particular course
	 * @return	List:	of all the assignments in the database for a course
	 */
	public List<Assignment> getAllAssignmentsForCourse(int id){
		return this.assignmentDao.findAllAssignmentsForCourse(id);
	}
	
	/**
	 * Delete a particular assignment from the system
	 * @param	id:	of the assignment to be deleted
	 * @return	boolean:	true if and only iff the assignment is successfully deleted
	 */
	public boolean deleteAssignment(int id) {
		return assignmentDao.deleteAssignment(id) == 1 ? true:false;
	}
	
	/**
	 * Insert a new assignment in the system
	 * @param	courseId:	of the course
	 * 			name:	of the assignment
	 * 			languageId:	is the id of the language for this assignment
	 * @return	boolean:	true if and only iff the assignment is successfully inserted
	 */
	public boolean insertAssignment(String name, int courseId) {
		return assignmentDao.addAssignment(name, courseId) == 1 ? true:false;
	}
	
	/**
	 * Update an assignment in the system
	 * @param	assignmentId: of the assignment to be updated
	 * 			courseId:	of the assignment
	 * 			name:	of the assignment
	 * 			languageId:	is the id of the language for this assignment
	 * @return	boolean:	true if and only iff the assignment is successfully updated
	 */
	public boolean updateAssignment(int assignmentId, String name, int courseId) {
		return assignmentDao.updateAssignment(assignmentId, name, courseId) == 1 ? true:false;
	}
	
	/**
	 * Copy the input file to the path on the instance
	 * @param file:	is input file given by the user
	 * @param studentId:	id the id of the student uploading the file
	 * @param assignmentId: id of the assignment uploading the file
	 * @return	boolean:	true iff and only if the file is successfully saved and 
	 * 					extracted(incase of .zip) from on the instance
	 */
	public boolean uploadFile(MultipartFile file, int studentId, int assignmentId) {
		int submissionId = this.submissionDao.addSubmission(assignmentId, studentId, Constants.getCurrentDate());
		StringBuilder build = new StringBuilder(Constants.ASSIGNMENTURL);
		try {
			/**Return false if the input file is empty or creating the folder structure fails**/
			if(file.isEmpty() || !this.createFileIfNotPresent(build.toString())
					|| !this.createFileIfNotPresent(build.append(submissionId).append("/").toString())) 
				return false;
			/**Copy the file to the instance**/
			copyFile(file, build.toString());
		}catch(Exception e) {
			return false;
		}
		return true;
	}	

	/**
	 * creates a file/folder given as the input to the function if not exists
	 * @param filePath: path and name of the file to created
	 * @return	boolean:	true iff and only if the file/folder is successfully created
	 * @throws IOException
	 */
	private boolean createFileIfNotPresent(String filePath) throws IOException{
		File file = new File(filePath);
		if(!file.exists()) {
			return file.mkdir();
			//return file.createNewFile();
		}
		return true;
	}
	
	/**
	 * copy the file to the given path
	 * @param file:	to be copied, given as the input by the user
	 * @param path:	path where the file is to be copied
	 * @throws IOException
	 * @throws ZipException
	 */
	private void copyFile(MultipartFile file, String path) throws IOException, ZipException{
		String filename = file.getOriginalFilename();
        InputStream is = file.getInputStream();
        /**Copy the file to the path on the instance**/
        Files.copy(is, Paths.get(path + filename),
                StandardCopyOption.REPLACE_EXISTING);
        /**Extract and delete only if the input file is a zip**/
        		extractZip(filename, path);
        		deleteZip(filename, path);
	}

	/**
	 * delete the zip file once successfully extracted on the instance
	 * @param filename: name of the zip to be deleted
	 * @param path: where the zip is present
	 */
	private void deleteZip(String filename, String path) {
		File file = new File(path + filename);
        if(file.exists()) {
        		file.delete();
        }
	}
	
	/**
	 * extract the zip file on the instance
	 * @param filename: name of the zip to be extracted
	 * @param path: where the zip is be extracted 
	 */
	private void extractZip(String filename, String path) throws ZipException{
		String source  = path + filename;
		String destination = path ;  
		ZipFile zipFile = new ZipFile(source);
		zipFile.extractAll(destination);	
	}
	
	/**
	 * Insert into queue for the plag detector to calculate the plag for new student
	 * @param studentId:	is the id of student who just uploaded files for the assignment
	 * @param assignmentId: is the id of the assignment which was just uploaded.
	 */
	public void insertIntoQueue(int studentId, int assignmentId) {
		return;
	}
}
