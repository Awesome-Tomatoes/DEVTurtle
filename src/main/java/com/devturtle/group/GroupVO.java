package com.devturtle.group;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupVO {

	private int groupId;
	private String name;
	private String description;
	private String category;
	private String gPrivate;
	private int totalScore;
	private int rankScore;
	private String createdAt;
	private String updatedAt;
	private int rank;
	private int size;
	private int condition;
	private String location;
	private String mainPng;
	
	private ArrayList<GroupUserVO> listOfGroupUser;
	
	public GroupVO(String name, int size, int condition,
					String description, String category,
					String gPrivate, String location ) {
		super();
		this.name = name;
		this.condition = condition;
		this.description = description;
		this.category = category;
		this.location = location;
		this.gPrivate = gPrivate;
	}
	
	
	// 그룹에 대한 정보
	public GroupVO(int groupId, String name, String description, String category, String gPrivate, int totalScore,
			int rankScore, String createdAt, String updatedAt) {
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
