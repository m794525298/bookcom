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
		return getSearchJSON(rs,page);
		
	}

	@Override
	public JSONObject searchPostByKeyword(String keyword, String page) {
		ResultSet rs = PostDao.searchPost(keyword);
		return getSearchJSON(rs,page);
	}
	
	private JSONObject getSearchJSON(ResultSet rs,String page) {
		JSONObject json = new JSONObject();
		int totalPage;
		try {
				totalPage = rs.getRow()/10;
				if (rs.getRow()%10 != 0) totalPage++;
				int num = (totalPage != Integer.valueOf(page))?10:rs.getRow()%10;
				int i = 0;
				
				json.put("totalPage",totalPage);
				json.put("num",num);
				rs.relative(Integer.valueOf(page)*10 - num);
				
				List<String> posts = new LinkedList<String>();
				while(rs.next()) {
					if (i == num) break;
					JSONObject post = new JSONObject();
					post.put("postID",rs.getString("POST_ID"));
					post.put("title",rs.getString("POST_TITLE"));
					post.put("content",rs.getString("POST_CONTENT"));
					post.put("publisher",rs.getString("POST_PUBLISHER"));
					post.put("cover",rs.getString("POST_COVER"));
					post.put("time",rs.getDate("POST_TIME"));
					post.put("publisherName",UserDao.getUser(rs.getString("POSE_PUBLISEHERID")));
					post.put("commentNum",rs.getString("POST_COMMENTNUM"));
					posts.add(post.toString());
					i++;
				}
				json.put("posts", posts);
				return json;
		} catch (SQLException e) {
				return null;
		}
			
		
	}

	@Override
	public JSONObject searchPostByUser(String keyword, String page) {
		ResultSet rs = PostDao.searchPostByNickName(keyword);
		return getSearchJSON(rs,page);
	}

	@Override
	public JSONObject getHotPost(String page) {
		ResultSet rs = PostDao.getHotPost(page);
		int count = PostDao.getPostNum();
		int totalPage = (count%10 != 0)?count/10+1:count/10;
		int num = (Integer.valueOf(page) != totalPage)?10:count%10;
		JSONObject json = new JSONObject();
		json.put("totalPage", totalPage);
		json.put("num",num);
		List<String> posts = new LinkedList<String>();
		try {
			while(rs.next()) {
				JSONObject post = new JSONObject();
					post.put("postID",rs.getString("POST_ID"));
					post.put("title",rs.getString("POST_TITLE"));
					post.put("content",rs.getString("POST_CONTENT"));
					post.put("publisher",rs.getString("POST_PUBLISHER"));
					post.put("cover",rs.getString("POST_COVER"));
					post.put("time",rs.getDate("POST_TIME"));
					post.put("publisherName",UserDao.getUser(rs.getString("POSE_PUBLISEHERID")));
					post.put("commentNum",rs.getString("POST_COMMENTNUM"));
				posts.add(post.toString());
			}
		} catch (SQLException e) {
			return null;
		}
		json.put("posts", posts);
		return json;
	}
	
}
