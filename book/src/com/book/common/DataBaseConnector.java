package com.book.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class DataBaseConnector {
	private static Connection conn;
	private static Statement sm;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			setConnection();
			sm = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			System.exit(1);
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
		Map<String,String> map = new HashMap<String,String>();
		try {
			InputStreamReader in = new InputStreamReader(new FileInputStream("/WebContent/WEB-INF/database.properties"));
			Properties prop = new Properties();
			prop.load(in);
			Iterator<String> it = prop.stringPropertyNames().iterator();
			
			while(it.hasNext()) {
				String key = it.next();
				map.put(key, prop.getProperty(key));
			}
			
			conn = DriverManager.getConnection(map.get("url"),map.get("name"),map.get("password"));
		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
         
	}
}
