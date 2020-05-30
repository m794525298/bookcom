package com.book.pojo;

public class PostBean {
	private String id;
	private String time;
	private String cover;
	private String content;
	private String title;
	private String bookType;
	private String bookTitle;
	private String bookAuthor;
	private String publisherId;
	private String commentNum;
	private String isExist;
	
	PostBean(){
		super();
	}
	
	public PostBean(String id, String time, String cover, String content, String title, String bookType,
			String bookTitle, String bookAuthor, String publisherId, String isExist) {
		this.id = id;
		this.time = time;
		this.cover = cover;
		this.content = content;
		this.title = title;
		this.bookType = bookType;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
