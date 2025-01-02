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
			// 그룹 업적 관련 정보 
		</div>
		<div id="group-detail-info-div">
			그룹의 상세정보 
			그룹 상세소개  - 정하 
			${GROUP_DETAIL.groupId}
			${GROUP_DETAIL.name}
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
	
	 <!-- 
	 	그룹랭킹차트 - 찬호 
		selectAllGroupRankOrderByDate
	 	// 넘겨줄 메서드는 ArrayList<Object> 그룹업적 리스트 전달
	  -->
	<div class="group-title-div">
		<div class="group-main-title">
			<h1> ${GROUP_DETAIL.name} 의 그룹랭킹 포인트</h1>
		</div>
		<div class="group-sub-title">
			<h1> 총10명 <button class="group-button">더보기</button></h1>
		</div>
	</div>
	<div class= "group-info-container-div">
	
	</div>
	
	
	
	<!-- 그룹업적 - 성관 -->
	<div class="group-title-div">
		<div class="group-main-title">
			
			<h1> ${GROUP_DETAIL.name} 의 그룹 최근업적 TOP3</h1>
		</div>
		<div class="group-sub-title">
			<h1> 총10명 <button class="group-button">더보기</button></h1>
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
	
	
	<!-- 그룹채팅 - 찬호 // 구현예정 -->
	<div class="group-title-div">
		<div class="group-main-title">
			<h1> ${GROUP_DETAIL.name} 의 그룹 채팅방</h1>
		</div>
		<div class="group-sub-title">
			<h1> 총10명 <button class="group-button">더보기</button></h1>
		</div>
	</div>
	<div class= "group-info-container-div">
	
	</div>

</div>



<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
$( document ).ready(function() {
	//$("#btn").click( function() {  
	//    	$("#input").val();
	//});
});
</script>


</body>
</html>