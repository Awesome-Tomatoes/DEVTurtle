package com.devturtle.group;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devturtle.user.UserDAO;

/**
 * Servlet implementation class UserImage
 */
@WebServlet("/groupImage")
public class GroupImageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String scoreParam = request.getParameter("rating");
//        int rating = (scoreParam != null) ? Integer.parseInt(scoreParam) : 0;

		String contextPath = request.getContextPath();
		
		GroupDAO gdao = new GroupDAO();
		
		int groupid = Integer.parseInt(request.getParameter("groupid"));
		int rating = gdao.selectGroupByIDWithRank(groupid).getTotalScore();
		
		
        // 점수에 따른 이미지 경로 결정
		
		
        String imagePath;
        
        if(rating >= 3000) {
        	imagePath = contextPath + "/assets/turtle_badge/pirate_king_badge.png";
		} else if(rating >= 2000) {
			imagePath = contextPath + "/assets/turtle_badge/attendance_badge.png";
		} else if(rating >= 1000) {
			imagePath = contextPath + "/assets/turtle_badge/pirates_badge.png";
		} else {
			imagePath = contextPath + "/assets/turtle_badge/rating_badge.png"; // 골드 등급 
		}

        response.sendRedirect(imagePath);
	}
}
