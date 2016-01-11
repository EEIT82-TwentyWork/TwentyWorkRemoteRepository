package com.iii.twentywork.model.service.invite;
// 設定/取消 兩階段 https://www.google.com/settings/security/lesssecureapps
// 取得Gmail兩階段Authentication的密碼: https://security.google.com/settings/security/apppasswords
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component("jMail")
public class JMail {

	
	static Properties mailServerProperties;
	static Session session;
	static MimeMessage message;
 
	
//	public static void main(String args[]) throws AddressException, MessagingException {
//		System.out.println("進入main準備sendEmail");
//		generateAndSendEmail();
//		System.out.println("\n\n ===> Your Java Program has just sent an Email successfully. Check your email..");
//	}

	
	public static void generateAndSendEmail(String [] string) throws AddressException, MessagingException {
		// TLS
		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");
 
		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		session = Session.getDefaultInstance(mailServerProperties, null);
		message = new MimeMessage(session);
//		String [] string = {"stu70226@gmail.com","x6041500@hotmail.com"};
		for(int i =0 ;i<=string.length-1;i++){
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(string[i]));
		}
		message.addRecipient(Message.RecipientType.CC, new InternetAddress("x6041500@hotmail.com"));
		message.setSubject("TwentyWork成員邀請信", "UTF-8");
//		Multipart mp = new MimeMultipart();
//		MimeBodyPart mbp = new MimeBodyPart();
//		mbp.setContent(message,"text/html;charset=UTF-8");
		String emailBody = "<h1>您好，XXX:</h1>" + 
				"<br><h2>OOO邀請您至TwentyWork團隊進行工作。</h2>" +
				"<br><h2>請點選以下連結開始工作:</h2>" +
				"<br><h2><a href='http://localhost:8080/TwentyWork/main/workHome/main.jsp'>進入Twenty Work</a></h2>";
//		"<br><font color='red' Regards, </font><br>OOO邀請您至TwentyWork團隊進行工作。<br>請點選以下連結開始工作";
		message.setContent(emailBody, "text/html;charset=UTF-8");
		System.out.println("Mail Session has been created successfully..");
 
		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = session.getTransport("smtp");
		System.out.println("session.getTransport結束");
		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		transport.connect("smtp.gmail.com", "EEIT82TwentyWork@gmail.com", "TwentyWork20");
		System.out.println("transport.connect結束");
		transport.sendMessage(message, message.getAllRecipients());
		System.out.println("transport.sendMessage結束");
		transport.close();
		System.out.println("transport.close結束");
	}
}