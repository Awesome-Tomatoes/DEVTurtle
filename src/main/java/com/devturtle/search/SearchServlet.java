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

import org.apache.tomcat.dbcp.dbcp2.PStmtKey;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;
import com.devturtle.follow.FollowDAO;
import com.devturtle.user.UserDAO;
import com.devturtle.user.UserVO;
import com.devturtle.group.GroupVO;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
    	System.out.println(query);
        
		HttpSession session = request.getSession(false);
		int userID = 0;
		//userID 세션에 없으면 0, 잇으면 로그인한 userID기준으로 검색 결과 팔로우, 팔로워 관계 찾기
		
		if (session!=null)  {
			Object sessionUserID = session.getAttribute("SESS_USER_ID");
		    if (sessionUserID != null) {
		        try {
		            userID = (Integer) sessionUserID; // null이 아님을 확인한 후 캐스팅
		        } catch (ClassCastException e) {
		            System.out.println("SESS_USER_ID가 Integer 형식이 아닙니다.");
		        }
		    } else {
		        System.out.println("SESS_USER_ID가 세션에 없습니다.");
		    }
		}
    	
        if (query == null || query.isEmpty()) {
            query = "";
            	
            request.setAttribute("contentPage", "/jsp/search/search.jsp");
    	    request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        
        else {
            //검색결과 담을 유저,그룹 VO
            UserDAO udao = new UserDAO();
            FollowDAO fdao = new FollowDAO();
            ArrayList<UserVO> ulist = udao.selectAllForSearch(userID, query);
            ArrayList<UserVO> flist = fdao.selectAllFollowed(userID, query);
            ArrayList<GroupVO> glist = new ArrayList<>();
            
            System.out.println("flist :" + flist.toString());
            for(int i = 0; i < ulist.size(); i++) {
                boolean bool = false;
                for(UserVO fvo : flist) {
                    if (ulist.get(i).getUserID() == fvo.getUserID())
                        bool = true;
                }
                if(bool) {
                    ulist.remove(i);
                    i--;
                }
            }
            
            request.setAttribute("ULIST",ulist);
            request.setAttribute("FLIST",flist);
            
    	    DBManager dbm = null;
    	    Connection conn = null ;
    	    PreparedStatement pstmt = null ;
    	    ResultSet rs = null;
    	 
//            try  {
//        	    dbm = OracleDBManager.getInstance();
//        	    conn = dbm.connect();
//        	    pstmt = null;
//        	    String userSql = "SELECT * FROM USERS WHERE NICKNAME LIKE ? ORDER BY NICKNAME";
//            	pstmt = conn.prepareStatement(userSql);
//            	pstmt.setString(1, "%" + query + "%");
//                rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    UserVO uvo = new UserVO();
//                    uvo.setUserID(rs.getInt("USER_ID"));
//    				uvo.setUserName(rs.getString("USER_NAME"));
//    				uvo.setNickname(rs.getString("NICKNAME"));
//    				uvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
//    				ulist.add(uvo);
//                }
//            } catch (SQLException e) {
//                System.err.println("SQL Error Code: " + e.getErrorCode());
//                System.err.println("SQL State: " + e.getSQLState());
//    			e.printStackTrace();
//    		} finally {
//    			dbm.close(conn, pstmt, rs);
//    		}
            
            
            //그룹
            try {
            	dbm = OracleDBManager.getInstance();
        	    conn = dbm.connect();
        	    pstmt = null;
                
                
                String sql = "SELECT * FROM GROUPS G LEFT JOIN GROUP_USER GU \r\n"
                		+ "ON G.GROUP_ID = GU.GROUP_ID\r\n"
                		+ "WHERE GU.USER_ID = ? AND NAME LIKE ?\r\n"
                		+ "ORDER BY G.NAME";
                
//                String sql2 = "SELECT * FROM GROUPS G ";
                
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, userID);
            	pstmt.setString(2, "%" + query + "%");
                rs = pstmt.executeQuery();                   
                while (rs.next()) {
                	System.out.println("참여한 그룹 : " + rs.getString("NAME"));
                	GroupVO gvo = new GroupVO();
    				gvo.setGroupId(rs.getInt("GROUP_ID"));
    				gvo.setName(rs.getString("NAME"));
    			    gvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
    			    gvo.setJoin(true);
    			    glist.add(gvo);
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
                
                //참여안한 안된 그룹 가져오기
                String sql = "SELECT * FROM GROUPS G WHERE\r\n"
                		+ "G.GROUP_ID NOT IN \r\n"
                		+ "(SELECT GROUPS.GROUP_ID \r\n"
                		+ " FROM GROUPS LEFT JOIN GROUP_USER \r\n"
                		+ "      ON GROUPS.GROUP_ID = GROUP_USER.GROUP_ID\r\n"
                		+ " WHERE  GROUP_USER.USER_ID = ?\r\n"
                		+ ")  AND NAME LIKE ?\r\n"
                		+ "ORDER BY G.NAME";

                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, userID);
            	pstmt.setString(2, "%" + query + "%");
                rs = pstmt.executeQuery();                   
                while (rs.next()) {
                	System.out.println("참여안한 그룹 : " + rs.getString("NAME"));
                	GroupVO gvo = new GroupVO();
    				gvo.setGroupId(rs.getInt("GROUP_ID"));
    				gvo.setName(rs.getString("NAME"));
    			    gvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
    			    gvo.setJoin(false);
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
    		
    		glist.sort((a, b) ->{
    			return a.getName().compareTo(b.getName());
    		});
    		
    		ulist.sort((a, b) ->{
    			return a.getNickname().compareTo(b.getNickname());
    		});
    		
//    		System.out.println(glist.toString());
//    		System.out.println(ulist.toString());

    		request.setAttribute("contentPage", "/jsp/search/search.jsp");
    		
    	    request.getRequestDispatcher("/index.jsp").forward(request, response);	
            
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
