package com.namoo.social.dao.jdbc;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.namoo.social.dao.UserDao;
import com.namoo.social.domain.Message;
import com.namoo.social.domain.User;
import com.namoo.social.shared.exception.NamooSocialExceptionFactory;

public class UserDaoJdbc implements UserDao{
	//
	private DataSource dataSource;
		
	@Override
	public List<User> readAllUser() {
		// 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet reSet = null;
		List<User> users = new ArrayList<User>();
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT userID, name, email, password, messages FROM user_tb";
			pstmt = conn.prepareStatement(sql);
			reSet = pstmt.executeQuery();
			
			while (reSet.next()) {
				//
				User user = new User();
				user.setUserID(reSet.getString("userID"));
				user.setName(reSet.getString("name"));
				user.setEmail(reSet.getString("email"));
				user.setMessages((List<Message>) reSet.getArray("messages"));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooSocialExceptionFactory.createRuntime("유저목록 조회 중 오류발생");
		} finally {
			if (conn != null) try { reSet.close(); } catch(Exception e) {}
			if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
			if (conn != null) try { conn.close(); } catch(Exception e) {}
		}
		return users;
	}

	@Override
	public User readUser(String userID) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet reSet = null;
		User user = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT userID, name, email, password, messages FROM user_tb WHERE userId = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			
			reSet= pstmt.executeQuery();
			if(reSet.next()) {
				userID = reSet.getString("userID");
				String name = reSet.getString("name");
				String email = reSet.getString("email");
				String password = reSet.getString("password");
				List<Message> messages = (List<Message>) reSet.getArray("messages");
				
				user = new User(userID, name, email, password, messages);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooSocialExceptionFactory.createRuntime("유저 조회 중 오류발생");
		} finally {
			 if ( reSet != null) try { reSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return user;
	}

	@Override
	public String insertUser(User user) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet reSet = null;
		try {
			conn = dataSource.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO user_tb(userID, name, email, password, messages)");
			sb.append("VALUES(?, ?, ?, ?, ?)");
			
			pstmt = conn.prepareStatement(sb.toString()	);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPassword());
			pstmt.setArray(5, (Array) user.getMessages());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooSocialExceptionFactory.createRuntime("유저 추가 중 오류 발생");
		} finally {
			 if ( reSet != null) try { reSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return user.getUserID();
	}

	@Override
	public void updateUser(User user) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();

			String sql = "UPDATE user_tb SET email = ?, name = ?, password = ?, messages = ? WHERE userID = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.setArray(4, (Array) user.getMessages());
			pstmt.setString(5, user.getUserID());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooSocialExceptionFactory.createRuntime("유저정보 업데이트 중 오류 발생");
		} finally {
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public void deleteUser(String userID) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();

			String sql = "DELETE FROM user_tb WHERE userID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userID);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooSocialExceptionFactory.createRuntime("메세지 삭제 중 오류 발생");
		} finally {
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}
	//--------------------------------------------------------------------------
}
