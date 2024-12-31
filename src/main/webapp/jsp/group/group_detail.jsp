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
그룹 상세소개  - 정하 
${GROUP_DETAIL.groupId}
${GROUP_DETAIL.name}

그룹원 소개 - 유저+ 그룹 <- 종현/정하
그룹랭킹차티 - 찬호 selectAllGroupRankOrderByDate
그룹업적 - 성관 // 넘겨줄 메서드는 ArrayList<Object> 그룹업적 리스트 전달
그룹채팅 - 찬호 // 구현예정

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