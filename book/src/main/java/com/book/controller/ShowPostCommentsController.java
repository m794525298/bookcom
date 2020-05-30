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
import com.book.service.CommentService;

/**
 * Servlet implementation class getComments
 */
@WebServlet("/ShowPostComments")
public class ShowPostCommentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map <String , String[]> map;
	private CommentService service;
    public ShowPostCommentsController() {
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
		response.setContentType("text/html;charset=UTF-8");

		JSONObject json = new JSONObject();

		map =request.getParameterMap();
		String page = (!map.containsKey("page") || map.get("page")[0].equals(""))? "1" : map.get("page")[0];
		String postId = map.get("postID")[0];
		
		json = service.getPostComments(postId, page);
				
		PrintWriter out = response.getWriter();
		out.println(json.toJSONString());
		out.flush();
		out.close();
	}

}
