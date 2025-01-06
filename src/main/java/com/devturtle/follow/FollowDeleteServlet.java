package com.devturtle.follow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FollowDeleteServlet
 */
@WebServlet("/followDelete")
public class FollowDeleteServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String before_address = request.getHeader("referer");
		
		
		int deleteId = Integer.parseInt(request.getParameter("deleteid"));
		int pathId = Integer.parseInt(request.getParameter("userid"));
		
		HttpSession session = request.getSession();
		
		int userId = (int) session.getAttribute("SESS_USER_ID");
		if(userId == 0) {
			response.sendRedirect("/session_check.jsp");
		} else {
			FollowDAO fdao = new FollowDAO();
			
			fdao.deleteFollow(userId, deleteId);

			response.sendRedirect(before_address);
		}
		
	}

}
