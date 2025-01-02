/**
 * 
 */
package com.devturtle.git;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.devturtle.common.OracleDBManager;


public class GitDAO {

ArrayList<GitVO> alist = new ArrayList<GitVO>();
	
	Connection conn = null;
	PreparedStatement pstmt = null; //sql 점검
	ResultSet rs = null; //결과 출력
	
	public GitVO select(int userid) {
		
		OracleDBManager o = OracleDBManager.getInstance();
		
		//TODO
		String sql = "select * from git where user_id=? order by git_id asc";
		GitVO gvo = new GitVO();
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userid);
			rs = pstmt.executeQuery();
			while(rs.next()) { // 1줄 읽는 것. (반복문으로 여러 줄 읽음)
				int git_id = rs.getInt("git_id");
				int rating = rs.getInt("rating"); //컬럼명 넣어야 해서 "" 필수
				String created_at = rs.getString("created_at");
				String updated_at = rs.getString("updated_at");
				int user_id = rs.getInt("user_id");

				gvo.setGit_id(git_id);
				gvo.setRating(rating);
				gvo.setCreated_at(created_at);
				gvo.setUpdated_at(updated_at);
				gvo.setUserid(user_id);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		o.close(conn, pstmt, rs);
		System.out.println("내부 select done--");
		return gvo;
	}
	
	public ArrayList<GitVO> select() {
		
		OracleDBManager o = OracleDBManager.getInstance();
		//TODO
		String sql = "select * from git order by rating asc";
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			GitVO gvo = null;
			while(rs.next()) { // 1줄 읽는 것. (반복문으로 여러 줄 읽음)
				gvo = new GitVO();
				int git_id = rs.getInt("git_id");
				int rating = rs.getInt("rating"); //컬럼명 넣어야 해서 "" 필수
				String created_at = rs.getString("created_at");
				String updated_at = rs.getString("updated_at");
				int userid = rs.getInt("user_id");
				gvo.setGit_id(git_id);
				gvo.setRating(rating);
				gvo.setCreated_at(created_at);
				gvo.setUpdated_at(updated_at);
				gvo.setUserid(userid);
				alist.add(gvo);
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
	

	public int insert(GitVO gvo) {
		OracleDBManager o = OracleDBManager.getInstance();
		
		int rows = 0;
		//TODO
		String sql = "insert into git(git_id, rating, created_at, updated_at, user_id) values(git_seq.nextval, ?, sysdate, sysdate, ?)";
		
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gvo.getRating());
			pstmt.setInt(2, gvo.getUserid());

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
	
	public int update(GitVO gvo) {

		OracleDBManager o = OracleDBManager.getInstance();
		
		int rows = 1;
		//TODO
		String sql = "UPDATE git SET rating = ?, updated_at = sysdate WHERE user_id = ?";
	
		try {
			conn = o.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gvo.getRating());
			pstmt.setInt(2, gvo.getUserid());
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
		String sql = "DELETE FROM git WHERE userid = ?";
	
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

