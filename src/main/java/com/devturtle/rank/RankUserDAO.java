package com.devturtle.rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;


public class RankUserDAO {
	public ArrayList<RankUserVO> selectRankUser() {
		ArrayList<RankUserVO> rulist = new ArrayList<RankUserVO>();
		rulist.add(new RankUserVO(1, 1, 100, "20241231"));
		rulist.add(new RankUserVO(2, 2, 200, "20241231"));
		rulist.add(new RankUserVO(3, 3, 300, "20241231"));
		rulist.add(new RankUserVO(4, 4, 100, "20241231"));
		rulist.add(new RankUserVO(5, 1, 1000, "20250101"));

		return rulist;
	}
	
	
	
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
	
	public static void main(String[] argv) {
		RankUserDAO rudao = new RankUserDAO();
		rudao.insertRankUser(15, 2400);
	}
	
}
