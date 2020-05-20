package com.book.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.PostService;

/**
 * Servlet implementation class PostDetailController
 */
@WebServlet("/PostDetail")
public class PostDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostDetailController() {
        super();
        this.service = new PostService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write(service.getPostDetail(request.getParameter("postID")).toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
