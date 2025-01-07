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

		 // URL 파라미터로 userId를 받는다
	    String userIdParam = request.getParameter("userid");
	    Integer userId = null;

	    // userId 파라미터가 있으면 그 값을 사용하고, 없으면 세션에서 userId를 가져온다
	    if (userIdParam != null && !userIdParam.isEmpty()) {
	        try {
	            // 파라미터에서 userId를 가져오기
	            userId = Integer.parseInt(userIdParam);
	        } catch (NumberFormatException e) {
	            // 파라미터 값이 잘못된 경우 처리
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

	    // 그룹 목록을 조회
	    GroupDAO gdao = new GroupDAO();
	    ArrayList<GroupVO> groupList = gdao.selectAllJoinGroup(userId);

	    // groupList가 비어있는 경우 한 번만 처리
	    if (groupList.isEmpty()) {
	        response.getWriter().write("{ \"message\": \"No groups found\" }");
	        return;
	    }

	    // JSON 응답 처리
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
