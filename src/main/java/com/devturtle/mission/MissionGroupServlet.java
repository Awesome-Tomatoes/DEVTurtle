package com.devturtle.mission;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		
		MissionGroupDAO mgd = new MissionGroupDAO();
		
		ArrayList<ObjectiveVO> mlist = mgd.selectAll();
		
		System.out.println(mlist.get(0).getContents());
		
		request.setAttribute("MLIST", mlist);
		
		request.getRequestDispatcher("/jsp/mission/mission_detail_group.jsp").forward(request, response);
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}

