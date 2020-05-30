package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.service.UserService;

/**
 * Servlet implementation class UserFollowNum
 */
@WebServlet("/GetUserFollowNum")
public class GetUserFollowNumController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    UserService service;
    public GetUserFollowNumController() {
        super();
        this.service = new UserService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
		
		String userId = request.getParameter("userID");
		
		json = service.getFollowNum(userId);
		
		PrintWriter out = response.getWriter();
		out.println(json.toJSONString());
		out.flush();
		out.close();
	}

}
