package com.iii.twentywork.model.service.websocket;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

//@ServerEndpoint("/com/iii/twentywork/model/service/websocket/TestEndpoint"+"/40289ff9523e0ad201523e0c86e6000040289ff9523e0ad201523e0e3e760002"+"'")

public class TestEndpoint {
	@OnOpen
	public void handleOpen() {
		
		System.out.println("client is now connected");
	}

	@OnMessage
	public String handleMessage(String message) {
		System.out.println("receive from client" + message);
		String replyMessage = "echo" + message;
		System.out.println("send to client" + replyMessage);
		return replyMessage;
	}

	@OnClose
	public void handleClose() {
		System.out.println("client is now disconnected");
	}

	@OnError
	public void handleError(Throwable t) {
		System.out.println("this error");
		t.printStackTrace();
	}

}
