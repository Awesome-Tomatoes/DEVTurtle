package com.devturtle.group;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GroupDetailServlet
 */
@WebServlet("/GroupDetailServlet")
public class GroupDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// session 가짜 데이터
		int userId =1;
		int groupId =1;
		
		// 그룹 전체 정보
		GroupDAO gdao = new GroupDAO();
		GroupVO groupDetail = gdao.selectGroupDetail(userId,groupId);
		request.setAttribute("GROUP_DETAIL", groupDetail);
		
		// 그룹 랭킹 정보
		
		
		
		// 그룹 달성 업정정보
		
		
		request.getRequestDispatcher("/jsp/group/group_detail.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
