package com.devturtle.follow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowVO {
	
	public int followID;
	public int follower;
	public int following;
	public String state;
	
}
