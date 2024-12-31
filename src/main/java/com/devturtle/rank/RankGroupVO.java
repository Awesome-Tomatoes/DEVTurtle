/**
 * 
 */
package com.devturtle.rank;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RankGroupVO {
	private int rankGroupID;
	private int groupID;
	private int scoreSum;
	private String date;
}
