package edu.northeastern.cs5500.mail;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

public class SendMail  {

	private Logger log;
	@Autowired
	private JavaMailSender javaMailSender;
	
	@PostConstruct
	public void sendMail() {
		String to = "varunnandu1992@gmail.com";
		String body = "Yee toh Chal Gaya!!";
		log = Logger.getAnonymousLogger();
	    log.log(Level.INFO, "Sending email...");
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(to);
	    message.setFrom("xxx@gmail.com");
	    message.setSubject("Confirm appointment");
	    message.setText(body);
	    javaMailSender.send(message);
	    log.log(Level.INFO, "Email Sent!");
	}
}
