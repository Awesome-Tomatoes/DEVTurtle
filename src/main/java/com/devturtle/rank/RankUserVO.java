package com.devturtle.rank;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RankUserVO {
	private int rankUserID;
	private int userID;
	private int scoreSum;
	private String date;
}

/*
select * 
from rank_user 
WHERE USER_ID = 9 AND TO_CHAR(RANK_USER_DATE, 'YYYYMM') LIKE  TO_CHAR(TO_DATE('20240101'), 'YYYYMM') 
ORDER BY RANK_USER_DATE;
*/