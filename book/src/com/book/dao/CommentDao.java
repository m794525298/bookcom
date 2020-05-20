package com.book.dao;

import java.sql.Statement;

import com.book.common.DataBaseConnector;

public class CommentDao {
static Statement st;
	
	static{
		st = DataBaseConnector.getStatement();
	}
	
}
