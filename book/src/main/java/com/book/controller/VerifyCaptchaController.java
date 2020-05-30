package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.book.service.RegsistService;

/**
 * Servlet implementation class VerifyCaptchaController
 */
@WebServlet("/VerifyCaptcha")
public class VerifyCaptchaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Map <String , String[]> map;
	private RegsistService service;
	
	public VerifyCaptchaController() {
		super();
		this.service = new RegsistService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject json = new JSONObject();
		
		map =request.getParameterMap();
		if (!map.containsKey("email") || map.get("email")[0].equals("")) {
			json.put("correct", "false");
		} else if (!map.containsKey("captcha") || map.get("captcha")[0].equals("")) {
			json.put("correct", "false");
		} else {
			String email = map.get("email")[0];
			String captcha = map.get("captcha")[0];
			
			if(service.isCaptchaCorrect(email, captcha)) {
					json.put("correct", "true");
					json.put("email", email);
			}
			else {
				json.put("corrent", "false");
			}
		}
		
		PrintWriter out = response.getWriter();
		out.println(json.toJSONString());
		out.flush();
		out.close();
	}

}
