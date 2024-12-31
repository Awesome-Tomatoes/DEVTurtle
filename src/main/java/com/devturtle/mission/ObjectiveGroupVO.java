/**
 * 
 */
package com.devturtle.mission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2024. 12. 30.<br>
 * History :<br>
 * - 작성자 : sk-choi, 날짜 : 2024. 12. 30., 설명 : 최초작성<br>
 *
 * @author sk-choi
 * @version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectiveGroupVO {

	private int objectiveID;
	private int groupID;
	private String successDate;
}
