package com.book.manage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONObject;
import com.book.dao.UserDao;
import com.book.pojo.UserBean;
import com.book.service.LoginService;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/manage/adminlogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String account = request.getParameter("account");
		String password = request.getParameter("password");

		UserBean user = UserDao.admin_signIn(account, password);
				
		HttpSession session = request.getSession();
				
		if(user != null) {
			if(user.getIdentity().equals("2")) {
				session.setAttribute("username", user.getNickname());
				session.setAttribute("isAdminLogin", "1");
		
				response.sendRedirect("admin_douserselect");
			} else {
				session.setAttribute("isAdminLogin", "0");

				PrintWriter out = response.getWriter();
				
				out.write("<script>");
				out.write("alert('非管理员用户请于本页面登录');");
				out.write("location.href='../login.jsp'");
				out.write("</script>");
			}
		} else {
			session.setAttribute("isAdminLogin", "0");

			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('账号或密码错误！');");
			out.write("window.history.go(-1)");
			out.write("</script>");
		}
	}

}
