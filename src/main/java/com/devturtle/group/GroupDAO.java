package com.devturtle.group;
import java.util.ArrayList;

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
	
	
	
	//-------------------------GROUP Create 메서드--------------------------------- 
	
	
	
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
	
}
