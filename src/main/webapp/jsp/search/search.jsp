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

    <%
    String query = request.getParameter("query");
    if (query == null) {
        query = ""; // query가 null일 경우 빈 문자열로 초기화
    }
	%>

    
<div class="search-container">
    <h1 class="search-title">사용자</h1>
    <ul class="search-list">
        <c:choose>
            <c:when test="${empty FLIST && empty ULIST}">
                <li class="search-item">검색된 사용자가 없습니다.</li>
            </c:when>
            <c:otherwise>
                <c:forEach var="uvo" items="${FLIST}">
                    <li class="search-item">
                        <div class="search-item-container">
                            <img src="${pageContext.request.contextPath}/userImage?userid=${uvo.userID}" alt="${uvo.userName}" class="user-avatar"> 
                            <div class="search-info">
                                <a href="/mypage?userid=${uvo.userID}">이름 : ${uvo.userName}</a>
                                <p>Point ${uvo.totalScore}p</p>
                            </div>
                        </div>
                        <a class="unfollow-btn" href="/followDelete?userid=${sessionScope.SESS_USER_ID}&deleteid=${uvo.userID}">언팔로우</a>
                    </li>
                </c:forEach>

                <c:forEach var="uvo" items="${ULIST}">
                    <li class="search-item">
                        <div class="search-item-container">
                            <img src="${pageContext.request.contextPath}/userImage?userid=${uvo.userID}" alt="${uvo.userName}" class="user-avatar"> 
                            <div class="search-info">
                                <a href="/mypage?userid=${uvo.userID}">이름 : ${uvo.userName}</a>
                                <p>Point ${uvo.totalScore}p </p>
                            </div>
                        </div>
                        <a class="follow-btn" href="/followFollow?userid=${sessionScope.SESS_USER_ID}&followid=${uvo.userID}">팔로우</a>
                    </li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

<div class="search-container">
    <h1 class="search-title">그룹</h1>
    <ul class="search-list">
        <c:choose>
            <c:when test="${empty GLIST}">
                <li class="search-item">검색된 그룹이 없습니다.</li>
            </c:when>
            <c:otherwise>
                <c:forEach var="gvo" items="${GLIST}">
                    <li class="search-item">
                        <c:if test="${gvo.join}">
	                        <div class="search-item-container">
	                            	<img class="user-avatar" src="${pageContext.request.contextPath}/groupImage?groupid=${gvo.groupId}"/>
	                            	<div class="search-info">
		                                <a href="/groupdetail?groupid=${gvo.groupId}">${gvo.name}</a>
		                                <p>그룹 Point ${gvo.totalScore}p</p>
	                            	</div>
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
                        <div class="search-item-container">
	                            	<img class="user-avatar" src="${pageContext.request.contextPath}/groupImage?groupid=${gvo.groupId}"/>
		                            <div class="search-info">
		                                <a href="/groupdetail?groupid=${gvo.groupId}">${gvo.name}</a>
		                                <p>그룹 Point ${gvo.totalScore}p</p>
		                            </div>
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
            </c:otherwise>
        </c:choose>
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
