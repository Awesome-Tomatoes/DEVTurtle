package com.devturtle.group;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devturtle.rank.RankGroupDAO;
import com.devturtle.rank.RankGroupVO;

/**
 * Servlet implementation class GroupDetailServlet
 */
@WebServlet("/groupdetail")
public class GroupDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // URL 파라미터로 groupId 받기
        String groupIdParam = request.getParameter("groupId");

        System.out.println("groupIdParam >>> "+groupIdParam);
        if (groupIdParam != null) {
            int groupId = Integer.parseInt(groupIdParam); // groupId를 정수로 변환

            // 그룹 전체 정보
            GroupDAO gdao = new GroupDAO();
            GroupVO groupDetail = gdao.selectGroupDetail(1, groupId);  // 사용자 ID와 groupId로 그룹 정보 조회
            request.setAttribute("GROUP_DETAIL", groupDetail);

            // 그룹 랭킹 정보
            GroupVO gRankVO = gdao.selectGroupByIDWithRank(groupId);
            int gRank = gRankVO.getRank();
            int allGroupSize = gdao.selectAllGroupSize();
            request.setAttribute("GROUP_RANK", gRank);
            request.setAttribute("GROUP_SIZE", allGroupSize);

            // 동적으로 포함할 contentPage 경로 설정
            request.setAttribute("contentPage", "/jsp/group/group_detail.jsp");

            // layout.jsp로 포워딩
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            // groupId가 없을 경우 에러 처리
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Group ID is required.");
        }
    }
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
