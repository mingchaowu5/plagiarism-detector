package edu.northeastern.cs5500.mail;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailClient {
  
    @Autowired
    public JavaMailSender emailSender;
 
    private Logger log;

    public void sendSimpleMessage(String to, String subject, String text) {
    		log = Logger.getAnonymousLogger();
    		log.log(Level.INFO, "Sending Mail Here");
    		SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}