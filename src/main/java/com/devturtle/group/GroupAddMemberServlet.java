package com.devturtle.group;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devturtle.user.UserDAO;
import com.devturtle.user.UserVO;


@WebServlet("/groupAdd")
public class GroupAddMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	        int groupId = Integer.parseInt(request.getParameter("groupId"));  
	        HttpSession session = request.getSession();
	        String searchParam = (String)request.getParameter("search");
	        int userId = (int) session.getAttribute("SESS_USER_ID"); 
	        GroupDAO gdao = new GroupDAO();
		    
		    int tmp = gdao.addGroupUser(userId,groupId,"MEMBER");
		    if(tmp > 0) {
		    	//그룹이 정상적으로 생성 후 그룹 점수 업데이트
				UserDAO udao = new UserDAO();
				UserVO uvo = udao.selectUser(userId);
				int userScore = uvo.getTotalScore();
				//gdao에 그룹원 점수추가 로직 만들기
				gdao.updateGroupScoreAfterAddUser(groupId, userScore);
		    }
		    // 검색 페이지에서 온거면 돌아가기
		    if(request.getParameter("search").equals("search")) {
		    	if(searchParam != null) {
					if(request.getParameter("search").equals("search")) {
//						response.sendRedirect("/search?query=" + query);
						response.sendRedirect(request.getHeader("Referer"));
					}
				}
			}
	}

}
