package com.devturtle.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devturtle.group.GroupDAO;
import com.devturtle.group.GroupVO;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	UserDAO udao = new UserDAO();
//	ArrayList<UserVO> ulist = udao.selectUser(1);
//	request.setAttribute("ULIST", ulist);
	
	
	GroupDAO gdao  = new GroupDAO();
	
	ArrayList<GroupVO> glist = gdao.selectAllGroup();
	request.setAttribute("GLIST", glist);
	
	// 동적으로 포함할 contentPage 경로 설정
    request.setAttribute("contentPage", "/jsp/main/main.jsp");

    // layout.jsp로 포워딩
    request.getRequestDispatcher("/index.jsp").forward(request, response);
	
	
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response);
}

}