package com.devturtle.mission;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;

/**
 * Servlet implementation class MissionGroupServlet
 */
@WebServlet("/missionGroup")
public class MissionGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MissionGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//int groupid = Integer.parseInt(request.getParameter("groupid"));
		
		MissionGroupDAO mgd = new MissionGroupDAO();
		
		ArrayList<MissionJoinGroupVO> mlist = mgd.selectMissionGroup(3); // 예시로 3 넣음
		ArrayList<MissionJoinGroupVO> blist = mgd.selectMissionGroupBadge(3);	
		//System.out.println(mlist.get(0).getContents());
		
		request.setAttribute("gname", mlist.get(0).getGname());
		request.setAttribute("MLIST", mlist);
		request.setAttribute("BLIST", blist);
		
		request.getRequestDispatcher("/jsp/mission/mission_detail_group.jsp").forward(request, response);
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// JSON 응답 처리
		
		String action = request.getParameter("action");
		
		if (action.equals("chart")) {
	        MissionGroupDAO mgd = new MissionGroupDAO();
	        ArrayList<MissionJoinGroupVO> mlist = mgd.selectMissionGroupChart(3); // 그룹 ID 예시로 3
	
	        // JSON 변환
	        JsonNode jsonData = new MissionJsonConverter().convertToJsonGroup(mlist);
	
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        response.getWriter().write(jsonData.toString()); // JSON 응답 반환
	        
	        System.out.println(jsonData.toString());
		} 
		
		if (action.equals("history")) {
			MissionGroupDAO mgd = new MissionGroupDAO();
			ArrayList<MissionJoinGroupVO> mlist = mgd.selectMissionGroupHistory(3);
			
			JsonNode jsonData = new MissionJsonConverter().convertToJsonGroup(mlist);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonData.toString());
			
			System.out.println(jsonData.toString());
		}
		
		
	}

}

