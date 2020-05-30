package com.book.manage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.pojo.PostBean;
import com.book.dao.PostDao;
/**
 * Servlet implementation class DoPostAdd
 */
@WebServlet("/manage/admin_dopostadd")
public class DoPostAdd extends HttpServlet {
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置字符集
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String cover = request.getParameter("POST_COVER");
		String content = request.getParameter("POST_CONTENT");
		String title = request.getParameter("POST_POSTTITLE");
		String bookType = request.getParameter("POST_BOOKTYPE");
		String bookTitle = request.getParameter("POST_BOOKTITLE");
		String bookAuthor = request.getParameter("POST_BOOKAUTHOR");
		String publisherId = request.getParameter("POST_PUBLISHERID");
		

		PostBean post =new PostBean(null, null, cover, content, title, bookType, bookTitle, bookAuthor, publisherId, null);
		
		// 加入数据库的用户表
		int count= PostDao.admin_insert(post);
		
		//判然后重定向
		if(count == 0){
			response.sendRedirect("admin_dopostselect");
		} else {
			PrintWriter out = response.getWriter();
			
			out.write("<script>");
			out.write("alert('添加失败');");
			out.write("location.href='admin_postadd.jsp'");

			out.write("</script>");
		}
		
		
	}

}
