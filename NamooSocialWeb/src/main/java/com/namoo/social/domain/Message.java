package com.namoo.social.domain;

import java.util.Date;

public class Message {
	//
	private int messageID;
	private String contents;
	private User writerID;
	private Date reg_dt;
	
	//--------------------------------------------------------------------------
	public Message() {
		//
	}
	
	public Message(int messageID, String contents, User writerID, Date reg_dt) {
		this.messageID = messageID;
		this.contents = contents;
		this.writerID = writerID;
		this.reg_dt = reg_dt;
	}
	//--------------------------------------------------------------------------

	public void setMessageID(int messageID) {
		this.messageID = messageID;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getContents() {
		return contents;
	}
	public int getMessageID() {
		return messageID;
	}
	public User getWriterID() {
		return writerID;
	}
	public void setWriterID(User writerID) {
		this.writerID = writerID;
	}
	public Date getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	

}


