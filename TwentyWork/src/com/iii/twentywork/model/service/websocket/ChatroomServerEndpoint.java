package com.iii.twentywork.model.service.websocket;



import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.json.Json;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

@ServerEndpoint(value="/contorller/ChatroomServerEndpoint/{chatroom}",configurator=ChatroomServerConfigurator.class)
public class ChatroomServerEndpoint {
	static Map<String,Set<Session>> chatrooms=(Map<String,Set<Session>>)java.util.Collections.synchronizedMap(new HashMap<String,Set<Session>>());
	public Set<Session> getChatroom(String chatroomName){
		Set<Session> chatroom=chatrooms.get(chatroomName);
		if(chatroom==null){
			chatroom=java.util.Collections.synchronizedSet(new HashSet<Session>());
			chatrooms.put(chatroomName, chatroom);
		}
		return chatroom;
	}
	
	@OnOpen
	public void handleOpen(EndpointConfig config, Session userSession,@PathParam("chatroom")String chatroom){
		System.out.println("Open connection");
		userSession.getUserProperties().put("username",config.getUserProperties().get("username"));
		userSession.getUserProperties().put("chatroom", chatroom);
		Set<Session> chatroomUsers=getChatroom(chatroom);
		chatroomUsers.add(userSession);
	}
	@OnMessage
	public void handleMessage(String message,Session userSession){
		System.out.println("on message");
		System.out.println("userSession.getId()="+userSession.getId());
		String username=(String)userSession.getUserProperties().get("username");
		String chatroom=(String)userSession.getUserProperties().get("chatroom");
		Set<Session> chatroomUsers =getChatroom(chatroom);
		chatroomUsers.stream().forEach(x->{
			try{
//				x.getBasicRemote().sendText(buildJsonData(username,message));
			}catch(Exception e){
				e.printStackTrace();
			}
		});
	}
	@OnClose
	public void handleClose(Session userSession){
		System.out.println("close connection");
		String chatroom=(String)userSession.getUserProperties().get("chatroom");
		Set<Session>chatroomUsers =getChatroom(chatroom);
		chatroomUsers.remove(userSession);
	}
	@OnError
	public void handlError(Throwable t){
	}
//	private String buildJsonData(String username,String message){
//		JsonObject jsonObject =Json.createObjectBuilder().add("message", username+":"+message).build();
//		StringWriter stringWriter =new StringWriter();
//		try(JsonWriter jsonWriter=Json.createWriter(stringWriter)){
//			jsonWriter.write(jsonObject);
//		}
//		return stringWriter.toString();
//	}
}

