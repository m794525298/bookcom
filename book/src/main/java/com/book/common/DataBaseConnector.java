package com.book.common;

import java.sql.*;

public class DataBaseConnector {
	
	private static Connection conn;
	
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			setConnection();
			System.setProperty("user.home", "D:/Program Files/eclipse/eclipse-workspace");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public static Statement getStatement(){
		try {
			return getConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public static PreparedStatement getPreparedStatement(String sql) {
		try {
			return conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	public static Connection setConnection() {
		try {
			conn = ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
         
	}
}
