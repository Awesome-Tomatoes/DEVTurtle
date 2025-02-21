<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="x" 	uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sql" 	uri="http://java.sun.com/jsp/jstl/sql" %>   
 
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>에러 페이지</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/error/error_page.css">
</head>
<body>
	<div class = "errordiv">
    	<h1>앗! 이런...</h1>
    	<br>
    	<h2>404 NOT FOUND</h2>
    		<img src = "${pageContext.request.contextPath}/assets/error/error_turtle404.png" width = "300" height = "300">
    		<p>요청하신 페이지는 없는 페이지입니다.</p>
    		<br>
    	<a href="${pageContext.request.contextPath}/main">메인으로 돌아가기</a>
    </div>
    
<%@ include file="/jsp/layout/footer.jsp" %>
</body>	
</html>
