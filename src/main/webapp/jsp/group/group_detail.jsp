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
			<h1>${GROUP_DETAIL.name} 's 업적 정보 </h1>
		<!-- 	
			<div class="contents__article--fragment" >
            
	            <form class="group-basic-info__form">
	                그룹 이름
	                <div class="group-basic-info__form-item">
	                    <label for="group-name">그룹 이름</label>
	                    <input type="text" id="group-name" name="groupName" value="">
	                    
	                </div>
	
	                최대 인원
	                <div class="group-basic-info__form-item">
	                    <label for="group-github">최대인원</label>
	                    <input type="text" id="group-github" name="groupGithub" value="">
	                </div>
	
	                참가 조건
	                <div class="group-basic-info__form-item">
	                    <label for="group-solvedac">참가 조건</label>
	                    <input type="text" id="group-solvedac" name="groupSolvedAc" value="">
	                    
	                </div>
	
					<label for="group-bio">그룹 소개글</label>
	                그룹 소개글
	                <div class="group-basic-info__form-item">
	                    <textarea id="group-bio" name="groupBio"></textarea>
	                </div>
	                
	            </form>
	        </div>
		 -->	
		</div>
		<div id="group-detail-info-div">
			 
        					
			<h1>${GROUP_DETAIL.name} 's 상세정보</h1>
			
			<article class="contents__article--fragment" id="contents__article--fragment-basic">
            
            <form class="group-basic-info__form">
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
 				<input type="hidden" id="groupId" name="groupId" value="${GROUP_DETAIL.groupId}" />
 
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
			<h1> 총10명 <button class="group-button">더보기</button></h1>
		</div>
	</div>
	<div class= "group-info-container-div group-flex-div">
		
		<div class ="group-card-button">
		</div>
		<div class="group-user-detail-info-div">
			
		</div>
		<div class="group-user-detail-info-div">
			
		</div>
		<div class="group-user-detail-info-div">
			
		</div>
		<div class ="group-card-button">
		</div>
	</div>
	
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
		<div class="group-success-mission-detail">
			1. ${GROUP_DETAIL.name}
		</div>
		<div class="group-success-mission-detail">
			2. ${GROUP_DETAIL.name}
		</div>
		<div class="group-success-mission-detail">
			3. ${GROUP_DETAIL.name}
		</div>
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

});
</script>


</body>
</html>