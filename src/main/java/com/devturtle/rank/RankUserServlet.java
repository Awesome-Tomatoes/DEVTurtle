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
import com.devturtle.common.PagingUtil;

/**
 * Servlet implementation class RankUserServlet
 */
@WebServlet("/rankUser")
public class RankUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		String pageGubun = request.getParameter("pageGubun");
		UserDAO udao = new UserDAO();
		RankUserDAO rudao = new RankUserDAO();

		// 날짜 기준으로 랭크 조회, 기본은 1달 기준
		ArrayList<RankUserVO> rulist = rudao.selectRankUserAllByMonth("20241231");
		
//		int rank = 1;
//		// 점수 기준으로 유저 리스트 조회해서 랭킹대로 유저 가져오기
//		ArrayList<UserVO> userlist = new ArrayList<>();
//		for(var x : rulist) {
//			UserVO tmpUser = udao.selectUser(x.getUserID());
//			tmpUser.setRank(rank++);
//			userlist.add(tmpUser);
//		}
		
		int currentPage = 1;
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr != null && !currentPageStr.equals(""))  {
			currentPage = Integer.parseInt(currentPageStr);
		}
		
		int totRecord = rulist.size();
		int blockCount = 1; 
		int blockPage = 4;
		
		PagingUtil pg = new PagingUtil("/DevTurtle/rankUser", currentPage, totRecord, blockCount, blockPage);
		request.setAttribute("MY_KEY_PAGING_HTML", pg.getPagingHtml().toString());
		
		
//		request.setAttribute("RULIST", rulist);
		ArrayList<UserVO> ulist = udao.selectAllUserOrderByRankPaging( pg.getStartSeq(), pg.getEndSeq());
		request.setAttribute("ULIST", ulist);
		request.getRequestDispatcher("/jsp/rank/rank_user.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
