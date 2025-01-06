<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="x" 	uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sql" 	uri="http://java.sun.com/jsp/jstl/sql" %>    

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/group/group_detail.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/group/group_detail_info_content.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/group/group_detail_info_mission.css">

<meta charset="UTF-8">

</head>
<body>
<%-- <c:forEach var="uvo" items="${ULIST}">
        <tr>
            <td>${uvo.nickname}</td>
            <td>${uvo.totalScore}</td>
            <td>${uvo.userName}</td>
            <td>${uvo.userBio}</td>
        </tr>
</c:forEach> --%>

<div id="group-detail-contents">

	<div id= "group-detail-container-div">
	
		<div id="group-success-info-div">
			
			<div id="group-ranking-info">
			    <p class="group-ranking-info-p">
			        <img src="${pageContext.request.contextPath}/assets/main/grade-star.svg" />
			        ${GROUP_DETAIL.name}
			        <span id="group-ranking-info-grade"></span>
			    </p>
			    <img id="group-ranking-info-img"
			        src="${pageContext.request.contextPath}/groupImage?groupid=${GROUP_DETAIL.groupId}" />
			    <p class="group-ranking-info-p">
			        <span class="group-ranking-info-p-title">그룹 랭킹 포인트 총 </span>
			        <span class="group-ranking-info-p-point">${GROUP_DETAIL.totalScore}p</span>
			    </p>
			    <ul id="group-ranking-info-ul">
			        <li class="group-ranking-info-li">
			            <p class="group-ranking-info-p">랭킹순위</p>
			            <p class="group-ranking-info-p">${GROUP_RANK}위</p>
			        </li>
			       
			        <li class="group-ranking-info-li">
			            <p class="group-ranking-info-p">인원수</p>
			            <p class="group-ranking-info-p">${GROUP_USER_CNT}명</p>
			        </li>
			        
			        <li class="group-ranking-info-li">
			            <p class="group-ranking-info-p">출석률</p>
			            <p class="group-ranking-info-p">90%</p>
			        </li>
			    </ul>
			
			    <div id="group-ranking-badge-div">
				    <ul>
				    	<li class="group-ranking-info-li">
			      	  		<p id="group_mission_badge">그룹 뱃지 ${MISSION_GROUP_CNT} 개 획득 </p>
			      			<div>
			      				<c:forEach var="mvo" items="${MISSION_GROUP_BADGE_LIST}">
						           <img src="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}${mvo.badgeLink}" 
						             alt="Badge Image" style="padding : 5px; width: 50px; height: 50px;">
							   	</c:forEach>
			      			</div>
			      			
			      		</li>
				    </ul>
			    </div>
			</div>
				
		</div>
		<div id="group-detail-info-div">				
			<h1>${GROUP_DETAIL.name} 's 상세정보</h1>
			
			<article class="contents__article--fragment" id="contents__article--fragment-basic">
            
            <form class="group-basic-info__form">
 				<input type="hidden" id="groupid" name="groupid" value="${GROUP_DETAIL.groupId}" />
 
                <!-- 그룹 이름 -->
                <div class="group-basic-info__form-item">
                    <label for="group-name">그룹 이름</label>
                    <input type="text" id="group-name" name="group-name" value="${GROUP_DETAIL.name}">
                    
                </div>

                <!-- 최대 인원 -->
                <div class="group-basic-info__form-item">
                    <label for="group-github">최대인원</label>
                    <input type="text" id="group-size" name="group-size" value="${GROUP_DETAIL.size}">
                </div>

                <!-- 참가 조건 -->
                <div class="group-basic-info__form-item">
                    <label for="group-solvedac">참가 조건</label>
                    <input type="text" id="group-condition" name="group-condition" value="${GROUP_DETAIL.condition}">
                    
                </div>

				<label for="group-bio">그룹 소개글</label>
                <!-- 그룹 소개글 -->
                <div class="group-basic-info__form-item">
                    <textarea id="group-description" name="group-description" >${GROUP_DETAIL.description}</textarea>
                </div>
 
                <button type="submit" class="group-detail-info-update-btn">수정</button>
               	 
            </form>
        </article>
		</div>
	</div>
	
	<!-- 그룹원 소개 - 유저+ 그룹 <- 종현/정하 -->
	<div class="group-title-div">
		<div class="group-main-title">
		
			<h1> "${GROUP_DETAIL.name}"의 그룹원 정보</h1>
		</div>
		<div class="group-sub-title">
			<h1> 총 ${GROUP_USER_CNT} / ${GROUP_DETAIL.size} 명 <button class="group-button">더보기</button></h1>
		</div>
	</div>
	<div class= "group-info-container-div group-flex-div">
		
		<div class ="group-card-button">
			
		</div>
		
		<c:forEach var="user" items="${GROUP_USER_LIST}">
	       
	       	<div class="group-user-detail-info-div">
			    <p class="group-ranking-info-p">
			         <c:choose>
				        <c:when test="${user.role == 'LEADER'}">
				            👑 
				        </c:when>
				        <c:otherwise>
				           	<img src="${pageContext.request.contextPath}/assets/main/grade-star.svg" />
				        </c:otherwise>
				    </c:choose>
	
			        ${user.nickname}
			        <span id="group-ranking-info-grade"></span>
			    </p>
			    <img id="group-ranking-info-img"
			        src="${pageContext.request.contextPath}/userImage?userid=${user.userId}" style=" width: 100px; height: 150px;" />
			    <p class="group-ranking-info-p">
			        <span class="group-ranking-info-p-title">유저 랭킹 포인트</span>
			        <span class="group-ranking-info-p-point">${user.totalScore}점</span>
			    </p>
			    <ul id="group-ranking-info-ul">
			        <li class="group-ranking-info-li">
			            <p class="group-ranking-info-p">직급</p>
			            <p class="group-ranking-info-p">${user.role}</p>
			        </li>
			       
			        <li class="group-ranking-info-li">
			            <p class="group-ranking-info-p">참여일</p>
			            <p class="group-ranking-info-p">${user.joinedAt}</p>
			        </li>
			        
			        <li class="group-ranking-info-li">
			            <p class="group-ranking-info-p">출석률</p>
			            <p class="group-ranking-info-p">90%</p>
			        </li>
			    </ul>
			</div>
	       
	       
	   </c:forEach>
		
		<div class ="group-card-button">
		</div>
	</div>
	<!--  -->
	<!-- 그룹업적 - 성관 -->
	<div class="group-title-div">
		<div class="group-main-title">
			
			<h1> ${GROUP_DETAIL.name} 의 그룹 최근업적 TOP3</h1>
		</div>
		<div class="group-sub-title">
		
			<h1> 총10명 
			
			<a class="group-button" 
				href="${pageContext.request.contextPath}/missionGroup?userid=<% int userId = (Integer) session.getAttribute("SESS_USER_ID"); %>
		">더 보기</a>
		</h1>
		</div>
	</div>
	
	<div class= "group-info-container-div">
		
		
		<c:forEach var="mvo" items="${MISSION_GROUP_LIST}" varStatus="seq">
		  	<div class="group-success-mission-detail">	
			  <c:if test="${seq.index >= 0 && seq.index < 3}">
			    <div class="mission-flex-div">
				  <div class = "mission-flex-div-items" >
				  	${seq.index + 1}
				  	${mvo.contents}
				  </div>
				  <div class="mission-flex-div-items" style="color:var(--main-color); ">+${mvo.points}p</div> <!-- 오른쪽 정렬 -->
				</div>
			  </c:if>
			</div>
		</c:forEach>
	</div>
	
	
	 <!-- 
	 	그룹랭킹차트 - 찬호 
		selectAllGroupRankOrderByDate
	 	// 넘겨줄 메서드는 ArrayList<Object> 그룹업적 리스트 전달
	  -->
	<div class="group-title-div">
		<div class="group-main-title">
			<h1> ${GROUP_DETAIL.name} 의 그룹랭킹 포인트</h1>
		</div>
	</div>
	<div class= "group-info-container-div">
	
	</div>
	
	
