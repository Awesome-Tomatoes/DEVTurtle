package com.devturtle.rank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devturtle.common.PagingUtil;
import com.devturtle.group.GroupDAO;
import com.devturtle.group.GroupVO;
import com.devturtle.mission.MissionGroupDAO;


@WebServlet("/rankGroup")
public class RankGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		HttpSession session = request.getSession();
//		//userID 세션에 없으면 0, 잇으면 로그인한 userID기준으로 검색 결과 팔로우, 팔로워 관계 찾기
//		int userID = 25;
//		if (session != null)  {
//			String uid = (String)session.getAttribute("SESS_USER_ID");
//			System.out.println(uid);
//			if(uid != null) {
//				userID = Integer.parseInt(uid);
//			}
////          session.getAttribute("SESS_USER_NICKNAME");
////          session.getAttribute("SESS_ROLE");
////          session.getAttribute("SESS_GROUP");
//		}
//		
		
		GroupDAO gdao = new GroupDAO();
		MissionGroupDAO mdao = new MissionGroupDAO();
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
		
		int currentPage = 1;
		String currentPageStr = request.getParameter("currentPage");
		if(currentPageStr != null && !currentPageStr.equals(""))  {
			currentPage = Integer.parseInt(currentPageStr);
		}
		
//		System.out.println("현재 가져올 그룹 크기 : " +  gdao.selectAllGroupSize());
		
		int totRecord = gdao.selectAllGroupSize();
		int blockCount = 4; 
		int blockPage = 4;
		
		PagingUtil pg = new PagingUtil(request.getContextPath() + "/rankGroup", currentPage, totRecord, blockCount, blockPage);
		request.setAttribute("MY_KEY_PAGING_HTML", pg.getPagingHtml().toString());
		
		ArrayList<GroupVO> glist = gdao.selectAllGroupByMonthOrderByRankPaging(date.format(new Date()), pg.getStartSeq(), pg.getEndSeq());
		request.setAttribute("GLIST", glist);
		
		// 각 그룹의 사용자 수
        Map<Integer, Integer> groupUserCountMap = new HashMap<>();
        for (GroupVO group : glist) {
            int userCount = gdao.getNumberOfGroupUser(group.getGroupId());
            groupUserCountMap.put(group.getGroupId(), userCount);  
        }

        request.setAttribute("GROUP_USER_COUNT", groupUserCountMap);		

		request.setAttribute("contentPage", "/jsp/rank/rank_group.jsp");
	    request.getRequestDispatcher("/index.jsp").forward(request, response);	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	public static void main(String[] argv) {
//		Date today = new Date();    
//		System.out.println(today);            
//		SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");    
//		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");           
//		System.out.println("Date: "+date.format(today));    
//		System.out.println("Time: "+time.format(today));
//		SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
//		System.out.println(date.format(new Date()));
	}

}
