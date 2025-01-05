package com.devturtle.follow;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devturtle.user.UserDAO;
import com.devturtle.user.UserVO;

/**
 * Servlet implementation class FollowServlet
 */
@WebServlet("/follow")
public class FollowServlet extends HttpServlet {
       
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath = request.getContextPath();
		
		HttpSession session=request.getSession();

		FollowDAO fdao = new FollowDAO();
		UserDAO udao = new UserDAO();
		
		String useridStr = request.getParameter("userid");
		int userid = 0;
		
		if (useridStr == null || useridStr == "") {
			userid = (int) session.getAttribute("SESS_USER_ID");
		} else {
			userid = Integer.parseInt(useridStr);
		}
		
		ArrayList<UserVO> followedlist = fdao.selectAllFollowed(userid);
		ArrayList<UserVO> followinglist = fdao.selectAllFollowing(userid);
		ArrayList<UserVO> waitlist = fdao.selectWaitFollowing(userid);
		
		request.setAttribute("FOLLOWER_LIST", followedlist);
		request.setAttribute("FOLLOWING_LIST", followinglist);
		request.setAttribute("WAIT_LIST", waitlist);
		System.out.println("followed");
		for(UserVO uvo : followedlist) {
			System.out.println(uvo.toString());
		}
		System.out.println("following");
		for(UserVO uvo : followinglist) {
			System.out.println(uvo.toString());
		}
		System.out.println("wait");
		for(UserVO uvo : waitlist) {
			System.out.println(uvo.toString());
		}
		
		request.setAttribute("USER_NICK", udao.selectUser(userid).getNickname());
		
		
	    // layout.jsp로 포워딩
		request.setAttribute("contentPage", "/jsp/follow/follow_list.jsp");
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
