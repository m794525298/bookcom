package com.book.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.FollowDao;
import com.book.pojo.FollowBean;

/**
 * Servlet implementation class DofollowUpdate
 */
@WebServlet("/manage/admin_dofollowupdate")
public class DoFollowUpdate extends HttpServlet {
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

		String followId = request.getParameter("FOLLOWID");
		String followerId = request.getParameter("FOLLOW_FOLLOWERID");
		String followingId = request.getParameter("FOLLOW_FOLLOWINGID");
		
		FollowBean follow =new FollowBean(followId, followerId, followingId);
		
		int res = FollowDao.admin_update(follow);

		// 判然后重定向
		if (res == 0) {
			response.sendRedirect("admin_dofollowselect?page="+request.getParameter("cpage")+"&keywords="+request.getParameter("keywords"));
		} else {
			PrintWriter out = response.getWriter();

			out.write("<script>");
			out.write("alert('用户修改失败');");
			out.write("location.href='admin_tofollowupdate?id"+followId+"'");

			out.write("</script>");
		}
	}

}
