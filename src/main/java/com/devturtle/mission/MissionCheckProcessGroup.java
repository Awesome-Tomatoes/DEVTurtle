/**
 * 
 */
package com.devturtle.mission;
import java.util.ArrayList;
/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2025. 1. 7.<br>
 * History :<br>
 * - 작성자 : sk-choi, 날짜 : 2025. 1. 7., 설명 : 최초작성<br>
 *
 * @author sk-choi
 * @version 1.0
 */
public class MissionCheckProcessGroup {

	public void missionGroupCompleteCheck(int groupId) {
		
		MissionGroupDAO mgd = new MissionGroupDAO();
		MissionCheckDAO mcd = new MissionCheckDAO();
		
		ArrayList<Integer> alist = mgd.selectAllMissionGroupNum();
		//HashMap<Integer, String> qmap = new HashMap<Integer, String>();
		
		String sql1 = mcd.getObjectQuery(alist.get(0));
		
		if (mcd.attendanceCheck(sql1, groupId)) {
			if (mcd.isMissionComplete(groupId, alist.get(0)) == false) {
				mcd.insertGruopMissionSuccessed(groupId, alist.get(0));
			}
		}
		
		String sql2 = mcd.getObjectQuery(alist.get(1));
		if (mcd.piratesCondition(sql2, groupId)) {
			if (mcd.isMissionComplete(groupId, alist.get(1)) == false) {
				mcd.insertGruopMissionSuccessed(groupId, alist.get(1));
			}
		}
		
		String sql3 = mcd.getObjectQuery(alist.get(2));
		if (mcd.ratingCount(sql3, groupId)) {
			if (mcd.isMissionComplete(groupId, alist.get(2)) == false) {
				mcd.insertGruopMissionSuccessed(groupId, alist.get(2));
			}
		}
		
		String sql4 = mcd.getObjectQuery(alist.get(3));
		if (mcd.allCollectBadge(sql4, groupId).isEmpty()) {
			if (mcd.isMissionComplete(groupId, alist.get(3)) == false) {
				mcd.insertGruopMissionSuccessed(groupId, alist.get(3));
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MissionCheckProcessGroup mcp = new MissionCheckProcessGroup();
		mcp.missionGroupCompleteCheck(1);
	}

}
