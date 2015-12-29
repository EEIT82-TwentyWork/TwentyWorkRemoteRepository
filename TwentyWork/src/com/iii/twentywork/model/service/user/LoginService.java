package com.iii.twentywork.model.service.user;

import java.util.Arrays;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.iii.twentywork.model.bean.TeamUserBean;
import com.iii.twentywork.model.bean.TeamUserIdBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.daointerface.TeamUserDAO;
import com.iii.twentywork.model.daointerface.UserDAO;

@Component(value = "loginService")
public class LoginService {
	@Autowired
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
		System.out.println("LoginService setUserDAO");
	}

	@Autowired
    private TeamUserDAO teamUserDAO;

    public void setTeamUserDAO(TeamUserDAO teamUserDAO) {
        this.teamUserDAO = teamUserDAO;
        System.out.println("LoginService setTeamUserDAO");
    }
	
	public UsersBean login(int userID, String password) 
	{//testing#1
		System.out.println("LoginService 進入login");
		UsersBean bean = userDAO.select(userID);
		System.out.println("LoginService userDAO.select結束");
		System.out.println(bean);
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

	public TeamUserBean loginTeam(int userID,int groupID) 
	{//testing#2
	    TeamUserBean bean =teamUserDAO.select(new TeamUserIdBean(userID,groupID)) ;
	    System.out.println("LoginService loginTeam");
	    if(bean != null) {
	        return bean;
	    }else {
	    return null;
	    }
	}
	
	public static void main(String[] args) {
	    ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
        SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
        Session session = sessionFactory.getCurrentSession();
        sessionFactory.getCurrentSession().beginTransaction();
        
//        //testing#1
//        LoginService service = (LoginService) context.getBean("loginService");
//        UsersBean bean= service.login(100,"A");
//        System.out.println(bean);
        
//        //testing#2
//        LoginService service = (LoginService) context.getBean("loginService");
//        TeamUserBean bean= service.loginTeam(100,200);
//        //TeamUser [id=TeamUserId [userId=100, teamId=200], team=Team [teamId=200, teamName=KukuCoding], users=UsersBean [userID=100, userName=Kirin, email=x6041500@hotmail.com, password=[65], birth=1990-05-13 00:00:00.0, cellPhone=0933-456-781, phone=03-4567891], post=null, department=null, extension=null, activeDate=2015-12-25, rights=1]
//        System.out.println(bean);
	}

}
