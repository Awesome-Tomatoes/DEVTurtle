package com.devturtle.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
	private int userID;
	private String userName;
	private String loginID;
	private String loginPW;
	private String nickname;
	private String createdAt;
	private String updatedAt;
	private String solvedID;
	private String gitID;
	private String userBio;
	private int totalScore;
	private int solvedScore;
	private int gitScore;
}
