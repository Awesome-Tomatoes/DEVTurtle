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

<div id="follow-list-container">
	<div class="follow-mylist-title-div">
		<div class="follow-mylist-main-title">
			
			<div id="follow-mylist-main-title"> 님의 가입한 follow LIST</div>
		</div>
	</div>


	<div class= "follow-mylist-container-div">
			<div id="follow-mylist-search-div">
				<p  class="follow-search-p" >follow LIST</p>
				<div>
					<input class="follow-search-input" type="text"  placeholder="친구 이름을 입력하세요">
					<button class="follow-search-btn">검색</button> 
				</div>
			</div>
			<div class="follow-mylist-join-info-div">
				<div id="follow-mylist-per-div">
					<div id="follow-mylist-image">
						img
					</div>
					<div id="follow-detail-simple-info-div">
						<p>1-1. follow title</p>	
						<p>1-2. follow point</p>					
					</div>
				</div>
				<div class="follow-mylist-btn-div">
					<button class="follow-no-btn">삭제</button> 
				</div>
			</div>
			<div id="follow-mylist-container-div" class="follow-mylist-join-info-div" >
			
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
