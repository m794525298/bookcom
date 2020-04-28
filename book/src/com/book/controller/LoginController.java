package com.book.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.common.Coder;
import com.book.service.LoginService;


@WebServlet("/Login")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private LoginService service;
    public LoginController() {
        super();
        this.service = new LoginService();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = new String(request.getParameter("Account").getBytes("ISO-8859-1"),"UTF-8");
		String password = new String(request.getParameter("Password"));
		ResultSet rs = service.login(account,password);
		Cookie cookie;
		try {
			if (!rs.wasNull()) {
				cookie = new Cookie("Id",Coder.encryptedId(rs.getInt("USER_ID")));
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
				
				cookie = new Cookie("Account",account);
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
				
				cookie = new Cookie("Nickname",rs.getString("USER_NICKNAME"));
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
				
				cookie = new Cookie("Icon",rs.getString("USER_ICON"));
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
				
				response.getWriter().append("登錄成功");
			}else{
				response.getWriter().append("用戶名或密碼錯誤");
			}
		} catch (SQLException e) {
			response.getWriter().append("500 Internal Server Error");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}




}
