package com.book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.common.Coder;
import com.book.service.PostService;


@WebServlet("/UserPost")
public class UserPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService service;
    
    public UserPostController() {
        super();
        this.service = new PostService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(service.getPostByUserID(request.getParameter("userID"),request.getParameter("page")).toJSONString());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
