package com.book.Interface;

import com.alibaba.fastjson.JSONObject;
import com.book.pojo.CommentBean;

public interface Comment {
	public boolean publishComment(CommentBean comment);
	public JSONObject getPostComments(String postId, String page);
	public JSONObject getUserComments(String userId, String page);
	public JSONObject getUserPageComments(String userId);
}
