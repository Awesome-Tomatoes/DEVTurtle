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
	
	private void handleResponse(HttpServletResponse response, boolean isSuccess, String successMessage, String errorMessage) throws IOException {
	    String message = isSuccess ? successMessage : errorMessage;
	    String redirectUrl = "/mypage";

	    response.getWriter().write(
	        "<!DOCTYPE html>" +
	        "<html>" +
	        "<head>" +
	        "    <script type='text/javascript'>" +
	        "        alert('" + message + "');" +
	        "        window.location.href = '" + redirectUrl + "';" +
	        "    </script>" +
	        "</head>" +
	        "<body></body>" +
	        "</html>"
	    );
	}

	public MypageServlet() {
		super();
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userIdParam = request.getParameter("userid");
        UserDAO udao = new UserDAO();
        MissionPersonalDAO mdao = new MissionPersonalDAO();
        FollowDAO fdao = new FollowDAO();
        GroupDAO gdao = new GroupDAO();
        RankUserDAO rudao = new RankUserDAO();

        Integer userId = null;
        
        if (userIdParam != null && !userIdParam.isEmpty()) {
        	userId = Integer.parseInt(userIdParam);
        } else {
        	HttpSession session = request.getSession();
            userId = (Integer) session.getAttribute("SESS_USER_ID");
            if (userId == null) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }
        }
        
        UserVO uinfo = udao.selectUser(userId);
        
        request.setAttribute("USER_INFO", uinfo);
        request.setAttribute("USER_RANK_CODE", UserRankText.getRank(userId));

        ArrayList<MissionJoinUserVO> mlist = mdao.selectMissionUser(userId);
        int missionPoints = mlist.stream().mapToInt(MissionJoinUserVO::getPoints).sum();
        request.setAttribute("USER_MISSION_SCORE", missionPoints);

        int[] progress = UserRankingProgress.getRankProgress(userId);
        request.setAttribute("USER_REMAINING_SCORE", progress[0]);
        request.setAttribute("USER_REMAINING_PERCENT", progress[1]);

        UserVO uRanking = udao.selectUserByIDWithRank(userId);
        request.setAttribute("USER_RANKING", uRanking);

        int totalUser = udao.selectAllUser().size();
        request.setAttribute("USER_COUNT", totalUser);

        int followerCount = fdao.countUserFollowed(userId);
        request.setAttribute("FOLLOWER_COUNT", followerCount);

        ArrayList<GroupVO> glist = gdao.selectAllJoinGroup(userId);
        request.setAttribute("GROUP_COUNT", glist.size());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String today = dateFormat.format(new Date());
        ArrayList<RankUserVO> rankList = rudao.selectRankUserAllByMonth(userId, today);

        request.setAttribute("RANK_CHART_DATA", new Gson().toJson(rankList));
        request.setAttribute("contentPage", "/jsp/mypage/mypage.jsp");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("SESS_USER_ID");
		UserDAO udao = new UserDAO();
		
		String actionType = request.getParameter("actionType");
		
		try {
	        if ("updateNickname".equals(actionType)) {
	            String nickname = request.getParameter("nickname");
	            int res = udao.updateUserNickname(userId, nickname);
	            handleResponse(response, res > 0, "닉네임이 변경되었습니다.", "오류가 발생했습니다. 다시 시도해주세요.");
	        } else if ("updateBio".equals(actionType)) {
	            String bio = request.getParameter("bio");
	            int res = udao.updateUserData(userId, bio);
	            handleResponse(response, res > 0, "자기소개가 변경되었습니다.", "오류가 발생했습니다. 다시 시도해주세요.");
	        } else {
	            handleResponse(response, false, "", "유효하지 않은 요청입니다.");
	        }
	    } catch (Exception e) {
	        handleResponse(response, false, "", "서버 오류가 발생했습니다.");
	    }
	}

}