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
	String emailBody;
	

	public void generateAndSendEmail(String[] string, String teamName )
			throws AddressException, MessagingException {

		// String [] string = {"stu70226@gmail.com","x6041500@hotmail.com"};
		for (int i = 0; i <= string.length - 1; i++) {
			
			message = new MimeMessage(session);
			mailServerProperties = System.getProperties();
			session = Session.getDefaultInstance(mailServerProperties, null);

			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(string[i]));
			String emailBody = "<h1>您好:</h1>" + "<br><h2>"+teamName+"邀請您至TwentyWork團隊進行工作。</h2>" + "<br><h2>請點選以下連結開始工作:</h2>"
					+ "<br><h2><a href='http://twentywork.cloudapp.net:8080/TwentyWork/login/registerPersonal.jsp?teamName="+teamName+"&email="+string[i]+"'>進入Twenty Work</a></h2>";
			
			message.setSubject("TwentyWork成員邀請信", "UTF-8");
			message.setContent(emailBody, "text/html;charset=UTF-8");
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", "EEIT82TwentyWork@gmail.com", "TwentyWork20");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		}
		// message.addRecipient(Message.RecipientType.CC, new
		// InternetAddress("x6041500@hotmail.com")); 副本
	}
	
}