package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.book.common.DataBaseConnector;

public class FollowDao {
static Statement st;
	
	static{
		st = DataBaseConnector.getStatement();
	}
	
	public static int follow(String userId,String followerId) {
		
		if (!UserDao.validUser(userId)) return 1;
		try {
			ResultSet rs = st.executeQuery("Select FOLLOW_ID from follow where FOLLOW_FOLLOWING =" + userId +"And FOLLOW_FOLLOWERS"+followerId+ ";");
			if (rs.getRow()>0) return 0;
			String sql="insert into follow(FOLLOW_FOLLOWING,FOLLOW_FOLLOWERS) values(?,?)";//sql语句
			PreparedStatement pstmt=DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1 , userId);
			pstmt.setString(2 , followerId);
			pstmt.executeUpdate();
			pstmt.close();
			sql = "updated user set USER_FOLLOWINGNUM = USER_FOLLOWINGNUM + 1 where USER_MD5ID = "+ userId +";";//sql语句
			st.executeUpdate(sql);
			sql = "updated user set USER_FOLLOWERSNUM = USER_FOLLOWERSNUM + 1 where USER_MD5ID = "+ followerId +";";//sql语句
			st.executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static int disFollow(String userId,String followerId) {
		if (!UserDao.validUser(userId)) return 1;
		try {
			ResultSet rs = st.executeQuery("Select FOLLOW_ID from follow where FOLLOW_FOLLOWING =" + userId +"And FOLLOW_FOLLOWERS"+followerId+ ";");
			if (rs.getRow()== 0) return 0;
			String sql="delete from follow where FOLLOW_FOLLOWING = "+userId + " And FOLLOW_FOLLOWINERS = " + followerId + ";";//sql语句
			st.executeUpdate(sql);
			sql = "updated user set USER_FOLLOWINGNUM = USER_FOLLOWINGNUM - 1 where USER_MD5ID = "+ userId +";";//sql语句
			st.executeUpdate(sql);
			sql = "updated user set USER_FOLLOWERSNUM = USER_FOLLOWERSNUM - 1 where USER_MD5ID = "+ followerId +";";//sql语句
			st.executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
}
