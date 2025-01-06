package com.devturtle.follow;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devturtle.user.UserVO;

/**
 * Servlet implementation class FollowFollowServlet
 */
@WebServlet("/followFollow")
public class FollowFollowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String before_address = request.getHeader("referer");
		
		
		int followId = Integer.parseInt(request.getParameter("followid"));
		int pathId = Integer.parseInt(request.getParameter("userid"));
		
		
		HttpSession session = request.getSession(false);
		
		int userId = (int) session.getAttribute("SESS_USER_ID");
		
		System.out.println("userId" + userId);
		System.out.println("pathId" + pathId);
		System.out.println("followId" + followId);
		
		
		if(userId == 0) {
			response.sendRedirect("/session_check.jsp");
		} else {
			FollowDAO fdao = new FollowDAO();
			ArrayList<UserVO> flist = fdao.selectWaitFollowing(userId);
			System.out.println(flist);
			boolean bool = true;
			for(UserVO uvo : flist) {
				if(uvo.getUserID() == followId) {
					
					bool = false;
					System.out.println("여기 들어왔어요 ");
					break;
				}
			}
			
			if (bool) {
				fdao.insertFollowed(pathId, followId);
				System.out.println("insert 실행");
			} else {
				fdao.updateState(followId, userId);
				System.out.println("update 실행");
			}
			response.sendRedirect(before_address);
		}
	
	}

}
