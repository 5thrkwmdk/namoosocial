package com.namoo.social.dao;

import java.util.List;

import com.namoo.social.domain.User;

public interface UserDao {
	//CRUD
	/**
	 * 모든 유저 목록 조회
	 * @return
	 */
	List<User> readAllUsers();
	
	/**
	 * 특정 유저 조회
	 * @param userID
	 * @return
	 */
	User readUser(String userID);
	
	/**
	 * 새 유저 생성
	 * @param user
	 * @return
	 */
	String createUser(User user);
	
	/**
	 * 유저 정보 업데이트
	 * @param user
	 */
	void updateUser(User user);
	
	/**
	 * 유저 삭제
	 * @param userID
	 */
	void deleteUser(String userID);
	
	//--------------------------------------------------------------------------
	
	/**
	 * 내가 following하는 사람들 목록 조회
	 * @param userID
	 * @return
	 */
	List<User> readAllFollowings(String userID);
	
	/**
	 * 나를 follower하는 사람들 목록 조회
	 * @param userID
	 * @return
	 */
	List<User> readAllFollowers(String userID);
	
	/**
	 * following 만들기
	 * @param user
	 */
	void createFollowing(String who, String whom);
	
	/**
	 * follower 지우기
	 * @param user
	 */
	void deleteFollower(String who, String whom);
}
