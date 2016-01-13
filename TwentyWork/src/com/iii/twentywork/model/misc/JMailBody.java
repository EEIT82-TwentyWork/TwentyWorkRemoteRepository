package com.iii.twentywork.model.misc;

import org.springframework.stereotype.Component;

@Component("jMailBody")
public class JMailBody {
	public String email(String team) {
		String emailBody = "<h1>您好:</h1>" + "<br><h2>"+team+"邀請您至TwentyWork團隊進行工作。</h2>" + "<br><h2>請點選以下連結開始工作:</h2>"
				+ "<br><h2><a href='http://localhost:8080/TwentyWork/login/registerPersonal.jsp?teamName="+team+"'>進入Twenty Work</a></h2>";
		return emailBody;
	}
}
