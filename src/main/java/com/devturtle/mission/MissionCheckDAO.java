/**
 * 
 */
package com.devturtle.mission;

import java.util.ArrayList;
import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2025. 1. 6.<br>
 * History :<br>
 * - 작성자 : sk-choi, 날짜 : 2025. 1. 6., 설명 : 최초작성<br>
 *
 * @author sk-choi
 * @version 1.0
 */
public class MissionCheckDAO {

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
	public boolean isMissionComplete(int groupId, int objectiveId) {
		
		DBManager o = OracleDBManager.getInstance();
		
		boolean result = false;
		int notGain = 0;
		String sql = "select objective_id from objective_group where group_id = ? and objective_id = ?";
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, groupId);
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
	public int insertGruopMissionSuccessed(int groupId, int objectiveId) {
			
		DBManager o = OracleDBManager.getInstance();
		
		int rows = 1;
		
		String sql = "insert into objective_group values(?, ?, sysdate)";
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, objectiveId);
			pstmt.setInt(2, groupId);
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("성공 미션 업데이트 완료");
		o.close(conn, pstmt);
		
		return rows;
	}
	
	// 그룹 전부 70퍼센트 이상 출석하면 true, 아니면 false
	public boolean attendanceCheck(String objectQuery, int groupId){
		
		ArrayList<Double> alist = new ArrayList<Double>();
		
		String sql = objectQuery;
		boolean result = true;
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, groupId);
			rs = pstmt.executeQuery();
			
			double attendance = 0.0;
			
			while(rs.next()) {
				attendance = rs.getDouble("attendance");
				alist.add(attendance);
			}
			
			for (double a : alist) {
				if (a < 70.00) {
					result = false;
					break;
				}
			}
			
			System.out.print("전체 출석 출력 : | ");
			for (int i = 0; i < alist.size(); i++) {
				System.out.print(alist.get(i) + " | ");
			}
			System.out.println("");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		o.close(conn, pstmt, rs);
		
		
		return result;
	}
	
	// 그룹 구성원 모두가 해적 뱃지 달았는지 확인
	public boolean piratesCondition(String objectQuery, int groupId) {
		
		int piratesCondition = 0;
		boolean result = false;
		String sql = objectQuery;
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, groupId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				piratesCondition = rs.getInt("pirates_condition");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		o.close(conn, pstmt, rs);
		
		if (piratesCondition == 0) { // 그룹원 중 해적 뱃지가 없는 사람이 없다면...
			result = true; // true이면 모두가 해적 뱃지 있음. false면 한 명 이상이 없음
		}
		
		return result;
	}
	
	//그룹 생성일 기준 socore가 100점 이상 달성되었는지 확인
	public boolean ratingCount(String objectQuery, int groupId) {
		
		String sql = objectQuery;
		boolean result = true;
		int diff = 0;
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, groupId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				diff = rs.getInt("diff");
			}
			
			if (diff < 100) {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		o.close(conn, pstmt, rs);
		
		return result;
	}
	
	// 수행 안한 미션 뱃지 번호 리스트 리턴. 모든 미션 수행 하면 빈 리스트 반환
	public ArrayList<Integer> allCollectBadge(String objectQuery, int groupId) {
		
		ArrayList<Integer> blist = new ArrayList<Integer>();
		String sql = objectQuery;
		int notGain = 0;
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, groupId);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				notGain = rs.getInt("not_gain_badge");
				blist.add(notGain);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		o.close(conn, pstmt, rs);
		
		return blist;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MissionCheckDAO mcd = new MissionCheckDAO();
		String query = mcd.getObjectQuery(1);
		System.out.println("test01: Attendance Check... ");
		System.out.println(mcd.attendanceCheck(query, 1));

		System.out.println("test02: PiratesCondition Check... ");
		String query2 = mcd.getObjectQuery(2);
		System.out.println(mcd.piratesCondition(query2, 3));
		
		System.out.println("test03: RatingDiff check... ");
		String query3 = mcd.getObjectQuery(3);
		System.out.println(mcd.ratingCount(query3, 3));
		
		System.out.println("test04: AllCollectBadge check... ");
		String query4 = mcd.getObjectQuery(4);
		ArrayList<Integer> blist = mcd.allCollectBadge(query4, 3);
		for (int a : blist) {
			System.out.print(a + " ");
		}
		System.out.println("");
		
		boolean result = mcd.isMissionComplete(1, 4);
		System.out.println(result + "");
	}

}
