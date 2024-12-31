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
		<div class="logo" id="signin-logo">
			<a href="index.html"> <img
				src="${pageContext.request.contextPath}/assets/rabTuttle.webp"
				alt="로고 이미지">
				<h1>DEVTurtle</h1>
			</a>
		</div>
		<form class="login-form">
			<label for="userid">아이디</label>
			<div class="input-container">
				<input type="text" id="userid" name="userid"
					placeholder="아이디를 입력하세요">
				<button type="button" class="check-btn" id="check-username">중복
					체크</button>
			</div>
			<label for="password">비밀번호</label> <input type="password"
				id="password" name="password" placeholder="비밀번호를 입력하세요"> <label
				for="username">이름</label> <input type="text" id="username"
				name="name" placeholder="이름을 입력하세요"> <label for="nickname">닉네임</label>
			<input type="text" id="nickname" name="nickname"
				placeholder="닉네임을 입력하세요"> <label for="gitname">Git
				Name</label> <input type="text" id="gitname" name="gitname"
				placeholder="Git 이름을 입력하세요"> <label for="sorname">Sorved.ac
				Name</label> <input type="text" id="sorname" name="sorname"
				placeholder="Sorved.ac 이름을 입력하세요">
			<button type="submit" class="login-btn" id="signin-btn">회원가입</button>
		</form>

	</div>
</body>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
  <script>
	$(document).ready(function() {

		$("#signin-btn").click(function(event) {
		    event.preventDefault(); // 기본 폼 제출 동작 방지
		    userid = $("#userid").val();
		    password = $("#password").val();
		    nickname = $("#nickname").val();
		    username = $("#username").val();
		    gitname = $("#gitname").val();
		    sorname = $("#sorname").val();
		    jsonObj = {
		        "userid": userid,
		        "password": password,
		        "nickname": nickname,
		        "username": username,
		        "gitname": gitname,
		        "sorname": sorname
		    };
		    console.log(jsonObj);
		    jsonStr = JSON.stringify(jsonObj);
		    console.log(jsonStr);
		    $.ajax({
		        url: "${pageContext.request.contextPath}/user",
		        method: 'POST',
		        data: jsonStr,
		        contentType: "application/json; charset=UTF-8",
		        success: function(res) {
		            console.log("응답:" + res);
		            
		        },
		        error: function(err) {
		            console.log("에러:" + err);
		        }
		    });
		});

	});
</script> 

</html>



