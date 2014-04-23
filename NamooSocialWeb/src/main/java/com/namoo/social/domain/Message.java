package com.namoo.social.domain;

public class Message {
	//
	private String contents;
	private User writer;

	//--------------------------------------------------------------------------

	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}

}


