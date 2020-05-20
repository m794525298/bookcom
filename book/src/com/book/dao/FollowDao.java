package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.book.common.DataBaseConnector;

public class FollowDao {


	
	public static int follow(String userId,String followerId) {
		Statement st = DataBaseConnector.getStatement();
		if (!UserDao.validUser(userId)||!UserDao.validUser(followerId)) return 1;
		try {
			ResultSet rs = st.executeQuery("Select FOLLOW_ID from follow where FOLLOW_FOLLOWING ='" + followerId +"'And FOLLOW_FOLLOWER='"+userId+ "';");
			if (rs.next()) return 0;
			st.close();
			String sql="insert into follow(FOLLOW_FOLLOWING,FOLLOW_FOLLOWER) values(?,?)";//sql语句
			PreparedStatement pstmt=DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(2 , userId);
			pstmt.setString(1 , followerId);
			pstmt.executeUpdate();
			pstmt.close();
			sql = "update user set USER_FOLLOWINGNUM = USER_FOLLOWINGNUM + 1 where USER_MD5ID = '"+ followerId +"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			sql = "update user set USER_FOLLOWERSNUM = USER_FOLLOWERSNUM + 1 where USER_MD5ID = '"+ userId +"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			System.out.println(e);
			return 1;
		}
	}
	
	public static int disFollow(String userId,String followerId) {
		Statement st = DataBaseConnector.getStatement();
		if (!UserDao.validUser(userId)||!UserDao.validUser(followerId)) return 1;
		try {
			ResultSet rs = st.executeQuery("Select FOLLOW_ID from follow where FOLLOW_FOLLOWING ='" + followerId +"'And FOLLOW_FOLLOWER='"+userId+ "';");
			if (!rs.next()) return 0;
			String sql="delete from follow where FOLLOW_FOLLOWING = '"+ followerId + "' And FOLLOW_FOLLOWER = '" + userId  + "';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			sql = "update user set USER_FOLLOWINGNUM = USER_FOLLOWINGNUM - 1 where USER_MD5ID = '"+followerId +"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			sql = "update user set USER_FOLLOWERSNUM = USER_FOLLOWERSNUM - 1 where USER_MD5ID = '"+  userId +"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			System.out.println(e);
			return 1;
		}
	}
	
	public static boolean isFollow(String userId,String followerId) {
		Statement st = DataBaseConnector.getStatement();
		if (!UserDao.validUser(userId)||!UserDao.validUser(followerId)) return false;
		try {
			ResultSet rs = st.executeQuery("Select FOLLOW_ID from follow where FOLLOW_FOLLOWING ='" + followerId +"'And FOLLOW_FOLLOWER='"+ userId+ "';");
			if (!rs.next()) return false;
			return true;
		}catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public static ResultSet getFollowingsID(String userId) {
		Statement st = DataBaseConnector.getStatement();
		if (!UserDao.validUser(userId)) return null;
		try {
			ResultSet rs = st.executeQuery("Select FOLLOW_FOLLOWER from follow where FOLLOW_FOLLOWING ='" + userId + "'limit 6;");
			return rs;
		}catch (SQLException e) {
			return null;
		}
		
	}
}
