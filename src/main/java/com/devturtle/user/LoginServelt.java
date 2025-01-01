package com.devturtle.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devturtle.group.GroupDAO;
import com.devturtle.group.GroupVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class LoginServelt
 */
@WebServlet("/login")
public class LoginServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/jsp/user/user_login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String loginid = request.getParameter("loginid");
        String password = request.getParameter("password");
        
		System.out.println(loginid + password);
        UserDAO dao = new UserDAO();
        UserVO uvo = dao.selectUserByLoginID(loginid);
        if(uvo.getUserID() == 0) {
        	System.out.println("아이디 틀림");
        	response.sendRedirect("/login");
        } else if (!uvo.getLoginPW().equals(password)) {
        	System.out.println("비밀번호 틀림");
        	response.sendRedirect("/login");
        } else {
        	ArrayList<Integer> list = new ArrayList<Integer>();
    		
    		HttpSession session = request.getSession();
            session.setAttribute("SESS_USER_ID", uvo.getUserID());
            session.setAttribute("SESS_ROLE", "user");
            session.setAttribute("SESS_GROUP", list);

        	request.getRequestDispatcher("/main").forward(request, response);
        	
        }
        
        
		
	}

}
