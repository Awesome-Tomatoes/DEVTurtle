package com.devturtle.rank;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devturtle.user.UserDAO;
import com.devturtle.user.UserVO;
import com.devturtle.common.PagingUtil;

@WebServlet("/rankUser")
public class RankUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null)  {
			int userId =  Integer.parseInt((String)session.getAttribute("SESS_USER_ID"));
			System.out.println(userId);
//            session.getAttribute("SESS_USER_NICKNAME");
//            session.getAttribute("SESS_ROLE");
//            session.getAttribute("SESS_GROUP");
		}
		
		
		UserDAO udao = new UserDAO();
		int currentPage = 1;
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr != null && !currentPageStr.equals(""))  {
			currentPage = Integer.parseInt(currentPageStr);
		}
		
		int totRecord =  udao.selectAllUser().size();
		int blockCount = 6; 
		int blockPage = 4;
		
		PagingUtil pg = new PagingUtil(request.getContextPath() + "/rankUser", currentPage, totRecord, blockCount, blockPage);
		request.setAttribute("MY_KEY_PAGING_HTML", pg.getPagingHtml().toString());
		
		
//		request.setAttribute("RULIST", rulist);
		ArrayList<UserVO> ulist = udao.selectAllUserByMonthOrderByRankPaging("20241231", pg.getStartSeq(), pg.getEndSeq());
		request.setAttribute("ULIST", ulist);
		
		request.setAttribute("contentPage", "/jsp/rank/rank_user.jsp");
		
	    request.getRequestDispatcher("/index.jsp").forward(request, response);		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
