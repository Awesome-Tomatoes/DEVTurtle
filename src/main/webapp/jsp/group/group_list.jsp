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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/group/group_list.css">

</head>
<body>

<div id="group-list-container">
	<div class="group-mylist-title-div">
		<div class="group-mylist-main-title">
			
			<div id="group-mylist-main-title"> ${GROUP_DETAIL.name}님의 가입한 GROUP LIST</div>
		</div>
		<div class="group-mylist-sub-title">
			GROUP 생성하기	
			<button class="group-yes-btn">CREATE</button>
		</div>
	</div>


	<div class= "group-mylist-container-div">
			<div id="group-mylist-search-div">
				<p  class="group-search-p" >GROUP LIST</p>
				<div>
					<input class="group-search-input" type="text"  placeholder="그룹 이름을 입력하세요">
					<button class="group-search-btn">검색</button> 
				</div>
			</div>
			<div class="group-mylist-join-info-div">
				<div id="group-mylist-per-div">
					<div id="group-mylist-image">
						img
					</div>
					<div id="group-detail-simple-info-div">
						<p>1-1. group title</p>	
						<p>1-2. group point</p>					
					</div>
				</div>
				<div class="group-mylist-btn-div">
					<button class="group-yes-btn">이동</button> 
					<button class="group-no-btn">탈퇴</button> 
				</div>
			</div>
			<div class="group-mylist-join-info-div">
				<div id="group-mylist-per-div">
					<div id="group-mylist-image">
						img
					</div>
					<div id="group-detail-simple-info-div">
						<p>1-1. group title</p>	
						<p>1-2. group point</p>					
					</div>
				</div>
				<div class="group-mylist-btn-div">
					
					<button class="group-yes-btn">이동</button> 
					<button class="group-no-btn">탈퇴</button> 
				</div>
			</div>
			<div class="group-mylist-join-info-div">
				<div id="group-mylist-per-div">
					<div id="group-mylist-image">
						img
					</div>
					<div id="group-detail-simple-info-div">
						
						<p>1-1. group title</p>	
						<p>1-2. group point</p> 				
					</div>
				</div>
				<div class="group-mylist-btn-div">
					
					<button class="group-yes-btn">이동</button> 
					<button class="group-no-btn">탈퇴</button> 
				</div>
			</div>
			<div class="group-mylist-join-info-div">
				<div id="group-mylist-per-div">
					<div id="group-mylist-image">
						img
					</div>
					<div id="group-detail-simple-info-div">
						<p>1-1. group title</p>	
						<p>1-2. group point</p>					
					</div>
				</div>
				<div class="group-mylist-btn-div">
					
					<button class="group-yes-btn">이동</button> 
					<button class="group-no-btn">탈퇴</button> 
				</div>
			</div>
	</div>

</div>

</body>
</html>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
$( document ).ready(function() {
	//$("#btn").click( function() {  
	//    	$("#input").val();
	//});
});
</script>
