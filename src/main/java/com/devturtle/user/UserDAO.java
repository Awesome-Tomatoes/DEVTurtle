package com.devturtle.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;
import com.devturtle.git.GitManager;
import com.devturtle.solved.SolvedDAO;
import com.devturtle.solved.SolvedManager;


public class UserDAO {

	//단일 유저 더미
	public UserVO selectUser(int userid) {
		UserVO uvo = new UserVO();

		DBManager dbm = OracleDBManager.getInstance();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from users where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid); //------파라미터를 1번째?에 바인딩

			rs = pstmt.executeQuery();
			if(rs.next()) {
			
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return uvo;
	}
	
	public UserVO selectUserByLoginID(String loginid) {
		UserVO uvo = new UserVO();

		DBManager dbm = OracleDBManager.getInstance();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from users where login_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, loginid); //------파라미터를 1번째?에 바인딩

			rs = pstmt.executeQuery();
			if(rs.next()) {
			
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return uvo;
	}

	public ArrayList<UserVO> selectAllUser() {
		ArrayList<UserVO> ulist = new ArrayList<UserVO>();
		DBManager dbm = OracleDBManager.getInstance();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from users";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
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
				ulist.add(uvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return ulist;
	}

	public ArrayList<UserVO> selectAllUserOrderByRank() {
		ArrayList<UserVO> ulist = new ArrayList<UserVO>();
		DBManager dbm = OracleDBManager.getInstance();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from users order by TOTAL_SCORE";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
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
				ulist.add(uvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return ulist;
	}

public ArrayList<UserVO> selectAllUserOrderByRankPaging(int startSeq , int endSeq) {
		
		ArrayList<UserVO> alist = new ArrayList<UserVO>();
		
		DBManager dbm = OracleDBManager.getInstance();  	//new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			String sql = "select s.* from\r\n"
					+ "(select users.*, (ROW_NUMBER() OVER(order by TOTAL_SCORE desc, USER_ID)) as rnum from users) s\r\n"
					+ "where  rnum between ? and ?";
			pstmt =  conn.prepareStatement(sql);
			pstmt.setInt(1, startSeq);
			pstmt.setInt(2, endSeq);
			rs = pstmt.executeQuery();  
			while(rs.next()) {
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
				uvo.setRank(rs.getInt("rnum"));
				alist.add(uvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		return alist;
	}

	public boolean checkLoginIdExists(String loginid) {

		DBManager dbm = OracleDBManager.getInstance();
		
        String query = "SELECT COUNT(*) FROM users WHERE login_id = ?";
        try (Connection conn = dbm.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, loginid);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // 중복이면 true 반환
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
	
	
	public int insertUser(UserVO uvo) {
		DBManager dbm = OracleDBManager.getInstance(); //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		SolvedManager smgr = new SolvedManager();
		GitManager gmgr = new GitManager();
		int rows = 0;
		try {
			conn.setAutoCommit(false);

			String sql = "insert into users(user_id, user_name, login_id, login_pw, nickname, created_at, updated_at, solved_id, git_id, user_bio, total_score, solved_score, git_score)\r\n"
				+ "values(user_seq.nextval,?,?,?,?,sysdate,sysdate,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uvo.getUserName()); //------파라미터를 1번째?에 바인딩
			pstmt.setString(2, uvo.getLoginID());
			pstmt.setString(3, uvo.getLoginPW());
			pstmt.setString(4, uvo.getNickname());
			pstmt.setString(5, uvo.getSolvedID());
			pstmt.setString(6, uvo.getGitID());
			pstmt.setString(7, uvo.getUserBio());
			pstmt.setInt(8, uvo.getTotalScore());
			pstmt.setInt(9, uvo.getSolvedScore());
			pstmt.setInt(10, uvo.getGitScore());
			rows = pstmt.executeUpdate();
			if (rows == 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
		
		UserVO uservo = selectUserByLoginID(uvo.getLoginID());
		smgr.insertSolvedData(uservo.getUserID());
		gmgr.insertGitData(uservo.getUserID());
		updateUserSolvedScore(uservo.getUserID(), smgr.selectUserSolvedData(uservo.getUserID()).getRating());
		updateUserGitScore(uservo.getUserID(), gmgr.selectUserGitData(uservo.getUserID()).getRating());
		
		return rows;
	}

	public int updateUserSolvedScore(int userid, int solvedRating) {
		DBManager dbm = OracleDBManager.getInstance(); //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		UserVO uvo = selectUser(userid);
		int rows = 0;
		try {
			String sql = "update users set total_score=?, solved_score = ?, updated_at = sysdate where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uvo.getGitScore() + solvedRating); //------파라미터를 1번째?에 바인딩
			pstmt.setInt(2, solvedRating);
			pstmt.setInt(3, userid);
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
		
		return rows;
	}
	
	public int updateUserGitScore(int userid, int gitRating) {
		DBManager dbm = OracleDBManager.getInstance(); //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		UserVO uvo = selectUser(userid);
		int rows = 0;
		try {
			String sql = "update users set total_score=?, git_score = ?, updated_at = sysdate where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uvo.getSolvedScore() + gitRating); //------파라미터를 1번째?에 바인딩
			pstmt.setInt(2, gitRating);
			pstmt.setInt(3, userid);
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
		
		return rows;
	}
	public int updateUserData(int userid, String nickname, String userBio) {
		DBManager dbm = OracleDBManager.getInstance(); //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			String sql = "update users set nickname=?, user_bio=?, updated_at = sysdate where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);
			pstmt.setString(2, userBio);
			pstmt.setInt(3, userid);
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
		return rows;
	}

	public int deleteUser(int userid) {
		DBManager dbm = OracleDBManager.getInstance(); //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		SolvedDAO dao = new SolvedDAO();
		dao.delete(userid);
		
		int rows = 0;
		try {
			String sql = "delete from users where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid); //------파라미터를 1번째?에 바인딩
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt);
		}
		return rows;
	}	
	
}
