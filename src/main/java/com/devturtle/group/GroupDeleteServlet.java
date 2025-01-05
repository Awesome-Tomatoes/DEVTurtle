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

/**
 * Servlet implementation class GroupLeaveServlet
 */
@WebServlet("/groupdelete")
public class GroupDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
			// Parameter :  groupId
	        int groupId = Integer.parseInt(request.getParameter("groupId"));  

	        // Session : userId : SESS_USER_ID
	        // Role : role : SESS_ROLE
	        HttpSession session = request.getSession();
	        int userId = (int) session.getAttribute("SESS_USER_ID");  // 세션에서 userId 가져오기

	        String role = (String) session.getAttribute("SESS_ROLE");
	        
	        System.out.println(" session parameter check>>> " + groupId  + " , " + userId + " , " + role );
	        
	        // 그룹탈퇴 처리 로직 
	        GroupDAO gdao = new GroupDAO();
	        int result = gdao.deleteGroupByUser(groupId,userId);
	        
	        if(result > 0) {
	        	UserDAO udao = new UserDAO();
				UserVO uvo = udao.selectUser(userId);
				int userScore = uvo.getTotalScore();
				// 탈퇴한 그룹원 점수 차감하기
				userScore = -1 *userScore;
				System.out.println("userScore  >>> "+ userScore);
				
				// 그룹점수 업데이트하기
				if(groupId > 0) { 
			  		gdao.initGroupScore(groupId, userScore);
			   	}
				if(request.getParameter("search").equals("search")) {
					 response.sendRedirect(request.getHeader("Referer"));
				}
				else {
					 response.getWriter().write("{\"success\": true}");
				}
	        }else {
	        	response.getWriter().write("{\"success\": false}");
	        }

	    
	}

}
