package com.devturtle.follow;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;
import com.devturtle.user.UserVO;

public class FollowDAO {
	
	public int countUserFollowed(int userid) {
		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		int countFollowing = 0;
		try {
			String sql = "select count(1) as cnt from follow where follower = ?";
			pstmt =  conn.prepareStatement(sql);
			
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();  
			if(rs.next()) {
				countFollowing = rs.getInt("CNT");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		return countFollowing;
	}
	
	public int countUserFollowing(int userid) {
		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		int countFollowing = 0;
		try {
			String sql = "select count(1) as cnt from follow where following = ?";
			pstmt =  conn.prepareStatement(sql);
			
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();  
			if(rs.next()) {
				countFollowing = rs.getInt("CNT");
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		return countFollowing;
	}
	
	//단일 팔로워 더미
	public FollowVO select(int followID) {
		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		FollowVO fvo = new FollowVO();
		try {
			String sql = "select * from follow where follow_id = ?";
			pstmt =  conn.prepareStatement(sql);
			
			pstmt.setInt(1, followID);
			rs = pstmt.executeQuery();  
			if(rs.next()) {
				fvo.setFollowID(rs.getInt("FOLLOW_ID"));
				fvo.setFollower(rs.getInt("FOLLOWER"));
				fvo.setFollowing(rs.getInt("FOLLOWING"));
				fvo.setState(rs.getString("STATE"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		return fvo;
	}
	
	
	public ArrayList<UserVO> selectAllFollowed(int userID){
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<UserVO> ulist = new ArrayList<UserVO>();
		
		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select following from follow where follower = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			rs = pstmt.executeQuery();  
			
			while(rs.next()) {
				list.add(rs.getInt("following"));
			}
			
			for(int uid : list) {
			sql = "select * from users where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();

			UserVO uvo = new UserVO();
			
				while(rs.next()) {
					uvo.setUserID(  rs.getInt("USER_ID")     );
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		return ulist;
	}
	
	public ArrayList<UserVO> selectAllFollowing(int userID){
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<UserVO> ulist = new ArrayList<UserVO>();
		
		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select follower from follow where following = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userID);
			rs = pstmt.executeQuery();  
			
			while(rs.next()) {
				list.add(rs.getInt("follower"));
			}
			
			for(int uid : list) {
			sql = "select * from users where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uid);
			rs = pstmt.executeQuery();

			UserVO uvo = new UserVO();
			
				while(rs.next()) {
					uvo.setUserID(  rs.getInt("USER_ID")     );
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		return ulist;
	}
	
	public int insertFollowing(int followerID, int followedID) {

		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int rows = 0;		
		try {
			String sql = "insert into follow values (follow_seq.nextval, ?, ?, 'wait')";
			pstmt =  conn.prepareStatement(sql);
			
			pstmt.setInt(1, followerID);
			pstmt.setInt(2, followedID);
			rows = pstmt.executeUpdate();
			if (rows == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		
		return rows;
	}
	
	public int insertFollowed(int followerID, int followedID) {

		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int rows = 0;		
		try {
			String sql = "insert into follow values (follow_seq.nextval, ?, ?, 'accept')";
			pstmt =  conn.prepareStatement(sql);
			
			pstmt.setInt(1, followerID);
			pstmt.setInt(2, followedID);
			rows = pstmt.executeUpdate();
			if (rows == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		
		return rows;
	}
	
	public int updateState(int followerID, int followedID) {

		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int rows = 0;		
		try {
			String sql = "update follow set state='accept' where follower = ? and following = ?";
			pstmt =  conn.prepareStatement(sql);
			
			pstmt.setInt(1, followerID);
			pstmt.setInt(2, followedID);
			rows = pstmt.executeUpdate();
			if (rows == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		
		if (rows == 1) {
			insertFollowed(followedID, followerID);
		}
		
		return rows;
	}
	
	public int deleteFollow(int followerID, int followedID) {

		DBManager dbm = OracleDBManager.getInstance(); 
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int rows = 0;
		try {
			String sql = "delete follow where follower = ? and following = ?";
			pstmt =  conn.prepareStatement(sql);
			
			pstmt.setInt(1, followerID);
			pstmt.setInt(2, followedID);
			rows = pstmt.executeUpdate();
			if (rows == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	

		try {
			String sql = "delete follow  where follower = ? and following = ?";
			pstmt =  conn.prepareStatement(sql);
			
			pstmt.setInt(1, followedID);
			pstmt.setInt(2, followerID);
			rows = pstmt.executeUpdate();
			if (rows == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		
		return rows;
	}
}
