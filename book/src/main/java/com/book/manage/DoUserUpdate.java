package com.book.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.UserDao;
import com.book.pojo.UserBean;

/**
 * Servlet implementation class DoUserUpdate
 */
@WebServlet("/manage/admin_douserupdate")
public class DoUserUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String userID = request.getParameter("USERID");
		String username = request.getParameter("USER_NAME");
		String account = request.getParameter("USER_ACCOUNT");
		String password = request.getParameter("USER_PASSWORD");
		String icon = request.getParameter("USER_ICON");
		String email = request.getParameter("USER_EMAIL");
		String identity = request.getParameter("USER_IDENTITY");
		
		UserBean user =new UserBean(userID, username, account, password ,icon, email, identity, null);
		
		int res = UserDao.admin_update(user);

		// 判然后重定向
		if (res == 0) {
			response.sendRedirect("admin_douserselect?page="+request.getParameter("cpage")+"&keywords="+request.getParameter("keywords"));
		} else {
			PrintWriter out = response.getWriter();

			out.write("<script>");
			out.write("alert('用户修改失败');");
			out.write("location.href='manage/admin_touserupdate?id"+userID+"'");

			out.write("</script>");
		}
	}

}
