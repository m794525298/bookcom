package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.pojo.CommentBean;
import com.book.service.CommentService;

/**
 * Servlet implementation class publishCommentController
 */
@WebServlet("/PublishComment")
public class PublishCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map <String , String[]> map;
	private CommentService service;
	
    public PublishCommentController() {
        super();
		this.service = new CommentService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		
		map =request.getParameterMap();
		if (!map.containsKey("postID") || map.get("postID")[0].equals("")) {
			json.put("success", "false");
		}else if (!map.containsKey("content") || map.get("content")[0].equals("")) {
			json.put("success", "false");
		}else if (!map.containsKey("commentPublisher") || map.get("commentPublisher")[0].equals("")) {
			json.put("success", "false");
		}else{
			String postId = map.get("postID")[0];
//			String content = new String(Base64.getDecoder().decode(map.get("content")[0]),"UTF-8");
			String content = map.get("content")[0];
			System.out.println(content);
			String parentId = map.containsKey("parent") ? map.get("parent")[0] : null;
			String publisherId = map.get("commentPublisher")[0];
			
			CommentBean comment = new CommentBean(null, null, postId, parentId, content, publisherId, null);
			
			json.put("success", String.valueOf(service.publishComment(comment)));
		}
		
		PrintWriter out = response.getWriter();
		out.println(json.toJSONString());
		out.flush();
		out.close();
	}

}
