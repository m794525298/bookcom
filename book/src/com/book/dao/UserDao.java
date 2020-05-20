package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.book.common.Coder;
import com.book.common.DataBaseConnector;
import com.book.pojo.UserBean;

public class UserDao {

	
	public static ResultSet login(String account,String password) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select * from user where USER_ACCOUNT ='" + account + "' And USER_PASSWORD = '" + Coder.encrypted(password) + "';");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ResultSet getUser(String id) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select * from user where USER_MD5ID ='" + id +   "';");
			return rs;
		} catch (SQLException e) {
			return null;
		}
		
	}
	
	public static int regsist(String account,String username,String password,String email) {
		Statement st = DataBaseConnector.getStatement();
		try {
			ResultSet rs = st.executeQuery("Select USER_ID from user where USER_ACCOUNT ='" + account + "' OR USER_EMAIL = '" + email +"';");
			if (rs.next()) return 2;
			rs.previous();
			String sql="insert into user(USER_ACCOUNT,USER_PASSWORD,USER_EMAIL,USER_NICKNAME) values(?,?,?,?) ";//sql语句
			st.close();
			PreparedStatement pstmt=DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1 , account);
			pstmt.setString(2 , Coder.encrypted(password));
			pstmt.setString(3 , email);
			pstmt.setString(4, username);
			int res = pstmt.executeUpdate();
			pstmt.close();
			
			if (res > 0) {
				Statement st1 = DataBaseConnector.getStatement();
				ResultSet rs1 = st1.executeQuery("Select USER_ID from user where USER_ACCOUNT ='" + account + "';");
				while(rs1.next()) {
					sql="update user set USER_MD5ID = '"+ Coder.encrypted(rs1.getString("USER_ID")) + "' where USER_ACCOUNT='"+account+"';";//sql语句
					st1.executeUpdate(sql);
					break;
				}
				st1.close();
			}
			
			return (res > 0)? 0 : 1 ;
		} catch (SQLException e) {
			System.out.println(e);
			return 1;
		}
	}
	
	public static boolean validUser(String id) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_NICKNAME from user where USER_MD5ID ='" + id + "';");
			if (rs.next()) {
				rs.previous();
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static int updatedUserData(String userId,String userName) {
		//0:成功 1:數据庫錯誤
		try {
			Statement st = DataBaseConnector.getStatement();
			String sql="update user set USER_NICKNAME = '" +userName+ "'where USER_MD5ID = '"+ userId +"';";//sql语句
			st.executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			System.out.println(e);
			return 1;
		}
	}
	
	public static String searchUserNickNameById(String id) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_NICKNAME from user where USER_MD5ID ='" + id + "';");
			return rs.getString("USER_NICKNAME");
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static int updatedUserIcon(String userId,String icon) {
		try {
			Statement st = DataBaseConnector.getStatement();
			String sql="update user set USER_ICON = '"+ icon +"' where USER_MD5ID = '"+ userId +"';";//sql语句
			st.executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static int updatedUserPassword(String userId,String oldPassword,String newPassword) {
		//0:成功 1:數据庫錯誤
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_ID from user where USER_MD5ID ='" + userId + "' And USER_PASSWORD ='"+Coder.encrypted(oldPassword)+"';");
			if (!rs.next()) return 1;
			rs.previous();
			String sql="update user set USER_PASSWORD = '" +Coder.encrypted(newPassword)+ "'where USER_MD5ID = '"+ userId +"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static int updatedUserPasswordByEmail(String email,String newPassword) {
		//0:成功 1:數据庫錯誤
		try {
			Statement st = DataBaseConnector.getStatement();
			System.out.println("breakPoint");
			if (checkEmail(email) == 1) return 1;
			System.out.println("breakPoint");
			String sql="update user set USER_PASSWORD = '"  + Coder.encrypted(newPassword)+ "' where USER_Email = '"+email+"';";//sql语句
			DataBaseConnector.getStatement().executeUpdate(sql);
			System.out.println("breakPoint");
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static int checkEmail(String email) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_MD5ID from user where USER_Email ='" + email +"';");
			if (!rs.next()) return 1;
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static int checkAccount(String account) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_MD5ID from user where USER_ACCOUNT ='" + account +"';");
			if (rs.next()) return 1;
			return 0;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	
	public static ResultSet getUserName(String id) {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_NICKNAME from user where USER_MD5ID ='" + id +"';");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ResultSet getHotUser() {
		try {
			Statement st = DataBaseConnector.getStatement();
			ResultSet rs = st.executeQuery("Select USER_MD5ID,USER_ICON,USER_NICKNAME,USER_FOLLOWERSNUM from user Order by USER_FOLLOWERSNUM limit 4;");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
}
