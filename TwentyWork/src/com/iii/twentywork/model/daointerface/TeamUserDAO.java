package com.iii.twentywork.model.daointerface;
import com.iii.twentywork.model.bean.TeamUserBean;
import com.iii.twentywork.model.bean.TeamUserIdBean;

public interface TeamUserDAO
{

    TeamUserBean insert(TeamUserBean userBean);

    TeamUserBean select(TeamUserIdBean teamUserId);

}
