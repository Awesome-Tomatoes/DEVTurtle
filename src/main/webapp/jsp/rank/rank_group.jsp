<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/rank/rank.css">
</head>
<body>

<h1 id="group-rank-title">👑 이번 달 그룹 랭킹</h1>
    <div class="group-rank-container">
        <c:forEach var="gvo" items="${GLIST}">
            <div class="group-rank-card" >
	                <div class="group-rank-card-header">
	                    <h2> ${gvo.rank}위</h2>     
	                    <!-- <button class="follow-btn">Join</button> -->
	                </div>
	                <div class="group-rank-card-title">
	                	<a href="${pageContext.request.contextPath}/groupdetail?groupid=${gvo.groupId}" class="group-name"> ${gvo.name}</a>
	                	<p class="group-people">
	                		<img src="${pageContext.request.contextPath}/assets/main/follower.svg" />
	                		<span>${GROUP_USER_COUNT[gvo.groupId]}명</span>
	                	</p>
	                </div>
	                <div class="group-rank-card-content">
	                    <p>${gvo.description}</p>
	                </div>
	                <div class="group-rank-card-footer">
	                    <p>TOTAL POINT <strong>${gvo.totalScore}P</strong></p>
	                </div>
               	
            </div>
        </c:forEach>
    </div>

${MY_KEY_PAGING_HTML}

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