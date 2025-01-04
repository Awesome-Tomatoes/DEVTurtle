package com.devturtle.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserImage
 */
@WebServlet("/userImage")
public class UserImageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String scoreParam = request.getParameter("rating");
//        int rating = (scoreParam != null) ? Integer.parseInt(scoreParam) : 0;

		String contextPath = request.getContextPath();
		UserDAO udao = new UserDAO();
		
		int userid = Integer.parseInt(request.getParameter("userid"));
		int rating = udao.selectUser(userid).getTotalScore();
		
		
        // 점수에 따른 이미지 경로 결정
		
		
        String imagePath;
        
        if(rating >= 2000) {
        	imagePath = contextPath + "/assets/turtle_grade/pirates_turtle.png";
		} else if(rating >= 1000) {
			imagePath = contextPath + "/assets/turtle_grade/pirates_junior_turtle.png";
		} else if(rating >= 600) {
			imagePath = contextPath + "/assets/turtle_grade/middle_turtle.png";
		} else if(rating >= 300) {
			imagePath = contextPath + "/assets/turtle_grade/element_turtle.png";
		} else if(rating >= 100) {
			imagePath = contextPath + "/assets/turtle_grade/kinder_turtle.png";
		} else if(rating >= 50) {
			imagePath = contextPath + "/assets/turtle_grade/baby_turtle.png";
		} else {
			imagePath = contextPath + "/assets/turtle_grade/turtle_egg.png"; // 골드 등급 
		}
//        
//        if (rating < 20) {
//            imagePath = contextPath + "/assets/turtle_grade/turtle_egg.png"; // 골드 등급
//        } else if (rating < 50) {
//            imagePath = contextPath + "/assets/turtle_grade/baby_turtle.png"; // 실버 등급
//        }else if (rating < 100) {
//            imagePath = contextPath + "/assets/turtle_grade/kinder_turtle.png"; // 실버 등급
//        }else if (rating < 200) {
//            imagePath = contextPath + "/assets/turtle_grade/element_turtle.png"; // 실버 등급
//        }else if (rating < 350) {
//            imagePath = contextPath + "/assets/turtle_grade/middle_turtle.png"; // 실버 등급
//        }else if (rating < 600) {
//            imagePath = contextPath + "/assets/turtle_grade/pirates_junior_turtle.png"; // 실버 등급
//        }else {
//            imagePath = contextPath + "/assets/turtle_grade/pirates_turtle.png"; 
//        }

        response.sendRedirect(imagePath);
	}
}
