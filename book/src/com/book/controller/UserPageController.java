package com.book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.PostService;
import com.book.service.UserService;

/**
 * Servlet implementation class UserPageController
 */
@WebServlet("/UserPageController")
public class UserPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService;
    private PostService postService;
    public UserPageController() {
        super();
        userService = new UserService();
        postService = new PostService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
