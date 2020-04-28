package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.book.common.DataBaseConnector;

public class UserDao {
	Statement st;
	
	public UserDao(){
		super();
		st = DataBaseConnector.getStatement();
	}
	
	public ResultSet login(String account,String password) {
		try {
			ResultSet rs = st.executeQuery("Select * from user where USER_ACCOUNT =" + account + "USER_PASSWORD = " + password + ";");
			return rs;
		} catch (SQLException e) {
			return null;
		}
		
	}
	
	public int regsist(String account,String password,String email) {
		//0:成功 1:數据庫錯誤 2:已存在用戶名 3:邮箱已注册
		try {
			ResultSet rs = st.executeQuery("Select USER_ID from user where USER_ACCOUNT =" + account +";");
			if (!rs.wasNull()) return 2;
			rs = st.executeQuery("Select USER_ID from user where USER_Email =" + email +";");
			if (!rs.wasNull()) return 3;
			String sql="insert into user(USER_ACCOUNT,USER_PASSWORD,USER_EMAIL) values(?,?,?)";//sql语句
			PreparedStatement pstmt=DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1 , account);
			pstmt.setString(2 , password);
			pstmt.setString(3 , email);
			int res = pstmt.executeUpdate();
			pstmt.close();
			return (res > 0)? 0 : 1 ;
		} catch (SQLException e) {
			return 1;
		}
	}
	
}
