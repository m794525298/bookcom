package com.book.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.common.Coder;
import com.book.dao.UserDao;

/**
 * Servlet implementation class UserServiceImpl1
 */
@WebServlet("/Regsist")
public class Regsist extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    public Regsist() {
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = new String(request.getParameter("account").getBytes("ISO-8859-1"),"UTF-8");
		String password = new String(request.getParameter("password"));
		String email = new String(request.getParameter("email"));
		response.setContentType("text/html;charset=UTF-8");
		int code = regsist(account,password,email); 
		switch(code) {
		case 0 :
			response.getWriter().append("注册成功");
			break;
		case 1 :
			response.getWriter().append("500 Internal Server Error");
			break;
		case 2 :
			response.getWriter().append("該用戶名已被注册");
			break;
		case 3 :
			response.getWriter().append("該邮箱已被注册");
			break;
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


	public int regsist(String account, String password,String email) {
		int code;
		try {
			code = UserDao.regsist(account, Coder.encryptedPassword(password), email);
			return code;
		} catch (NoSuchAlgorithmException e) {
			return 1;
		}
		
	}

	


}
