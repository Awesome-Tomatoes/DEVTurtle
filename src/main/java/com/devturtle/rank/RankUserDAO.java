package com.devturtle.rank;

import java.util.ArrayList;

import com.devturtle.user.UserVO;

public class RankUserDAO {
	public ArrayList<RankUserVO> selectRankUser() {
		ArrayList<RankUserVO> rulist = new ArrayList<RankUserVO>();
		rulist.add(new RankUserVO(1, 1, 100, "20241231"));
		rulist.add(new RankUserVO(2, 2, 200, "20241231"));
		rulist.add(new RankUserVO(3, 3, 300, "20241231"));
		rulist.add(new RankUserVO(4, 4, 100, "20241231"));
		rulist.add(new RankUserVO(5, 1, 1000, "20250101"));

		return rulist;
	}
}
