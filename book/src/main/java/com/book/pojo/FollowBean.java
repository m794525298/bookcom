package com.book.pojo;

public class FollowBean {
	private String id;
	private String followerId;		// 跟随者
	private String followingId;		// 被关注人
	
	public FollowBean(String id, String followerId, String followingId) {
		super();
		this.id = id;
		this.followerId = followerId;
		this.followingId = followingId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFollowerId() {
		return followerId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

	public String getFollowingId() {
		return followingId;
	}

	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}
}
