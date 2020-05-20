package com.book.Interface;

public interface Follow {
	public int follow(String userId,String followingUser);
	public int disfollow(String userId,String followingUser);
	public boolean isFollow(String userId,String followingUser);
}
