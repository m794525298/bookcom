package com.book.service;

import java.sql.ResultSet;

import com.book.Interface.Post;
import com.book.dao.UserDao;

public class PostService implements Post {

	@Override
	public int updatedPost(Post post) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPosts(ResultSet idSet) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean validUser(String id, String account) {
		return UserDao.validUser(id,account);
	}
}
