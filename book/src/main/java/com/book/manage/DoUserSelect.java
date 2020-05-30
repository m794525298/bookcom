package com.book.manage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.book.dao.UserDao;
import com.book.pojo.UserBean;

/**
 * Servlet implementation class DoUserSelect
 */
@WebServlet("/manage/admin_douserselect")
public class DoUserSelect extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int cpage = 1;
		int count = 5;

		// 获取用户至定页面
		String cp = request.getParameter("page");
		// 接受关键字
		String keywords = request.getParameter("keywords");
		if(keywords != null) {
			keywords = new String(keywords.getBytes("iso8859-1"), "utf-8");
			request.setAttribute("keywords", keywords);
		}
		
		if (cp != null) {
			cpage = Integer.parseInt(cp);
		}
		int arr[] = UserDao.admin_totalPage(count, keywords);

		// 获取了所有的用户记录了
		ArrayList<UserBean> list = UserDao.admin_selectAll(cpage, count, keywords);

		// 放到请求对象
		request.setAttribute("userlist", list);
		request.setAttribute("tsum", arr[0]);
		request.setAttribute("tpage", arr[1]);
		request.setAttribute("cpage", cpage);

		if (keywords != null) {
			request.setAttribute("search", "&keywords=" + keywords);
		}
		request.getRequestDispatcher("admin_user.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

}
