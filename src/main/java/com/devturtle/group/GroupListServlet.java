package com.devturtle.group;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devturtle.user.UserDAO;
import com.devturtle.user.UserVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * Servlet implementation class GroupInfoServlet
 */
@WebServlet("/grouplist")
public class GroupListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userIdParam = request.getParameter("userid");
	    Integer userId = null;
	    UserDAO udao = new UserDAO();

	    if (userIdParam != null && !userIdParam.isEmpty()) {
	        try {
	            userId = Integer.parseInt(userIdParam);
	            UserVO uvo = udao.selectUser(userId);

	    		request.setAttribute("USER_INFO", uvo);
	            
	        } catch (NumberFormatException e) {
	            
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid userId parameter");
	            return;
	        }
	    } else {
	        // userId 파라미터가 없을 경우 세션에서 현재 로그인된 사용자의 userId를 가져오기
	        HttpSession session = request.getSession();
	        userId = (Integer) session.getAttribute("SESS_USER_ID");
	        
	        if (userId == null) {
	            // 세션에 userId가 없으면 로그인 페이지로 리디렉션
	            response.sendRedirect(request.getContextPath() + "/login");
	            return;
	        }
	    }

	    
        
	    GroupDAO gdao = new GroupDAO();	    
	    GroupVO searchGroupVO = new GroupVO();

	    // 그룹 목록을 조회
	    ArrayList<GroupVO> groupList = gdao.selectAllJoinGroup(userId);
	  	
	    for (GroupVO groupVO : groupList) {
	  		
	    	int returnGroupId = groupVO.getGroupId();
	  		System.out.println("returnGroupId"+returnGroupId);
	    	
	  		// 그룹 관련정보 VO에 가져오기 
	  		searchGroupVO = gdao.selectGroupByIDWithRank(returnGroupId);
	  		int rank = searchGroupVO.getRank();
	  		System.out.println("rank"+rank);
	  		groupVO.setRank(rank);
		}
	    
	    // groupList가 비어있는 경우 
	    if (groupList.isEmpty()) {

	    }
	    if ("json".equals(request.getParameter("type"))) {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        ObjectMapper objectMapper = new ObjectMapper();
	        String json = objectMapper.writeValueAsString(groupList);
	        response.getWriter().write(json);
	    } else {
	        // JSP 페이지로 포워딩
	        request.setAttribute("contentPage", "/jsp/group/group_list.jsp");
	        request.getRequestDispatcher("/index.jsp").forward(request, response);
	    }
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
