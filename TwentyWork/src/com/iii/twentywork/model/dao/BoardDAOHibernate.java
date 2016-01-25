package com.iii.twentywork.model.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.Board;
import com.iii.twentywork.model.bean.Sub;
import com.iii.twentywork.model.bean.UsersBean;

@Component("boardDAO")
public class BoardDAOHibernate 
{
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	
	public BoardDAOHibernate() {}
	
	//testing#1
	public Board selectByID(String id){
		Board bean = (Board) getSession().get(Board.class, id);
		return bean;
	}
	//testing#2
	private static final String SELECT_BY_TEAMID = "Select * from Board where teamID =?"; 
	public List<Board> selectByTeamID(String teamID){
		List<Board> list= new ArrayList<Board>();
		SQLQuery query = getSession().createSQLQuery(SELECT_BY_TEAMID);
		query.setParameter(0, teamID);
		query.addEntity(Board.class);
		return query.list();
	}
	
	//testing#3
	public Board insert(Board bean) {
	    String pk = (String) getSession().save(bean);
	    return selectByID(pk);
	}

	public Sub insertSub(Sub bean){
		String pk = (String) getSession().save(bean);
		return selectSubByID(pk);
	}
	public Sub selectSubByID(String id){
		Sub bean = (Sub) getSession().get(Sub.class, id);
		return bean;
	}
	
	/**
	 * 更新Board的發言人及發佈時間
	 *
	 */
	public Board updateBoardInfo(String boardId,UsersBean user){
		Board updated =(Board) getSession().get(Board.class,boardId);
		updated.setBoardTime(new Date());
		updated.setUsers(user);
		return updated;
	}
	
	
	
	
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory =(SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().beginTransaction();
		
		//testing#1
//		BoardDAOHibernate dao = (BoardDAOHibernate) context.getBean("boardDAO");
//		System.out.println(dao.selectByID("451763144AAC419B9C59D333943531CE"));
		
		
		//testing#2
//		BoardDAOHibernate dao = (BoardDAOHibernate) context.getBean("boardDAO");
//		System.out.println(dao.selectByTeamID("51D5D2C16DE341CAA69FE1849AF6CE78"));
		
		//testing#3
      BoardDAOHibernate dao = (BoardDAOHibernate) context.getBean("boardDAO");
      Board bean = dao.selectByID("9DEF0E515FE84F7CA7338AEBE828C19D");
      Board bean2 = new Board();
      bean2.setBoardId("7f3acaea97ab4847bf795b153a0becc2");
      bean2.setBoardText("現在12點了");
      bean2.setBoardTitle("明天要上班嗎");
      bean2.setBoardTime(bean.getBoardTime());
      bean2.setUsers(bean.getUsers());
      bean2.setTeam(bean.getTeam());
      dao.insert( bean2);
		
		sessionFactory.getCurrentSession().getTransaction().commit();
	}

}
