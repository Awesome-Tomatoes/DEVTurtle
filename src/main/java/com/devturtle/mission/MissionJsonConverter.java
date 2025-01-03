/**
 * 
 */
package com.devturtle.mission;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

/**
 * Description : 클래스에 대한 설명을 입력해주세요.<br>
 * Date : 2025. 1. 2.<br>
 * History :<br>
 * - 작성자 : sk-choi, 날짜 : 2025. 1. 2., 설명 : 최초작성<br>
 *
 * @author sk-choi
 * @version 1.0
 */
public class MissionJsonConverter {
	public JsonNode convertToJsonGroup(ArrayList<MissionJoinGroupVO> dataList) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResult = null;

        try {
            // ArrayList를 JSON으로 변환
            jsonResult = objectMapper.valueToTree(dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
	}
	
	public JsonNode convertToJsonUser(ArrayList<MissionJoinUserVO> dataList) {
		ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonResult = null;

        try {
            // ArrayList를 JSON으로 변환
            jsonResult = objectMapper.valueToTree(dataList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResult;
	}
}
