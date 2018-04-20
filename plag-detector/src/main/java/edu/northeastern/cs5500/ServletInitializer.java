package edu.northeastern.cs5500;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/** 
 * 
 * @author varunnandu
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {
	
	/** 
	 * Send Mail
	 */

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PlagDetectorApplication.class);
	}

}
