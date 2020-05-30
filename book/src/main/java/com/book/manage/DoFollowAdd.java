package com.book.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.pojo.FollowBean;
import com.book.dao.FollowDao;
/**
 * Servlet implementation class DofollowAdd
 */
@WebServlet("/manage/admin_dofollowadd")
public class DoFollowAdd extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String followerId = request.getParameter("FOLLOW_FOLLOWERID");
		String followingId = request.getParameter("FOLLOW_FOLLOWINGID");

		FollowBean follow =new FollowBean(null, followerId, followingId);
		
		// 加入数据库的用户表
		int res= FollowDao.admin_insert(follow);
		System.out.println(res);
		
		//判然后重定向
		if(res == 0){
			response.sendRedirect("admin_dofollowselect");
		} else {
			PrintWriter out = response.getWriter();
			out.write("<script>");
			out.write("alert('添加失败');");
			out.write("location.href='admin_followadd.jsp'");
			out.write("</script>");
		}
		
		
	}

}
