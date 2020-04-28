package com.book.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.common.Coder;
import com.book.dao.UserDao;


@WebServlet("/Login")
public class Login extends HttpServlet{
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = new String(request.getParameter("account").getBytes("ISO-8859-1"),"UTF-8");
		String password = new String(request.getParameter("password"));
		ResultSet rs = login(account,password);
		Cookie cookie;
		try {
			if (!rs.wasNull()) {
				cookie = new Cookie("USER_ID",Coder.encryptedPassword(String.valueOf(rs.getInt("USER_ID"))));
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
				
				cookie = new Cookie("USER_NICKNAME",rs.getString("USER_NICKNAME"));
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
				
				cookie = new Cookie("USER_ICON",rs.getString("USER_ICON"));
				cookie.setMaxAge(3600);
				response.addCookie(cookie);
				
				response.getWriter().append("登錄成功");
			}else{
				response.getWriter().append("用戶名或密碼錯誤");
			}
		} catch (SQLException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public ResultSet login(String account, String password) {
		UserDao userDao = new UserDao();
		ResultSet rs;
		try {
			rs = userDao.login(account, Coder.encryptedPassword(password));
			return rs;
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}



}
