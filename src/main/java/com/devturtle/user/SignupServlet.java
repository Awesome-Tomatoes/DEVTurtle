package com.devturtle.user;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        // JSON 데이터를 읽어오기
        String joiningStr = request.getReader().lines().collect(Collectors.joining());
        System.out.println("Received JSON: " + joiningStr);

        // Gson으로 JSON 파싱
        Gson gson = new Gson();
        Map<String, String> jsonMap = gson.fromJson(joiningStr, Map.class);

        String userid = jsonMap.get("userid");
        String sorname = jsonMap.get("sorname");

        
        // 확인 출력
        System.out.println("Parsed userid: " + userid);
        System.out.println("Parsed sorname: " + sorname);

        // 데이터 처리: insertSolvedData 메서드 호출
        try {
            if (userid != null && sorname != null) {
//            	SolvedManager mgr = new SolvedManager();
//                mgr.insertSolvedData(userid, sorname);
                System.out.println("Data inserted: " + userid + ", " + sorname);
            } else {
                System.out.println("Invalid JSON data");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 클라이언트에게 로그인 페이지로 리다이렉트
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
