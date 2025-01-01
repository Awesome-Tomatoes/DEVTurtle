package com.devturtle.rank;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devturtle.user.UserDAO;
import com.devturtle.user.UserVO;

/**
 * Servlet implementation class RankUserServlet
 */
@WebServlet("/RankUserServlet")
public class RankUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO udao = new UserDAO();
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
RankUserDAO rudao = new RankUserDAO();
		
		// 날짜 기준으로 랭크 조회, 기본은 1달 기준
		ArrayList<RankUserVO> rulist = rudao.selectRankUserAllByMonth("20241231");
		
		// 점수 기준으로 유저 리스트 조회해서 추가
		ArrayList<UserVO> ulist = new ArrayList<>();
		for(var x : rulist) {
			ulist.add(udao.selectUser(x.getUserID()));
		}
		
		for(var x : ulist) {
			System.out.println(x.toString());
		}
		
		request.setAttribute("RULIST", rulist);
		request.setAttribute("ULIST", ulist);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/jsp/rank/rank_user.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
