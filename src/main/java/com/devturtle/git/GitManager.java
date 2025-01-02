package com.devturtle.git;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.devturtle.common.OracleDBManager;
import com.devturtle.user.UserDAO;
import com.devturtle.user.UserVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class GitManager {
    private static final String GITHUB_API_URL = "https://api.github.com";
	Properties props = new Properties();

    // username만 받아서 전체 데이터를 반환하는 통합 메서드
    public GitVO insertGit(int userid) {
    	UserDAO udao = new UserDAO();
    	GitDAO gdao = new GitDAO();

    	GitVO gvo= new GitVO();
		try {
			
			UserVO uvo = udao.selectUser(userid);
    		GitHubStats stat = getStats(uvo.getGitID());

	    	gvo.setRating(stat.getRating());
	    	gvo.setUserid(uvo.getUserID());
	    	gdao.insert(gvo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return gvo;
    }
    
    public GitHubStats getStats(String gitName) throws Exception {
        int userCommits = 0;
        int userPRs = 0;
        int userIssues = 0;
        int orgCommits = 0;
        int orgPRs = 0;
        int orgIssues = 0;

        // 1. 사용자 저장소 커밋 수 계산
        
        try {
        List<String> userRepositories = getRepositories(gitName);
        userCommits = getTotalCommits(gitName, gitName,userRepositories);
//        userPRs = getTotalPRs(gitName, userRepositories);
//        userIssues = getTotalIssues(gitName, userRepositories);

        // 2. 사용자 소속 Organizations 가져오기
        List<String> organizations = getOrganizations(gitName);

        // 3. 각 Organization 저장소 커밋 수 계산
        for (String org : organizations) {
        	
            List<String> orgRepositories = getOrganizationRepositories(org);
            orgCommits += getTotalCommits(gitName, org, orgRepositories);
//            orgPRs += getTotalPRs(gitName, org, orgRepositories);
//            orgIssues += getTotalIssues(gitName, org, orgRepositories);
        }

        // 4. 결과 객체 생성 및 반환
        return new GitHubStats(gitName, (userCommits + orgCommits)*2, (userPRs+ orgPRs)*8, (userIssues+ orgIssues)*5);
        } catch(Exception e) {
        	return new GitHubStats();
        }
    }

    // 사용자의 모든 저장소 가져오기
    private List<String> getRepositories(String gitName) throws Exception {
        String apiUrl = GITHUB_API_URL + "/users/" + gitName + "/repos";
        String jsonResponse = makeApiCall(apiUrl);
        List<String> repositories = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);

        for (JsonNode node : rootNode) {
            repositories.add(node.get("name").asText()); // 저장소 이름 추가
        }
        return repositories;
    }

    // 사용자가 소속된 Organizations 가져오기
    public List<String> getOrganizations(String gitName) throws Exception {
        // 사용자 본인의 Public + Private Organizations 가져오기
        String apiUrl = GITHUB_API_URL + "/users/" + gitName + "/orgs";
        String jsonResponse = makeApiCall(apiUrl);
        List<String> organizations = new ArrayList<>();

        System.out.println(jsonResponse);
        // 디버깅 로그 추가

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);

        for (JsonNode node : rootNode) {
            organizations.add(node.get("login").asText()); // Organization 이름 추가
        }

        System.out.println("Fetched Organizations: " + organizations);
        return organizations;
    }


    // Organization의 모든 저장소 가져오기
    private List<String> getOrganizationRepositories(String org) throws Exception {
        String apiUrl = GITHUB_API_URL + "/orgs/" + org + "/repos";
        String jsonResponse = makeApiCall(apiUrl);
        List<String> repositories = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);

        for (JsonNode node : rootNode) {
            repositories.add(node.get("name").asText()); // 저장소 이름 추가
        }
        return repositories;
    }

    public int getTotalCommits(String gitName, List<String> repositories) throws Exception {
        int totalCommits = 0;

        for (String repo : repositories) {
            String apiUrl = GITHUB_API_URL + "/repos/" + gitName + "/" + repo + "/commits";
            try {
                String jsonResponse = makeApiCall(apiUrl);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(jsonResponse);

                totalCommits += rootNode.size(); // 커밋 수 추가
            } catch (Exception e) {
                // 빈 저장소나 기타 오류 처리
                System.err.println("Error fetching commits for repo: " + repo + " - " + e.getMessage());
            }
        }
        return totalCommits;
    }

    public int getTotalPRs(String gitName, List<String> repositories) throws Exception {
        int totalPRs = 0;

        for (String repo : repositories) {
            String apiUrl = GITHUB_API_URL + "/repos/" + gitName + "/" + repo + "/pulls";
            try {
                String jsonResponse = makeApiCall(apiUrl);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(jsonResponse);

                totalPRs += rootNode.size(); // 커밋 수 추가
            } catch (Exception e) {
                // 빈 저장소나 기타 오류 처리
                System.err.println("Error fetching commits for repo: " + repo + " - " + e.getMessage());
            }
        }
        return totalPRs;
    }

    public int getTotalIssues(String gitName, List<String> repositories) throws Exception {
        int totalIssues = 0;

        for (String repo : repositories) {
            String apiUrl = GITHUB_API_URL + "/repos/" + gitName + "/" + repo + "/issues";
            try {
                String jsonResponse = makeApiCall(apiUrl);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(jsonResponse);

                totalIssues += rootNode.size(); // 커밋 수 추가
            } catch (Exception e) {
                // 빈 저장소나 기타 오류 처리
                System.err.println("Error fetching commits for repo: " + repo + " - " + e.getMessage());
            }
        }
        return totalIssues;
    }

    
    // 특정 저장소에서 사용자의 총 커밋 수 계산
    private int getTotalCommits(String gitName, String owner, List<String> repositories) throws Exception {
        int totalCommits = 0;

        for (String repo : repositories) {
            String apiUrl = GITHUB_API_URL + "/repos/" + owner + "/" + repo + "/commits?author=" + gitName;
            try {
                String jsonResponse = makeApiCall(apiUrl);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(jsonResponse);

                totalCommits += rootNode.size(); // 커밋 수 추가
            } catch (Exception e) {
                System.err.println("Error fetching commits for repo: " + repo + " - " + e.getMessage());
            }
        }
        return totalCommits;
    }
    
 // 특정 저장소에서 사용자의 총 커밋 수 계산
    private int getTotalPRs(String gitName, String owner, List<String> repositories) throws Exception {
        int totalPRs = 0;

        for (String repo : repositories) {
            String apiUrl = GITHUB_API_URL + "/repos/" + owner + "/" + repo + "/pulls?author=" + gitName;
            try {
                String jsonResponse = makeApiCall(apiUrl);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(jsonResponse);

                totalPRs += rootNode.size(); // 커밋 수 추가
            } catch (Exception e) {
                System.err.println("Error fetching commits for repo: " + repo + " - " + e.getMessage());
            }
        }
        return totalPRs;
    }
    
 // 특정 저장소에서 사용자의 총 커밋 수 계산
    private int getTotalIssues(String gitName, String owner, List<String> repositories) throws Exception {
        int totalIssues = 0;

        for (String repo : repositories) {
            String apiUrl = GITHUB_API_URL + "/repos/" + owner + "/" + repo + "/issues?author=" + gitName;
            try {
                String jsonResponse = makeApiCall(apiUrl);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(jsonResponse);

                totalIssues += rootNode.size(); // 커밋 수 추가
            } catch (Exception e) {
                System.err.println("Error fetching commits for repo: " + repo + " - " + e.getMessage());
            }
        }
        return totalIssues;
    }

    // API 호출 메서드
    private String makeApiCall(String apiUrl) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        

    	props.load(GitManager.class.getClassLoader().getResourceAsStream("mydb.properties"));
    	String TOKEN = props.getProperty("git.token");
    	
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer " + TOKEN);
        conn.setRequestProperty("Accept", "application/vnd.github+json");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseContent = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                responseContent.append(inputLine);
            }
            in.close();
            return responseContent.toString();
        } else {
            throw new Exception("GitHub API 호출 실패. 응답 코드: " + responseCode + " - " + conn.getResponseMessage());
        }
    }
    
    public static void main(String[] args) {
		GitManager mgr = new GitManager();
		try {
			System.out.println(mgr.getStats("sir-Crab").toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

// GitHubStats 결과 객체
@Data
@AllArgsConstructor
@NoArgsConstructor
class GitHubStats {
    private String gitName;
    private int userCommits;
    private int userPRs;
    private int userIssues;
    private int rating;
    
	public GitHubStats(String gitName, int userCommits, int userPRs, int userIssues) {
		super();
		this.gitName = gitName;
		this.userCommits = userCommits;
		this.userPRs = userPRs;
		this.userIssues = userIssues;
		rating = userCommits + userPRs + userIssues;
	}

    
}
