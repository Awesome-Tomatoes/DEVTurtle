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
 * - 작성자 : victo, 날짜 : 2024. 12. 30., 설명 : 최초작성<br>
 *
 * @author victo
 * @version 1.0
 */	

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ObjectiveUserVO {

	private int userID;
	private int objectiveID;
	private String successDate;
}

