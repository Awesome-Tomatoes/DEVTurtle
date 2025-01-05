package com.devturtle.group;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * Servlet implementation class GroupInfoServlet
 */
@WebServlet("/grouplist")
public class GroupListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GroupListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int userId = 1;

		GroupDAO gdao = new GroupDAO();
		ArrayList<GroupVO> groupList = gdao.selectAllJoinGroup(userId);

		for(GroupVO g : groupList) {
			System.out.println(g.toString());
		}
		
		if ("json".equals(request.getParameter("type"))) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			ObjectMapper objectMapper = new ObjectMapper(); // groupList -> JSON 형식
			String json = objectMapper.writeValueAsString(groupList);
			response.getWriter().write(json); // JSON 데이터 전송
		
		} else {
			request.setAttribute("contentPage", "/jsp/group/group_list.jsp");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
