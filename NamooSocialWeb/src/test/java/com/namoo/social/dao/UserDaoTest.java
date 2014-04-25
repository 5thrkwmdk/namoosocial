package com.namoo.social.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.namoo.social.domain.User;


public class UserDaoTest extends DbCommonTest{
	//
	private static final String DATASET_XML="UserDaoTest_dataset.xml";
	
	@Autowired
	UserDao userDao;

	//--------------------------------------------------------------------------
	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllUsers() {
		//
		List<User> users = userDao.readAllUsers();
		assertEquals(3, users.size());
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadUser() {
		//
//		userDao.readUser(userID);
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testCreateUser() {
		fail("Not yet implemented");
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testUpdateUser() {
		fail("Not yet implemented");
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteUser() {
		fail("Not yet implemented");
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllFollowings() {
		fail("Not yet implemented");
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testReadAllFollowers() {
		fail("Not yet implemented");
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testCreateFollowing() {
		fail("Not yet implemented");
	}

	@Test
	@DatabaseSetup(DATASET_XML)
	public void testDeleteFollower() {
		fail("Not yet implemented");
	}

}
