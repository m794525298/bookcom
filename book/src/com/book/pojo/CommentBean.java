package com.book.pojo;

public class CommentBean {
	private String id;
	private String time;
	private String parent;
	private String postId;
	private String content;
	private String publisherId;
	
	public CommentBean(String id, String time, String parent, String postId, String content, String publisherId) {
		super();
		this.id = id;
		this.time = time;
		this.parent = parent;
		this.postId = postId;
		this.content = content;
		this.publisherId = publisherId;
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

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
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
}
