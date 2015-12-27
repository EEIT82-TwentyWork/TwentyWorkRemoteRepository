package com.iii.twentywork.model.service.user;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.users.UsersBean;
import com.iii.twentywork.model.daointerface.UserDAO;

@Component(value = "loginService")
public class LoginService {
	@Autowired
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
		System.out.println("setUserDAO");
	}

	public UsersBean login(int userID, String password) {
		System.out.println("進入login");
		UsersBean bean = userDAO.select(userID);
		System.out.println("userDAO.select結束");
		if (bean != null) {
			if (password != null && password.length() != 0) {
				byte[] pass = bean.getPassword(); // 資料庫抓出的密碼
				byte[] temp = password.getBytes(); // 使用者輸入的密碼
				if (Arrays.equals(pass, temp)) {
					return bean;
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {

	}

}
