package com.book.dao;

import java.sql.Statement;

import com.book.common.DataBaseConnector;

public class PostDao {
static Statement st;
	
	static{
		st = DataBaseConnector.getStatement();
	}
	
	public static int updatedPost() {
		return 0;
	}
	

}
