package com.book.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.common.Coder;
import com.book.service.RegsistService;

/**
 * Servlet implementation class UserServiceImpl1
 */
@WebServlet("/Regsist")
public class RegsistController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	Map <String , String[]> map;
	private RegsistService service;
    public RegsistController() {
    	super();
    	this.service = new RegsistService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = map.get("account")[0];
		String username = (map.containsKey("username"))?map.get("username")[0]:account;
		String password = Coder.encrypted(map.get("password")[0]);
		String email = map.get("email")[0];
		JSONObject rs = service.regsist(account,username,password,email); 
		
		response.getWriter().write(rs.toJSONString());
	}



	


}
