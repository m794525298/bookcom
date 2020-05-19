package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.book.common.Coder;
import com.book.common.DataBaseConnector;
import com.book.pojo.UserBean;

public class UserDao {
	static Statement st;
	
	static{
		st = DataBaseConnector.getStatement();
	}
	

	
	public static ResultSet login(String account,String password) {
		try {
			ResultSet rs = st.executeQuery("Select * from user where USER_ACCOUNT =" + account + "And USER_PASSWORD = " + password + ";");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ResultSet getUser(String id) {
		try {
			ResultSet rs = st.executeQuery("Select * from user where USER_ID =" + id +   ";");
			return rs;
		} catch (SQLException e) {
			return null;
		}
		
	}
	
	public static int regsist(String account,String username,String password,String email) {
		//0:成功 1:數据庫錯誤 2:已存在用戶名 
		try {
			ResultSet rs = st.executeQuery("Select USER_ID from user where USER_ACCOUNT =" + account + "or " +"USER_Email =" + email +";");
			if (!rs.wasNull()) return 2;
			
			String sql="insert into user(USER_ACCOUNT,USER_PASSWORD,USER_EMAIL,USER_NICKNAME) values(?,?,?,?) ";//sql语句
			PreparedStatement pstmt=DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1 , account);
			pstmt.setString(2 , password);
			pstmt.setString(3 , email);
			pstmt.setString(4, username);
			int res = pstmt.executeUpdate();
			pstmt.close();
			if (res > 0) {
				rs = st.executeQuery("Select USER_ID from user where USER_ACCOUNT =" + account + ";");
				sql="updated user set USER_MD5ID= "+ Coder.encrypted(rs.getString("USER_ID")) + ";";//sql语句
				st.executeUpdate(sql);
			}
			return (res > 0)? 0 : 1 ;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static boolean validUser(String id) {
		try {
			ResultSet rs = st.executeQuery("Select USER_NICKNAME from user where USER_MD5ID =" + id + ";");
			return !rs.wasNull();
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static int updatedUserData(String userId,String userName) {
		//0:成功 1:數据庫錯誤
		try {
			String sql="updated user set USER_NICKNAME = " +userName+ "where USER_MD5ID = "+ userId +";";//sql语句
			st.executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static String searchUserNickNameById(String id) {
		try {
			ResultSet rs = st.executeQuery("Select USER_NICKNAME from user where USER_MD5ID =" + id + ";");
			return rs.getString("USER_NICKNAME");
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static int updatedUserIcon(String userId,String icon) {
		try {
			String sql="updated user set USER_ICON = "+ icon +"where USER_MD5ID = "+ userId +";";//sql语句
			st.executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static int updatedUserPassword(String userId,String oldPassword,String newPassword) {
		//0:成功 1:數据庫錯誤
		try {
			ResultSet rs = st.executeQuery("Select USER_ID from user where USER_MD5ID =" + userId + "And USER_PASSWORD ="+oldPassword+";");
			if (rs.getRow() == 0) return 1;
			String sql="updated user set USER_PASSWORD = " +newPassword+ "where USER_MD5ID = "+ userId +";";//sql语句
			st.executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
}
