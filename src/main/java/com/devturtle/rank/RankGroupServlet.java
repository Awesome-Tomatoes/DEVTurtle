package com.devturtle.rank;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/rankGroup")
public class RankGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		RankGroupDAO rgdao = new RankGroupDAO();
		
		ArrayList<RankGroupVO> rglist = rgdao.selectAllRankUserByGroupID(1);
		request.setAttribute("RGLIST", rglist);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/jsp/rank/rank_group.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
