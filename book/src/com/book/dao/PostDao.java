package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.alibaba.fastjson.JSONObject;
import com.book.common.DataBaseConnector;
import com.book.pojo.PostBean;

public class PostDao {

	public static int insertPost(PostBean post,String userId,String userAccount) {
		if (!UserDao.validUser(userId)) return 1;
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
	
	public static boolean deletePost(String postId,String userId) {
		if (!UserDao.validUser(userId)) return false;
		Statement st = DataBaseConnector.getStatement();
		try {
			ResultSet rs = st.executeQuery("Select POST_POSTTITLE from post where POST_ID ='" + postId + "' And POST_PUBLISHERID ='" + userId + "' And POST_ISEXIST = 0;");
			if (!rs.next()) return false;
			String sql="update post set POST_ISEXIST = 1 where POST_ID = '"+postId+"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
		
	}
	
	public static ResultSet getPostByUser(String userId) {
		Statement st = DataBaseConnector.getStatement();
		try {
			System.out.println(userId);
			ResultSet rs = st.executeQuery("Select * from post where POST_PUBLISHERID ='" + userId +"' And POST_ISEXIST = 0;");
			return rs;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	
	
	public static ResultSet searchPost(String keyword) {
		Statement st = DataBaseConnector.getStatement();
		String sql;
		try {
			if (keyword.isEmpty()) {
				sql = "Select * from post where POST_ISEXIST = 0;";
			}else {
				sql ="Select * from post where POST_POSTTITLE like  '%"+keyword+"%' And POST_ISEXIST = 0;";
			}
			ResultSet rs = st.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ResultSet searchPost(String keyword,String bookType) {
		Statement st = DataBaseConnector.getStatement();
		String sql;
		try {
			if (keyword.isEmpty()) {
				sql = "Select * from post where POST_BOOKTYPE = "+ bookType +" And POST_ISEXIST = 0;";
			}else {
				sql =" Select * from post where POST_POSTTITLE like  '%"+keyword+"%' and POST_BOOKTYPE = "+ bookType +" And POST_ISEXIST = 0;";
			}
			ResultSet rs = st.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public static ResultSet searchPostByNickName(String keyword) {
		Statement st = DataBaseConnector.getStatement();
		try {
			ResultSet rs = st.executeQuery("Select post.* from post,user where post.POST_PUBLISHERID = user.USER_MD5ID and user.USER_NICKNAME like '%"+keyword+"%' And POST_ISEXIST = 0;");
			return rs;
		} catch (SQLException e) {
			System.out.println(e);
			return null;
		}
	}
	
	public static ResultSet getHotPost(String page) {
		Statement st = DataBaseConnector.getStatement();
		try {
			int count = getPostNum();
			int totalPage = (count%10 != 0)?count/10+1:count/10;
			int start = (Integer.valueOf(page) != totalPage)?count-count%10:Integer.valueOf(page)*10-10;
			ResultSet rs = st.executeQuery("Select * from post where POST_ISEXIST = 0 Order by POST_COMMENTNUM limit "+start+","+(start+10)+";");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static int getPostNum() {
		Statement st = DataBaseConnector.getStatement();
		try {
			ResultSet rs = st.executeQuery("Select POST_ID as count from post where POST_ISEXIST = 0;");
			rs.last();
			return rs.getRow();
		} catch (SQLException e) {
			return 0;
		}
	}
	
	public static ResultSet getPostDetail(String postId) {
		Statement st = DataBaseConnector.getStatement();
		try {
			ResultSet rs = st.executeQuery("Select * from post where POST_ID = "+postId+" And POST_ISEXIST = 0;");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
}
