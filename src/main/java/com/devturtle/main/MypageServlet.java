package com.devturtle.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mypage")
public class MypageServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    public MypageServlet() {
        super();
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	
//	//상단 유저정보	+ 유저 랭킹, 인자로 userID 넣을것
//	UserDAO udao = new UserDAO();
//	UserVO uvo = udao.selectUserByIDWithRank(15);
//	//총 유저 수, 전체 유저중에 몇등일지 쓸때 필요함
//	int totalUser = udao.selectAllUser().size();
//	request.setAttribute("USER_INFO_VO", uvo);
//	request.setAttribute("USER_COUNT", totalUser);
	
	//미션 : 
//	int userMissionPoint = udao.selectUserMissionPoint("userid");
//	request.setAttribute("USER_MISSION_POINT", userMissionPoint);
	
	
	//팔로워 : 
//	FollowDAO fdao = new FollowDAO();
//	int selectUserFollow = fdao.selectUserFollow("userid");
//	request.setAttribute("SELECT_USER_FOLLOW", selectUserFollow);

	
	//그룹 : 
//	GroupDAO gdao = new GroupDAO();
//	int numberOfJoinGroup = gdao.getNumberOfJoinGroup();
//	request.setAttribute("NUMBER_OF_JOIN_GROUP", numberOfJoinGroup);
	
	//차트 userID, 뽑아올 달 yyyymmdd 문자열로 입력
//	RankUserDAO rudao = new RankUserDAO();
//	ArrayList<RankUserVO> arr = rudao.selectRankUserAllByMonth(9, "20250101");
//	 request.setAttribute("CHART_RANK_LIST", chartRankList);
	 
	
	// 동적으로 포함할 contentPage 경로 설정
    request.setAttribute("contentPage", "/jsp/mypage/mypage.jsp");

    // layout.jsp로 포워딩
    request.getRequestDispatcher("/index.jsp").forward(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response);
}

}