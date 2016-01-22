package com.iii.twentywork.model.service.sketch;



import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class SketchMessageEncoder implements Encoder.Text<SketchMessage> {

	@Override
	public void destroy() {
	}
	@Override
	public void init(EndpointConfig arg0) {
	}

	@Override
	public String encode(SketchMessage sketchMessage) throws EncodeException {
		// TODO Auto-generated method stub
		return Json.createObjectBuilder().add("x", sketchMessage.getX())
										 .add("y", sketchMessage.getY())
										 .add("size", sketchMessage.getSize())
										 .add("color", sketchMessage.getColor())
										 .build().toString();
				
	}
	

}
