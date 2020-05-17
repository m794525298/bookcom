package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.book.common.DataBaseConnector;
import com.book.pojo.PostBean;

public class PostDao {
static Statement st;
	
	static{
		st = DataBaseConnector.getStatement();
	}
	
	public static int insertPost(PostBean post,String userId,String userAccount) {
		if (!UserDao.validUser(userId, userAccount)) return 1;
		try {
			String sql="insert into Post(POST_POSTTITLE,POST_PUBLISHERID,POST_CONTENT,POST_COVER,BOOK_ID) values(?,?,?,?,?)";//sql语句
			PreparedStatement pstmt=DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1 , post.getTitle());
			pstmt.setString(2 , post.getPublisherId());
			pstmt.setString(3 , post.getContent());
			pstmt.setString(4 , post.getCover());
			pstmt.setString(5 , post.getBookId());
			int res = pstmt.executeUpdate();
			pstmt.close();
			return (res > 0)? 0 : 1 ;
		} catch (SQLException e) {
			return 1;
		}
		
	}
	
	public static int deletePost(String postId,String userId,String userAccount) {
		if (!UserDao.validUser(userId, userAccount)) return 1;
		try {
			ResultSet rs = st.executeQuery("Select POST_TITLE from post where POST_id =" + postId + ", POST_PUBLISHERID =" + userId + " And POST_ISEXIST = 1"+";");
			if (!rs.wasNull()) return 1;
			String sql="updated user set POST_ISEXIST = 0 where POST_ID = +"+postId+";";//sql语句
			st.executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			return 1;
		}
		
	}
	
	public static ResultSet getPostByUser(String userId) {
		try {
			ResultSet rs = st.executeQuery("Select * from post where POST_PUBLISHERID =" + userId +" And POST_ISEXIST = 1;");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	
}
