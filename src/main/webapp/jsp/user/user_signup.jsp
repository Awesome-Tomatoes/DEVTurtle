<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원가입 페이지</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/user/user_login.css">
</head>
<body>
	<div class="container">
		<div class="logo" id="signup-logo">
			<a href="/main"> 
	<!-- 		<img src="${pageContext.request.contextPath}/assets/rabTuttle.webp" alt="로고 이미지">-->
				<h1>DEVTurtle</h1>
			</a>
		</div>
		<form class="signup-form" id="signup-form">
			<label for="loginid">아이디</label>
			<input type="text" id="loginid" name="loginid" placeholder="아이디를 입력하세요">
			<div class="input-container">
				<button type="button" class="check-btn" id="check-loginid">중복 체크</button>
			</div>
			
			<label for="password">비밀번호</label> 
			<input type="password" id="password" name="password" placeholder="비밀번호를 입력하세요"> 
			<label for="username">이름</label>
			<input type="text" id="username" name="username" placeholder="닉네임을 입력하세요"> 
			<label for="nickname">닉네임</label>
			<input type="text" id="nickname" name="nickname" placeholder="닉네임을 입력하세요"> 
			<label for="gitname">Git Name</label> 
			<input type="text" id="gitname" name="gitname" placeholder="Git 이름을 입력하세요"> 
			<label for="sorname">Sorved.ac Name</label> 
			<input type="text" id="sorname" name="sorname" placeholder="Sorved.ac 이름을 입력하세요">
			<button type="button" class="signup-btn" id="signup-btn">회원가입</button>
		</form>

	</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	$(document).ready(function() {
		$("#signup-btn").click(function() {
			$("#signup-form").attr("method", "post");
			$("#signup-form").attr("action", "/signup");
			$("#signup-form").submit();
		});
	});
</script>

</html>



