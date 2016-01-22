package com.iii.twentywork.model.service.websocket;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import com.iii.twentywork.model.bean.UsersBean;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;
public class ChatroomServerConfigurator extends ServerEndpointConfig.Configurator {
	
	public void modifyHandshake(ServerEndpointConfig sec ,HandshakeRequest request,HandshakeResponse response){
		
		sec.getUserProperties().put("chatID",(String)((HttpSession)request.getHttpSession()).getAttribute("chatID"));
		String chatID=(String)sec.getUserProperties().put("chatID",(String)((HttpSession)request.getHttpSession()).getAttribute("chatID"));
		
		sec.getUserProperties().put("chatOwnName",(String)((HttpSession)request.getHttpSession()).getAttribute("chatOwnName"));
		String ownID=(String)sec.getUserProperties().put("chatOwnName",(String)((HttpSession)request.getHttpSession()).getAttribute("chatOwnName"));
		System.out.println("設定檔抓到的ownID:"+ownID);
	
	}
}
