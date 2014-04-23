package com.namoo.social.domain;

import java.util.List;

public class User {
	//
	private String usedId;
	private String name;
	private String email;
	private String password;
	private List<Message> messages;
	
	// 관계
	private List<User> followings;
	private List<User> followers;
	
	//--------------------------------------------------------------------------
	
	public String getUsedId() {
		return usedId;
	}
	public void setUsedId(String usedId) {
		this.usedId = usedId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public List<User> getFollowings() {
		return followings;
	}
	public void setFollowings(List<User> followings) {
		this.followings = followings;
	}
	public List<User> getFollowers() {
		return followers;
	}
	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	//--------------------------------------------------------------------------
	
}

/*
 sql문:
특정 사용자에 대한 팔로윙 목록 조회
SELECT whom FROM usertouser_tb WHERE who="aaa";

특정 사용자에 대한 팔로워 목록 조회
SELECT who FROM usertouser_tb WHERE whom="aaa";

특정 사용자가 작성한 글 목록
SELECT contents FROM message_tb WHERE userID="aaa";

타임라인 조회(특정 사용자와 팔로윙 사용자가 작성한 모든 글 조회)
SELECT contents FROM message_tb WHERE userID="aaa" OR userID=(SELECT whom FROM usertouser_tb WHERE who="aaa");
*/