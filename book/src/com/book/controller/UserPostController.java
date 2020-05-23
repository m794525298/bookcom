package com.book.controller;

import java.io.IOException;
import java.util.Map;

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
		Map<String,String[]> map = request.getParameterMap();
		
		String page = (!map.containsKey("page")||map.get("page")[0].equals(""))?"1":map.get("page")[0];
		response.getWriter().write(service.getPostByUserID(request.getParameter("userID"),page).toJSONString());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
