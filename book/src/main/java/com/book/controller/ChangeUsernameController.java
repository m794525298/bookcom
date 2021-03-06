package com.book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.service.UserService;

/**
 * Servlet implementation class UserNameController
 */
@WebServlet("/ChangeUsername")
public class ChangeUsernameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUsernameController() {
        super();
        this.service = new UserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userID");
		String userName = request.getParameter("userName");
		JSONObject rs = new JSONObject();
		if (service.updatedUser(userId, userName)) {
			rs.put("success",true+"");
		}else {
			rs.put("success",false+"");
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
