<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rank/rank.css">
</head>
<body>

    <h1>검색 페이지</h1>
    
    <div class="container">
        <c:forEach var="uvo" items="${ULIST}">
            <div class="card">
                <div class="card-header">
                    <h2> ${uvo.userName}</h2>
                    <button class="follow-btn">Follow</button>
                </div>
                <div class="card-content">
                    <p><strong>전체 점수:</strong> ${uvo.totalScore}</p>
                </div>
            </div>
        </c:forEach>
    </div>
    
    <div class="container">
        <c:forEach var="gvo" items="${GLIST}">
            <div class="card">
                <div class="card-header">
                    <h2>  ${gvo.name}</h2>
                    <button class="follow-btn">Join</button>
                </div>
                <div class="card-content">
                    <p><strong>전체 점수:</strong> ${gvo.totalScore}</p>
                </div>
            </div>
        </c:forEach>
    </div>
    
    
    
    <div class="results">
        <div id="user-results">
            <h2>Users</h2>
            <ul></ul>
        </div>
        <div id="group-results">
            <h2>Groups</h2>
            <ul></ul> 
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script>
        $(document).ready(function () {
        	$("#searchBtn").click( function() {  
        		$("#header-search").attr("method", "get");
    	    	$("#header-search").attr("action", "${pageContext.request.contextPath}/search");
    	    	$("#query").val($("#searchForm").val());
    	    	$("#header-search").submit();
    	    } );
        });
    </script>
</body>
</html>
