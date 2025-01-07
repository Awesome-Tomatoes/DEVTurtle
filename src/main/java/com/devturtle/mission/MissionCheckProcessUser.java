/**
 * 
 */
package com.devturtle.mission;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2025. 1. 8.<br>
 * History :<br>
 * - 작성자 : sk-choi, 날짜 : 2025. 1. 8., 설명 : 최초작성<br>
 *
 * @author sk-choi
 * @version 1.0
 */

import java.util.ArrayList;

public class MissionCheckProcessUser {

	public void missionUserCompleteCheck(int userId) {

        MissionPersonalDAO mud = new MissionPersonalDAO();
        MissionCheckUserDAO mcd = new MissionCheckUserDAO();

        // 모든 사용자 미션 ID 가져오기
        ArrayList<Integer> missionList = mud.selectAllMissionUserNum();

        // 1. 첫 번째로 그룹에 참여한 경우
        String sql1 = mcd.getObjectQuery(missionList.get(0));
        if (mcd.joinGroupFirst(sql1, userId)) {
            if (!mcd.isMissionComplete(userId, missionList.get(0))) {
                mcd.insertUserMissionSuccessed(userId, missionList.get(0));
            }
        }
        
        // 2. 오늘 출석 완료 여부 확인
        String sql4 = mcd.getObjectQuery(missionList.get(1));
        if (mcd.attendanceToday(sql4, userId)) {
            if (!mcd.isMissionComplete(userId, missionList.get(1))) {
                mcd.insertUserMissionSuccessed(userId, missionList.get(1));
            }
        }

        // 3. 첫 10회 이상 출석했는지 확인
        String sql2 = mcd.getObjectQuery(missionList.get(2));
        if (mcd.attendanceOver10(sql2, userId)) {
            if (!mcd.isMissionComplete(userId, missionList.get(2))) {
                mcd.insertUserMissionSuccessed(userId, missionList.get(2));
            }
        }

        // 4. 첫 30회 이상 출석했는지 확인
        String sql3 = mcd.getObjectQuery(missionList.get(3));
        if (mcd.attendanceOver30(sql3, userId)) {
            if (!mcd.isMissionComplete(userId, missionList.get(3))) {
                mcd.insertUserMissionSuccessed(userId, missionList.get(3));
            }
        }
    }

    public static void main(String[] args) {
        // 테스트를 위한 메인 함수
        MissionCheckProcessUser mcp = new MissionCheckProcessUser();
        int testUserId = 1; // 테스트할 사용자 ID
        mcp.missionUserCompleteCheck(testUserId);
    }

}
