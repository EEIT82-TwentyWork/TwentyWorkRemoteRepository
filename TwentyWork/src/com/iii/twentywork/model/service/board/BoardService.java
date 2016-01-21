package com.iii.twentywork.model.service.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.Board;
import com.iii.twentywork.model.bean.Sub;
import com.iii.twentywork.model.bean.TeamBean;
import com.iii.twentywork.model.bean.UsersBean;
import com.iii.twentywork.model.dao.BoardDAOHibernate;

@Component("boardService")
public class BoardService {
	
	@Autowired
	private BoardDAOHibernate boardDAO;
	public BoardDAOHibernate getBoardDAO() {return boardDAO;}
	public void setBoardDAO(BoardDAOHibernate boardDAO) {this.boardDAO = boardDAO;}

	
	public BoardService() {
	}
	
	//testing#1
	public List<Board> boardList(String teamId){
		List<Board> list = boardDAO.selectByTeamID(teamId);
//		System.out.println("排序前");
//		for(Board e:list){
//			System.out.println(e);
//		}
		Collections.sort(list,new Comparator<Board>(){
			public int compare(Board o1, Board o2){
				return -o1.getBoardTime().compareTo(o2.getBoardTime());
			}
		});
//		System.out.println("排序後");
//		for(Board e:list){
//			System.out.println(e);
//		}
		return list;
	}
	
	//web testing pass
	public void insert(TeamBean team,UsersBean user,String boardTitle,String boardText) {
	    Board bean = new Board();
	    bean.setBoardTitle(boardTitle);
	    bean.setBoardText(boardText);
	    bean.setBoardTime(new Date());
	    bean.setUsers(user);
	    bean.setTeam(team);
	    boardDAO.insert(bean);
	    
	}
//	boardID 	boardTitle	boardText	boardTime	userID	teamID	boardClassID
	
	//testing#3
	//排序:新的在最後
	public List<Sub> getSubList(String boardID){
	   Board boardBean= boardDAO.selectByID(boardID);
	   List<Sub> list = new ArrayList<Sub>(boardBean.getSubs());
	   Collections.sort(list,new Comparator<Sub>() {
	       public int compare(Sub o1, Sub o2){
               return o1.getSubTime().compareTo(o2.getSubTime());
           }
	   });
//	   System.out.println("BoardService--getSubList:  "+list);
	   return list;
	}
	
	//testing#3
	public Board getBoardBean(String id) {
	    return boardDAO.selectByID(id);
	}
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.config.xml");
		SessionFactory sessionFactory =(SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().beginTransaction();
		
		//testing#1
//		BoardService service = (BoardService) context.getBean("boardService");
//		service.boardList("C29037CA374D416AAADF6861D119356B");
		
		//testing#2
//		BoardService service = (BoardService) context.getBean("boardService");
//		BoardDAOHibernate dao = (BoardDAOHibernate) context.getBean("boardDAO");
//		Board bean = dao.selectByID("9DEF0E515FE84F7CA7338AEBE828C19D");
//		service.insert(bean.getTeam(), bean.getUsers(), "hello world", "hello kiki");
	      
		//testing#3
		BoardService service = (BoardService) context.getBean("boardService");
		List<Sub> list =  service.getSubList("B704C70DF9AD45258EBAF07A5FACE9F5");
		Board board =  service.getBoardBean("B704C70DF9AD45258EBAF07A5FACE9F5");
		System.out.println("BoardService -- main --board:"+board);
		System.out.println("BoardService -- main --list:"+list);
		
		sessionFactory.getCurrentSession().getTransaction().commit();

	}

    public String listConver2JSON(List<Board> list){
        List<Map<String, Object>> back = new ArrayList<Map<String, Object>>();
        for (int i=0;i<list.size();i++){
            Board bean = list.get(i);
            Map<String,Object> e = new HashMap<String,Object>();
//          (boardID,boardTitle,boardText,boardTime,userID,teamID,boardClassID)
            e.put("boardID",bean.getBoardId());
            e.put("boardTitle",bean.getBoardTitle());
            e.put("boardText",bean.getBoardText());
            e.put("boardTime",bean.getBoardTime());
            e.put("userName",bean.getUsers().getUserName());
            e.put("userImage",bean.getUsers().getUserImage());
            back.add(e);
        }
        String jsonString = JSONValue.toJSONString(back); 
        System.out.println(jsonString);
        return jsonString;
    }
    
}
