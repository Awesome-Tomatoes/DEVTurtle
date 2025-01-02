package com.devturtle.solved;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.net.*;
import java.util.List;

import com.devturtle.user.UserDAO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class SolvedManager {
	
	// api uri 생성
	public static String getUserByName(int userid) {
		UserDAO dao = new UserDAO();
		
	    String uri = "https://solved.ac/api/v3/search/user?query=" + dao.selectUser(userid).getSolvedID();
	    return uri;
	}
	
	// userid 를 통해 api 호출
	public static SolvedVO solvedacAPIRequest(int userid) throws IOException, InterruptedException {
		String uri = getUserByName(userid);
		
	    HttpRequest request = HttpRequest.newBuilder()
	            .uri(URI.create(uri))
	            .header("x-solvedac-language", "ko")
	            .header("Accept", "application/json")
	            .GET()
	            .build();

	    HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // Pretty printing 설정
		
		// JSON 데이터를 ApiResponse 객체로 변환
		ApiResponse apiResponse = objectMapper.readValue(response.body(), ApiResponse.class);


        SolvedVO svo = new SolvedVO();
        
        
        // handle과 rating 값 가져오기
        for (ApiResponse.Item item : apiResponse.getItems()) {
        	svo.setUserid(userid);
        	svo.setRating((int)item.getRating());
        }
        
        return svo;
	}
	
	// userid 로 solvedData 가져오기
	public SolvedVO selectUserSolvedData(int userid) {
		SolvedDAO dao = new SolvedDAO();
		SolvedVO svo = dao.select(userid);
		return svo;
	}

	// 전체 solvedData 가져오기
	public ArrayList<SolvedVO> selectUsersSolvedData() {
		SolvedDAO dao = new SolvedDAO();
		ArrayList<SolvedVO> alist = dao.select();
		return alist;
	}

	// 유저 1명 solvedData 수정
	public void updateSolvedData(int userid) {
		SolvedDAO dao = new SolvedDAO();
		SolvedVO svo;
		UserDAO udao = new UserDAO();
		try {
			svo = solvedacAPIRequest(userid);
			dao.update(svo);
			udao.updateUserSolvedScore(svo.getUserid(), svo.getRating());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// 전체 solvedData 수정 (1일 1회 수행)
	public void updateSolvedAllData() {
		SolvedDAO dao = new SolvedDAO();
		ArrayList<SolvedVO> alist = dao.select();
		UserDAO udao = new UserDAO();
		try {
			for(SolvedVO svo : alist) {
				svo = solvedacAPIRequest(svo.getUserid());
				dao.update(svo);
				udao.updateUserSolvedScore(svo.getUserid(), svo.getRating());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//SolvedData 생성
	public void insertSolvedData(int userid) {
		SolvedDAO dao = new SolvedDAO();
		SolvedVO svo;
		
		try {
			svo = solvedacAPIRequest(userid);
			dao.insert(svo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


class ApiResponse {
    private double count;
    private List<Item> items;

    // Getters and Setters
    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String handle;
        private double rating;

        // Getters and Setters
        public String getHandle() {
            return handle;
        }

        public void setHandle(String handle) {
            this.handle = handle;
        }

        public double getRating() {
            return rating;
        }

        public void setRating(double rating) {
            this.rating = rating;
        }
    }
}