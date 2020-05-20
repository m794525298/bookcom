package com.book.service;

import com.book.Interface.Follow;
import com.book.dao.FollowDao;

public class FollowService implements Follow {

	@Override
	public int follow(String userId, String followingUser) {
		return FollowDao.follow(userId,followingUser);
	}

	@Override
	public int disfollow(String userId, String followingUser) {
		return FollowDao.disFollow(userId,followingUser);
	}

	@Override
	public boolean isFollow(String userId, String followingUser) {
		return FollowDao.isFollow(userId, followingUser);
	}

}
