package com.book.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.service.LoginService;


@WebServlet("/Login")
public class LoginController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	Map <String , String[]> map;
    private LoginService service;
    public LoginController() {
        super();
        this.service = new LoginService();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject rs = new JSONObject();
		map = request.getParameterMap();
		if (!map.containsKey("account") || map.get("account")[0].equals("")) {
			rs.put("match", "false");
			response.getWriter().write(rs.toJSONString());
		}else if (!map.containsKey("password") || map.get("password")[0].equals("")) {
			rs.put("match", "false");
			response.getWriter().write(rs.toJSONString());
		}else {
			rs = service.login(map.get("account")[0], map.get("password")[0]);
			if (rs == null) {
				rs = new JSONObject();
				rs.put("match", "false");
			}else {
				rs.put("match", "true");
			}
			response.getWriter().write(rs.toJSONString());
			System.out.println(rs);
		}
	}




}
