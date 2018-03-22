package edu.northeastern.cs5500.controller.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileService {

	
	/**
	 * Connect to the database layer and get the semesters
	 * @return
	 */
	public edu.northeastern.cs5500.models.file.File uploadFile(MultipartFile multiPartFile, int student_id, int assignment_id) throws IOException{
		edu.northeastern.cs5500.models.file.File file = new edu.northeastern.cs5500.models.file.File();
		//File convertFile = this.convertMultiPartToFile(multiPartFile);
		String filePath = "/home/ec2-user/files/" + assignment_id + "/" + student_id;
		String fileName = multiPartFile.getOriginalFilename();
	    File newFile = new File(filePath + fileName);
	    OutputStream outputStream = null;
	    InputStream inputStream = null;
	    try {
	        inputStream = multiPartFile.getInputStream();

	        if (!newFile.exists() && !newFile.createNewFile()) {
	            return file;
	        }
	        outputStream = new FileOutputStream(newFile);
	        int read = 0;
	        byte[] bytes = new byte[1024];

	        while ((read = inputStream.read(bytes)) != -1) {
	            outputStream.write(bytes, 0, read);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }finally {
	    		inputStream.close();
	    		outputStream.close();
	    }
	    //return newFile.getAbsolutePath();
		return file;
	}
	
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = null;
		try {	
	        fos = new FileOutputStream(convFile);
	        fos.write(file.getBytes());
	        fos.close();
	    }catch(IOException e) {
	    		fos.close();
	    }
        return convFile;
    }
	
}
