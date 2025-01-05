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

import com.devturtle.follow.FollowDAO;
import com.devturtle.group.GroupDAO;
import com.devturtle.group.GroupVO;
import com.devturtle.mission.MissionJoinUserVO;
import com.devturtle.mission.MissionPersonalDAO;
import com.devturtle.rank.RankUserDAO;
import com.devturtle.rank.RankUserVO;
import com.devturtle.user.UserDAO;
import com.devturtle.user.UserRankText;
import com.devturtle.user.UserRankingProgress;
import com.devturtle.user.UserVO;
import com.google.gson.Gson;

@WebServlet("/mypage")
public class MypageServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    public MypageServlet() {
        super();
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session = request.getSession();
	UserDAO udao = new UserDAO();
	MissionPersonalDAO mdao = new MissionPersonalDAO();
	FollowDAO fdao = new FollowDAO();
	GroupDAO gdao  = new GroupDAO();
	RankUserDAO rudao = new RankUserDAO();
	
	
	if (session != null) {
		Integer userId = (Integer) session.getAttribute("SESS_USER_ID");
		if (userId != null) {
			// 사용자 기본 정보
        	UserVO uinfo = udao.selectUser(userId);
        	request.setAttribute("USER_INFO", uinfo);
        	
			//사용자 랭킹명 조회
			String uRankCode = UserRankText.getRank(userId);
			request.setAttribute("USER_RANK_CODE", uRankCode);
			
			// 사용자 미션 점수 조회 
			ArrayList<MissionJoinUserVO> mlist = mdao.selectMissionUser(userId);
			
			int missonPoints = 0;
        	for (MissionJoinUserVO mission : mlist) {
        		missonPoints += mission.getPoints();
        	}
        	
        	request.setAttribute("USER_MISSON_SCORE", missonPoints);
        	
			
			// 사용자 남은 랭킹 점수 및 퍼센트
        	int[] progress = UserRankingProgress.getRankProgress(userId);
        	int remainingScore = progress[0];
        	int remainingPercent = progress[1];

        	request.setAttribute("USER_REMAINING_SCORE", remainingScore);
        	request.setAttribute("USER_REMAINING_PERCENT", remainingPercent);
        	
        	// 사용자 랭킹 순위 정보
        	UserVO uRanking = udao.selectUserByIDWithRank(userId);
        	request.setAttribute("USER_RANKING", uRanking);
        	
        	int totalUser = udao.selectAllUser().size();
        	request.setAttribute("USER_COUNT", totalUser);
        	
        	// 팔로워 명수
        	int followerCount = fdao.countUserFollowed(userId);
        	request.setAttribute("FOLLOWER_COUNT", followerCount);
        	
        	// 그룹 수
        	ArrayList<GroupVO> glist = gdao.selectAllJoinGroup(userId);
        	
        	int groupCount = glist.size();
        	request.setAttribute("GROUP_COUNT", groupCount);
        	
        	// 차트 데이터
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        	String today = dateFormat.format(new Date());
        	
        	ArrayList<RankUserVO> rankList = rudao.selectRankUserAllByMonth(userId, today);
        	
        	String rankjson = new Gson().toJson(rankList);
        	request.setAttribute("RANK_CHART_DATA", rankjson);
        	
        	
		}
	}
    request.setAttribute("contentPage", "/jsp/mypage/mypage.jsp");
    request.getRequestDispatcher("/index.jsp").forward(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response);
}

}