package com.devturtle.group;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupVO {

	private long groupId;
	private String name;
	private String description;
	private String category;
	private String gPrivate;
	private long totalScore;
	private long rankScore;
	private String createdAt;
	private String updatedAt;
	
	private ArrayList<GroupUserVO> listfGroupUser;
	
	// 그룹에 대한 정보
	public GroupVO(long groupId, String name, String description, String category, String gPrivate, long totalScore,
			long rankScore, String createdAt, String updatedAt) {
		super();
		this.groupId = groupId;
		this.name = name;
		this.description = description;
		this.category = category;
		this.gPrivate = gPrivate;
		this.totalScore = totalScore;
		this.rankScore = rankScore;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

}
