package com.book.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.PostService;

/**
 * Servlet implementation class UserPagePostController
 */
@WebServlet("/UserPagePost")
public class UserPagePostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService service;
    
    public UserPagePostController() {
        super();
        this.service = new PostService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		Map<String,String[]> map = request.getParameterMap();

		response.getWriter().write(service.getUserPagePost(request.getParameter("userID")).toJSONString());
	}

}