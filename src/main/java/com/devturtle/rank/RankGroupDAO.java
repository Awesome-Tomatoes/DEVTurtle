package com.devturtle.rank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;

public class RankGroupDAO {
	public ArrayList<RankGroupVO> selectAllRankUserByGroupID(int groupID) {
		ArrayList<RankGroupVO> rglist = new ArrayList<RankGroupVO>();
		
		rglist.add(new RankGroupVO(1, 1, 100, "20241231"));
		rglist.add(new RankGroupVO(2, 2, 200, "20241231"));
		rglist.add(new RankGroupVO(3, 3, 300, "20241231"));
		rglist.add(new RankGroupVO(4, 4, 100, "20241231"));
		rglist.add(new RankGroupVO(5, 1, 1000, "20250101"));
		return rglist;
	}
	
	//그룹 생성할때 랭크 0점 초기화, 자정에 당일 점수 랭킹에 반영해서 insert하는 로직
	public int insertRankGroup(int groupID, int sumScore) {
		
		DBManager dbm = OracleDBManager.getInstance(); //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
        int rows = 0;
        
        
        try {
            conn.setAutoCommit(false);

            String sql = "INSERT INTO RANK_GROUP (RANK_GROUP_ID, GROUP_ID, SCORE_SUM, RANK_GROUP_DATE) VALUES (RANK_GROUP_SEQ.NEXTVAL, ?, ?, SYSDATE)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, groupID);
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
	
	// 월별 그룹 랭킹 변동사항 조회 - 차트용
	public ArrayList<RankGroupVO> selectRankGroupAllByMonth(int gID, String date) {
	    ArrayList<RankGroupVO> rglist = new ArrayList<>();
	    DBManager dbm = OracleDBManager.getInstance();
	    Connection conn = dbm.connect();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {
	        String sql = "select * \r\n"
	        		+ "from RANK_GROUP \r\n"
	        		+ "WHERE GROUP_ID = ? AND TO_CHAR(RANK_GROUP_DATE, 'YYYYMM') LIKE  TO_CHAR(TO_DATE(?), 'YYYYMM') \r\n"
	        		+ "ORDER BY RANK_GROUP_DATE";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, gID);
	        pstmt.setString(2, date);
	        rs = pstmt.executeQuery();

	        while (rs.next()) {
	            int rankGroupID = rs.getInt("RANK_GROUP_ID");
	            int groupID = rs.getInt("GROUP_ID");
	            int scoreSum = rs.getInt("SCORE_SUM");
	            String rankGroupDate = rs.getString("RANK_GROUP_DATE");
	            rglist.add(new RankGroupVO(rankGroupID, groupID, scoreSum, rankGroupDate));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        dbm.close(conn, pstmt, rs);
	    }
	    return rglist;
	}
	
		public static void main(String[] argv) {
		RankGroupDAO rudao = new RankGroupDAO();
		ArrayList<RankGroupVO> arr = rudao.selectRankGroupAllByMonth(1, "20250101");
		for(var x : arr) System.out.println(x.toString());
	}
}
