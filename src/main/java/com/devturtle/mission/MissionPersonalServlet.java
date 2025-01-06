package com.devturtle.mission;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.devturtle.user.UserDAO;
import com.devturtle.user.UserVO;

/**
 * Servlet implementation class MissionPersonalServlet
 */
@WebServlet("/missionPersonal")
public class MissionPersonalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MissionPersonalServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userid = request.getParameter("userid");
		System.out.println(userid);
		
		if (userid == null || userid.isEmpty()) {
            System.out.println("error");
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/jsp/error/error_page404.jsp");
        }
		else {
			int userId = Integer.parseInt(userid);
		
			MissionPersonalDAO mpd = new MissionPersonalDAO();
			
			ArrayList<MissionJoinUserVO> ulist = mpd.selectMissionUser(userId);
			
			if (ulist.size() != 0) {
				request.setAttribute("uname", ulist.get(0).getNickname());
				request.setAttribute("ULIST", ulist);
			} else {
				UserDAO udao = new UserDAO();
				UserVO uvo = udao.selectUser(userId);
				request.setAttribute("uname", uvo.getNickname());
				request.setAttribute("ULIST", ulist);
			}
			String contextPath = request.getContextPath();
			request.getRequestDispatcher(contextPath+"/jsp/mission/mission_detail_user.jsp").forward(request, response);
			//request.getRequestDispatcher("/jsp/mission/mission_detail_user.jsp").forward(request, response);	
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userid");
		System.out.println(userid);
		
		if (userid == null || userid.isEmpty()) {
            System.out.println("error");
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/jsp/error/error_page404.jsp");
        }
		else {
			int userId = Integer.parseInt(userid);
			String action = request.getParameter("action");
		
			if (action.equals("chart")) {
				MissionPersonalDAO mpd = new MissionPersonalDAO();
		        ArrayList<MissionJoinUserVO> ulist = mpd.selectMissionUserChart(userId); // 그룹 ID 예시로 3
		
		        // JSON 변환
		        JsonNode jsonData = new MissionJsonConverter().convertToJsonUser(ulist);
		
		        response.setContentType("application/json");
		        response.setCharacterEncoding("UTF-8");
		        response.getWriter().write(jsonData.toString()); // JSON 응답 반환
		        
		        System.out.println(jsonData.toString());
			}
			
			if (action.equals("history")) {
				MissionPersonalDAO mpd = new MissionPersonalDAO();
				ArrayList<MissionJoinUserVO> ulist = mpd.selectMissionUserHistory(userId);
				
				JsonNode jsonData = new MissionJsonConverter().convertToJsonUser(ulist);
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(jsonData.toString());
				
				System.out.println(jsonData.toString());
			}
		}	
	}
}

