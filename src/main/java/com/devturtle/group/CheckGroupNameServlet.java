package com.devturtle.group;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Servlet implementation class GroupNameCheckServlet
 */
@WebServlet("/checkGroupName")
public class CheckGroupNameServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    String groupName = request.getParameter("groupName");
        GroupDAO dao = new GroupDAO();
        
        boolean isDuplicate = dao.checkGroupNameExists(groupName); // group name 중복 여부 확인
        
        // JSON 응답 생성
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Boolean> jsonResponse = Map.of("isDuplicate", isDuplicate);
        
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(jsonResponse));
    
	}

}
