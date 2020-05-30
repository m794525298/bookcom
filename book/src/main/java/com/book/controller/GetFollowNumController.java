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
import com.book.service.UserService;

/**
 * Servlet implementation class GetFollowNumController
 */
@WebServlet("/GetFollowNum")
public class GetFollowNumController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map <String , String[]> map;
	private UserService service;
    
    public GetFollowNumController() {
        super();
        this.service = new UserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		if (!map.containsKey("userID")) {
			json.put("followerNum", "0");
			json.put("followingNum", "0");
		}else{
			String userId = map.get("userID")[0];
			
			json = service.getFollow(userId);
		}
		
		PrintWriter out = response.getWriter();
		out.println(json.toJSONString());
		out.flush();
		out.close();
	}

}
