/**
 * 
 */
package com.devturtle.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2024. 12. 31.<br>
 * History :<br>
 * - 작성자 : user, 날짜 : 2024. 12. 31., 설명 : 최초작성<br>
 *
 * @author user
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupUserVO {

	private long groupId;
	private String userId;
	private String role;
	private String status;
	private String joinedAt;
	private String leavedAt;
}
