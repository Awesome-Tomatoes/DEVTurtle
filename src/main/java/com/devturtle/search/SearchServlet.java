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
import javax.servlet.http.HttpSession;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;
import com.devturtle.user.UserVO;
import com.devturtle.group.GroupVO;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
    	System.out.println(query);
        
//		HttpSession session = request.getSession();
//		//userID 세션에 없으면 0, 잇으면 로그인한 userID기준으로 검색 결과 팔로우, 팔로워 관계 찾기
//		int userID = 25;
//		if (session != null)  {
//			String uid = (String)session.getAttribute("SESS_USER_ID");
//			System.out.println(uid);
//			if(uid != null) {
//				userID = Integer.parseInt(uid);
//			}
////          session.getAttribute("SESS_USER_NICKNAME");
////          session.getAttribute("SESS_ROLE");
////          session.getAttribute("SESS_GROUP");
//		}
        
        if (query == null || query.isEmpty()) {
            query = "";
            request.setAttribute("contentPage", "/jsp/search/search.jsp");
    	    request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        
        else {
        	//검색결과 담을 유저,그룹 VO
            ArrayList<UserVO> followUlist = new ArrayList<>();
            ArrayList<GroupVO> joinedGlist = new ArrayList<>();
            ArrayList<UserVO> unFollowUlist = new ArrayList<>();
            ArrayList<GroupVO> unjoinedGlist = new ArrayList<>();
            
    	    DBManager dbm = null;
    	    Connection conn = null ;
    	    PreparedStatement pstmt = null ;
    	    ResultSet rs = null;
    	 
            try  {
        	    dbm = OracleDBManager.getInstance();
        	    conn = dbm.connect();
        	    pstmt = null;
        	    String userSql = "SELECT * FROM USERS WHERE USER_NAME LIKE ? ORDER BY USER_NAME";
            	pstmt = conn.prepareStatement(userSql);
            	pstmt.setString(1, "%" + query + "%");
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    UserVO uvo = new UserVO();
                    uvo.setUserID(rs.getInt("USER_ID"));
    				uvo.setUserName(rs.getString("USER_NAME"));
    				uvo.setNickname(rs.getString("NICKNAME"));
    				uvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
    				followUlist.add(uvo);
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
                String groupSql = "SELECT * FROM GROUPS WHERE NAME LIKE ? ORDER BY NAME";
                
                //참여한 그룹 가져오기
                String s1 = "SELECT * FROM GROUPS G LEFT JOIN GROUP_USER GU \r\n"
                		+ "ON G.GROUP_ID = GU.GROUP_ID\r\n"
                		+ "WHERE GU.USER_ID = ? AND NAME LIKE ?\r\n"
                		+ "ORDER BY G.GROUP_ID";
                
                //참여안한 안된 그룹 가져오기
                String s2 = "SELECT * FROM GROUPS G WHERE\r\n"
                		+ "G.GROUP_ID NOT IN \r\n"
                		+ "(SELECT GROUPS.GROUP_ID \r\n"
                		+ " FROM GROUPS LEFT JOIN GROUP_USER \r\n"
                		+ "      ON GROUPS.GROUP_ID = GROUP_USER.GROUP_ID\r\n"
                		+ " WHERE NAME LIKE ? GROUP_USER.USER_ID = ?\r\n"
                		+ ")\r\n"
                		+ "ORDER BY G.GROUP_ID";
                pstmt = conn.prepareStatement(groupSql);
            	pstmt.setString(1, "%" + query + "%");
                rs = pstmt.executeQuery();                   
                while (rs.next()) {
                	GroupVO gvo = new GroupVO();
    				gvo.setGroupId(rs.getInt("GROUP_ID"));
    				gvo.setName(rs.getString("NAME"));
    			    gvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
    			    joinedGlist.add(gvo);
    			}
            } catch (SQLException e) {
                System.err.println("SQL Error Code: " + e.getErrorCode());
                System.err.println("SQL State: " + e.getSQLState());
    			e.printStackTrace();
    		} finally {
    			dbm.close(conn, pstmt, rs);
    		}
            
    		request.setAttribute("GLIST", joinedGlist);
    		request.setAttribute("ULIST", followUlist);
    		
    		System.out.println(joinedGlist.toString());
    		System.out.println(followUlist.toString());

    		request.setAttribute("contentPage", "/jsp/search/search.jsp");
    		
    	    request.getRequestDispatcher("/index.jsp").forward(request, response);	
            
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
