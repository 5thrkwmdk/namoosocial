package com.namoo.social.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;
import com.namoo.social.dao.MessageDao;
import com.namoo.social.domain.Message;
import com.namoo.social.domain.User;
import com.namoo.social.shared.exception.NamooSocialExceptionFactory;

@Repository
public class MessageDaoJdbc implements MessageDao{
	//
	// 기본 CRUD
	@Autowired
	private DataSource dataSource;
	
	@Override
	public List<Message> readAllMessages(String writerID) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet reSet = null;
		List<Message> messages = new ArrayList<Message>();
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT a.messageID, a.contents, a.writerID, a.reg_dt, b.name FROM message_tb a JOIN user_tb b ON a.writerID = b.userID WHERE writerID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writerID);
			reSet = pstmt.executeQuery();
			
			while (reSet.next()) {
				//
				Message message = new Message();
				message.setMessageID(reSet.getInt("messageID"));
				message.setContents(reSet.getString("contents"));
				message.setWriterID(new User(reSet.getString("writerID"), reSet.getString("name")));
				message.setReg_dt(reSet.getDate("reg_dt"));
				
				messages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooSocialExceptionFactory.createRuntime("메세지목록 조회 중 오류발생");
		} finally {
			if (conn != null) try { reSet.close(); } catch(Exception e) {}
			if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
			if (conn != null) try { conn.close(); } catch(Exception e) {}
		}
		return messages;
	}

	@Override
	public Message readMessage(int messageID) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet reSet = null;
		Message message = null;
		try {
			conn = dataSource.getConnection();
			String sql = "SELECT a.messageID, a.contents, a.writerID, a.reg_dt, b.name FROM message_tb a JOIN user_tb b WHERE messageID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, messageID);
			
			reSet= pstmt.executeQuery();
			if(reSet.next()) {
				messageID = reSet.getInt("messageID");
				String contents = reSet.getString("contents");
				User writerID = new User(reSet.getString("writerID"), reSet.getString("name"));
				Date reg_dt = reSet.getDate("reg_dt");
				
				message = new Message(messageID, contents, writerID);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooSocialExceptionFactory.createRuntime("메세지 조회 중 오류발생");
		} finally {
			 if ( reSet != null) try { reSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}

		return message;
	}

	@Override
	public int insertMessage(Message message) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet reSet = null;
		try {
			conn = dataSource.getConnection();

			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO message_tb(contents, writerID, reg_dt)");
			sb.append("VALUES(?, ?, sysDate())");
			
			pstmt = conn.prepareStatement(sb.toString(), Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, message.getContents());
			pstmt.setObject(2, message.getWriterID());
			
			pstmt.executeUpdate();
			
			reSet = pstmt.getGeneratedKeys();
			if (reSet.next()) {
				message.setMessageID(reSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooSocialExceptionFactory.createRuntime("메세지 생성 중 오류 발생");
		} finally {
			 if ( reSet != null) try { reSet.close(); } catch (SQLException e) { }
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
		return message.getMessageID();
	}

	@Override
	public void updateMessage(Message message) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();

			String sql = "UPDATE message_tb SET contents = ?, reg_dt = sysdate() WHERE messageID = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, message.getContents());
			pstmt.setInt(2, message.getMessageID());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooSocialExceptionFactory.createRuntime("메세지정보 업데이트 중 오류 발생");
		} finally {
			 if ( pstmt != null) try { pstmt.close(); } catch (SQLException e) { }
			 if ( conn != null) try { conn.close(); } catch (SQLException e) { }
		}
	}

	@Override
	public void deleteMessage(int messageID) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();

			String sql = "DELETE FROM message_tb WHERE messageID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, messageID);
			
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
	// following 관련 기능
	@Override
	public List<Message> readAllFollowingMessages(User writer) {
		//
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet reSet = null;
		List<Message> messages = new ArrayList<Message>();
		try {
			conn = dataSource.getConnection();
//			String sql = "SELECT a.messageID, a.contents, a.writerID, a.reg_dt, b.name FROM message_tb a JOIN user_tb b ";
			String sql = "SELECT a.messageID, a.contents, a.writerID, a.reg_dt, b.name FROM message_tb a JOIN user_tb b WHERE writerID=? OR writerID IN (SELECT whom FROM usertouser_tb WHERE who= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setObject(1, writer);
			pstmt.setObject(2, writer);
			reSet = pstmt.executeQuery();
			
			while (reSet.next()) {
				//
				Message message = new Message();
				message.setMessageID(reSet.getInt("MessageID"));
				message.setContents(reSet.getString("Contents"));
				message.setWriterID(new User(reSet.getString("writerID"), reSet.getString("name")));
				message.setReg_dt(reSet.getDate("reg_dt"));
				
				messages.add(message);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw NamooSocialExceptionFactory.createRuntime("메세지목록 조회 중 오류발생");
		} finally {
			if (conn != null) try { reSet.close(); } catch(Exception e) {}
			if (pstmt != null) try { pstmt.close(); } catch(Exception e) {}
			if (conn != null) try { conn.close(); } catch(Exception e) {}
		}
		return messages;
		
	}
}
