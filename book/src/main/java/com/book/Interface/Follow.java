package com.book.Interface;

import com.alibaba.fastjson.JSONObject;

public interface Follow {
	public int follow(String userId,String followingUser);
	public int disfollow(String userId,String followingUser);
	public boolean isFollow(String userId,String followingUser);
	public JSONObject getUserFollowing(String userId);
}
