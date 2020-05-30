package com.book.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.book.Interface.Post;
import com.book.common.Coder;
import com.book.dao.PostDao;
import com.book.dao.UserDao;
import com.book.pojo.PostBean;

public class PostService implements Post {

	@Override
	public JSONObject publishPost(PostBean post) {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		int postId;
		try {
			postId = PostDao.insertPost(post);
			
			System.out.println(postId);
			
			if(postId > 0) {
				json.put("success", "true");
				json.put("postID", String.valueOf(postId));
			} else {
				json.put("success", "false");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			json.put("success", "false");
		}
		
		return json;
	}

	@Override
	public boolean updatedPost(PostBean post) {
		// TODO Auto-generated method stub
		int res = PostDao.admin_update(post);
		return res > 0 ? true : false;
	}
	
	@Override
	public JSONObject searchPostByKeyword(String keyword, String bookType, String page) {
		ResultSet rs = PostDao.searchPost(keyword, bookType);
		return getPostJSON(rs,page, 10);
		
	}

	@Override
	public JSONObject searchPostByKeyword(String keyword, String page) {
		ResultSet rs = PostDao.searchPost(keyword);
		return getPostJSON(rs,page, 10);
	}
	
	private JSONObject getPostJSON(ResultSet rs,String page, int count) {
		JSONObject json = new JSONObject();
		int totalPage,num;
		try {
				if (!rs.next()) {
					json.put("totalPage",0+"");
					json.put("num",0+"");
					json.put("posts", "[]");
					return json;
				}
				rs.last();
				totalPage = rs.getRow()/count;
				if (rs.getRow()%count != 0) {
					totalPage++;
					num = (totalPage != Integer.valueOf(page))?count:rs.getRow()%count;
				}else{
					num = count;
				}

				int i = 0;
				json.put("totalPage",totalPage+"");
				json.put("num",num+"");	
				rs.beforeFirst();	
				rs.relative((Integer.valueOf(page)-1)*count);
				
				List<JSONObject> posts = new LinkedList<JSONObject>();
				while(rs.next()) {
					if (i == num) break;
					JSONObject post = new JSONObject();
					post.put("postID",rs.getString("POST_ID"));
					post.put("postTitle",rs.getString("POST_POSTTITLE"));
					post.put("content",rs.getString("POST_CONTENT"));
					post.put("publisher",rs.getString("POST_PUBLISHERID"));
					post.put("cover",rs.getString("POST_COVER"));
					post.put("time",rs.getString("POST_TIME"));
					post.put("bookType", rs.getString("POST_BOOKTYPE"));
					post.put("bookTitle", rs.getString("POST_BOOKTITLE"));
					post.put("author", rs.getString("POST_BOOKAUTHOR"));
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
				json.put("posts", posts);
				return json;
		} catch (SQLException e) {
				System.out.println(e);
				return null;
		}
		
	}


	@Override
	public JSONObject searchPostByUser(String keyword, String page) {
		ResultSet rs = PostDao.searchPostByNickName(keyword);
		return getPostJSON(rs,page, 10);
	}

	@Override
	public JSONObject getHotPost(String page) {
		ResultSet rs = PostDao.getHotPost(page);
		
		return getPostJSON(rs,page, 10);	
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
			System.out.println(rs.toJSONString());
			return rs;
		} catch (SQLException e) {
			return null;
		}
	}

	@Override
	public JSONObject getPostByUserID(String userId, String page) {
		ResultSet rs = PostDao.getPostByUser(userId);
		return getPostJSON(rs,page, 10);
	}
	
	@Override
	public JSONObject getUserPagePost(String userId) {
		ResultSet rs = PostDao.getPostByUser(userId);
		return getPostJSON(rs, "1", 20);
	}
}
