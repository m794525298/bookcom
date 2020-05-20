package com.book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.service.FollowService;

/**
 * Servlet implementation class GetFollow
 */
@WebServlet("/IsFollow")
public class IsFollowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private FollowService service;
    public IsFollowController() {
        super();
        this.service = new FollowService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userID");
		String followingUser = request.getParameter("followingUser");
		JSONObject rs = new JSONObject();
		rs.put("following",service.isFollow(userId, followingUser));
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
