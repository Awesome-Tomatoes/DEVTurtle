/**
 * 
 */
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
