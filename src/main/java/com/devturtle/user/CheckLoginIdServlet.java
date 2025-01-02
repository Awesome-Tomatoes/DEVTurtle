package com.devturtle.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/checkLoginId")
public class CheckLoginIdServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginid = request.getParameter("loginid");
        UserDAO dao = new UserDAO();
        
        boolean isDuplicate = dao.checkLoginIdExists(loginid); // loginid 중복 여부 확인
        
        // JSON 응답 생성
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Boolean> jsonResponse = Map.of("isDuplicate", isDuplicate);
        
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(jsonResponse));
    }
}
