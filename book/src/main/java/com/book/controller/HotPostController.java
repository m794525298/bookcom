package com.book.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.service.PostService;

/**
 * Servlet implementation class HotPostController
 */
@WebServlet("/HotPost")
public class HotPostController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PostService service;
    public HotPostController() {
        super();
        this.service = new PostService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		Map<String,String[]> map = request.getParameterMap();
		String page = (!map.containsKey("page") ||map.get("page")[0].equals(""))?"1":map.get("page")[0];
		JSONObject rs = service.getHotPost(page);
		response.getWriter().write(rs.toJSONString());
	}

}
