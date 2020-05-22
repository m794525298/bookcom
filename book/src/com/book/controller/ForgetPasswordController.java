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
 * Servlet implementation class ForgetPasswordController
 */
@WebServlet("/ForgetPassword")
public class ForgetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService service;
    public ForgetPasswordController() {
        super();
        this.service = new UserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String newPassword = request.getParameter("newPassword");
		JSONObject rs = new JSONObject();
		if (service.updatedUserPasswordByEmail(email, newPassword)) {
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
