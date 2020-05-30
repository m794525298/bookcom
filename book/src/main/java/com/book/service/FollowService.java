package com.book.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.book.Interface.Follow;
import com.book.dao.CommentDao;
import com.book.dao.FollowDao;
import com.book.dao.UserDao;
import com.book.pojo.CommentBean;

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
	
	private JSONObject getFollowJson(ResultSet rs) {
		JSONObject json = new JSONObject();
		
		try {
			if (rs == null || !rs.next()) {
				json.put("num","0");
				json.put("list", "[]");
			} else {
				int num;
				List<JSONObject> followings = new LinkedList<JSONObject>();
				
				for(num = 0, rs.previous(); rs.next(); ++num) {
					JSONObject following = new JSONObject();
					String followingId = rs.getString("FOLLOW_FOLLOWING");
					
					ResultSet temp = UserDao.getUser(followingId);
					if(temp.next()) {
						following.put("userID", temp.getString("USER_MD5ID"));
						following.put("icon", temp.getString("USER_ICON"));
						following.put("userName", temp.getString("USER_NICKNAME"));
					} else {
						following.put("userID", "");
						following.put("icon", "");
						following.put("userName", "");
					}
					
					followings.add(following);
				}
				json.put("num", String.valueOf(num));
				json.put("followings", followings);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}

	@Override
	public JSONObject getUserFollowing(String userId) {
		ResultSet rs = FollowDao.getUserFollowing(userId);
		return getFollowJson(rs);
	}
}
