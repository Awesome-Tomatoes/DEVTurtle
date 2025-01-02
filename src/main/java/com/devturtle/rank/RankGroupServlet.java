package com.devturtle.rank;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devturtle.common.PagingUtil;
import com.devturtle.group.GroupDAO;
import com.devturtle.group.GroupVO;

@WebServlet("/rankGroup")
public class RankGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		GroupDAO gdao = new GroupDAO();
		int currentPage = 1;
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr != null && !currentPageStr.equals(""))  {
			currentPage = Integer.parseInt(currentPageStr);
		}
		
		int totRecord = gdao.selectAllGroupSize();
		int blockCount = 4; 
		int blockPage = 4;
		
		PagingUtil pg = new PagingUtil("/DevTurtle/rankGroup", currentPage, totRecord, blockCount, blockPage);
		request.setAttribute("MY_KEY_PAGING_HTML", pg.getPagingHtml().toString());
		
		ArrayList<GroupVO> glist = gdao.selectAllGroupByMonthOrderByRankPaging("20241231", pg.getStartSeq(), pg.getEndSeq());
		request.setAttribute("GLIST", glist);
		request.getRequestDispatcher("/jsp/rank/rank_group.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
