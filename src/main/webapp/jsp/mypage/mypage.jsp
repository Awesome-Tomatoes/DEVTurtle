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
<title>Insert title here</title>
</head>
<body>




<h1>유저 정보</h1>
${USER_INFO_VO.nickname}<br>
<br><br>


[4명]랭킹 미션 팔로워 그룹<br>

랭킹 : ${USER_RANK} 

미션 : ${USER_MISSION_POINT}

팔로워 : ${SELECT_USER_FOLLOW}

그룹 : ${NUMBER_OF_JOIN_GROUP}

<br><br>


차트
<c:forEach var="v" items="${CHART_RANK_LIST}">
        <tr>
            <td>${v}</td>
        </tr>
</c:forEach>


출석부 - 후순위, 종현


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