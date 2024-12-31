/**
 * 
 */
package com.devturtle.solved;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.devturtle.common.OracleDBManager;


public class SolvedDAO {

ArrayList<SolvedVO> alist = new ArrayList<SolvedVO>();
	
	Connection conn = null;
	PreparedStatement pstmt = null; //sql 점검
	ResultSet rs = null; //결과 출력
	
	public SolvedVO select(int userid) {
		
		OracleDBManager o = OracleDBManager.getInstance();
		
		//TODO
		String sql = "select * from SOLVED_AC where user_id=? order by solved_id asc";
		SolvedVO svo = new SolvedVO();
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			while(rs.next()) { // 1줄 읽는 것. (반복문으로 여러 줄 읽음)
				int solved_id = rs.getInt("solved_id");
				int rating = rs.getInt("rating"); //컬럼명 넣어야 해서 "" 필수
				String created_at = rs.getString("created_at");
				String updated_at = rs.getString("updated_at");
				int user_id = rs.getInt("user_id");

				svo.setSolved_id(solved_id);
				svo.setRating(rating);
				svo.setCreated_at(created_at);
				svo.setUpdated_at(updated_at);
				svo.setUserid(user_id);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		o.close(conn, pstmt, rs);
		System.out.println("내부 select done--");
		return svo;
	}
	
	public ArrayList<SolvedVO> select() {
		
		OracleDBManager o = OracleDBManager.getInstance();
		//TODO
		String sql = "select * from solved_ac order by rating asc";
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SolvedVO svo = null;
			while(rs.next()) { // 1줄 읽는 것. (반복문으로 여러 줄 읽음)
				svo = new SolvedVO();
				int solved_id = rs.getInt("solved_id");
				int rating = rs.getInt("rating"); //컬럼명 넣어야 해서 "" 필수
				String created_at = rs.getString("created_at");
				String updated_at = rs.getString("updated_at");
				int userid = rs.getInt("user_id");
				svo.setSolved_id(solved_id);
				svo.setRating(rating);
				svo.setCreated_at(created_at);
				svo.setUpdated_at(updated_at);
				svo.setUserid(userid);
				alist.add(svo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			System.out.println("--내부 select * done--");
			o.close(conn, pstmt, rs);
		}
		return alist;
	}
	

	public int insert(SolvedVO svo) {
		OracleDBManager o = OracleDBManager.getInstance();
		
		int rows = 0;
		//TODO
		String sql = "insert into solved_ac(solved_id, rating, created_at, updated_at, user_id) values(solved_ac_seq.nextval, ?, sysdate, sysdate, ?)";
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, svo.getRating());
			pstmt.setInt(2, svo.getUserid());

			rows = pstmt.executeUpdate();
			System.out.println("insert done--");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			o.close(conn, pstmt);
		}
		return rows;
	}
	
	public int update(SolvedVO svo) {

		OracleDBManager o = OracleDBManager.getInstance();
		
		int rows = 1;
		//TODO
		String sql = "UPDATE solved_ac SET rating = ?, updated_at = sysdate WHERE user_id = ?";
	
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, svo.getRating());
			pstmt.setInt(2, svo.getUserid());
			rows = pstmt.executeUpdate(); 
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("update done");
		o.close(conn, pstmt);
		return rows;
	}
//	
	public int delete(int userid) {
		
		OracleDBManager o = OracleDBManager.getInstance();
		
		int rows = 1;
		//TODO
		String sql = "DELETE FROM solved_ac WHERE userid = ?";
	
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			rows = pstmt.executeUpdate(); 
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println(" DELETE done");
			o.close(conn, pstmt);
		}
		
		return rows;
	}
}

