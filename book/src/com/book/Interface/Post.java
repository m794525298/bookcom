package com.book.Interface;

import java.sql.ResultSet;

import com.alibaba.fastjson.JSONObject;

public interface Post {
	public int updatedPost(Post ppost);
	public JSONObject searchPostByKeyword(String keyword,String bookType,String page);
	public JSONObject searchPostByKeyword(String keyword,String page);
	
}
