package com.iii.twentywork.model.service.websocket;


import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
@ServerEndpoint(value="/com/iii/twentywork/model/service/websocket/ChatroomServerEndpoint/{chatroom}",configurator=ChatroomServerConfigurator.class)
public class ChatroomServerEndpoint {
	static Set<Session> chatroomUsers= Collections.synchronizedSet(new HashSet<Session>());
	@OnOpen
	public void handleOpen(EndpointConfig config, Session usersession,@PathParam("chatroom")String chatroom){
		
		System.out.println("Open connection");
		String ownName=(String)config.getUserProperties().get("chatOwnName");
		usersession.getUserProperties().put("ownName", ownName);
		
		chatroomUsers.add(usersession);
	
		Iterator<Session>  iterator =chatroomUsers.iterator();
		
	}	
	@OnMessage
	public void handleMessage(String message,Session usersession)throws IOException{
		Iterator<Session>  iterator =chatroomUsers.iterator();
		String usersName=(String)usersession.getUserProperties().get("ownName");
		while(iterator.hasNext()){
		iterator.next().getBasicRemote().sendText(buildJsonMessageData(usersName, message));
	}
		System.out.println(message);
		System.out.println(buildJsonMessageData(usersName, message));
	}
	@OnClose
	public void handleClose(Session userSession){
		System.out.println("close connection");
	}
	@OnError
	public void handlError(Throwable t){
		
	}
//	private String convertGSON(String usersName,String message){
//		Gson gson =new GsonBuilder().create();
//		List objIII =new ArrayList<>();
//		objIII.set(0, usersName);
//		objIII.set(1, message);
//		String result= gson.toJson(objIII);
//		
//		return result;
//	}

	private String buildJsonMessageData(String username, String message){
		JsonObject jsonobject= Json.createObjectBuilder().add("message", username+":"+message).build();
		StringWriter stringWriter =new StringWriter();
		try(JsonWriter jsonwriter =Json.createWriter(stringWriter)){jsonwriter.write(jsonobject);}
		return stringWriter.toString();
	}
}

