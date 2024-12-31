package com.devturtle.rank;

import java.util.ArrayList;

public class RankGroupDAO {
	public ArrayList<RankGroupVO> selectAllRankUserByGroupID(int groupID) {
		ArrayList<RankGroupVO> rglist = new ArrayList<RankGroupVO>();
		
		rglist.add(new RankGroupVO(1, 1, 100, "20241231"));
		rglist.add(new RankGroupVO(2, 2, 200, "20241231"));
		rglist.add(new RankGroupVO(3, 3, 300, "20241231"));
		rglist.add(new RankGroupVO(4, 4, 100, "20241231"));
		rglist.add(new RankGroupVO(5, 1, 1000, "20250101"));
		return rglist;
	}
}
