package com.book.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.CommentDao;
import com.book.pojo.CommentBean;

/**
 * Servlet implementation class DoCommentUpdate
 */
@WebServlet("/manage/admin_docommentupdate")
public class DoCommentUpdate extends HttpServlet {
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

		String commentId = request.getParameter("COMMENTID");
		String postId = request.getParameter("COMMENT_POSTID");
		String parentId = request.getParameter("COMMENT_PARENTID");
		String content = request.getParameter("COMMENT_CONTENT");
		String publisherId = request.getParameter("COMMENT_PUBLISHERID");
		String isExist = request.getParameter("COMMENT_ISEXIST");

		if(parentId.isEmpty())
			parentId = null;
		
		CommentBean comment =new CommentBean(commentId, null, postId, parentId, content, publisherId, isExist);
		
		int res = CommentDao.admin_update(comment);
		System.out.println(res);

		// 判然后重定向
		if (res == 0) {
			response.sendRedirect("admin_docommentselect?page="+request.getParameter("cpage")+"&keywords="+request.getParameter("keywords"));
		} else {
			PrintWriter out = response.getWriter();

			out.write("<script>");
			out.write("alert('评论修改失败');");
			out.write("location.href='admin_tocommentupdate?id"+commentId+"'");

			out.write("</script>");
		}
	}

}
