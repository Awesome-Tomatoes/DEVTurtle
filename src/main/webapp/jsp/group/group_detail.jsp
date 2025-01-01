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


<div id= "group_detail_container_div">

	<div id="group_success_info_div">
		// 그룹 업적 관련 정보 
	</div>
	<div id="group_detail_info_div">
		그룹의 상세정보 
		그룹 상세소개  - 정하 
		${GROUP_DETAIL.groupId}
		${GROUP_DETAIL.name}
	</div>
</div>

<!-- 그룹원 소개 - 유저+ 그룹 <- 종현/정하 -->
<div class="group_title_div">
	<div class="group_main_title">
		<h1> "${GROUP_DETAIL.name}"의 그룹원 정보</h1>
	</div>
	<div class="group_sub_title">
		<h1> 총10명 <button class="group_button">더보기</button></h1>
	</div>
</div>
<div class= "group_info_container_div group_flex_div">
	
	<div class ="group_card_button">
	</div>
	<div class="group_user_detail_info_div">
		
	</div>
	<div class="group_user_detail_info_div">
		
	</div>
	<div class="group_user_detail_info_div">
		
	</div>
	<div class ="group_card_button">
	</div>
</div>

 <!-- 
 	그룹랭킹차트 - 찬호 
	selectAllGroupRankOrderByDate
 	// 넘겨줄 메서드는 ArrayList<Object> 그룹업적 리스트 전달
  -->
<div class="group_title_div">
	<div class="group_main_title">
		<h1> ${GROUP_DETAIL.name} 의 그룹랭킹 포인트</h1>
	</div>
	<div class="group_sub_title">
		<h1> 총10명 <button class="group_button">더보기</button></h1>
	</div>
</div>
<div class= "group_info_container_div">

</div>



<!-- 그룹업적 - 성관 -->
<div class="group_title_div">
	<div class="group_main_title">
		
		<h1> ${GROUP_DETAIL.name} 의 그룹 최근업적 TOP3</h1>
	</div>
	<div class="group_sub_title">
		<h1> 총10명 <button class="group_button">더보기</button></h1>
	</div>
</div>
<div class= "group_info_container_div">
	<div class="group_success_mission_detail">
		1. ${GROUP_DETAIL.name}
	</div>
	<div class="group_success_mission_detail">
		2. ${GROUP_DETAIL.name}
	</div>
	<div class="group_success_mission_detail">
		3. ${GROUP_DETAIL.name}
	</div>
</div>


<!-- 그룹채팅 - 찬호 // 구현예정 -->
<div class="group_title_div">
	<div class="group_main_title">
		<h1> ${GROUP_DETAIL.name} 의 그룹 채팅방</h1>
	</div>
	<div class="group_sub_title">
		<h1> 총10명 <button class="group_button">더보기</button></h1>
	</div>
</div>
<div class= "group_info_container_div">

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