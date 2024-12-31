/**
 * 
 */
package com.devturtle.solved;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter 대신(직렬화 안할 거면)
@AllArgsConstructor
@NoArgsConstructor
public class SolvedVO {
	private int solved_id;
	private int rating;
	private String created_at;
	private String updated_at;
	private int userid;
}
