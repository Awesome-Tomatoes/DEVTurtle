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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/follow/follow_list.css">

</head>
<body>

<%
String userId = request.getParameter("USER_ID");
boolean isUserIdPresent = (userId == null);
System.out.println(userId);
%>
<div id="follow-list-container">
	<div class="follow-mylist-title-div">
		<div class="follow-mylist-main-title">		
			<div id="follow-mylist-main-title">${USER_NICK} 님의 FOLLOWER 목록</div>
		</div>
	</div>
	<div class= "follow-mylist-container-div">
       <!-- 팔로워 리스트 출력 -->
       <div class="follow-list-section">
           <div id="follow-mylist-container-div">
				<div id="follow-mylist-search-div">
					<div>
						<input class="follow-search-input" type="text"  placeholder="친구 이름을 입력하세요">
						<button class="follow-search-btn">검색</button> 
					</div>
				
				</div>
            	<c:forEach var="user" items="${FOLLOWER_LIST}"> 
					<div class="follow-mylist-join-info-div">
	                    <div id="follow-mylist-per-div">
	                        <img src="${pageContext.request.contextPath}/userImage?userid=${user.userID}" alt="${user.nickname}" class="follow-mylist-image">
	                        
	                        <div id="follow-detail-simple-info-div">
	                            <p class="follow_p_margin">${user.nickname}</p> <!-- user nickname -->
	                            <p class="follow_p_margin">점수: ${user.totalScore}</p> <!-- user total score -->
	                        </div>
	                    </div>
	                    <div class="follow-mylist-btn-div">
	                    	<a class="follow-detail-btn" href="${pageContext.request.contextPath}/mypage?=${user.userID}">상세</a>
							<c:if test="${USER_ID eq sessionScope.SESS_USER_ID}">
		                        	<button class="follow-no-btn">삭제</button>
		                    </c:if>
	                    </div>
        			</div>
        		</c:forEach>
       		</div>
   		</div>
	</div>
	<!-- -------------------------------------------------------- -->
	
	<div class="follow-mylist-title-div">
		<div class="follow-mylist-main-title">		
			<div id="follow-mylist-main-title">${USER_NICK} 님의 FOLLOWER 목록</div>
		</div>
	</div>
	<div class= "follow-mylist-container-div">
       <!-- 팔로워 리스트 출력 -->
       <div class="follow-list-section">
           <div id="follow-mylist-container-div">
				<div id="follow-mylist-search-div">
					<div>
						<input class="follow-search-input" type="text"  placeholder="친구 이름을 입력하세요">
						<button class="follow-search-btn">검색</button> 
					</div>
				
				</div>
	            <c:forEach var="user" items="${FOLLOWING_LIST}">
	                <div class="follow-mylist-join-info-div">
	                    <div id="follow-mylist-per-div">
	                        <img src="${pageContext.request.contextPath}/userImage?userid=${user.userID}" alt="${user.nickname}" class="follow-mylist-image">
	                        <div id="follow-detail-simple-info-div">
	                            <p>${user.nickname}</p>
	                            <p>점수: ${user.totalScore}</p>
	                        </div>
	                    </div>
	                    <div class="follow-mylist-btn-div">
	                    	<a class="follow-detail-btn" href="${pageContext.request.contextPath}/mypage?=${user.userID}">상세</a>
							<c:if test="${USER_ID eq sessionScope.SESS_USER_ID}">
		                        	<button class="follow-no-btn">삭제</button>
		                    </c:if>
	                    </div>
	                </div>
	            </c:forEach>
       		</div>
   		</div>
	</div>
	<!-- -------------------------------------------------------- -->
	<div class="follow-mylist-title-div">
		<div class="follow-mylist-main-title">		
			<div id="follow-mylist-main-title">${USER_NICK}님의 대기 목록</div>
		</div>
	</div>
	<div class= "follow-mylist-container-div">
       <!-- 팔로워 리스트 출력 -->
       <div class="follow-list-section">
           <div id="follow-mylist-container-div">
				<div id="follow-mylist-search-div">
					
					<div>
						<input class="follow-search-input" type="text"  placeholder="친구 이름을 입력하세요">
						<button class="follow-search-btn">검색</button> 
					</div>
				
				</div>
            	<c:forEach var="user" items="${WAIT_LIST}">
					<div id="follow-mylist-container-div" class="follow-mylist-join-info-div" >

		                <div class="follow-mylist-join-info-div">
		                    <div id="follow-mylist-per-div">
		                        <img src="${pageContext.request.contextPath}/userImage?userid=${user.userID}" alt="${user.nickname}" class="follow-mylist-image">
		                        <div id="follow-detail-simple-info-div">
		                            <p>${user.nickname}</p>
		                            <p>점수: ${user.totalScore}</p>
		                        </div>
		                    </div>
		                    <div class="follow-mylist-btn-div">
	                    	<a class="follow-detail-btn" href="${pageContext.request.contextPath}/mypage?=${user.userID}">상세</a>
		                    	<c:if test="${USER_ID eq sessionScope.SESS_USER_ID}">
		                        	<button class="follow-yes-btn">팔로우</button>
	                        		<button class="follow-no-btn">삭제</button>
		                    	</c:if>
		                    </div>
		                </div>
		              </div>
		         </c:forEach>
       		</div>
   		</div>
	</div>
</div>

</body>
</html>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
$( document ).ready(function() {
	
});


</script>
