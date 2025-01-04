package com.devturtle.search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;
import com.devturtle.user.UserVO;
import com.devturtle.group.GroupVO;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        
        if (query == null || query.isEmpty()) {
            query = "";
            request.setAttribute("contentPage", "/jsp/search/search.jsp");
    	    request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        
        else {
        	System.out.println(query);
        	//검색결과 담을 유저,그룹 VO
    
            ArrayList<UserVO> ulist = new ArrayList<>();
            ArrayList<GroupVO> glist = new ArrayList<>();
            
    	    DBManager dbm = OracleDBManager.getInstance();
    	    Connection conn = dbm.connect();
    	    PreparedStatement pstmt = null;


    	    String userSql = "SELECT * FROM USERS WHERE USER_NAME LIKE ?";
            try  {
            	PreparedStatement userStmt = conn.prepareStatement(userSql);
                userStmt.setString(1, "%" + query + "%");
                ResultSet rs = userStmt.executeQuery();
                while (rs.next()) {
                    UserVO uvo = new UserVO();
    				uvo.setUserName(rs.getString("USER_NAME"));
    				uvo.setNickname(rs.getString("NICKNAME"));
    				uvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
    				ulist.add(uvo);
                }
            } catch (SQLException e) {
                System.err.println("SQL Error Code: " + e.getErrorCode());
                System.err.println("SQL State: " + e.getSQLState());
    			e.printStackTrace();
    		}

            String groupSql = "SELECT * FROM GROUPS WHERE NAME LIKE ?";
            try {
            	PreparedStatement groupStmt = conn.prepareStatement(groupSql);
                groupStmt.setString(1, "%" + query + "%");
                ResultSet rs = groupStmt.executeQuery();                   
                while (rs.next()) {
                	GroupVO gvo = new GroupVO();
    				gvo.setGroupId(rs.getInt("GROUP_ID"));
    			    gvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
    			    glist.add(gvo);
    			}
            } catch (SQLException e) {
                System.err.println("SQL Error Code: " + e.getErrorCode());
                System.err.println("SQL State: " + e.getSQLState());
    			e.printStackTrace();
    		}
            
    		request.setAttribute("GLIST", glist);
    		request.setAttribute("ULIST", glist);

    		request.setAttribute("contentPage", "/jsp/search/search.jsp");
    		
    	    request.getRequestDispatcher("/index.jsp").forward(request, response);	
            
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
