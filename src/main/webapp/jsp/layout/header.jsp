<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/layout/header.css" />
<header id="layout-header">
	<a href="${pageContext.request.contextPath}/main" id="header-logo">
		<button id="header-logo">
			<img
				src="${pageContext.request.contextPath}/assets/turtle_grade/pirates_turtle.png" />
			<p>DevTurtle</p>
		</button>
	</a>
	<div id="header-search">
		<input type="text" placeholder="사용자 또는 그룹을 검색해보세요!" /> <img
			src="${pageContext.request.contextPath}/assets/layout/search.svg" />
	</div>
	<div id="header-auth">
		<%
        Integer userId = (Integer) session.getAttribute("SESS_USER_ID");

        if (userId == null) {
    	%>
		<a href="${pageContext.request.contextPath}/login" class="header-auth-a">Login</a>
		<%
        } else {
    	%>
		<a href="${pageContext.request.contextPath}/mypage" class="header-auth-a">MyPage</a> 
		<a href="${pageContext.request.contextPath}/logout" id="header-auth-logout">Logout</a>
		<%
        }
    %>

	</div>
</header>