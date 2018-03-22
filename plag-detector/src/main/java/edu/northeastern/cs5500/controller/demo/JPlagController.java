package edu.northeastern.cs5500.controller.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/demo")
public class JPlagController {

	
	
	/**
	 * Get all the courses when an user logs in.
	 * @return
	 */
	@GetMapping(value = "/all")
	public String jplagConnection() {
		/*String[] tm = {"/home/ec2-user/assignments","-","/home/ec2-user/results"};
		CommandLineOptions op;
		try {
			op = new CommandLineOptions(tm);
			Program p = new Program(op);
			p.run();
		} catch (ExitException e) {
			e.printStackTrace();
		}*/
		return "Ok";
	}
	
	
}
