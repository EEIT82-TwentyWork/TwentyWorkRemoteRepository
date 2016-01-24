package com.iii.twentywork.model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iii.twentywork.model.bean.ShareFileBean;
import com.iii.twentywork.model.bean.TeamBean;

@Component(value = "teamFileDAO")
public class TeamDAOHibernate 
{
    @Autowired
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public Session getSession() {
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
    public TeamDAOHibernate() {}
    
    public TeamBean selectByTeamId(String pk)
    {
    	TeamBean bean = (TeamBean) getSession().get(TeamBean.class, pk);
        return bean;
    }
}
