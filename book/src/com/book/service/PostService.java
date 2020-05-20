package com.book.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.book.Interface.Post;
import com.book.dao.PostDao;
import com.book.dao.UserDao;

public class PostService implements Post {

	@Override
	public int updatedPost(Post post) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public JSONObject searchPostByKeyword(String keyword, String bookType, String page) {
		ResultSet rs = PostDao.searchPost(keyword, bookType);
		return getPostJSON(rs,page);
		
	}

	@Override
	public JSONObject searchPostByKeyword(String keyword, String page) {
		ResultSet rs = PostDao.searchPost(keyword);
		return getPostJSON(rs,page);
	}
	
	private JSONObject getPostJSON(ResultSet rs,String page) {
		JSONObject json = new JSONObject();
		int totalPage;
		try {
				rs.last();
				totalPage = rs.getRow()/10;
				if (rs.getRow()%10 != 0) totalPage++;
				int num = (totalPage != Integer.valueOf(page))?10:rs.getRow()%10;
				if (rs.getRow()<10) num = rs.getRow();
				int i = 0;
				json.put("totalPage",totalPage);
				json.put("num",num);
				rs.relative(Integer.valueOf(page)*10 - num);
				
				List<JSONObject> posts = new LinkedList<JSONObject>();
				rs.beforeFirst();
				while(rs.next()) {
					if (i == num) break;
					JSONObject post = new JSONObject();
					post.put("postID",rs.getString("POST_ID"));
					post.put("title",rs.getString("POST_POSTTITLE"));
					post.put("content",rs.getString("POST_CONTENT"));
					post.put("publisher",rs.getString("POST_PUBLISHERID"));
					post.put("cover",rs.getString("POST_COVER"));
					post.put("time",rs.getString("POST_TIME"));
					ResultSet temp = UserDao.getUserName(rs.getString("POST_PUBLISHERID"));
					String publisherName = "";
					while(temp.next()) {
						publisherName =temp.getString("USER_NICKNAME");
					}
					post.put("publisherName",publisherName);
					post.put("commentNum",rs.getString("POST_COMMENTNUM"));
					posts.add(post);
					i++;
				}
				json.put("post", posts);
				return json;
		} catch (SQLException e) {
				System.out.println(e);
				return null;
		}
			
		
	}

	@Override
	public JSONObject searchPostByUser(String keyword, String page) {
		ResultSet rs = PostDao.searchPostByNickName(keyword);
		return getPostJSON(rs,page);
	}

	@Override
	public JSONObject getHotPost(String page) {
		ResultSet rs = PostDao.getHotPost(page);
		
		return getPostJSON(rs,page);
		
	}

	@Override
	public boolean deletePost(String userId, String postId) {
		return PostDao.deletePost(postId, userId);
	}

	@Override
	public JSONObject getPostDetail(String postId) {
		try {
			JSONObject rs = new JSONObject();
			ResultSet postSet = PostDao.getPostDetail(postId);
			String userId = "";
			while(postSet.next()) {
			userId = postSet.getString("POST_PUBLISHERID");
			rs.put("postID",postSet.getString("POST_ID"));
			rs.put("author",postSet.getString("POST_BOOKAUTHOR"));
			rs.put("content",postSet.getString("POST_CONTENT"));
			rs.put("postTitle",postSet.getString("POST_POSTTITLE"));
			rs.put("bookTitle",postSet.getString("POST_BOOKTITLE"));
			rs.put("publisher",postSet.getString("POST_PUBLISHERID"));
			rs.put("cover",postSet.getString("POST_COVER"));
			rs.put("time",postSet.getString("POST_TIME"));
			}
			ResultSet userSet = UserDao.getUser(userId);
			while(userSet.next()) {
				rs.put("icon",userSet.getString("USER_ICON"));
				rs.put("publisherName",userSet.getString("USER_NICKNAME"));
			}
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public JSONObject getPostByUserID(String userId, String page) {
		ResultSet rs = PostDao.getPostByUser(userId);
		return getPostJSON(rs,page);
	}
	
}
