package com.book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.service.PostService;

/**
 * Servlet implementation class DeletePostController
 */
@WebServlet("/DeletePost")
public class DeletePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService service;
    public DeletePostController() {
        super();
        this.service = new PostService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject rs = new JSONObject();
		rs.put("success",service.deletePost(request.getParameter("userID"),request.getParameter("postID")));
		response.getWriter().write(rs.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
