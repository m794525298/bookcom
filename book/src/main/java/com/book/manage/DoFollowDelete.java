package com.book.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.FollowDao;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

/**
 * Servlet implementation class DofollowDelete
 */
@WebServlet("/manage/admin_dofollowdelete")
public class DoFollowDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String followID = request.getParameter("id");

		int res = FollowDao.admin_delete(followID);

		// 判然后转发请求
		if (res == 0) {
			PrintWriter out = response.getWriter();

			out.write("<script>");
			out.write("alert('删除失败');");
			out.write("</script>");
		}
		
		String keywords = request.getParameter("keywords");
		if(keywords != null) {
			response.sendRedirect("admin_dofollowselect?page=" + request.getParameter("page") + "&keywords=" + request.getParameter("keywords"));
		} else {
			response.sendRedirect("admin_dofollowselect?page=" + request.getParameter("page"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String ids[] = request.getParameterValues("id[]");
		
		if(ids != null)
			for(int i = 0; i < ids.length; ++i)
				FollowDao.admin_delete(ids[i]);
		
		String keywords = request.getParameter("keywords");
		if(keywords != null) {
			response.sendRedirect("admin_dofollowselect?page=" + request.getParameter("page") + "&keywords=" + request.getParameter("keywords"));
		} else {
			response.sendRedirect("admin_dofollowselect?page=" + request.getParameter("page"));
		}
	}

}
