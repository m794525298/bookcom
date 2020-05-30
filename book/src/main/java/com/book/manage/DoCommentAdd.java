package com.book.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.pojo.CommentBean;
import com.book.dao.CommentDao;
/**
 * Servlet implementation class DoCommentAdd
 */
@WebServlet("/manage/admin_docommentadd")
public class DoCommentAdd extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String postId = request.getParameter("COMMENT_POSTID");
		String parentId = request.getParameter("COMMENT_PARENTID");
		String content = request.getParameter("COMMENT_CONTENT");
		String publisherId = request.getParameter("COMMENT_PUBLISHERID");
		
		if(parentId.isEmpty())
			parentId = null;

		CommentBean comment =new CommentBean(null, null, postId ,parentId, content, publisherId, null);
		
		// 加入数据库的用户表
		int res = CommentDao.admin_insert(comment);
		
		//判然后重定向
		if(res == 0){
			response.sendRedirect("admin_docommentselect");
		} else {
			System.out.println(res);
			
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('添加失败');");
			out.write("location.href='admin_commentadd.jsp'");

			out.write("</script>");
		}
		
		
	}

}
