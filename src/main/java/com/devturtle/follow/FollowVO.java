package com.devturtle.follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowVO {
	
	public int followID;
	public int followed;
	public int following;
	public String status;
	
}
