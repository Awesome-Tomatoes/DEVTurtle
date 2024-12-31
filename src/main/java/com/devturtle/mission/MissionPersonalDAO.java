/**
 * 
 */
package com.devturtle.mission;
import java.util.ArrayList;

import com.devturtle.mission.ObjectiveVO;

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
	
	
	public ObjectiveVO select() { 
		
		ObjectiveVO uov = new ObjectiveVO();
		uov.setContents("파일 압축하고 풀기 100번 달성!");
		uov.setPoints(100);
		
		
		return uov;
	}
	
	public ArrayList<ObjectiveVO> selectAll() {
		
		ArrayList<ObjectiveVO> ulist = new ArrayList<ObjectiveVO>();
		
		ObjectiveVO uov1 = new ObjectiveVO();
		ObjectiveVO uov2 = new ObjectiveVO();
		uov1.setContents("파일 압축하고 풀기 100번 달성!");
		uov1.setPoints(100);
		uov2.setContents("폴더 지웠다 다시 만들기 1000번 달성!");
		uov2.setPoints(1000);
		
		ulist.add(uov1);
		ulist.add(uov2);
		
		for (int i = 0; i < ulist.size(); i++) {
			System.out.println(ulist.get(i).getContents());
			System.out.println(ulist.get(i).getPoints());
		}
		
		return ulist;
	}
	
	public ObjectiveVO insert(String contents, int points) {
		
		ObjectiveVO uov = new ObjectiveVO();
		
		uov.setContents(contents);
		uov.setPoints(points);
		
		return uov;
		
	}
	
	public int update() {
		
		int row = 1;
		
		return row;
	}
	
	public int delete() {
		
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
		System.out.println(ulist.get(1).getContents() + " " + ulist.get(1).getContents());
		
		
		
	}

}
