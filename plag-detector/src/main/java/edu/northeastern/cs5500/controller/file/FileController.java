package edu.northeastern.cs5500.controller.file;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import edu.northeastern.cs5500.models.file.File;

@RestController
@RequestMapping("/rest/file")
public class FileController {

	@Autowired
	private FileService fileService;
	
	/**
	 * Get all the courses when an user logs in.
	 * @return
	 */
	@PostMapping(value = "/uploadFile")
	public ResponseEntity<File> uploadFile(@RequestPart(value = "file") MultipartFile multiPartFile, 
			@RequestPart(value = "s_id") int student_id, @RequestPart(value = "a_id") int assignment_id) {
		File file = null;
		try {
			file = this.fileService.uploadFile(multiPartFile, student_id, assignment_id);
		}catch(IOException e) {
			
		}
		return new ResponseEntity<>(file, HttpStatus.OK);
	}
	
	
}
