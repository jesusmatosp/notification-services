package com.ibm.garage.loginsecurityservices.utils;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	
	public static void sendEmailGmailAccount(String emailTo, boolean iscc, List<String> emailCC, String emailBody,
			String subject) throws AddressException, MessagingException {
		System.out.println("\n 1st=> Setup Mail Server Properties ");
		
		// Google
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");

		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
		if (iscc) {
			for (String data : emailCC) {
				generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(data));
				// generateMailMessage.addRecipient(Message.RecipientType.CC,
				// new InternetAddress(emailCC));
			}
		}
		generateMailMessage.setSubject(subject);
		// String emailBody = "Test email by Crunchify.com JavaMail API example.
		// " + "<br><br> Regards, <br>Crunchify Admin";
		generateMailMessage.setContent(emailBody, "text/html");
		System.out.println("Mail Session has been created successfully..");
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = getMailSession.getTransport("smtp");
		transport.connect("smtp.gmail.com",
				"jesus.matosp@gmail.com",
				"S@nd@Ch1n3s32018");
		transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		transport.close();
	}
	
	
}
