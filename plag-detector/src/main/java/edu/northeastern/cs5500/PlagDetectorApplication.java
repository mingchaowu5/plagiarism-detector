package edu.northeastern.cs5500;

import java.util.List;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration(exclude = {JpaRepositoriesAutoConfiguration.class})
@SpringBootApplication
@EnableScheduling
public class PlagDetectorApplication extends SpringBootServletInitializer{
	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	  return application.sources(PlagDetectorApplication.class);
	 }

	public static void main(String[] args) {
		SpringApplication.run(PlagDetectorApplication.class, args);
	}
	
	/*@Bean
	 public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(465);

        javaMailSender.setJavaMailProperties(getMailProperties());
        javaMailSender.setUsername("team204cs5500@gmail.com ");
        javaMailSender.setPassword("YTREWQ");

        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.ssl.enable","true");
        properties.setProperty("mail.test-connection","true");
        return properties;
    }*/
}
