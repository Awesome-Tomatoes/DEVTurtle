package com.devturtle.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devturtle.rank.RankUserDAO;
import com.google.gson.Gson;


@WebServlet("/signup")
public class SignupServlet extends HttpServlet {protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	response.getWriter().append("Served at: ").append(request.getContextPath());
	request.getRequestDispatcher("/jsp/user/user_signup.jsp").forward(request, response);
}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String loginid = request.getParameter("loginid");
    String password = request.getParameter("password");
    String username = request.getParameter("username");
    String nickname = request.getParameter("nickname");
    String gitname = request.getParameter("gitname");
    String sorname = request.getParameter("sorname");
    
    System.out.println(loginid);
    System.out.println(password);
    System.out.println(username);
    System.out.println(nickname);
    System.out.println(gitname);
    System.out.println(sorname);
    UserDAO dao = new UserDAO();

    UserVO uvo = new UserVO(username, loginid, password, nickname, sorname, gitname, "", 0, 0, 0);

	response.sendRedirect("/signup");
	
    if(loginid.equals("") || password.equals("") || username.equals("") || nickname.equals("") ) {
    	System.out.println("빈곳 존재");
    	response.sendRedirect("/signup");
    } else {
    	dao.insertUser(uvo);

    	System.out.println(dao.selectUserByLoginID(loginid).toString());
    	System.out.println("회원가입 완료");
    	response.sendRedirect("/login");
    	
    }
    
    
	
}
}
