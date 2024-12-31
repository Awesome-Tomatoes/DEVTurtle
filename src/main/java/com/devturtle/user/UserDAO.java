package com.devturtle.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;

public class UserDAO {

	//단일 유저 더미
	public UserVO select(String userID) {
		return new UserVO(1,"name","id","pw","nickname","create","update","solved","git","userbio",1000,300,700);
	}
	
	//유저리스트 더미
	public ArrayList<UserVO> select() {
		ArrayList<UserVO> alist = new ArrayList<UserVO>();
		alist.add(new UserVO(1,"name1","id1","pw1","nickname1","create1","update1","solved1","git1","userbio",1000,300,700));
		alist.add(new UserVO(2,"name2","id2","pw2","nickname2","create2","update2","solved2","git2","userbio",1200,500,700));
		alist.add(new UserVO(3,"name3","id3","pw3","nickname3","create3","update3","solved3","git3","userbio",1100,500,600));
		alist.add(new UserVO(4,"name4","id4","pw4","nickname4","create4","update4","solved4","git4","userbio",940,510,430));
		return alist;
	}
	
	public ArrayList<UserVO> selectAllUser(){
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
	
	public ArrayList<UserVO> selectAllUserOrderByRank(){
		ArrayList<UserVO> ulist = new ArrayList<UserVO>();
		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from users order by TOTAL_SCORE";
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
	
	public void insert(UserVO uvo) {
		System.out.println("insert 성공");
	}

	public void update(UserVO uvo, int gitScore, int solvedScore) {
		uvo.setGitScore(gitScore);
		uvo.setSolvedScore(solvedScore);
		uvo.setTotalScore(gitScore+solvedScore);
		System.out.println("update 성공");
	}
	
	public void delete(UserVO uvo) {
		System.out.println("delete 성공");
	}
}
