package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.book.common.DataBaseConnector;

public class PostDao {
static Statement st;
	
	static{
		st = DataBaseConnector.getStatement();
	}
	
	public static int insertPost() {
		return 0;
		
	}
	
	public static int deletePost(String postId,String userId,String userAccount) {
		if (!UserDao.validUser(userId, userAccount)) return 1;
		try {
			ResultSet rs = st.executeQuery("Select POST_TITLE from post where POST_id =" + postId + ", POST_PUBLISHERID =" + userId + " And POST_ISEXIST = 1"+";");
			if (!rs.wasNull()) return 1;
			String sql="updated user set POST_ISEXIST = 0 where POST_ID = ?;";//sql语句
			PreparedStatement pstmt=DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1 , postId);
			int res = pstmt.executeUpdate();
			pstmt.close();
			return (res > 0)? 0 : 1 ;
		} catch (SQLException e) {
			return 1;
		}
		
	}
	
	public static ResultSet getPostByUser() {
		return null;
	}

	public static ResultSet getPostByBookId() {
		return null;
	}
}
