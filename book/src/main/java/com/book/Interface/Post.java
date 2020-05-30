package com.book.Interface;

import com.alibaba.fastjson.JSONObject;
import com.book.pojo.PostBean;

public interface Post {
	public JSONObject publishPost(PostBean post);
	public boolean updatedPost(PostBean post);
	public JSONObject searchPostByKeyword(String keyword,String bookType,String page);
	public JSONObject searchPostByKeyword(String keyword,String page);
	public JSONObject searchPostByUser(String keyword,String page);
	public JSONObject getHotPost(String page);
	public boolean deletePost(String userId,String postId);
	public JSONObject getPostDetail(String postId);
	public JSONObject getPostByUserID(String userId,String page);
	public JSONObject getUserPagePost(String userId);
}
