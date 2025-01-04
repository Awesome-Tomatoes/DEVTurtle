<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="x" 	uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sql" 	uri="http://java.sun.com/jsp/jstl/sql" %>    
<%-- <%@ include file="/jsp/layout/header.jsp" %> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/rank/rank.css">
</head>
<body>

<h1>그룹 랭킹 정보</h1>
    <div class="container">
        <c:forEach var="gvo" items="${GLIST}">
            <div class="card">
                <div class="card-header">
                    <h2> ${gvo.rank} ${gvo.name}</h2>
                    <button class="follow-btn">Join</button>
                </div>
                <div class="card-content">
                    <p><strong>그룹 소개:</strong> ${gvo.description}</p>
                    <p><strong>전체 점수:</strong> ${gvo.totalScore}</p>
                </div>
                <div class="card-footer">
                    <p><strong>POINT:</strong> ${gvo.totalScore}</p>
                </div>
            </div>
        </c:forEach>
    </div>

${MY_KEY_PAGING_HTML}

<%-- <%@ include file="/jsp/layout/footer.jsp" %> --%>



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