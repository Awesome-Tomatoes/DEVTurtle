<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/search/search.css">
</head>
<body>

    <h1>검색 페이지</h1>
    
    <%
    String query = request.getParameter("query");
    if (query == null) {
        query = ""; // query가 null일 경우 빈 문자열로 초기화
    }
	%>
	

    
<div class="user-container">
    <h3>사용자</h3>
    <ul class="user-list">
    
    	<c:forEach var="uvo" items="${FLIST}">
            <li class="user-item">
                <img src="${pageContext.request.contextPath}/userImage?userid=${uvo.userID}" alt="${uvo.userName}" class="user-avatar"> 
                <div class="user-info">
                    <a href="/mypage?userid=${uvo.userID}">${uvo.userName}</a>
                    <p>랭킹 Point: ${uvo.totalScore}</p>
                </div>
					
                	<a class="follow-btn" href="/followDelete?userid=${sessionScope.SESS_USER_ID}&deleteid=${uvo.userID}">삭제</a>
            </li>
        </c:forEach>
    
        <c:forEach var="uvo" items="${ULIST}">
            <li class="user-item">
                <img src="${pageContext.request.contextPath}/userImage?userid=${uvo.userID}" alt="${uvo.userName}" class="user-avatar"> 
                <div class="user-info">
                	<a href="/mypage?userid=${uvo.userID}">${uvo.userName}</a>
                    <p>랭킹 Point: ${uvo.totalScore}</p>
                </div>
                <a href="/followFollow?userid=${sessionScope.SESS_USER_ID}&followid=${uvo.userID}">팔로우</a>
            </li>
        </c:forEach>
    </ul>
</div>

<div class="group-container">
    <h3>그룹</h3>
    <ul class="group-list">
        <c:forEach var="gvo" items="${GLIST}">
            <li class="group-item">
                <c:if test="${gvo.join}">
                <div class="group-info">
                    <a href="/groupdetail?groupid=${gvo.groupId}">${gvo.name}</a>
                   
                    <p>그룹 Point: ${gvo.totalScore}</p>
                </div>
                    <form id="unjoinForm_${gvo.groupId}" method="POST" action="${pageContext.request.contextPath}/groupdelete">
                        <input type="hidden" name="groupId" value="${gvo.groupId}">
                        <input type="hidden" name="search" value="search">
                        <button type="button" class="unjoin-btn">
                            그룹 탈퇴
                        </button>
                    </form>
                </c:if>

                <c:if test="${not gvo.join}">
                <div class="group-info">
                    <a href="/groupdetail?groupid=${gvo.groupId}">${gvo.name}</a>
                    <p>그룹 Point: ${gvo.totalScore}</p>
                </div>
                    <form id="joinForm_${gvo.groupId}" method="POST" action="${pageContext.request.contextPath}/groupAdd">
                        <input type="hidden" name="groupId" value="${gvo.groupId}">
                        <input type="hidden" name="search" value="search">
                        <button type="button" class="join-btn">
                            그룹 참여
                        </button>
                    </form>
                </c:if> 

            </li>
        </c:forEach>
    </ul>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
    $(document).ready(function () {
        const query = "<%= query %>";
        $("#searchForm").val(query);

        $("#searchBtn").click(function() {
            $("#header-search").attr("method", "get");
            $("#header-search").attr("action", "${pageContext.request.contextPath}/search");
            $("#query").val($("#searchForm").val());
            $("#header-search").submit();
        });

        <c:forEach var="gvo" items="${GLIST}">
            $("#joinForm_${gvo.groupId}").click(function() {
                $(this).attr("method", "post");
                $(this).attr("action", "${pageContext.request.contextPath}/groupAdd");
                $(this).submit();
            });

            $("#unjoinForm_${gvo.groupId}").click(function() {
                $(this).attr("method", "post");
                $(this).submit();
            });
        </c:forEach>
    });
</script>
</body>
</html>
