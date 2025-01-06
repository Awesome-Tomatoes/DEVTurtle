<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Dev Turtle</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/index.css" />
<link rel="icon" href="${pageContext.request.contextPath}/assets/favicon/favicon.ico">
</head>
<body>
	<jsp:include page="./jsp/layout/header.jsp" />
	<% 
        String contentPage = (String) request.getAttribute("contentPage");
    %>
	<jsp:include page="<%= contentPage %>" />
	<jsp:include page="./jsp/layout/footer.jsp" />
</body>
</html>
