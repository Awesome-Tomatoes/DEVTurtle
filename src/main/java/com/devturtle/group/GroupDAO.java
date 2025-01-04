package com.devturtle.group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.devturtle.common.DBManager;
import com.devturtle.common.OracleDBManager;
import com.devturtle.user.UserDAO;
import com.devturtle.user.UserVO;

public class GroupDAO {

	// -------------------------GROUP Info 메서드---------------------------------
	// 전체그룹 리스트 정보
	public ArrayList<GroupVO> selectAllGroup() {
		ArrayList<GroupVO> alist = new ArrayList<GroupVO>();

		alist.add(new GroupVO(1, "group1", "설명1", "스터디", "공개", 50000, 60, "2024-12-12", "2024-12-12"));
		alist.add(new GroupVO(2, "group2", "설명2", "스터디", "공개", 40000, 70, "2024-12-12", "2024-12-12"));
		alist.add(new GroupVO(3, "group3", "설명3", "스터디", "공개", 60000, 50, "2024-12-12", "2024-12-12"));
		alist.add(new GroupVO(4, "group4", "설명4", "스터디", "공개", 80000, 30, "2024-12-12", "2024-12-12"));

		return alist;
	}
	
	public int selectGroupIDByName(String name) {
		DBManager dbm = OracleDBManager.getInstance();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int res = 0;
		try {
			String sql = "select * from GROUPS where NAME=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name); //------파라미터를 1번째?에 바인딩

			rs = pstmt.executeQuery();
			if(rs.next()) {
				res = rs.getInt("GROUP_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return res;
	}

	public int selectAllGroupSize() {

		ArrayList<GroupVO> alist = new ArrayList<GroupVO>();

		DBManager dbm = OracleDBManager.getInstance(); // new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// 202501
			String sql = "select GROUP_ID from GROUPS";

			pstmt = conn.prepareStatement(sql);

//			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GroupVO uvo = new GroupVO();
				uvo.setGroupId(rs.getInt("GROUP_ID"));
				alist.add(uvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return alist.size();
	}

	// 자신의 그룹이름 검색 대한 그룹정보 리스트
	public ArrayList<GroupVO> selectGroupBySearhName(int userId, int groupId, String groupName) {

		// Like 검색 그룹 이름 검색 쿼리
		ArrayList<GroupVO> alist = new ArrayList<GroupVO>();

		alist.add(new GroupVO(1, "group11", "설명1", "스터디", "공개", 50000, 60, "2024-12-12", "2024-12-12"));
		alist.add(new GroupVO(2, "group12", "설명2", "스터디", "공개", 40000, 70, "2024-12-12", "2024-12-12"));
		alist.add(new GroupVO(3, "group13", "설명3", "스터디", "공개", 60000, 50, "2024-12-12", "2024-12-12"));

		return alist;
	}

	// 로그인한 유저가 참여중인 그룹 리스트
	public ArrayList<GroupVO> selectAllJoinGroup(int userId) {
		ArrayList<GroupVO> glist = new ArrayList<GroupVO>();
		
		
		DBManager dbm = OracleDBManager.getInstance(); // new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// GROUP 더미가 7개 들어가있음 : RANK_GROUP_SEQ.NEXTVAL+7

			String sql =
					"SELECT G.*, GU.* 		\r\n"
					+ "FROM GROUPS G		\r\n"
					+ "JOIN GROUP_USER GU	\r\n"
					+ "ON (g.group_id = gu.group_id)\r\n"
					+ "WHERE gu.user_id =	? 	\r\n";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GroupVO gvo = new GroupVO();
				gvo.setGroupId(rs.getInt("GROUP_ID"));
				gvo.setName(rs.getString("NAME"));
				gvo.setSize(rs.getInt("SIZE"));
			    gvo.setCondition(rs.getInt("CONDITION"));
			    gvo.setDescription(rs.getString("DESCRIPTION"));
			    gvo.setCategory(rs.getString("CATEGORY"));
			    gvo.setGPrivate(rs.getString("PRIVATE"));
			    gvo.setLocation(rs.getString("LOCATION"));
			    gvo.setCreatedAt(rs.getString("CREATED_AT"));
			    gvo.setUpdatedAt(rs.getString("UPDATED_AT"));
			    gvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
			    gvo.setRankScore(rs.getInt("RANK_SCORE"));
			    glist.add(gvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		
		return glist;
	}

	// -------------------------GROUP Detail 메서드---------------------------------
	// 그룹의 상세정보
	public GroupVO selectGroupDetail(int userId, int groupId) {
		// 상세보기 클릭 후 해당 그룹의 상세 정보들

		GroupVO gvo = new GroupVO();
		
		DBManager dbm = OracleDBManager.getInstance(); // new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// GROUP 더미가 7개 들어가있음 : RANK_GROUP_SEQ.NEXTVAL+7

			String sql =
					"SELECT G.*, GU.* 		\r\n"
					+ "FROM GROUPS G		\r\n"
					+ "JOIN GROUP_USER GU	\r\n"
					+ "ON (g.group_id = gu.group_id)\r\n"
					+ "WHERE G.GROUP_ID = ? \r\n"
					+ "AND gu.user_id =	? 	\r\n";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupId);
			pstmt.setInt(2, userId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				gvo.setGroupId(rs.getInt("GROUP_ID"));
				gvo.setName(rs.getString("NAME"));
				gvo.setSize(rs.getInt("SIZE"));
			    gvo.setCondition(rs.getInt("CONDITION"));
			    gvo.setDescription(rs.getString("DESCRIPTION"));
			    gvo.setCategory(rs.getString("CATEGORY"));
			    gvo.setGPrivate(rs.getString("PRIVATE"));
			    gvo.setLocation(rs.getString("LOCATION"));
			    gvo.setCreatedAt(rs.getString("CREATED_AT"));
			    gvo.setUpdatedAt(rs.getString("UPDATED_AT"));
			    gvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
			    gvo.setRankScore(rs.getInt("RANK_SCORE"));
			} else {
				System.out.println("그룹 상세정보 select error");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		
		return gvo;
	}

	public int updateGroupDetail(int userId, GroupVO gvo) {

		DBManager dbm = OracleDBManager.getInstance(); // new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			conn.setAutoCommit(false);

			// GROUP 더미가 7개 들어가있음 : RANK_GROUP_SEQ.NEXTVAL+7

			String sql =
					"update GROUPS \r\n"
					+ "		set \r\n"
					+ "     NAME = ?,\r\n"
					+ "    \"SIZE\"= ?,\r\n"
					+ "    \"CONDITION\" = ?,\r\n"
					+ "    \"DESCRIPTION\" = ?,\r\n"
					+ "    \"CATEGORY\" = ?, \r\n"
					+ "    \"LOCATION\" = ?,\r\n"
					+ "    UPDATED_AT = sysdate \r\n"
					+ "where GROUP_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gvo.getName()); // ------파라미터를 1번째?에 바인딩
			pstmt.setInt(2, gvo.getSize());
			pstmt.setInt(3, gvo.getCondition());
			pstmt.setString(4, gvo.getDescription());
			pstmt.setString(5, gvo.getCategory());
			pstmt.setString(6, gvo.getLocation());
			pstmt.setInt(7, gvo.getGroupId());

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

	//update groups set total_score = total_score - 500 where group_id = 22;
	public int initGroupScore(int gid, int userScore) {

		DBManager dbm = OracleDBManager.getInstance(); // new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			conn.setAutoCommit(false);


			String sql =
					"update groups set total_score = total_score + ? where group_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userScore);
			pstmt.setInt(2, gid);
			

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

	// ------------------------- 전체 그룹 랭킹 조회 메서드---------------------------------
	public ArrayList<GroupVO> selectAllGroupByMonthOrderByRankPaging(String date, int startSeq, int endSeq) {

		ArrayList<GroupVO> alist = new ArrayList<GroupVO>();

		DBManager dbm = OracleDBManager.getInstance(); // new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// 202501
			String sql = "select s.*, TO_CHAR(UPDATED_AT, 'YYYYMM') from\r\n"
					+ "(select GROUPS.*, (ROW_NUMBER() OVER(order by TOTAL_SCORE desc, GROUP_ID)) as rnum from GROUPS) s\r\n"
					+ "where TO_CHAR(UPDATED_AT, 'YYYYMM') = TO_CHAR(TO_DATE(?), 'YYYYMM') and rnum between ? and ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, date);
			pstmt.setInt(2, startSeq);
			pstmt.setInt(3, endSeq);
//			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				GroupVO uvo = new GroupVO();
				uvo.setGroupId(rs.getInt("GROUP_ID"));
				uvo.setName(rs.getString("NAME"));
				uvo.setDescription(rs.getString("DESCRIPTION"));
				uvo.setCategory(rs.getString("CATEGORY"));
				uvo.setGPrivate(rs.getString("PRIVATE"));
				uvo.setUpdatedAt(rs.getString("UPDATED_AT"));
				uvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
				uvo.setRank(rs.getInt("RNUM"));
				alist.add(uvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return alist;
	}

	// ------------------------- 특정 그룹 VO + 랭킹 조회 메서드---------------------------------
	public GroupVO selectGroupByIDWithRank(int groupID) {

		GroupVO gvo = new GroupVO();

		DBManager dbm = OracleDBManager.getInstance(); // new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			// 202501
			String sql = "select g.* from\r\n"
					+ "(select groups.*, (ROW_NUMBER() OVER(order by TOTAL_SCORE desc, group_id)) as rnum from groups) g\r\n"
					+ "where g.group_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupID);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				gvo.setGroupId(rs.getInt("GROUP_ID"));
				gvo.setName(rs.getString("NAME"));
				gvo.setDescription(rs.getString("DESCRIPTION"));
				gvo.setCategory(rs.getString("CATEGORY"));
				gvo.setGPrivate(rs.getString("PRIVATE"));
				gvo.setUpdatedAt(rs.getString("UPDATED_AT"));
				gvo.setTotalScore(rs.getInt("TOTAL_SCORE"));
				gvo.setRank(rs.getInt("RNUM"));
				gvo.setSize(rs.getInt("SIZES"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		return gvo;
	}
	
	
	// -------------------------GROUP Create 메서드---------------------------------
	public int createGroup(int userId, GroupVO gvo) {

	    DBManager dbm = OracleDBManager.getInstance();
	    Connection conn = dbm.connect();
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    int rows = 0;
	    int createdGroupId = 0;

	    try {
	        conn.setAutoCommit(false); // 트랜잭션 수동 관리

	        // GROUP_SEQ.NEXTVAL을 가져오는 쿼리
	        String sqlSeq = "SELECT GROUP_SEQ.NEXTVAL AS VAL FROM DUAL";
	        try (PreparedStatement pstmtSeq = conn.prepareStatement(sqlSeq);
	                ResultSet rsSeq = pstmtSeq.executeQuery()) {
	               if (rsSeq.next()) {
	                   createdGroupId = rsSeq.getInt("VAL"); // NEXTVAL 값 가져오기
	               }
	               System.out.println("createdGroupId >> "+createdGroupId);
	           } catch (SQLException e) {
	               System.out.println("GROUP_SEQ 쿼리 실행 오류");
	               e.printStackTrace();
	               conn.rollback();
	               return 0;
	        }
	        // GROUPS 테이블에 데이터를 삽입하는 SQL
	        String sqlInsert = "INSERT INTO GROUPS (" 
	                + "    GROUP_ID, " 
	                + "    \"NAME\", \"SIZES\", CONDITION," 
	                + "    \"DESCRIPTION\"," 
	                + "    \"CATEGORY\", " 
	                + "    \"PRIVATE\", \"LOCATION\", " 
	                + "    CREATED_AT, UPDATED_AT, TOTAL_SCORE" 
	                + ") VALUES (" 
	                + "    ?, " 
	                + "    ?, ?, ?, " 
	                + "    ?, " 
	                + "    ?, " 
	                + "    ?, ?, " 
	                + "    SYSDATE, SYSDATE, 0" // 원래대로 RANK_GROUP_SEQ.NEXTVAL + 7 사용
	                + ")";

	        try (PreparedStatement pstmtInsert = conn.prepareStatement(sqlInsert)) {
	            pstmtInsert.setInt(1, createdGroupId); // GROUP_ID
	            pstmtInsert.setString(2, gvo.getName());
	            pstmtInsert.setInt(3, gvo.getSize());
	            pstmtInsert.setInt(4, gvo.getCondition());
	            pstmtInsert.setString(5, gvo.getDescription());
	            pstmtInsert.setString(6, gvo.getCategory());
	            pstmtInsert.setString(7, gvo.getGPrivate());
	            pstmtInsert.setString(8, gvo.getLocation());

	            rows = pstmtInsert.executeUpdate();

	            System.out.println("Rows inserted: " + rows);
	            if (rows == 1) {
	            	conn.commit(); 
	                int tmp = addGroupUser(userId, createdGroupId, "LEADER");
	                
	                //그룹장 추가까지 성공하면 그룹 생성
	                if(tmp > 0) {
	                	conn.commit();
	                }
	                
	                else {
	                	System.out.println("그룹장 추가 실패");
	                	conn.rollback();
	                }
	                // 성공적으로 삽입된 경우 커밋
	            } else {
	                System.out.println("그룹 생성 에러");
	                conn.rollback(); // 삽입 실패 시 롤백
	            }
	        } catch (SQLException e) {
	            System.out.println("PreparedStatement 생성 오류>>>>>>>>");
	            e.printStackTrace();
	            conn.rollback(); // 예외 발생 시 롤백
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	    	 dbm.close(conn, pstmt, rs); 
	    }
	    return rows;
	}

	// 그룹원 추가하기
	public int addGroupUser( int userId, int groupId, String status) {
		
		DBManager dbm = OracleDBManager.getInstance(); // new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			conn.setAutoCommit(false);
			String sql = "INSERT INTO group_user (group_id, user_id, role, status, joined_at) "
					+ "VALUES (?, ?, ?, ?, SYSDATE)";

			// GROUP 더미가 7개 들어가있음 : RANK_GROUP_SEQ.NEXTVAL+7
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupId); // groupId
			pstmt.setInt(2, userId); // userId
			if (status == null || status.isEmpty()) {
				pstmt.setString(3, "MEMBER"); 
			}else{
				pstmt.setString(3, "LEADER"); // 기본 역할: 'MEMBER'
			}
			pstmt.setString(4, "ACTIVE"); // 기본 상태: 'ACTIVE'

			rows = pstmt.executeUpdate();
			if (rows == 1) {

				conn.commit();
				dbm.close(conn, pstmt);

			} else {
				conn.rollback();
				dbm.close(conn, pstmt);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			if (conn != null) {
		        try {
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		}

		return rows;
	}

	
	
	
	// 유저가 그룹 탈퇴하기
	public int deleteGroupByUser(int groupId, int userId) {

		DBManager dbm = OracleDBManager.getInstance(); // new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM group_user WHERE group_id = ? AND user_id = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupId); // groupId
			pstmt.setInt(2, userId); // userId

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
	
	public int deleteGroupByLeader(int groupId, int userId, int deleteUserId) {

		DBManager dbm = OracleDBManager.getInstance(); // new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		int rows = 0;

		try {
			conn.setAutoCommit(false);
			String sql = "DELETE FROM group_user\r\n"
						+ "WHERE group_id = ?	\r\n"
						+ "  AND user_id = ?	\r\n"
						+ "  AND EXISTS (		\r\n"
						+ "      SELECT 1		\r\n"
						+ "      FROM group_user		\r\n"
						+ "      WHERE group_id = ?		\r\n"
						+ "        AND user_id = ?		\r\n"
						+ "        AND role = 'LEADER'	\r\n"
						+ "  )";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, groupId); // groupId
			pstmt.setInt(2, deleteUserId); // userId
			pstmt.setInt(3, groupId);
			pstmt.setInt(4, userId);
			
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
	
	// 리더 변경하기
	public int grantGroupLeaderByLeader(int groupId,int currentLeaderId,int newLeaderId) {

		    DBManager dbm = OracleDBManager.getInstance(); // DB 연결 객체
		    Connection conn = dbm.connect(); // DB 연결
		    PreparedStatement pstmt = null;
		    int rows = 0;

		    try {
		        conn.setAutoCommit(false); // 트랜잭션 수동 관리

		        // 첫 번째 UPDATE: 현재 리더를 MEMBER로 변경
		        String sqlUpdateCurrentLeader = "UPDATE group_user "
		                                      + "SET role = 'MEMBER' "
		                                      + "WHERE group_id = ? "
		                                      + "AND user_id = ? "
		                                      + "AND role = 'LEADER'";

		        pstmt = conn.prepareStatement(sqlUpdateCurrentLeader);
		        pstmt.setInt(1, groupId);
		        pstmt.setInt(2, currentLeaderId); // 현재 리더의 user_id

		        rows = pstmt.executeUpdate();

		        if (rows == 1) {
		            // 두 번째 UPDATE: 새로운 리더를 LEADER로 설정
		            String sqlUpdateNewLeader = "UPDATE group_user "
		                                       + "SET role = 'LEADER' "
		                                       + "WHERE group_id = ? "
		                                       + "AND user_id = ? "
		                                       + "AND role != 'LEADER'";

			        try (PreparedStatement pstmtSecondUpdate = conn.prepareStatement(sqlUpdateNewLeader)) {
			        	pstmtSecondUpdate.setInt(1, groupId);
			        	pstmtSecondUpdate.setInt(2, newLeaderId); // 새로운 리더의 user_id

				            rows = pstmtSecondUpdate.executeUpdate();

			            System.out.println("Rows inserted: " + rows);
			            if (rows == 1) {
			            	conn.commit(); 
			            } else {
			                System.out.println("update leader 임명 error>>>>>>>>");
			                conn.rollback(); 
			            }
			        } catch (SQLException e) {
			            System.out.println("PreparedStatement 생성 오류>>>>>>>>");
			            e.printStackTrace();
			            conn.rollback(); 
			        }
		        } else {
		            conn.rollback(); // 첫 번째 업데이트가 실패하면 롤백
		        }

		    } catch (SQLException e) {
		        e.printStackTrace(); // 예외 발생 시 출력
		        try {
		            if (conn != null) {
		                conn.rollback(); // 예외 발생 시 롤백
		            }
		        } catch (SQLException rollbackEx) {
		            rollbackEx.printStackTrace();
		        }
		    } finally {
		        dbm.close(conn, pstmt); // 리소스 정리
		    }

		    return rows; // 수정된 행 수 반환
	}
	// -------------------------GROUP 신청관련 메서드---------------------------------

	// 그룹 가입신청 / 그룹초대하기 / 대기 / 승인 / 탈퇴 GroupUser의 status = [ accept , reject /
	// approve]
	public int requestGroupByUser(int userId, int groupId, String message) {

		// update status 쿼리
		// message가 가입 / 초대 이면 groupUser insert 문 넣기

		int result = 1;
		return result;

	}

	// 그룹에 GroupUser의 status = [ accept , reject / approve]
	public int inviteGroupByJoinUser(int userId, int groupId, String message) {

		int result = 1;
		return result;
	}

	// -------------------------다른 api---------------------------

	// 로그인한 유저가 참여중인 그룹수
	public int getNumberOfJoinGroup(int userId) {

		int joinGroupCount =0;
		DBManager dbm = OracleDBManager.getInstance(); // new OracleDBManager();
		Connection conn = dbm.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// GROUP 더미가 7개 들어가있음 : RANK_GROUP_SEQ.NEXTVAL+7

			String sql =
						"SELECT count(1) as CNT\r\n"
						+ "FROM GROUPS G\r\n"
						+ "JOIN GROUP_USER GU\r\n"
						+ "ON (g.group_id = gu.group_id)\r\n"
						+ "WHERE GU.user_id =?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				joinGroupCount = rs.getInt("CNT");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbm.close(conn, pstmt, rs);
		}
		
		return joinGroupCount;
	}

	public static void main(String[] argv) {
		GroupDAO gdao = new GroupDAO();
		GroupVO gvoTest = new GroupVO(0, "개발자 그룹", "개발을 좋아하는 사람들", "기술", "Y", 100, 200, "2025-01-02", "2025-01-02", 1,
				5, 1, "서울", null);

//		System.out.println( "리더 임명 test>>>"+ gdao.grantGroupLeaderByLeader(1, 1, 2));
//		int testInsert = gdao.createGroup(2, gvoTest);
//		System.out.println("testInsert" + testInsert);
//		
//		gdao.addGroupUser(14,2,"LEADER");
//		GroupVO selectGvoTest = gdao.selectGroupDetail(1, 1);
//		System.out.println(selectGvoTest.toString());

		//로그인 한 유저의 그룹 리스트
//		ArrayList<GroupVO> arr = gdao.selectAllJoinGroup(1);
//		System.out.println(arr.size());
//		for(var x : arr) System.out.println(x.toString());
		
//	
//		System.out.println(gdao.getNumberOfJoinGroup(1));
		
//		System.out.println(gdao.deleteGroupUser(4, 2));
		
	}

}
