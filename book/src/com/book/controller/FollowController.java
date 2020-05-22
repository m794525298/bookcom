package com.book.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.service.FollowService;

/**
 * Servlet implementation class AddFollowController
 */
@WebServlet("/Follow")
public class FollowController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FollowService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowController() {
        super();
        this.service = new FollowService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String[]> map = request.getParameterMap();
		JSONObject rs = new JSONObject();
		String userId =map.get("userID")[0];
		String followingUser = map.get("followingUser")[0];
		System.out.println(userId);
		System.out.println(followingUser);
		if (service.isFollow(userId, followingUser)) {
			if (service.disfollow(map.get("userID")[0], map.get("followingUser")[0]) == 0)
			{
				rs.put("success", true+"");
			}else {
				rs.put("success", false+"");
			}
		}else {
			if ( service.follow(map.get("userID")[0], map.get("followingUser")[0]) == 0)
			{
				rs.put("success", true+"");
			}else {
				rs.put("success", false+"");
			}
		} 
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
