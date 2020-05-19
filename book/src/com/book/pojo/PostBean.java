package com.book.pojo;

public class PostBean {
	private String title;
	private String publisherId;
	private String content;
	private String bookId;
	private String cover;
	PostBean(){
		super();
	}
	
	public void setCover(String cover) {
		this.cover = cover;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public  void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}
	
	public  void setContent(String content) {
		this.content = content;
	}
	
	public  void setBookId(String bookId) {
		this.bookId = bookId;
	}
	
	public  String getTitle() {
		return title;
	}
	
	public  String getPublisherId() {
		return publisherId;
	}
	
	public  String getContent() {
		return content;
	}
	
	public  String getBookId() {
		return bookId;
	}
	
	public String getCover() {
		return cover;
	}
}
