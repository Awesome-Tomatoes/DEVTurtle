package com.devturtle.follow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.DevTurtle.common.DBManager;
import com.DevTurtle.common.OracleDBManager;
import com.DevTurtle.user.UserVO;

public class FollowDAO {
	
	//단일 팔로워 더미
	public FollowVO select(int followID) {
		return new FollowVO(1,1,2,"accept");
	}
	
	//팔로우 리스트 더미
	public ArrayList<FollowVO> select(){
		ArrayList<FollowVO> flist = new ArrayList<FollowVO>();
		flist.add(new FollowVO(1,1,2,"accept"));
		flist.add(new FollowVO(2,1,3,"accept"));
		return flist;
	}
	
	public ArrayList<UserVO> selectAllFollowed(int userID){
		ArrayList<UserVO> ulist = new ArrayList<UserVO>();
		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from users";
			pstmt =  conn.prepareStatement(sql);
			rs = pstmt.executeQuery();  
			while(rs.next()) {
				UserVO uvo = new UserVO();
				uvo.setUserID(  rs.getInt("USER_SEQ")     );
				uvo.setUserName(  rs.getString("USER_NAME")  );
				uvo.setLoginID(  rs.getString("LOGIN_ID")  );
				uvo.setLoginPW(  rs.getString("LOGIN_PW")  );
				uvo.setNickname(  rs.getString("NICKNAME")  );
				uvo.setGitID(  rs.getString("GIT_ID")  );
				uvo.setSolvedID(  rs.getString("SOLVED_ID")  );
				uvo.setUserBio(  rs.getString("USER_BIO")  );
				uvo.setTotalScore(  rs.getInt("TOTAL_SCORE")  );
				uvo.setSolvedScore(  rs.getInt("SOLVED_SCORE")  );
				uvo.setGitScore(  rs.getInt("GIT_SCORE")  );
				ulist.add(uvo);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		return ulist;
	}
	
	public ArrayList<UserVO> selectAllFollowing(int userID){
		ArrayList<UserVO> ulist = new ArrayList<UserVO>();
		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from users";
			pstmt =  conn.prepareStatement(sql);
			rs = pstmt.executeQuery();  
			while(rs.next()) {
				UserVO uvo = new UserVO();
				uvo.setUserID(  rs.getInt("USER_SEQ")     );
				uvo.setUserName(  rs.getString("USER_NAME")  );
				uvo.setLoginID(  rs.getString("LOGIN_ID")  );
				uvo.setLoginPW(  rs.getString("LOGIN_PW")  );
				uvo.setNickname(  rs.getString("NICKNAME")  );
				uvo.setGitID(  rs.getString("GIT_ID")  );
				uvo.setSolvedID(  rs.getString("SOLVED_ID")  );
				uvo.setUserBio(  rs.getString("USER_BIO")  );
				uvo.setTotalScore(  rs.getInt("TOTAL_SCORE")  );
				uvo.setSolvedScore(  rs.getInt("SOLVED_SCORE")  );
				uvo.setGitScore(  rs.getInt("GIT_SCORE")  );
				ulist.add(uvo);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		return ulist;
	}
}
