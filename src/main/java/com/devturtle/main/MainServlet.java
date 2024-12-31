package com.devturtle.main;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DevTurtle.user.UserDAO;
import com.DevTurtle.user.UserVO;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO udao = new UserDAO();
		ArrayList<UserVO> ulist = udao.select();
		
		request.setAttribute("ulist", ulist);
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("/sb/board_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
