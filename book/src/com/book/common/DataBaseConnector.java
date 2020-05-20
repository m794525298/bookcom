package com.book.common;

import java.sql.*;

public class DataBaseConnector {
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			setConnection();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}
	
	public static Statement getStatement(){
		try {
			return setConnection().createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	public static PreparedStatement getPreparedStatement(String sql) {
		try {
			return setConnection().prepareStatement(sql);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static Connection setConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://cdb-nvpev74d.gz.tencentcdb.com:10078/book?useSSL=false","root","Ab123123");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
         
	}
}
