package com.book.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.PostDao;
import com.book.pojo.PostBean;

/**
 * Servlet implementation class DopostUpdate
 */
@WebServlet("/manage/admin_dopostupdate")
public class DoPostUpdate extends HttpServlet {
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

		String postID = request.getParameter("POSTID");
		String cover = request.getParameter("POST_COVER");
		String content = request.getParameter("POST_CONTENT");
		String title = request.getParameter("POST_POSTTITLE");
		String bookType = request.getParameter("POST_BOOKTYPE");
		String bookTitle = request.getParameter("POST_BOOKTITLE");
		String bookAuthor = request.getParameter("POST_BOOKAUTHOR");
		String publisherId = request.getParameter("POST_PUBLISHERID");
		String isExist = request.getParameter("POST_ISEXIST");
				
		PostBean post =new PostBean(postID, null, cover, content ,title, bookType, bookTitle, bookAuthor, publisherId, isExist);
		
		int res = PostDao.admin_update(post);

		// 判然后重定向
		if (res == 0) {
			response.sendRedirect("admin_dopostselect?page="+request.getParameter("cpage")+"&keywords="+request.getParameter("keywords"));
		} else {
			PrintWriter out = response.getWriter();

			out.write("<script>");
			out.write("alert('用户修改失败');");
			out.write("location.href='manage/admin_topostupdate?id"+postID+"'");

			out.write("</script>");
		}
	}

}
