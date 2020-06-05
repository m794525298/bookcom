package com.book.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.book.Interface.Comment;
import com.book.dao.CommentDao;
import com.book.dao.PostDao;
import com.book.dao.UserDao;
import com.book.pojo.CommentBean;
import com.book.pojo.UserBean;

public class CommentService implements Comment{
	final static int countEachPage = 10;

	@Override
	public boolean publishComment(CommentBean comment) {
		int res = CommentDao.insertComment(comment);
		if(res == 0)
			res = PostDao.addCommentNum(comment.getPostId());
		
		return res == 0 ? true : false;
	}
	
	private JSONObject getCommentJSON(ResultSet rs, int commentNum) {
		JSONObject json = new JSONObject();
		
		try {
			if (rs == null || !rs.next() || commentNum == 0) {
				json.put("totalPage","0");
				json.put("num","0");
				json.put("posts", "[]");
			} else {
				int num;
				List<JSONObject> comments = new LinkedList<JSONObject>();
				
				for(num = 0, rs.previous(); rs.next(); ++num) {
					JSONObject comment = new JSONObject();
					comment.put("commentID", rs.getString("COMMENT_ID"));
					comment.put("publisher", rs.getString("COMMENT_PUBLISHERID"));
					comment.put("time", rs.getString("COMMENT_TIME"));
					comment.put("content", rs.getString("COMMENT_CONTENT"));
					ResultSet publisher = UserDao.getUser(rs.getString("COMMENT_PUBLISHERID"));
					if(publisher.next()) {
						comment.put("icon", publisher.getString("USER_ICON"));
						comment.put("publisherName", publisher.getString("USER_NICKNAME"));
					}
					if(rs.getString("COMMENT_PARENTID") != null) {						
						CommentBean commentParent = CommentDao.admin_selectByID(rs.getString("COMMENT_PARENTID"));
						if(commentParent.getIsExist().equals("0")) {
							comment.put("commentParentContent", commentParent.getContent());
							comment.put("commentParentPublisher", commentParent.getPublisherId());
							ResultSet parentPublisher = UserDao.getUserName(rs.getString("COMMENT_PUBLISHERID"));
							if(parentPublisher.next())
								comment.put("commentParentPublisherName", parentPublisher.getString(1));
						} else {
							comment.put("commentParentContent", "此评论不可见");
							comment.put("commentParentPublisher", "此评论不可见");
							comment.put("commentParentPublisherName", "此评论不可见");
						}
					} else {
						comment.put("commentParentContent", "");
						comment.put("commentParentPublisher", "");
						comment.put("commentParentPublisherName", "");
					}
					
					comments.add(comment);
				}
				json.put("num", String.valueOf(num));
				json.put("comments", comments);
				int totalPage = (commentNum % countEachPage == 0) ? (commentNum / countEachPage) : (commentNum / countEachPage + 1);
				json.put("totalPage", String.valueOf(totalPage));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Override
	public JSONObject getPostComments(String postId, String page) {
		int commentNum = CommentDao.getPostCommentNum(postId);
		ResultSet rs = CommentDao.getPostComments(postId, page, countEachPage);
		
		return getCommentJSON(rs, commentNum);
	}

	private JSONObject getReplyJSON(ResultSet rs, int commentNum, String userId, int limit) {
		JSONObject json = new JSONObject();
		
		try {
			if (rs == null || !rs.next() || commentNum == 0) {
				json.put("totalPage","0");
				json.put("num","0");
				json.put("posts", "[]");
			} else {
				int num;
				List<JSONObject> comments = new LinkedList<JSONObject>();
				
				for(num = 0, rs.previous(); rs.next() && num < limit;) {
					JSONObject comment = new JSONObject();
					CommentBean commentBean = CommentDao.admin_selectByID(rs.getString("COMMENT_ID"));
					if(commentBean.getPublisherId().equals(userId)) {
						continue;
					}
					
					if(commentBean.getParentId() != null) {
						CommentBean commentParent = CommentDao.admin_selectByID(commentBean.getParentId());
						
						if(commentParent.getIsExist().equals("0")) {
							comment.put("commentParentContent", commentParent.getContent());
							comment.put("commentParentPublisher", commentParent.getPublisherId());
							ResultSet parentPublisher = UserDao.getUserName(commentParent.getPublisherId());
							if(parentPublisher.next())
								comment.put("commentParentPublisherName", parentPublisher.getString(1));
						} else {
							comment.put("commentParentContent", "此评论不可见");
							comment.put("commentParentPublisher", "此评论不可见");
							comment.put("commentParentPublisherName", "此评论不可见");
						}
					} else {
						comment.put("commentParentContent", "");
						comment.put("commentParentPublisher", "");
						comment.put("commentParentPublisherName", "");
					}
					
					comment.put("commentID", commentBean.getId());
					comment.put("publisher", commentBean.getPublisherId());
					comment.put("time", commentBean.getTime());
					comment.put("content", commentBean.getContent());
					
					ResultSet post = PostDao.selectPostByID(commentBean.getPostId());
					if(post.next())
						comment.put("postID", post.getString("POST_ID"));
						comment.put("cover", post.getString("POST_COVER"));
					
					ResultSet publisher = UserDao.getUserName(commentBean.getPublisherId());
					if(publisher.next())
						comment.put("publisherName", publisher.getString(1));
					
					 ++num;
					
					comments.add(comment);
				}
				json.put("num", String.valueOf(num));
				json.put("comments", comments);
				int totalPage = (commentNum % countEachPage == 0) ? (commentNum / countEachPage) : (commentNum / countEachPage + 1);
				json.put("totalPage", String.valueOf(totalPage));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return json;
	}
	
	@Override
	public JSONObject getUserComments(String userId, String page) {
		int replyNum = CommentDao.getUserCommentNum(userId);
		ResultSet rs = CommentDao.getUserComments(userId, page, countEachPage);
		
		return getReplyJSON(rs, replyNum, userId, countEachPage);
	}
	
	@Override
	public JSONObject getUserPageComments(String userId) {
		int commentsNum = 15;
		int replyNum = CommentDao.getUserCommentNum(userId);
		ResultSet rs = CommentDao.getUserComments(userId, "1", commentsNum);
		
		return getReplyJSON(rs, replyNum, userId, commentsNum);
	}
}