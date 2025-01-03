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
    		request.getRequestDispatcher("/jsp/search/search.jsp").forward(request, response);
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
            try (PreparedStatement userStmt = conn.prepareStatement(userSql)) {
                userStmt.setString(1, "%" + query + "%");
                try (ResultSet rs = userStmt.executeQuery()) {
                    while (rs.next()) {
                        UserVO uvo = new UserVO();
                        uvo.setUserID(rs.getInt("USER_ID"));
        				uvo.setUserName(rs.getString("USER_NAME"));
        				uvo.setLoginID(rs.getString("LOGIN_ID"));
        				uvo.setLoginPW(rs.getString("LOGIN_PW"));
        				uvo.setNickname(rs.getString("NICKNAME"));
        				uvo.setGitID(rs.getString("GIT_ID"));
        				uvo.setSolvedID(rs.getString("SOLVED_ID"));
        				uvo.setUserBio(rs.getString("USER_BIO"));
        				uvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
        				uvo.setSolvedScore(rs.getInt("SOLVED_SCORE"));
        				uvo.setGitScore(rs.getInt("GIT_SCORE"));
                    }
                }
            } catch (SQLException e) {
    			e.printStackTrace();
    		}

            String groupSql = "SELECT * FROM GROUPS WHERE NAME LIKE ?";
            try (PreparedStatement groupStmt = conn.prepareStatement(groupSql)) {
                groupStmt.setString(1, "%" + query + "%");
                try (ResultSet rs = groupStmt.executeQuery()) {
                    while (rs.next()) {
                    	GroupVO gvo = new GroupVO();
        				gvo.setGroupId(rs.getInt("GROUP_ID"));
        				gvo.setName(rs.getString("NAME"));
        				gvo.setSize(rs.getInt("SIZES"));
        			    gvo.setCondition(rs.getInt("CONDITIONS"));
        			    gvo.setDescription(rs.getString("DESCRIPTION"));
        			    gvo.setCategory(rs.getString("CATEGORY"));
        			    gvo.setGPrivate(rs.getString("PRIVATE"));
        			    gvo.setLocation(rs.getString("LOCATION"));
        			    gvo.setCreatedAt(rs.getString("CREATED_AT"));
        			    gvo.setUpdatedAt(rs.getString("UPDATED_AT"));
        			    gvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
        			    glist.add(gvo);
                    }
                }
            } catch (SQLException e) {
    			e.printStackTrace();
    		}
            ObjectMapper objectMapper = new ObjectMapper();
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("users", ulist);
            resultMap.put("groups", glist);

            // JSON 응답 설정
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(resultMap));
            System.out.println(resultMap.toString());
        }
        

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
