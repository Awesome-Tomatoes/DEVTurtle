package com.devturtle.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.devturtle.group.GroupDAO;
import com.devturtle.group.GroupVO;
import com.devturtle.user.UserDAO;
import com.devturtle.user.UserVO;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
    }

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	UserDAO udao = new UserDAO();
//	ArrayList<UserVO> ulist = udao.selectUser(1);
//	request.setAttribute("ULIST", ulist);
	
	HttpSession session = request.getSession();
	System.out.println(session.getAttribute("SESS_USER_ID"));
//    session.setAttribute("SESS_USER_ID", uvo.getUserID());
	
	
	GroupDAO gdao  = new GroupDAO();
	
	ArrayList<GroupVO> glist = gdao.selectAllGroup();
	request.setAttribute("GLIST", glist);
	
	// 동적으로 포함할 contentPage 경로 설정
    request.setAttribute("contentPage", "/jsp/main/main.jsp");

    // layout.jsp로 포워딩
    request.getRequestDispatcher("/index.jsp").forward(request, response);
    
//	  //랭킹뽑기
//    //그룹 상위 1~6위
//	ArrayList<GroupVO> groupList = gdao.selectAllGroupByMonthOrderByRankPaging("20250102", 1, 6);
//	for(var x : groupList) {
//		System.out.println(x.toString());
//	}
//	
//	//개인 상위 1~6위
//	ArrayList<UserVO> userList = udao.selectAllUserByMonthOrderByRankPaging("20241231", 1, 6);
//	for(var x : userList) {
//		System.out.println(x.toString());
//	}
	
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response);
}

}