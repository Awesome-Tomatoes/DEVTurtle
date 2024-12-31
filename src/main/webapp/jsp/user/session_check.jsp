<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty sessionScope.SESS_USER_ID}">
	<script>
		alert("로그인 후 이용하세요");
		location.href = "${pageContext.request.contextPath}/login";
	</script>
</c:if>