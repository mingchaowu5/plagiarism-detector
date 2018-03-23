package edu.northeastern.cs5500.controller.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.northeastern.cs5500.models.file.FileStructure;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;


@Service
public class FileService {
	
	private String fixedPath = "/Users/takyon/Documents/homework2/assignment/";
	private String path = null;
	
	public FileStructure uploadFile(MultipartFile file, int s_id, int a_id) throws IOException{
		path = fixedPath;
		this.createFolder(path);
		path += a_id + "/";
		this.createFolder(path);
		path += s_id + "/";
		this.createFolder(path);
		if(!file.isEmpty()) {
			return copyFile(file);
		}
		return null;
	}
	
	private FileStructure copyFile(MultipartFile file) throws IOException{
		FileStructure structure = null;
		String fileName = file.getOriginalFilename();
        InputStream is = file.getInputStream();
        Files.copy(is, Paths.get(path + fileName),
                StandardCopyOption.REPLACE_EXISTING);
        extractZip(fileName);
        deleteZip(fileName);
        structure = new FileStructure();
        structure.setFile(fileName);
		return structure;
	}
	
	private void deleteZip(String fileName) throws IOException{
		File file = new File(path + fileName);
        if(file.exists()) {
        		file.delete();
        }
	}
	
	private void extractZip(String fileName) {
		String source  = path + fileName;
		String destination = path ;  

		try {
			ZipFile zipFile = new ZipFile(source);
			zipFile.extractAll(destination);
			
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
	
	private void createFolder(String folder) throws IOException{
		File file = new File(folder);
		if(!file.exists()) {
			file.mkdir();
		}
	}
	
	
}
