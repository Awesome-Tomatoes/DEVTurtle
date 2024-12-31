package com.devturtle.user;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devturtle.rank.RankUserDAO;
import com.google.gson.Gson;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // JSON 데이터를 읽어오기
        String joiningStr = request.getReader().lines().collect(Collectors.joining());
        System.out.println("Received JSON: " + joiningStr);

//        UserDAO udao = new UserDAO();
//        RankUserDAO rdao = new RankUserDAO();
//        
//        if(udao.insertUser(uvo) == 1) {
//        	rdao.insertRankUser(udao.selectUserByLoginID(uvo.getLoginID).getUserID(), 0);
//        }
        // 클라이언트에게 로그인 페이지로 리다이렉트
        request.getRequestDispatcher(joiningStr).forward(request, response);
    }
}