</div>



<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
$( document ).ready(function() {
	loadPage();
	
	$("#group-detail-info-update-btn").click(function() {
		e.preventDefault();
		const groupName = $("#group-name")
				.val();
		const groupCategory = $(
				"#group-category").val();
		const groupSize = $("#group-size")
				.val();
		const groupCondition = $(
				"#group-condition").val();
		const groupRule = $("#group-rule")
				.val();
		const groupDescription = $(
				"#group-description").val();
		const groupGroupId= $(
		"#groupId").val();

		
		alert(groupName + "  "
				+ groupCategory + "  "
				+ groupSize + "  "
				+ groupPrivate + "  "
				+ groupCondition + "  "
				+ groupRule + "  "
				+ groupDescription);
	
	
		$("#group-basic-info__form").attr("method",
				"post");
		$("#group-basic-info__form").attr("action",
				"${pageContext.request.contextPath}/groupdetail");
		// 폼 제출
        $("#group-basic-info__form").submit();
	});
	
	
	
	//-----------------------------------------------
	
	const GROUP_USER_LIST = ${GROUP_USER_LIST}; // 서버에서 전달받은 전체 유저 리스트
    let currentPage = 1;  // 현재 페이지 (1부터 시작)
    const pageSize = 3;  // 한 페이지에 표시할 유저 수

	// 페이지 이동 (이전 / 다음)
    function changePage(direction) {
        if (direction === 'next') {
            currentPage++;
        } else if (direction === 'prev' && currentPage > 1) {
            currentPage--;
        }
        loadPage();
    }
	
	function loadPage() {
        const startIndex = (currentPage - 1) * pageSize;
        const endIndex = currentPage * pageSize;
        const currentUsers = GROUP_USER_LIST.slice(startIndex, endIndex);

        // 유저 목록을 출력할 div를 선택
        const userListDiv = document.getElementById("group-user-list");
        userListDiv.innerHTML = ''; // 기존 목록 초기화

        // 현재 페이지에 해당하는 유저 정보 표시
        currentUsers.forEach(user => {
            const userDiv = document.createElement('div');
            userDiv.classList.add('group-user-detail-info-div');
            userDiv.innerHTML = `
                <p>User ID: ${user.userId}</p>
                <p>User Name: ${user.userName}</p>
                <p>User Nickname: ${user.nickname}</p>
                <p>User Total Score: ${user.totalScore}</p>
            `;
            userListDiv.appendChild(userDiv);
        });
    }

});


</script>


</body>
</html>