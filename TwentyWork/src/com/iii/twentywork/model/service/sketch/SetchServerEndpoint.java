package com.iii.twentywork.model.service.sketch;



import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/com/iii/twentywork/model/service/sketch/SetchServerEndpoint", encoders ={SketchMessageEncoder.class},decoders={ SketchMessageDecoder.class} )
public class SetchServerEndpoint {
	static Set<Session> sketchusers =Collections.synchronizedSet(new HashSet<Session>());
	@OnOpen
	public void handleOpen(Session userSession){
		sketchusers.add(userSession);
		System.out.println("開始上線");
	}
	@OnMessage
	public void dhandleMessage(SketchMessage incomingSketchMessge,Session userSession) throws IOException, EncodeException{
		SketchMessage outgoingSketchMessage =new SketchMessage();
		outgoingSketchMessage.setX(incomingSketchMessge.getX());
		outgoingSketchMessage.setY(incomingSketchMessge.getY());
		outgoingSketchMessage.setSize(incomingSketchMessge.getSize());
		outgoingSketchMessage.setColor(incomingSketchMessge.getColor());
		Iterator<Session> iterator =sketchusers.iterator();
		Session tempSession =null;
		while(iterator.hasNext()){
			tempSession =iterator.next();
			if(!tempSession.equals(userSession)){
				tempSession.getBasicRemote().sendObject(outgoingSketchMessage);
			}
		}
	}
	@OnClose
	public void handleClose(Session UserSession){
		sketchusers.remove(UserSession);
	}
	@OnError
	public void handleError(Throwable t){
		System.out.println(t);
	}
}
