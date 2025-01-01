<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ include file="/jsp/layout/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>거부크님의 성과</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mission/mission_page.css">
</head>
<body>

<div class="table-container">
    <h2 class="table-title">[거부크]님의 달성한 과제</h2>
    <table>
        <thead>
            <tr>
                <th>Contents</th>
                <th>Points</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="mvo" items="${ULIST}">
                <tr>
                    <td>${mvo.contents}</td>
                    <td class="table-points">+${mvo.points}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<div class="table-container">
    <h2 class="table-title">[거부크] 님의 과제 히스토리</h2>
    <!-- 과제 히스토리 내용 추가 -->
</div>

<!-- <div class="table-container">
    	<h2 class="table-title">[거부크] 팀의 획득한 뱃지</h2>
</div> -->


<div class="table-container">
    <h2 class="table-title">[거부크] 팀의 수행 과제 통계</h2>
    <!-- 수행 과제 통계 내용 추가 -->
</div>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
$(document).ready(function() {
    // Example: Additional JavaScript functionality can go here
});
</script>

<%@ include file="/jsp/layout/footer.jsp" %>
</body>
</html>
