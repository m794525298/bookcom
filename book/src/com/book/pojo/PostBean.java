package com.book.pojo;

public class PostBean {
	private String id;
	private String time;
	private String cover;
	private String content;
	private String postTitle;
	private String bookType;
	private String bookTitle;
	private String publisherId;
	private String bookAuthor;
	private String commentNum;
	private String isExist;
	
	public PostBean(String id, String time, String cover, String content, String postTitle, String bookType,
			String bookTitle, String publisherId, String bookAuthor, String commentNum, String isExist) {
		super();
		this.id = id;
		this.time = time;
		this.cover = cover;
		this.content = content;
		this.postTitle = postTitle;
		this.bookType = bookType;
		this.bookTitle = bookTitle;
		this.publisherId = publisherId;
		this.bookAuthor = bookAuthor;
		this.commentNum = commentNum;
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

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getBookType() {
		return bookType;
	}

	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}

	public String getIsExist() {
		return isExist;
	}

	public void setIsExist(String isExist) {
		this.isExist = isExist;
	}
	
}
