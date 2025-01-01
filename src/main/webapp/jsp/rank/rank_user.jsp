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
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/rank/rank_user.css">
</head>
<body>

<h1>사용자 랭킹 정보<br></h1>

<c:forEach var="ruvo" items="${RULIST}">
        <tr>
            <td>랭크id${ruvo.rankUserID}<br></td>
            <td>유저id${ruvo.userID}<br></td>
            <td>총점${ruvo.scoreSum}<br></td>
            <td>날짜${ruvo.date}<br><br></td>
            
        </tr>
</c:forEach>
<br><br>

<h1>사용자 정보<br></h1>
<c:forEach var="uvo" items="${ULIST}">
        <tr>
            <td>유저명${uvo.userName}<br></td>
            <td>솔브드점수${uvo.solvedScore}<br></td>
            <td>깃점수${uvo.gitScore}<br></td>
            <td>유저소개${uvo.userBio}<br><br></td>
        </tr>
</c:forEach>

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