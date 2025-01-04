package com.devturtle.search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;
import com.devturtle.user.UserVO;
import com.devturtle.group.GroupVO;

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
            
    	    DBManager dbm = null;
    	    Connection conn = null ;
    	    PreparedStatement pstmt = null ;
    	    ResultSet rs = null;
    	 
            try  {
        	    dbm = OracleDBManager.getInstance();
        	    conn = dbm.connect();
        	    pstmt = null;
        	    String userSql = "SELECT * FROM USERS WHERE USER_NAME LIKE ?";
            	pstmt = conn.prepareStatement(userSql);
            	pstmt.setString(1, "%" + query + "%");
                rs = pstmt.executeQuery();
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
    		} finally {
    			dbm.close(conn, pstmt, rs);
    		}

            try {
            	dbm = OracleDBManager.getInstance();
        	    conn = dbm.connect();
        	    pstmt = null;
                String groupSql = "SELECT * FROM GROUPS WHERE NAME LIKE ?";
                pstmt = conn.prepareStatement(groupSql);
            	pstmt.setString(1, "%" + query + "%");
                rs = pstmt.executeQuery();                   
                while (rs.next()) {
                	GroupVO gvo = new GroupVO();
    				gvo.setGroupId(rs.getInt("GROUP_ID"));
    				gvo.setName(rs.getString("NAME"));
    			    gvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
    			    glist.add(gvo);
    			}
            } catch (SQLException e) {
                System.err.println("SQL Error Code: " + e.getErrorCode());
                System.err.println("SQL State: " + e.getSQLState());
    			e.printStackTrace();
    		} finally {
    			dbm.close(conn, pstmt, rs);
    		}
            
    		request.setAttribute("GLIST", glist);
    		request.setAttribute("ULIST", ulist);
    		
    		System.out.println(glist.toString());
    		System.out.println(ulist.toString());

    		request.setAttribute("contentPage", "/jsp/search/search.jsp");
    		
    	    request.getRequestDispatcher("/index.jsp").forward(request, response);	
            
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
