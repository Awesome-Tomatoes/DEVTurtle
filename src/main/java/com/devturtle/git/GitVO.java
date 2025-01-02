/**
 * 
 */
package com.devturtle.git;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class GitVO {
	private int git_id;
	private int rating;
	private String created_at;
	private String updated_at;
	private int userid;
}
