package com.namoo.social.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.social.domain.Message;
import com.namoo.social.domain.User;


public class MessageDaoTest extends DbCommonTest{
	//
	private static final String DATASET_XML="MessageDaoTest_dataset.xml";
	
	@Autowired
	private MessageDao messageDao;
	

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllMessages() {
		//
		String writerID = "aaa";
		List<Message> messages = messageDao.readAllMessages(writerID);
		// 검증
		assertEquals(3, messages.size());
		assertEquals("contents1", messages.get(0).getContents());
		assertEquals(4, messages.get(1).getMessageID());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadMessage() {
		//
		Message message = messageDao.readMessage(1);
		// 검증
		assertEquals("aaa", message.getWriterID().getUserID());
		assertEquals("aaaa", message.getWriterID().getName());
		assertEquals("contents1", message.getContents());
		
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testInsertMessage() {
		//
		List<Message> messages = messageDao.readAllMessages("ccc");
		Message message = new Message(10, "testContents", new User("ccc", "cccc"));
		messages.add(message);
		// 검증
		assertEquals(2, messages.size());
		assertEquals(3, messages.get(0).getMessageID());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testUpdateMessage() {
		//
		Message message = messageDao.readMessage(1);
		message.setContents("contents10");
		messageDao.updateMessage(message);
		// 검증
		message = messageDao.readMessage(1);
		assertEquals("contents10", message.getContents());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteMessage() {
		//
//		List<Message> messages = messageDao.readAllMessages("aaa");
//		Message messsage = messageDao.deleteMessage(1);
//		
//		assertEquals(2, messages.size());
//		
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllFollowingMessages() {
		fail("Not yet implemented");
	}

}
