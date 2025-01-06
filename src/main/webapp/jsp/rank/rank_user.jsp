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
<meta charset="UTF-8">
<title>UserRanking</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/rank/rank_user.css">
</head>
<body>
    <h1 id="user-rank-title">👑 이번 달 사용자 랭킹 정보</h1>
    <div class="user-rank-container">
        <c:forEach var="uvo" items="${ULIST}">
            <div class="user-rank-card">
                <div class="user-rank-card-header">
                    <a href="${pageContext.request.contextPath}/mypage?userid=${uvo.userID}">${uvo.rank}위 ${uvo.userName}</a>
                    <button class="follow-btn">Follow</button>
                </div>
                <div class="user-rank-card-content">
                		<div class="user-rank-card-content-desc">
	                		<img src="${pageContext.request.contextPath}/userImage?userid=${uvo.userID}" alt="${uvo.userName}" class="user-avatar">
	                		<div class="user-rank-card-content-p">
	                			<p class="user-rank-bio"><span>${uvo.userBio}</span></p>
			                    <p><strong>solved.ac</strong><span>${uvo.solvedScore}p</span></p>
			                    <p><strong>Github</strong><span>${uvo.gitScore}p</span></p>
		                    </div>
	                    </div>
                    
                </div>
                <div class="user-rank-card-footer">
                	<p>TOTAL POINT <strong>${uvo.totalScore}P</strong></p>
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