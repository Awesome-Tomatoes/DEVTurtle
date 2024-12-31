package com.devturtle.rank;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RankUserServlet
 */
@WebServlet("/RankUserServlet")
public class RankUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.getWriter().append("Served at: ").append(request.getContextPath());
RankUserDAO rudao = new RankUserDAO();
		
		ArrayList<RankUserVO> rulist = rudao.selectRankUser();
		request.setAttribute("RULIST", rulist);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/jsp/rank/rank_user.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
