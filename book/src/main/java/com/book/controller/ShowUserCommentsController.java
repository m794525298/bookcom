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
 * Servlet implementation class ShowUserCommentController
 */
@WebServlet("/ShowUserComments")
public class ShowUserCommentsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map <String , String[]> map;
	private CommentService service;
	
    public ShowUserCommentsController() {
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
		response.setContentType("text/html;charset=UTF-8");
		
		JSONObject json = new JSONObject();
		
		map =request.getParameterMap();
		String userId = map.get("userID")[0];
		String page = (!map.containsKey("page")|| map.get("page")[0].equals("null") || map.get("page")[0].equals(""))? "1" : map.get("page")[0];

		
		json = service.getUserComments(userId, page);
		
		PrintWriter out = response.getWriter();
		out.println(json.toJSONString());
		out.flush();
		out.close();
	}

}
