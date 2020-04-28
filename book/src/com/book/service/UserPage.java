package com.book.service;

import java.io.IOException;

import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.UserDao;
@WebServlet("/UserPage")
public class UserPage {
	
	UserPage(){
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(getUser(request.getParameter("Id")).toString());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public ResultSet getUser(String id) {
		ResultSet rs = UserDao.getUser(id);
		return rs;
		
		
	}
}
