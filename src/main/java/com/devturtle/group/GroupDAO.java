package com.devturtle.group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;

public class GroupDAO {


	//-------------------------GROUP Info 메서드--------------------------------- 
	// 전체그룹 리스트 정보
	public ArrayList<GroupVO> selectAllGroup() {
		ArrayList<GroupVO> alist = new ArrayList<GroupVO>();

		alist.add(new GroupVO(1,"group1","설명1","스터디","공개",50000,60,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(2,"group2","설명2","스터디","공개",40000,70,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(3,"group3","설명3","스터디","공개",60000,50,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(4,"group4","설명4","스터디","공개",80000,30,"2024-12-12","2024-12-12"));	
		
		return alist;
	}
	
	// 자신의 그룹이름 검색 대한 그룹정보 리스트
	public ArrayList<GroupVO> selectGroupBySearhName(int userId, int groupId, String groupName ) {	
		
		// Like 검색 그룹 이름 검색 쿼리
		ArrayList<GroupVO> alist = new ArrayList<GroupVO>();

		alist.add(new GroupVO(1,"group11","설명1","스터디","공개",50000,60,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(2,"group12","설명2","스터디","공개",40000,70,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(3,"group13","설명3","스터디","공개",60000,50,"2024-12-12","2024-12-12"));
		
		return alist; 
	}
	
	// 로그인한 유저가 참여중인 그룹 리스트 
	public ArrayList<GroupVO> selectAllJoinGroup(int userId) {
		ArrayList<GroupVO> alist = new ArrayList<GroupVO>();

		alist.add(new GroupVO(1,"group1","설명1","스터디","공개",50000,60,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(2,"group2","설명2","스터디","공개",40000,70,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(3,"group3","설명3","스터디","공개",60000,50,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(4,"group4","설명4","스터디","공개",80000,30,"2024-12-12","2024-12-12"));	
		return alist;
	}
	
	//-------------------------GROUP Detail 메서드--------------------------------- 
	// 그룹의 상세정보
	public GroupVO selectGroupDetail(int userId, int groupId) {	
		// 상세보기 클릭 후 해당 그룹의 상세 정보들
		// 
	
		
		return new GroupVO(groupId,"멋쟁이그룹","설명","스터디","공개",50000,60,"2024-12-12","2024-12-12"); 
	}
	
	
	//------------------------- 전체 그룹 랭킹 조회 메서드--------------------------------- 
	public ArrayList<GroupVO> selectAllGroupByMonthOrderByRankPaging(String date, int startSeq , int endSeq) {
		
		ArrayList<GroupVO> alist = new ArrayList<GroupVO>();
		
		DBManager dbm = OracleDBManager.getInstance();  	//new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		
			// 202501
			String sql = "select s.*, TO_CHAR(UPDATED_AT, 'YYYYMM') from\r\n"
					+ "(select GROUPS.*, (ROW_NUMBER() OVER(order by TOTAL_SCORE desc, GROUP_ID)) as rnum from GROUPS) s\r\n"
			        + "where TO_CHAR(UPDATED_AT, 'YYYYMM') = ? and rnum between ? and ?";
			
			pstmt =  conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setInt(2, startSeq);
			pstmt.setInt(3, endSeq);
//			
			rs = pstmt.executeQuery();  
			while(rs.next()) {
				GroupVO uvo = new GroupVO();
				uvo.setGroupId(rs.getLong("GROUP_ID"));
				uvo.setName(rs.getString("NAME"));
				uvo.setDescription(rs.getString("DESCRIPTION"));
				uvo.setCategory(rs.getString("CATEGORY"));
				uvo.setGPrivate(rs.getString("PRIVATE"));
				uvo.setUpdatedAt(rs.getString("UPDATED_AT"));
				uvo.setTotalScore(rs.getLong("TOTAL_SCORE"));
				uvo.setRank(rs.getInt("RNUM"));
				alist.add(uvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	finally {
				dbm.close(conn, pstmt, rs);
		}
		return alist;
	}
	
	
	//-------------------------GROUP Create 메서드--------------------------------- 
	public int createGroup(int userId) {

		DBManager dbm = OracleDBManager.getInstance(); //new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
        int rows = 0;
        
        
        try {
            conn.setAutoCommit(false);
            
            // GROUP 더미가 7개 들어가있음 : RANK_GROUP_SEQ.NEXTVAL+7 
            
            String sql = "INSERT INTO GROUPS ("
            		+ "    GROUP_ID, \"NAME\", \"SIZE\", CONDITION,"
            		+ "    \"DESCRIPTION\","
            		+ "    \"CATEGORY\", "
            		+ "    \"PRIVATE\", \"LOCATION\", "
            		+ "		CREATED_AT, UPDATED_AT, TOTAL_SCORE, RANK_SCORE"
            		+ ") VALUES ("
            		+ "    GROUP_SEQ.NEXTVAL, ?, ?, ?, "
            		+ "    ?, "
					+ "    ?,"
            		+ "    ?, ?, "
            		+ "		SYSDATE, SYSDATE, 0, RANK_GROUP_SEQ.NEXTVAL+7"
            		+ ")";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, userId);
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
	
	//-------------------------GROUP Join 관련 메서드--------------------------------- 
	
	// 그룹 가입신청 / 그룹초대하기 / 대기 / 승인 / 탈퇴  GroupUser의 status = [ accept , reject / approve]
	public int requestGroupByUser(int userId, int groupId, String message) {
		
		// update status 쿼리
		// message가 가입 / 초대 이면 groupUser insert 문 넣기
		
		int result = 1;
		return result;
		
	}
	
	// 그룹에  GroupUser의 status = [ accept , reject / approve]
	public int inviteGroupByJoinUser(int userId, int groupId, String message) {
		
		int result = 1;
		return result;
	}
	
	// 그룹 신청 대기
	
	//-------------------------다른 api---------------------------
	
	// 그룹 신청 대기
	
	// 로그인한 유저가 참여중인 그룹수
	public int getNumberOfJoinGroup() {
		
		int count = 10000;
		return count;
	}
	
	public static void main(String[] argv) {
		GroupDAO gdao = new GroupDAO();
		ArrayList<GroupVO> arr = gdao.selectAllGroupByMonthOrderByRankPaging("202501", 1, 6);
		System.out.println(arr.size());
		for(var x : arr) System.out.println(x.toString());
	}
	
}
