package com.book.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.service.UserService;

@WebServlet("/Search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService;
    public SearchController() {
        super();
        this.userService = new UserService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map <String , String[]> map = request.getParameterMap();
		String page = (map.containsKey("page"))?map.get("page")[0]:"1";
		String keyword = map.get("keyword")[0];
		String bookType = (map.containsKey("bookType"))?map.get("bookType")[0]:"";
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
