package com.iii.twentywork.model.daointerface;

import com.iii.twentywork.model.bean.teamuser.TeamUserBean;
import com.iii.twentywork.model.bean.teamuser.TeamUserIdBean;

public interface TeamUserDAO
{

    TeamUserBean insert(TeamUserBean userBean);

    TeamUserBean select(TeamUserIdBean teamUserId);

}