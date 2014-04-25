package com.namoo.social.dao;

import java.util.List;

import com.namoo.social.domain.Message;
import com.namoo.social.domain.User;

public interface MessageDao {
	//CRUD
	/**
	 * 모든 메시지목록 조회
	 * @return
	 */
	List<Message> readAllMessages(String writerID);
	
	/**
	 * 특정 메시지 조회
	 * @param messageID
	 * @return
	 */
	Message readMessage(int messageID);
	
	/**
	 * 메시지 생성
	 * @param message
	 * @return
	 */
	int insertMessage(Message message);
	
	/**
	 * 메시지 정보 업데이트
	 * @param message
	 */
	void updateMessage(Message message);
	
	/**
	 * 메시지 삭제
	 * @param messageID
	 */
	void deleteMessage(int messageID);
	
	//--------------------------------------------------------------------------
	
	/**
	 * 내가 following 하는 모든 유저들의 메시지목록 조회
	 * @return
	 */
	List<Message> readAllFollowingMessages(User writer);
}
