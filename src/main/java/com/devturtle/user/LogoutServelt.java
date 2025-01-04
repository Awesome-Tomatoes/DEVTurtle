package com.devturtle.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public LogoutServelt() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        if (session != null) {
            session.invalidate();
        }
        
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(
            "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "    <script type='text/javascript'>" +
            "        alert('로그아웃 되었습니다.');" +
            "        window.location.href = '" + request.getContextPath() + "/main';" +
            "    </script>" +
            "</head>" +
            "<body></body>" +
            "</html>"
        );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request, response);
	}

}
