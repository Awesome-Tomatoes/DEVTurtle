package com.devturtle.rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;


public class RankUserDAO {
	//테스트용 유저랭킹 리스트
	public ArrayList<RankUserVO> selectRankUser() {
		ArrayList<RankUserVO> rulist = new ArrayList<RankUserVO>();
		rulist.add(new RankUserVO(1, 1, 100, "20241231"));
		rulist.add(new RankUserVO(2, 2, 200, "20241231"));
		rulist.add(new RankUserVO(3, 3, 300, "20241231"));
		rulist.add(new RankUserVO(4, 4, 100, "20241231"));
		rulist.add(new RankUserVO(5, 1, 1000, "20250101"));

		return rulist;
	}
	
	//유저 생성할때 랭크 0점 초기화, 자정에 당일 점수 랭킹에 반영해서 insert하는 로직
	public int insertRankUser(int userID, int sumScore) {
		
		DBManager dbm = OracleDBManager.getInstance(); //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
        int rows = 0;
        
        
        try {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO Rank_User (RANK_USER_ID, USER_ID, SCORE_SUM, RANK_USER_DATE) VALUES (RANK_USER_SEQ.NEXTVAL, ?, ?, SYSDATE)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userID);
            pstmt.setInt(2, sumScore);
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

        return rows;
    }
	
	//일별 유저 랭킹 조회
	public ArrayList<RankUserVO> selectRankUserAllByDay(String date) {
        ArrayList<RankUserVO> rulist = new ArrayList<>();
        DBManager dbm = OracleDBManager.getInstance();
        Connection conn = dbm.connect();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT RANK_USER_ID, USER_ID, SCORE_SUM, TO_CHAR(RANK_USER_DATE, 'YYYYMMDD') AS RANK_USER_DATE " +
                         "FROM Rank_User " +
                         "WHERE TO_CHAR(RANK_USER_DATE, 'YYYYMMDD') = ? " +
                         "ORDER BY SCORE_SUM DESC, RANK_USER_ID";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, date);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int rankUserID = rs.getInt("RANK_USER_ID");
                int userID = rs.getInt("USER_ID");
                int scoreSum = rs.getInt("SCORE_SUM");
                String rankUserDate = rs.getString("RANK_USER_DATE");

                rulist.add(new RankUserVO(rankUserID, userID, scoreSum, rankUserDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbm.close(conn, pstmt, rs);
        }

        return rulist;
    }
	
	// 월별 유저 랭킹 조회
	public ArrayList<RankUserVO> selectRankUserAllByMonth(String date) {
	    ArrayList<RankUserVO> rulist = new ArrayList<>();
	    DBManager dbm = OracleDBManager.getInstance();
	    Connection conn = dbm.connect();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String sql = "SELECT RANK_USER_ID, USER_ID, SCORE_SUM, TO_CHAR(RANK_USER_DATE, 'YYYYMMDD') AS RANK_USER_DATE " +
	                     "FROM Rank_User " +
	                     "WHERE TRUNC(RANK_USER_DATE, 'MM') = TRUNC(TO_DATE(?, 'YYYYMMDD'), 'MM') " +
	                     "ORDER BY SCORE_SUM DESC, RANK_USER_ID";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, date);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int rankUserID = rs.getInt("RANK_USER_ID");
	            int userID = rs.getInt("USER_ID");
	            int scoreSum = rs.getInt("SCORE_SUM");
	            String rankUserDate = rs.getString("RANK_USER_DATE");

	            rulist.add(new RankUserVO(rankUserID, userID, scoreSum, rankUserDate));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        dbm.close(conn, pstmt, rs);
	    }

	    return rulist;
	}
	
	public static void main(String[] argv) {
		RankUserDAO rudao = new RankUserDAO();
//		rudao.insertRankUser(15, 2400);
//		ArrayList<RankUserVO> arr = rudao.selectRankUserAllByDay("20241231");
		ArrayList<RankUserVO> arr = rudao.selectRankUserAllByMonth("20241231");
		for(var x : arr) System.out.println(x.toString());
	}
	
}
