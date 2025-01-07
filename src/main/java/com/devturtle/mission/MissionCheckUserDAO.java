/**
 * 
 */
package com.devturtle.mission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2025. 1. 8.<br>
 * History :<br>
 * - 작성자 : victo, 날짜 : 2025. 1. 8., 설명 : 최초작성<br>
 *
 * @author victo
 * @version 1.0
 */
public class MissionCheckUserDAO {

	DBManager o = OracleDBManager.getInstance();
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	// 특정 미션 번호에 해당하는 objective_query 가져옴.
	public String getObjectQuery(int objectiveId){
	
		DBManager o = OracleDBManager.getInstance();
		// 쿼리 작성 (objective에서 objective_query 가져오기
		String sql = "select objective_query from objective where objective_id = ?";
		String objectQuery = null;
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, objectiveId);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				objectQuery = rs.getString("objective_query");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		o.close(conn, pstmt, rs);
		
		return objectQuery;
	}
	
	// 해당 그룹이 미션 수행했는지 조회해서 있으면 true, 없으면 false
	public boolean isMissionComplete(int userId, int objectiveId) {
		
		DBManager o = OracleDBManager.getInstance();
		
		boolean result = false;
		int notGain = 0;
		String sql = "select objective_id from objective_user where user_id = ? and objective_id = ?";
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, userId);
			pstmt.setInt(2, objectiveId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				notGain = rs.getInt("objective_id");
			}
			
			if (notGain != 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		o.close(conn, pstmt, rs);
		
		return result;
	}
	
	// 성공한 미션 Objective_Group에 insert
	public int insertUserMissionSuccessed(int userId, int objectiveId) {
			
		DBManager o = OracleDBManager.getInstance();
		
		int rows = 1;
		
		String sql = "insert into objective_user values(?, ?, sysdate)";
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, userId);
			pstmt.setInt(2, objectiveId);
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("성공 미션 업데이트 완료");
		o.close(conn, pstmt);
		
		return rows;
	}
	
	//첫 번째로 그룹 참여하면 true
	boolean joinGroupFirst(String objectQuery, int userId) {
		
		boolean result = false;
		
		String sql = objectQuery;
		int joinCount = 0;
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				joinCount = rs.getInt("cnt");
			}
			
			if (joinCount == 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		o.close(conn, pstmt, rs);
		
		
		return result;
	}
	
	// 첫 10회 이상 출석하면 true
	boolean attendanceOver10(String objectQuery, int userId) {
		
		boolean result = false;
		
		String sql = objectQuery;
		int attendCount = 0;
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				attendCount = rs.getInt("cnt");
			}
			
			if (attendCount >= 10) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		o.close(conn, pstmt, rs);
		
		
		return result;
	}
	
	// 첫 30회 이상 출석하면 true
	boolean attendanceOver30(String objectQuery, int userId) {
			
		boolean result = false;
		
		String sql = objectQuery;
		int attendCount = 0;
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				attendCount = rs.getInt("cnt");
			}
			
			if (attendCount >= 30) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		o.close(conn, pstmt, rs);
		
		return result;
	}
	
	// 오늘 출석 완료하면 true
	boolean attendanceToday(String objectQuery, int userId) {
	
		boolean result = false;
		
		String sql = objectQuery;
		int attendCount = 0;
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				attendCount = rs.getInt("cnt");
			}
			
			if (attendCount >= 1) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		o.close(conn, pstmt, rs);
		
		
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MissionCheckUserDAO dao = new MissionCheckUserDAO();
		
		// 1. 그룹에 처음 참여했는지 확인
	    String joinGroupQuery = dao.getObjectQuery(5); 
	    boolean isFirstJoin = dao.joinGroupFirst(joinGroupQuery, 1);
	    System.out.println(isFirstJoin);
	    
	    // 2. 첫 10회 이상 출석했는지 확인
	    String attendance10Query = dao.getObjectQuery(7); 
	    boolean hasAttendedOver10 = dao.attendanceOver10(attendance10Query, 1);
	    System.out.println(hasAttendedOver10);
	    
	    // 3. 첫 30회 이상 출석했는지 확인
	    String attendance30Query = dao.getObjectQuery(8); 
	    boolean hasAttendedOver30 = dao.attendanceOver30(attendance30Query, 1);
	    System.out.println(hasAttendedOver30);
	    
	    // 4. 오늘 출석했는지 확인
	    String attendanceTodayQuery = dao.getObjectQuery(6); 
	    boolean hasAttendedToday = dao.attendanceToday(attendanceTodayQuery, 1);
	    System.out.println(hasAttendedToday);	
		
	}

}
