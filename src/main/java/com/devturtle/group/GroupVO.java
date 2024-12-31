package com.devturtle.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2024. 12. 30.<br>
 * History :<br>
<<<<<<< HEAD
 * - 작성자 : user, 날짜 : 2024. 12. 30., 설명 : 최초작성<br>
 *
 * @author user
 * @version 1.0
 */
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
	

}
