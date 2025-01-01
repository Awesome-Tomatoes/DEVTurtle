<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="x" 	uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sql" 	uri="http://java.sun.com/jsp/jstl/sql" %>    
<%@ include file="/jsp/layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UserRanking</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/rank/rank_user.css">
</head>
<body>
    <h1>사용자 랭킹 정보</h1>
    <div class="container">
        <c:forEach var="uvo" items="${ULIST}">
            <div class="card">
                <div class="card-header">
                    <h2> ${uvo.rank} ${uvo.userName}</h2>
                    <button class="follow-btn">Follow</button>
                </div>
                <div class="card-content">
                    <p><strong>솔브드 점수:</strong> ${uvo.solvedScore}</p>
                    <p><strong>깃 점수:</strong> ${uvo.gitScore}</p>
                    <p><strong>전체 점수:</strong> ${uvo.totalScore}</p>
                    <p><strong>유저 소개:</strong> ${uvo.userBio}</p>
                </div>
                <div class="card-footer">
                    <p><strong>POINT:</strong> ${uvo.totalScore}</p>
                </div>
            </div>
        </c:forEach>
    </div>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
$( document ).ready(function() {
	//$("#btn").click( function() {  
	//    	$("#input").val();
	//});
});
</script>
<%@ include file="/jsp/layout/footer.jsp" %>

</body>
</html>