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
 * Date : 2024. 12. 30.<br>
 * History :<br>
 * - 작성자 : sk-choi, 날짜 : 2024. 12. 30., 설명 : 최초작성<br>
 *
 * @author sk-choi
 * @version 1.0
 */
public class MissionPersonalDAO {
	
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
	
	public ArrayList<MissionJoinUserVO> selectMissionUser(int userid) {
		
		DBManager o = OracleDBManager.getInstance();
		
		ArrayList<MissionJoinUserVO> ulist = new ArrayList<MissionJoinUserVO>();
		
		String sql = "select user_id, nickname, objective_id, contents, success_date, points\r\n"
				+ "from \r\n"
				+ "(\r\n"
				+ "select u.user_id, u.nickname, o.objective_id, o.objective_query as contents, o.points, ou.success_date\r\n"
				+ "from users u, objective_user ou, objective o\r\n"
				+ "where u.user_id = ou.user_id and o.objective_id = ou.objective_id\r\n"
				+ ")\r\n"
				+ "where user_id = ?\r\n"
				+ "order by success_date desc";
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);	
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			MissionJoinUserVO mjuv = null;
			
			while (rs.next()) {
				String nickname = rs.getString("nickname");
				String success_date = rs.getString("success_date");
				String contents = rs.getString("contents"); // objective_query는 contents로 별칭이 변경됨
				int user_id = rs.getInt("user_id");
				int objective_id = rs.getInt("objective_id");
				int points = rs.getInt("points");
				
				mjuv = new MissionJoinUserVO();
				
				mjuv.setContents(contents);
				mjuv.setUser_id(user_id);
				mjuv.setObjective_id(objective_id);
				mjuv.setNickname(nickname);
				mjuv.setPoints(points);
				mjuv.setSuccess_date(success_date);
				
				ulist.add(mjuv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		o.close(conn, pstmt, rs);
		
		for (int i = 0; i < ulist.size(); i++) {
			System.out.println(ulist.get(i).getContents());
			System.out.println(ulist.get(i).getPoints());
		}
		
		return ulist;
	}	
	
	public ArrayList<MissionJoinUserVO> selectMissionUserHistory(int userid) {
			
			DBManager o = OracleDBManager.getInstance();
			
			ArrayList<MissionJoinUserVO> ulist = new ArrayList<MissionJoinUserVO>();
			
			String sql = "select user_id, nickname, objective_id, contents, points, success_date, count(success_date) as cnt\r\n"
					+ "from \r\n"
					+ "(\r\n"
					+ "select u.user_id, u.nickname, o.objective_id, o.objective_query as contents, o.points, ou.success_date\r\n"
					+ "from users u, objective_user ou, objective o\r\n"
					+ "where u.user_id = ou.user_id and o.objective_id = ou.objective_id\r\n"
					+ ") s\r\n"
					+ "where user_id = ?\r\n"
					+ "group by user_id, nickname, objective_id, contents, points, success_date";
			
			try {
				conn = o.connect();
				pstmt = conn.prepareStatement(sql);		
				pstmt.setInt(1, userid);
				rs = pstmt.executeQuery();
				MissionJoinUserVO mjuv = null;
				
				while (rs.next()) {
					String nickname = rs.getString("nickname");
					String success_date = rs.getString("success_date");
					String contents = rs.getString("contents"); // objective_query는 contents로 별칭이 변경됨
					int user_id = rs.getInt("user_id");
					int objective_id = rs.getInt("objective_id");
					int points = rs.getInt("points");
					int cnt = rs.getInt("cnt");
					
					mjuv = new MissionJoinUserVO();
					
					mjuv.setContents(contents);
					mjuv.setUser_id(user_id);
					mjuv.setObjective_id(objective_id);
					mjuv.setNickname(nickname);
					mjuv.setPoints(points);
					mjuv.setSuccess_date(success_date);
					mjuv.setCount(cnt);
					
					ulist.add(mjuv);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			o.close(conn, pstmt, rs);
			
			for (int i = 0; i < ulist.size(); i++) {
				System.out.println(ulist.get(i).getContents());
				System.out.println(ulist.get(i).getPoints());
			}
			
			return ulist;
		}	
	
	public ArrayList<MissionJoinUserVO> selectMissionUserChart(int userid) {
		
		DBManager o = OracleDBManager.getInstance();
		
		ArrayList<MissionJoinUserVO> ulist = new ArrayList<MissionJoinUserVO>();
		
		String sql = "select user_id, nickname, objective_id, contents, points, success_date, count(contents) as cnt\r\n"
				+ "from \r\n"
				+ "(\r\n"
				+ "select u.user_id, u.nickname, o.objective_id, o.objective_query as contents, o.points, ou.success_date\r\n"
				+ "from users u, objective_user ou, objective o\r\n"
				+ "where u.user_id = ou.user_id and o.objective_id = ou.objective_id\r\n"
				+ ") s\r\n"
				+ "where user_id = ?\r\n"
				+ "group by user_id, nickname, objective_id, contents, points, success_date\r\n"
				+ "order by cnt desc";
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			MissionJoinUserVO mjuv = null;
			
			while (rs.next()) {
				String nickname = rs.getString("nickname");
				String success_date = rs.getString("success_date");
				String contents = rs.getString("contents"); // objective_query는 contents로 별칭이 변경됨
				int user_id = rs.getInt("user_id");
				int objective_id = rs.getInt("objective_id");
				int points = rs.getInt("points");
				int cnt = rs.getInt("cnt");
				
				mjuv = new MissionJoinUserVO();
				
				mjuv.setContents(contents);
				mjuv.setUser_id(user_id);
				mjuv.setObjective_id(objective_id);
				mjuv.setNickname(nickname);
				mjuv.setPoints(points);
				mjuv.setSuccess_date(success_date);
				mjuv.setCount(cnt);
				
				ulist.add(mjuv);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		o.close(conn, pstmt, rs);
		
		for (int i = 0; i < ulist.size(); i++) {
			System.out.println(ulist.get(i).getContents());
			System.out.println(ulist.get(i).getPoints());
		}
		
		return ulist;
	}	

	public ObjectiveVO insert(String contents, int points) { //admin용
	
		ObjectiveVO uov = new ObjectiveVO();
		
		uov.setContents(contents);
		uov.setPoints(points);
		
		return uov;
	
	}

	public int update(String contents, int points) { //admin용
		
		int row = 1;
		
		return row;
	}
	
	public int delete(String contents, int points) { //admin용
		
		int row = 1;
		
		return row;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//UserObjectiveVO uov = new UserObjectiveVO();
		
		MissionPersonalDAO mpd = new MissionPersonalDAO();
		
		ObjectiveVO ddd = mpd.select();
		
		System.out.println(ddd.getContents() + " " + ddd.getPoints());
		
		ArrayList<ObjectiveVO> ulist = mpd.selectAll();
		
		System.out.println(ulist.get(0).getContents() + " " + ulist.get(0).getContents());
		//System.out.println(ulist.get(1).getContents() + " " + ulist.get(1).getContents());
		
		
		
	}

}




