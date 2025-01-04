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

        String contextPath = request.getContextPath();
		response.getWriter().append("Served at: ").append(contextPath);
		request.getRequestDispatcher(contextPath+"/jsp/user/user_login.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String contextPath = request.getContextPath();
        String loginid = request.getParameter("loginid");
        String password = request.getParameter("password");
        
		System.out.println(loginid + password);
        UserDAO dao = new UserDAO();
        GroupDAO gdao = new GroupDAO();
        UserVO uvo = dao.selectUserByLoginID(loginid);
        ArrayList<GroupVO> glist = gdao.selectAllJoinGroup(uvo.getUserID());
    	ArrayList<Integer> list = new ArrayList<Integer>();
        for (GroupVO gvo : glist) {
        	list.add((int)gvo.getGroupId());
        }
        if(uvo.getUserID() == 0) {
            request.setAttribute("errorMessage", "아이디를 입력해주세요");
            request.getRequestDispatcher(contextPath+"/jsp/user/user_login.jsp").forward(request, response);
            return;
        } else if (!uvo.getLoginPW().equals(password)) {
            request.setAttribute("errorMessage", "아이디 혹은 비밀번호가 일치하지 않습니다");
            request.getRequestDispatcher(contextPath+"/jsp/user/user_login.jsp").forward(request, response);
        } else {
    		
    		HttpSession session = request.getSession();
            session.setAttribute("SESS_USER_ID", uvo.getUserID());
            session.setAttribute("SESS_ROLE", "user");
            session.setAttribute("SESS_GROUP", list);

        	request.getRequestDispatcher(contextPath+"/main").forward(request, response);
        	
        }
        
        
		
	}

}
