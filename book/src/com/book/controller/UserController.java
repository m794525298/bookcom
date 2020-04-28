package com.book.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.common.Coder;
import com.book.service.UserService;

@WebServlet("/User")
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserService service;
	
	UserController(){
		super();
		this.service = new UserService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = new String(request.getParameter("Id"));
		String account = new String(request.getParameter("Account").getBytes("ISO-8859-1"),"UTF-8");
		response.getWriter().append(service.getUser(id,account).toString());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = Coder.decryptedId(new String(request.getParameter("Id")));
		String account = new String(request.getParameter("Account").getBytes("ISO-8859-1"),"UTF-8");
		String icon = new String(request.getParameter("Icon"));
		String nickname = new String( request.getParameter("Nickname"));
		if (service.validUser(id,account)) {
			response.getWriter().append("500 Internal Server Error");
			return;
		}
		if (!service.saveIcon(id, icon)) {
			response.getWriter().append("500 Internal Server Error");
			return;
		}
		int endIndex = request.getRequestURL().length() - request.getPathInfo().length() + 1;
        String url = request.getRequestURL().substring(0, endIndex);
		String iconPath = url + "Icon/" + id + ".jpg";
		int code = service.updatedUser(id, iconPath, nickname);
		switch(code) {
		case 0 :
			response.getWriter().append("更新成功");
			break;
		case 1 :
			response.getWriter().append("500 Internal Server Error");
			break;
		}
		
		
	}

	
}
