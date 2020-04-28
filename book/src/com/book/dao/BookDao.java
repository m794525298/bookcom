package com.book.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.book.common.DataBaseConnector;

public class BookDao {
	static Statement st;
	
	static{
		st = DataBaseConnector.getStatement();
	}
	
	public static int insertNewBook(String bookTitle,String author) {
		//0:成功 1:數据庫錯誤 2:該討論區已存在
		try {
			ResultSet rs = st.executeQuery("Select BOOK_ID from book where BOOK_TITLE =" + bookTitle + "And BOOK_AUTHOR = "+ author+ ";");
			if (!rs.wasNull()) return 2;
			String sql="insert into book(BOOK_TITLE,BOOK_AUTHOR) values(?,?)";//sql语句
			PreparedStatement pstmt=DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1 , bookTitle);
			pstmt.setString(2 , author);
			int res = pstmt.executeUpdate();
			pstmt.close();
			return (res > 0)? 0 : 1 ;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static int insertNewBook(String bookTitle,String author,String iconPath) {
		//0:成功 1:數据庫錯誤 2:該討論區已存在
		try {
			ResultSet rs = st.executeQuery("Select BOOK_ID from book where BOOK_TITLE =" + bookTitle + "And BOOK_AUTHOR = "+ author+ ";");
			if (!rs.wasNull()) return 2;
			String sql="insert into book(BOOK_TITLE,BOOK_AUTHOR,BOOK_ICON) values(?,?,?)";//sql语句
			PreparedStatement pstmt=DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1 , bookTitle);
			pstmt.setString(2 , author);
			pstmt.setString(3, iconPath);
			int res = pstmt.executeUpdate();
			pstmt.close();
			return (res > 0)? 0 : 1 ;
		} catch (SQLException e) {
			return 1;
		}
	}
	
	public static int updatedIcon(String bookTitle,String author,String iconPath) {
		try {
			//0:成功 1:數据庫錯誤
			String sql="updated book set BOOK_ICON = ? where BOOK_TITLE = ? AND BOOK_AUTHOR = ?;";//sql语句
			PreparedStatement pstmt=DataBaseConnector.getPreparedStatement(sql);
			pstmt.setString(1 , bookTitle);
			pstmt.setString(2 , author);
			pstmt.setString(3, iconPath);
			int res = pstmt.executeUpdate();
			pstmt.close();
			return (res > 0)? 0 : 1 ;
		}catch (SQLException e) {
			return 1;
		}
	}
	
	public static ResultSet fuzzySearchBook(String bookTitle) {
		try {
			ResultSet rs = st.executeQuery("Select * from book where BOOK_TITLE Like \'%" + bookTitle + "%\';");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public static ResultSet fuzzySearchAuthor(String author) {
		try {
			ResultSet rs = st.executeQuery("Select * from book where BOOK_AUTHOR Like \'%" + author + "%\';");
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}
	
	
}
