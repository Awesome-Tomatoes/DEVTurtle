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
 * Date : 2024. 12. 31.<br>
 * History :<br>
 * - 작성자 : sk-choi, 날짜 : 2024. 12. 31., 설명 : 최초작성<br>
 *
 * @author sk-choi
 * @version 1.0
 */
public class MissionGroupDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	public ObjectiveVO select() { 
		
		ObjectiveVO uov = new ObjectiveVO();
		uov.setContents("파일 압축하고 풀기 100번 달성!");
		uov.setPoints(100);
		
		
		return uov;
	}
	
	public ArrayList<ObjectiveVO> selectAll() {
		
		ArrayList<ObjectiveVO> ulist = new ArrayList<ObjectiveVO>();
		
		ObjectiveVO uov1 = new ObjectiveVO(1, "dsds", "dsdsd","파일 압축하고 풀기 100번 달성!", 100);
	    ObjectiveVO uov2 = new ObjectiveVO(2, "sdsd", "saaaa","폴더 지웠다 다시 만들기 1000번 달성!", 1000);

	    ulist.add(uov1);
	    ulist.add(uov2);
		
		return ulist;
	}
	
	
	// 그룹별 미션 성취 여부를 조인을 통해 조회해서 mlist에 저장(최신순)
	public ArrayList<MissionJoinGroupVO> selectMissionGroup(int groupid) {
		
		DBManager o = OracleDBManager.getInstance();
		
		ArrayList<MissionJoinGroupVO> mlist = new ArrayList<MissionJoinGroupVO>();
		
		String sql = "SELECT \r\n"
				+ "    group_id, name, objective_id, \r\n"
				+ "    success_date, contents, points\r\n"
				+ "FROM (\r\n"
				+ "    SELECT \r\n"
				+ "        g.group_id, g.name, s.objective_id, \r\n"
				+ "        s.success_date, s.contents, s.points\r\n"
				+ "    FROM (\r\n"
				+ "        SELECT \r\n"
				+ "            og.group_id, og.success_date, o.objective_id, \r\n"
				+ "            o.contents, o.points  \r\n"
				+ "        FROM \r\n"
				+ "            objective_group og\r\n"
				+ "        JOIN \r\n"
				+ "            objective o \r\n"
				+ "        ON \r\n"
				+ "            og.objective_id = o.objective_id\r\n"
				+ "    ) s\r\n"
				+ "    JOIN \r\n"
				+ "        groups g \r\n"
				+ "    ON \r\n"
				+ "        s.group_id = g.group_id \r\n"
				+ "    WHERE \r\n"
				+ "        g.group_id = ?\r\n"
				+ ")\r\n"
				+ "ORDER BY \r\n"
				+ "    success_date DESC\r\n";
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, groupid);
			rs = pstmt.executeQuery();
			MissionJoinGroupVO mjgv = null;
			
			while (rs.next()) {
				String gname = rs.getString("name");
				String success_date = rs.getString("success_date");
				String contents = rs.getString("contents"); // objective_query는 contents로 별칭이 변경됨
				int group_id = rs.getInt("group_id");
				int objective_id = rs.getInt("objective_id");
				int points = rs.getInt("points");
				
				mjgv = new MissionJoinGroupVO();
				
				mjgv.setContents(contents);
				mjgv.setGroup_id(group_id);
				mjgv.setObjective_id(objective_id);
				mjgv.setGname(gname);
				mjgv.setPoints(points);
				mjgv.setSuccess_date(success_date);
				
				mlist.add(mjgv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		o.close(conn, pstmt, rs);
		
		for (int i = 0; i < mlist.size(); i++) {
			System.out.println(mlist.get(i).getContents());
			System.out.println(mlist.get(i).getPoints());
		}
		
		return mlist;
	}	
	
	public ArrayList<MissionJoinGroupVO> selectMissionGroupHistory(int groupid) {
		
		DBManager o = OracleDBManager.getInstance();
		
		ArrayList<MissionJoinGroupVO> mlist = new ArrayList<MissionJoinGroupVO>();
		
		String sql = "SELECT GROUP_ID, NAME, OBJECTIVE_ID, SUCCESS_DATE, CONTENTS, POINTS, COUNT(SUCCESS_DATE) AS CNT\r\n"
				+ "FROM\r\n"
				+ "(SELECT \r\n"
				+ "    g.group_id, \r\n"
				+ "    g.name, \r\n"
				+ "    o.objective_id, \r\n"
				+ "    og.success_date, \r\n"
				+ "    o.contents, \r\n"
				+ "    o.points\r\n"
				+ "FROM \r\n"
				+ "    objective_group og\r\n"
				+ "JOIN \r\n"
				+ "    objective o \r\n"
				+ "    ON og.objective_id = o.objective_id\r\n"
				+ "JOIN \r\n"
				+ "    groups g \r\n"
				+ "    ON og.group_id = g.group_id\r\n"
				+ "    WHERE g.group_id = ?\r\n"
				+ ")\r\n"
				+ "group by GROUP_ID, NAME, OBJECTIVE_ID, SUCCESS_DATE, CONTENTS, POINTS";
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, groupid);
			rs = pstmt.executeQuery();
			MissionJoinGroupVO mjgv = null;
			
			while (rs.next()) {
				String gname = rs.getString("name");
				String success_date = rs.getString("success_date");
				String contents = rs.getString("contents"); // objective_query는 contents로 별칭이 변경됨
				int group_id = rs.getInt("group_id");
				int objective_id = rs.getInt("objective_id");
				int points = rs.getInt("points");
				int cnt = rs.getInt("cnt");
				
				mjgv = new MissionJoinGroupVO();
				
				mjgv.setContents(contents);
				mjgv.setGroup_id(group_id);
				mjgv.setObjective_id(objective_id);
				mjgv.setGname(gname);
				mjgv.setPoints(points);
				mjgv.setSuccess_date(success_date);
				mjgv.setCount(cnt);
				
				mlist.add(mjgv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		o.close(conn, pstmt, rs);
		
		for (int i = 0; i < mlist.size(); i++) {
			System.out.println(mlist.get(i).getContents());
			System.out.println(mlist.get(i).getPoints());
		}
		
		return mlist;
	}	
	
public ArrayList<MissionJoinGroupVO> selectMissionGroupChart(int groupid) {
		
		DBManager o = OracleDBManager.getInstance();
		
		ArrayList<MissionJoinGroupVO> mlist = new ArrayList<MissionJoinGroupVO>();
		
		String sql = "SELECT group_id, name, objective_id, success_date, contents, points, COUNT(contents) AS cnt\r\n"
				+ "FROM (\r\n"
				+ "    SELECT g.group_id, g.name, s.objective_id, s.success_date, s.contents, s.points\r\n"
				+ "    FROM (\r\n"
				+ "        SELECT og.group_id, og.success_date, o.objective_id, o.contents, o.points  \r\n"
				+ "        FROM objective_group og\r\n"
				+ "        JOIN objective o ON og.objective_id = o.objective_id\r\n"
				+ "    ) s\r\n"
				+ "    JOIN groups g ON s.group_id = g.group_id AND g.group_id = ?\r\n"
				+ ")\r\n"
				+ "GROUP BY group_id, name, objective_id, success_date, contents, points\r\n"
				+ "ORDER BY cnt DESC\r\n";
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, groupid);
			rs = pstmt.executeQuery();
			MissionJoinGroupVO mjgv = null;
			
			while (rs.next()) {
				String gname = rs.getString("name");
				String success_date = rs.getString("success_date");
				String contents = rs.getString("contents"); // objective_query는 contents로 별칭이 변경됨
				int group_id = rs.getInt("group_id");
				int objective_id = rs.getInt("objective_id");
				int points = rs.getInt("points");
				int cnt = rs.getInt("cnt");
				
				mjgv = new MissionJoinGroupVO();
				
				mjgv.setContents(contents);
				mjgv.setGroup_id(group_id);
				mjgv.setObjective_id(objective_id);
				mjgv.setGname(gname);
				mjgv.setPoints(points);
				mjgv.setSuccess_date(success_date);
				mjgv.setCount(cnt);
				
				mlist.add(mjgv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		o.close(conn, pstmt, rs);
		
		for (int i = 0; i < mlist.size(); i++) {
			System.out.println(mlist.get(i).getContents());
			System.out.println(mlist.get(i).getPoints());
		}
		
		return mlist;
	}	
	
public ArrayList<MissionJoinGroupVO> selectMissionGroupBadge(int groupid) {
	
	DBManager o = OracleDBManager.getInstance();
	
	ArrayList<MissionJoinGroupVO> mlist = new ArrayList<MissionJoinGroupVO>();
	
	String sql = "select g.group_id, g.name, o.contents, o.badge_link, o.points, og.success_date\r\n"
			+ "from groups g, objective_group og, objective o\r\n"
			+ "where og.objective_id = o.objective_id and g.group_id = og.group_id\r\n"
			+ "and g.group_id = ?";
	
	try {
		conn = o.connect();
		pstmt = conn.prepareStatement(sql);		
		pstmt.setInt(1, groupid);
		rs = pstmt.executeQuery();
		MissionJoinGroupVO mjgv = null;
		
		while (rs.next()) {
			String gname = rs.getString("name");
			String success_date = rs.getString("success_date");
			String contents = rs.getString("contents"); // objective_query는 contents로 별칭이 변경됨
			int group_id = rs.getInt("group_id");
			String badge_link = rs.getString("badge_link");
			int points = rs.getInt("points");
			
			mjgv = new MissionJoinGroupVO();
			
			mjgv.setContents(contents);
			mjgv.setGroup_id(group_id);
			mjgv.setBadgeLink(badge_link);
			mjgv.setGname(gname);
			mjgv.setPoints(points);
			mjgv.setSuccess_date(success_date);
			
			mlist.add(mjgv);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	o.close(conn, pstmt, rs);
	
	for (int i = 0; i < mlist.size(); i++) {
		System.out.println(mlist.get(i).getContents());
		System.out.println(mlist.get(i).getPoints());
		System.out.println(mlist.get(i).getBadgeLink());
	}
	
	return mlist;
}	

	public ObjectiveVO insertMission(String contents, int points) { //admin용
		
		ObjectiveVO uov = new ObjectiveVO();
		
		uov.setContents(contents);
		uov.setPoints(points);
		
		return uov;
		
	}
	
	public int updateMission(String contents, int points) { //admin용
		
		int row = 1;
		
		return row;
	}
	
	public int deleteMission(String contents, int points) { //admin용
		
		int row = 1;
		
		return row;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//UserObjectiveVO uov = new UserObjectiveVO();
		
		MissionGroupDAO mpd = new MissionGroupDAO();
		
		ArrayList<MissionJoinGroupVO> mlist = mpd.selectMissionGroup(1);
		
		System.out.println(mlist.get(0).getContents() + " " + mlist.get(0).getContents());
		//System.out.println(mlist.get(1).getContents() + " " + mlist.get(1).getContents());
		
		
		
	}

}




