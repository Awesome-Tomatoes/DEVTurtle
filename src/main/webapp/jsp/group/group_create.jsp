<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/group/group_create.css">


</head>
<body>
	<div id="group-create-container-div">

		<div id="group-create-title-container-div">
			<h1>GROUP 생성</h1>
		</div>
		<div id="group-create-contents-container-div">
			<form class="group-create-form" id="group-create-form">

				<div class="group-input-div">
					<label for="group-name">그룹이름</label>
					<div class="group-create-input group-name-container">
						<input type="text" style="color: #cccccc" id="group-name"
							name="group-name" placeholder="그룹 이름을 입력하세요">
						<button type="button" class="group-name-check-btn"
							id="group-name-check-btn">중복 체크</button>
					</div>
				</div>

				<div class="group-input-div">
					<label for="group-category">카테고리</label> <select
						class="group-create-input" style="color: gray"
						name="group-category" id="group-category">
						<option style="color: gray" value="job-interview" selected>면접스터디</option>
						<option value="java">자바스터디</option>
						<option value="front">프론트스터디</option>
						<option value="code-review">코드리뷰</option>
						<!-- 프론트엔드 백엔드 풀스택 파이썬 자바  코드 리뷰  기술 공유 프론트엔드 -->
					</select>
				</div>

				<div class="group-input-div">
					<label for="group-size">최대인원</label> <input type="text"
						class="group-create-input" id="group-size" name="group-size"
						placeholder="그룹인원수를 입력하세요">
				</div>

				<!-- 공개여부 : css 안먹히는중  -->
				<div class="group-input-div" style="display: none">
					<label for="group-private">공개여부</label> <label> <input
						type="radio" name="group-private" value="N" checked
						style="back-groudwidth: 20px; height: 20px; margin-right: 10px;">공개
					</label> <label> <input type="radio" name="group-private" value="Y"
						style="width: 20px; height: 20px; margin-right: 10px;">
						비공개
					</label>

				</div>
				<div class="group-input-div">
					<label for="group-condition">참가조건</label> <input
						class="group-create-input" type="text" id="group-condition"
						name="group-condition" placeholder="최소 point">
				</div>
				<div class="group-input-div">
					<label for="group-rule">그룹규칙</label> <input
						class="group-create-input" type="text" id="group-rule"
						name="group-rule" placeholder="그룹규칙을 입력하세요">
				</div>

				<div class="group-input-div">
					<label for="group-description">그룹소개글</label> <input
						class="group-create-input" type="text" id="group-description"
						name="group-description" placeholder="그룹소개를 입력하세요">
				</div>

				<button type="button" class="group-create-submit-btn"
					id="group-create-submit-btn">GROUP 생성하기</button>
			</form>
		</div>

	</div>

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
		$(document).ready(
				function() {
					//$("#btn").click( function() {  
					//    	$("#input").val();
					//});

					$("#group-create-submit-btn")
							.click(
									function() {
										const groupName = $("#group-name")
												.val();
										const groupCategory = $(
												"#group-category").val();
										const groupSize = $("#group-size")
												.val();
										const groupCondition = $(
												"#group-condition").val();
										const groupRule = $("#group-rule")
												.val();
										const groupDescription = $(
												"#group-description").val();

										//const groupPrivate = $("#group-private").val();

										if (!groupName || !groupCategory
												|| !groupSize
												|| !groupCondition
												|| !groupRule
												|| !groupDescription) {
											alert(groupName + "  "
													+ groupCategory + "  "
													+ groupSize + "  "
													+ groupPrivate + "  "
													+ groupCondition + "  "
													+ groupRule + "  "
													+ groupDescription);

											alert(" 내용을 입력하시오.");
											return;
										}

										$("#group-create-form").attr("method",
												"post");
										$("#group-create-form").attr("action",
												"${pageContext.request.contextPath}/groupcreate");
										$("#group-create-form").submit();
									});
				});
	</script>


</body>
</html>