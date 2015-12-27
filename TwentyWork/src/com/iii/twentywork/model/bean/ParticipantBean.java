package com.iii.twentywork.model.bean;

public class ParticipantBean {
	private int ParticipantID;

	@Override
	public String toString() {
		return "ParticipantBean [ParticipantID=" + ParticipantID + "]";
	}

	public int getParticipantID() {
		return ParticipantID;
	}

	public void setParticipantID(int participantID) {
		ParticipantID = participantID;
	}
	
}
