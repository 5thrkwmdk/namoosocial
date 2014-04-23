package com.namoo.social.dao;

import java.util.List;

import com.namoo.social.domain.User;


public interface UserDao {
	//CRUD
		/*
		List<Blog> readAllBlog();
		List<Blog> readAllMyBlogs(String userId);
		Blog readBlog(int blogId);
		int insertBlog(Blog blog);
		void updateBlog(Blog blog);
		void deleteBlog(int blogId);
		*/
	
	List<User> readAllUser();
	User readUser(String userID);
	int insertUser(User user);
	void updateUser(User user);
	void deleteUser(String UserID);
	

}
