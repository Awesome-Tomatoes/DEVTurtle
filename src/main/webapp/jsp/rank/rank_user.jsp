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
</head>
<body>

<h1>사용자 랭킹 정보<br></h1>

<c:forEach var="ruvo" items="${RULIST}">
        <tr>
            <td>${ruvo.rankUserID}</td>
            <td>${ruvo.userID}</td>
            <td>${ruvo.scoreSum}</td>
            <td>${ruvo.date}<br></td>
            
        </tr>
</c:forEach>
<br>

<h1>사용자 정보<br></h1>
<c:forEach var="uvo" items="${ULIST}">
        <tr>
            <td>${uvo.userName}</td>
            <td>${uvo.solvedScore}</td>
            <td>${uvo.gitScore}</td>
            <td>${uvo.userBio}<br></td>
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