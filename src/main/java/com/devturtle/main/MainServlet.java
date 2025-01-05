package com.devturtle.main;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devturtle.group.GroupDAO;
import com.devturtle.group.GroupVO;
import com.devturtle.user.UserDAO;
import com.devturtle.user.UserRankText;
import com.devturtle.user.UserRankingProgress;
import com.devturtle.user.UserVO;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	UserDAO udao = new UserDAO();
	GroupDAO gdao  = new GroupDAO();
	
	// -------------- 로그인 사용자 랭킹 조회 ----------------
	if (session != null) {
        Integer userId = (Integer) session.getAttribute("SESS_USER_ID");

        if (userId != null) {
        	// 사용자 기본 정보
        	UserVO uinfo = udao.selectUser(userId);
        	request.setAttribute("USER_INFO", uinfo);
        	
        	// 사용자 랭킹명 정보
        	String uRankCode = UserRankText.getRank(userId);
        	request.setAttribute("USER_RANK_CODE", uRankCode);
        	
        	// 사용자 랭킹 순위 정보
        	UserVO uRanking = udao.selectUserByIDWithRank(userId);
        	request.setAttribute("USER_RANKING", uRanking);
        	
        	int totalUser = udao.selectAllUser().size();
        	request.setAttribute("USER_COUNT", totalUser);
        	
        	// 사용자 남은 랭킹 점수 및 퍼센트
        	int[] progress = UserRankingProgress.getRankProgress(userId);
        	int remainingScore = progress[0];
        	int remainingPercent = progress[1];

        	request.setAttribute("USER_REMAINING_SCORE", remainingScore);
        	request.setAttribute("USER_REMAINING_PERCENT", remainingPercent);
        	
        	// 사용자가 가입한 그룹 정보
        	ArrayList<GroupVO> glist = gdao.selectAllJoinGroup(userId);
        	request.setAttribute("USER_GROUP_LIST", glist);
        	
        	// 전체 그룹 갯수
        	int gsize = gdao.selectAllGroupSize();
        	request.setAttribute("ALL_GROUP_SIZE", gsize);
        
        } else {
            response.getWriter().write("로그인 정보가 없습니다.");
        }
    } else {
        response.getWriter().write("세션이 없습니다.");
    }
    
	// -------------- 전체 이번달 랭킹 조회 ----------------
	
	// 오늘 날짜 확인
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	String today = dateFormat.format(new Date());
	
    //그룹 상위 1~10위
	ArrayList<GroupVO> groupRankList = gdao.selectAllGroupByMonthOrderByRankPaging(today, 1, 10);
	request.setAttribute("GROUP_RANK_LIST", groupRankList);
	
	//개인 상위 1~10위
	ArrayList<UserVO> userRankList = udao.selectAllUserByMonthOrderByRankPaging(today, 1, 10);
	
	request.setAttribute("USER_RANK_LIST", userRankList);
	
	// 동적으로 포함할 contentPage 경로 설정
    request.setAttribute("contentPage", "/jsp/main/main.jsp");

    // layout.jsp로 포워딩
    request.getRequestDispatcher("/index.jsp").forward(request, response);
	
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response);
}

}