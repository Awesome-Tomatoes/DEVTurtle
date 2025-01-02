/**
 * 
 */
package com.devturtle.mission;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2025. 1. 2.<br>
 * History :<br>
 * - 작성자 : sk-choi, 날짜 : 2025. 1. 2., 설명 : 최초작성<br>
 *
 * @author sk-choi
 * @version 1.0
 */

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MissionJoinUserVO {
	// Objective와 Users를 join한 정보를 담는 VO
	private int user_id;
	private String contents;
	private String success_date;
	private int points;
	
}
