<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>유저 정보</h1>
사용자정보, 그룹랭킨 [찬호]<br>

<c:forEach var="uvo" items="${ULIST}">
        <tr>
            <td>${uvo.nickname}</td>
            <td>${uvo.totalScore}</td>
            <td>${uvo.userName}</td>
            <td>${uvo.userBio}</td>
        </tr>
</c:forEach>

<h1>사용자 랭킹</h1>
 [User lank 조인]<br>



<h1>그룹 랭킹</h1>
 [정하]<br>
<c:forEach var="uvo" items="${GLIST}">
        <tr>
            <td>${uvo.name}</td>
            <td>${uvo.totalScore}</td>
            <td>${uvo.rankScore}</td>
        </tr>
</c:forEach>
