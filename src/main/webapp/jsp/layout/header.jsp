<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/layout/header.css"
    />
<header id="layout-header">
  <div id="header-logo">
    <img src="${pageContext.request.contextPath}/assets/turtle_grade/pirates_turtle.png" />
    <p>DevTurtle</p>
  </div>
  <div id="header-search">
    <input type="text" placeholder="사용자 또는 그룹을 검색해보세요!" />
    <img src="${pageContext.request.contextPath}/assets/layout/search.svg" />
  </div>
  <div id="header-auth">
    <button id="header-auth-mypage">MyPage</button>
    <button id="header-auth-logout">Logout</button>
  </div>
</header>