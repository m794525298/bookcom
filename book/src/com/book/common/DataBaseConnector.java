package com.book.common;

import java.sql.*;

public class DataBaseConnector {
	private static Connection conn;
	private static Statement sm;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			setConnection();
			sm = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}
	
	public static Statement getStatement(){
		return sm;
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	public static PreparedStatement getPreparedStatement(String sql) {
		try {
			return conn.prepareStatement(sql);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static void setConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://cdb-nvpev74d.gz.tencentcdb.com:10078/book","root","Ab123123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
         
	}
}
