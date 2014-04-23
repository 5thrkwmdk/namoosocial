package com.namoo.social.dao;

import java.util.List;

import com.namoo.social.domain.Message;

public interface MessageDao {
	//CRUD
	List<Message> readAllMessage();
	Message readMessage(int messsageID);
	int insertMessage(Message message);
	void updateMessage(Message message);
	void deleteMessage(int messageID);
}
