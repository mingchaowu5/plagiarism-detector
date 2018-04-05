package edu.northeastern.cs5500.controller.result;

import org.springframework.stereotype.Service;

@Service
public class ResultsService {
	
	private int sub1;
	private int sub2;
	
	public void setSubmissionIds(int sub1, int sub2) {
		this.sub1 = sub1;
		this.sub2 = sub2;
	}
	
	public void findResults() {
		
	}
	

}
