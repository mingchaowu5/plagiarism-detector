package edu.northeastern.cs5500.controller.result;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import edu.northeastern.cs5500.models.Assignment.Assignment;
import edu.northeastern.cs5500.models.extras.Graph;

@RestController
@RequestMapping("/rest/result/")
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	/**
	 * Get all the courses when an user logs in.
	 * @return
	 */
	@GetMapping(value = "all")
	public ResponseEntity<Graph> allResult(@RequestParam(value = "id") int id) throws IOException{
		resultService.run(id);
		Graph graph = resultService.getResultForStudent(id);
		return new ResponseEntity<>(graph, HttpStatus.OK);
	}	
}
