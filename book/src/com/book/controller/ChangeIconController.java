package com.book.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.service.UserService;
import java.util.regex.*;
/**
 * Servlet implementation class IconController
 */
@WebServlet("/ChangeIcon")
public class ChangeIconController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserService service;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeIconController() {
        super();
        this.service = new UserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userID");
		BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) {
		    sb.append(temp);
		}
		br.close();
		temp = sb.toString();
		int i=temp.indexOf("name=\"Icon\"");
		temp=temp.substring(i+11);
		i = temp.indexOf("-");
		temp=temp.substring(0,i);
		String icon = temp;
		JSONObject rs = new JSONObject();
		String path = service.updatedIcon(userId, icon);
		rs.put("path",path);
		response.getWriter().write(rs.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
