/**
 * 
 */
package com.devturtle.user;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2025. 1. 5.<br>
 * History :<br>
 * - 작성자 : Baikjonghyun, 날짜 : 2025. 1. 5., 설명 : 최초작성<br>
 *
 * @author Baikjonghyun
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginHistoryVO {

	private int historyID;
	private String loginDate;
	private int userID;
	
}
