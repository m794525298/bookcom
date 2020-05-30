package com.book.manage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.PostDao;
import com.book.pojo.PostBean;

/**
 * Servlet implementation class ToPostUpdate
 */
@WebServlet("/manage/admin_topostupdate")
public class ToPostUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id");
		
		// 通过ID到数据里查找
		PostBean post = PostDao.admin_selectByID(id);
		
		request.setAttribute("post", post);
		request.setAttribute("cpage", request.getParameter("page"));
		request.setAttribute("keywords", request.getParameter("keywords"));
		request.getRequestDispatcher("admin_postmodify.jsp").forward(request, response);
	}

}
