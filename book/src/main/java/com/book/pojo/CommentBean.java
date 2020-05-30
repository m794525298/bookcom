package com.book.pojo;

public class CommentBean {
	private String id;
	private String time;
	private String postId;
	private String parentId;
	private String content;
	private String publisherId;
	private String isExist;
	
	public CommentBean(String id, String time, String postId, String parentId, String content, String publisherId,
			String isExist) {
		super();
		this.id = id;
		this.time = time;
		this.postId = postId;
		this.parentId = parentId;
		this.content = content;
		this.publisherId = publisherId;
		this.isExist = isExist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getIsExist() {
		return isExist;
	}

	public void setIsExist(String isExist) {
		this.isExist = isExist;
	}
}
