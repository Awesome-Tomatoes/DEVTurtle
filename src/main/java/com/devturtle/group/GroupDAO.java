package com.devturtle.group;
import java.util.ArrayList;

public class GroupDAO {


	// 한그룹에 대한 정보
	public GroupVO selectGroup(int groupID) {	
		return new GroupVO(groupID,"멋쟁이그룹","설명","스터디","공개",50000,60,"2024-12-12","2024-12-12"); 
	}
	
	// 전체그룹 리스트 정보
	public ArrayList<GroupVO> selectAllGroup() {
		ArrayList<GroupVO> alist = new ArrayList<GroupVO>();

		alist.add(new GroupVO(1,"group1","설명1","스터디","공개",50000,60,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(2,"group2","설명2","스터디","공개",40000,70,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(3,"group3","설명3","스터디","공개",60000,50,"2024-12-12","2024-12-12"));
		alist.add(new GroupVO(4,"group4","설명4","스터디","공개",80000,30,"2024-12-12","2024-12-12"));	
		
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
	
	// 유저가 그룹 가입신청하기 GroupUser의 status = [ accept , reject / approve]
	public int requestJoinGroup(int userId, int groupId, String message) {
		int result = 1;
		return result;
	}
	
	
	// 그룹 신청 대기
	
	// 로그인한 유저가 참여중인 그룹수
	public int getNumberOfJoinGroup() {
		
		int count = 10000;
		return count;
	}
	
}
