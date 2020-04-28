package com.book.Interface;

import java.sql.ResultSet;

public interface Post {
	public int updatedPost(Post ppost);
	public String getPosts(ResultSet idSet);
	public boolean validUser(String id,String account);
}
