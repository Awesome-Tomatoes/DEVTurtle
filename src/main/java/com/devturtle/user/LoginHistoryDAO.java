package com.devturtle.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;


public class LoginHistoryDAO {

	//단일 유저 더미
	public ArrayList<LoginHistoryVO> selectLoginHistory(int userid) {
	
		ArrayList<LoginHistoryVO> list = new ArrayList<LoginHistoryVO> ();
		
		DBManager dbm = OracleDBManager.getInstance();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from login_history where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid); //------파라미터를 1번째?에 바인딩

			rs = pstmt.executeQuery();
			while(rs.next()) {
				LoginHistoryVO lvo = new LoginHistoryVO();
			
				lvo.setUserID(rs.getInt("USER_ID"));
				lvo.setHistoryID(rs.getInt("LOGIN_HISTORY_ID"));
				lvo.setLoginDate(rs.getString("LOGIN_DATE"));
				
				list.add(lvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return list;
	}

	public int insertLoginHistory(int userid) {
		DBManager dbm = OracleDBManager.getInstance(); //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
		String currentDate = LocalDate.now().format(formatter);
		
		ArrayList<LoginHistoryVO> loginHistory = selectLoginHistory(userid);
		
		
		boolean logined = true;
		
		for(LoginHistoryVO hvo : loginHistory) {
			if(hvo.getLoginDate().equals(currentDate)) 
				logined = false;
		}
		
		
		int rows = 0;
		
		if(logined) {
			try {
				conn.setAutoCommit(false);
	
				String sql = "insert into login_history(login_history_id, login_date, user_id)\r\n"
					+ "values(login_history_seq.nextval,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, currentDate);
				pstmt.setInt(2, userid);
				
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
		}
		
		return rows;
	}

}
