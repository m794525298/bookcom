package com.book.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Base64;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.dao.PostDao;
import com.book.service.PostService;
import com.book.service.UserService;

@WebServlet("/Search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService userService;
    private PostService postService;
    public SearchController() {
        super();
        this.userService = new UserService();
        this.postService = new PostService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		Map <String , String[]> map = request.getParameterMap();
		String page = (map.containsKey("page") && !map.get("page")[0].equals("null") && !map.get("page")[0].equals(""))?map.get("page")[0]:"1";
		String keyword =(map.containsKey("keywords"))?new String(Base64.getDecoder().decode(map.get("keywords")[0]),"UTF-8"):"";
		String bookType = (map.containsKey("bookType") && !map.get("bookType")[0].equals(""))?map.get("bookType")[0]:"";
		String searchType = "0";
		if (map.containsKey("searchType")&& !map.get("searchType")[0].equals("null")) {
			if (map.get("searchType")[0].equals("0") ||map.get("searchType")[0].equals("1")){
				searchType =map.get("searchType")[0];
			}
		}
		
		JSONObject rs = new JSONObject();
		if (searchType.equals("0")) {
			rs = (bookType.equals(""))?postService.searchPostByKeyword(keyword, page):postService.searchPostByKeyword(keyword, bookType, page);
		}else {
			rs = postService.searchPostByUser(keyword, page);
		}
		System.out.println(rs.toJSONString());
		response.getWriter().write(rs.toJSONString());
	}

}
