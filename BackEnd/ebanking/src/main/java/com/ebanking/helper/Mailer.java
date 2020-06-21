package com.ebanking.helper;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Mailer {
	
	@Autowired
	JavaMailSender mailSender;
	
	public void send(String to, String subject, String content) {
		String from = "phamhung.pitit@gmail.com";
		this.send(from, to, subject, content);
	}
	
	public void send(String from, String to, String subject, String content) {
		this.send(from, to, null, null, subject, content, null);
	}
	
	public void send(String from, String to, String cc, String bcc, String subject, String content, String files) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			helper.setReplyTo(from, from);
			
			//CC & BCC
			if(cc != null && cc.length() > 0) {
				helper.setCc(cc.split("[,; ]+"));
			}
			
			if(bcc != null && bcc.length() > 0) {
				helper.setBcc(bcc.split("[,; ]+"));
			}
			
			// Attach files
			if(files != null && files.length() > 0) {
				String[] paths = files.split("[,; ]+");
				for(String path : paths) {
					File newFile = new File(path);
					helper.addAttachment(newFile.getName(), newFile);
				}
			}
			
			// Gửi thư
			mailSender.send(message);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}