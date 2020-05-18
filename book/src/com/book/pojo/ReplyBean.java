package com.book.pojo;

public class ReplyBean {
	private String replyId;
	private String commentId;
	private String commentPostPublisherId;
	private String commentParentPublisherId;
	
	
	public ReplyBean(String replyId, String commentId, String commentPostPublisherId, String commentParentPublisherId) {
		super();
		this.replyId = replyId;
		this.commentId = commentId;
		this.commentPostPublisherId = commentPostPublisherId;
		this.commentParentPublisherId = commentParentPublisherId;
	}


	public String getReplyId() {
		return replyId;
	}


	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}


	public String getCommentId() {
		return commentId;
	}


	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}


	public String getCommentPostPublisherId() {
		return commentPostPublisherId;
	}


	public void setCommentPostPublisherId(String commentPostPublisherId) {
		this.commentPostPublisherId = commentPostPublisherId;
	}


	public String getCommentParentPublisherId() {
		return commentParentPublisherId;
	}


	public void setCommentParentPublisherId(String commentParentPublisherId) {
		this.commentParentPublisherId = commentParentPublisherId;
	}
}
