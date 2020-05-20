package com.book.Interface;

import com.alibaba.fastjson.JSONObject;

public interface Post {
	public int updatedPost(Post post);
	public JSONObject searchPostByKeyword(String keyword,String bookType,String page);
	public JSONObject searchPostByKeyword(String keyword,String page);
	public JSONObject searchPostByUser(String keyword,String page);
	public JSONObject getHotPost(String page);
	public boolean deletePost(String userId,String postId);
	public JSONObject getPostDetail(String postId);
	public JSONObject getPostByUserID(String userId,String page);
}
