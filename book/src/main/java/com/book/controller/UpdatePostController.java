package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.dao.PostDao;
import com.book.pojo.PostBean;
import com.book.service.PostService;

/**
 * Servlet implementation class UpdatePostController
 */
@WebServlet("/UpdatePost")
public class UpdatePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map <String , String[]> map;
	private PostService service;

 
    public UpdatePostController() {
        super();
        this.service = new PostService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json = new JSONObject();
		map =request.getParameterMap();
		if (!map.containsKey("publisher") || map.get("publisher")[0].equals("")) {
			json.put("success", "false");
		}else if (!map.containsKey("postTitle") ||map.get("postTitle")[0].equals("")) {
			json.put("success", "false");
		}else if (!map.containsKey("postTitle") ||map.get("postTitle")[0].equals("")) {
			json.put("success", "false");
		}else if (!map.containsKey("bookTitle") ||map.get("bookTitle")[0].equals("")) {
			json.put("success", "false");
		}else if (!map.containsKey("bookType") ||map.get("bookType")[0].equals("")) {
			json.put("success", "false");
		}else if (!map.containsKey("author") ||map.get("author")[0].equals("")) {
			json.put("success", "false");
		}else if (!map.containsKey("content") ||map.get("content")[0].equals("")) {
			json.put("success", "false");
		}else if (!map.containsKey("cover") ||map.get("cover")[0].equals("")) {
			json.put("success", "false");
		}else if (!map.containsKey("postID") ||map.get("postID")[0].equals("")) {
			json.put("success", "false");
		}else {
			String postId = map.get("postID")[0];
			String cover = map.get("cover")[0];
			String content = map.get("content")[0];
			String title = map.get("postTitle")[0];
			String bookType = map.get("bookType")[0];
			String bookTitle = map.get("bookTitle")[0];
			String bookAuthor = map.get("author")[0];
			String publisherId = map.get("publisher")[0];
			
			PostBean post = new PostBean(postId, null, cover, content, title, bookType, bookTitle, bookAuthor, publisherId, null);

			json.put("success", String.valueOf(service.updatedPost(post)));
			
			PrintWriter out = response.getWriter();
			out.println(json.toJSONString());
			out.flush();
			out.close();
		}
	}

}
