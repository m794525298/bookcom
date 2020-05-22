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
 * Servlet implementation class PasswordController
 */
@WebServlet("/ChangePassword")
public class ChangePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService service;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordController() {
        super();
        this.service = new UserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userID");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		System.out.println(userId);
		System.out.println(oldPassword);
		System.out.println(newPassword);
		JSONObject rs = new JSONObject();
		if (service.updatedUserPassword(userId, oldPassword, newPassword)) {
			rs.put("success", true+"");
		}else {
			rs.put("success", false+"");
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
