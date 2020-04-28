package com.book.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
		if (login(account,password)) {
			response.getWriter().append("登錄成功");
		}else{
			response.getWriter().append("用戶名或密碼錯誤");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public boolean login(String account, String password) {
		UserDao userDao = new UserDao();
		ResultSet rs;
		try {
			rs = userDao.login(account, Coder.encryptedPassword(password));
			return (rs.wasNull())?false:true;
		} catch (NoSuchAlgorithmException e1) {
			return false;
		} catch (SQLException e) {
			return false;
		}
		
	}



}
