<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/jsp/layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rank/rank.css">
</head>
<body>
    <%@ include file="/jsp/layout/footer.jsp" %>

    <h1>검색 페이지</h1>
    <div class="results">
        <div id="user-results">
            <h2>Users</h2>
            <ul></ul>
        </div>
        <div id="group-results">
            <h2>Groups</h2>
            <ul></ul> 
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <script>
        $(document).ready(function () {
        	$(document).on('keydown', "#search",function(){
                const query = $(this).val();
                console.log(query);

                if (query == null) {
                    $('#user-results ul').empty();
                    $('#group-results ul').empty();
                    return;
                }

                else{
                	$.ajax({
                        url: '/DevTurtle/search',
                        method: 'GET',
                        data: "query=" + query ,
                        dataType: 'json', // JSON으로 파싱 설정
                        success 	: function(response) {
                        	console.log(response); // 서버 응답 확인용
                            $('#user-results ul').empty();
                            $('#group-results ul').empty();

                            if (response.users.length > 0) {
                                response.users.forEach(user => {
                                    $('#user-results ul').append(
                                    	test
                                        //<li>${user.nickname} - ${user.totalScore} points</li>
                                    );
                                });
                            } 
                            
                            else {
                                $('#user-results ul').append('<li>No users found</li>');
                            }

                            if (response.groups.length > 0) {
                                response.groups.forEach(group => {
                                    $('#group-results ul').append(
                                    		tgroup
                                        //<li>${group.name} - ${group.totalScore} points</li>
                                    );
                                });
                            } 
                            
                            else {
                                $('#group-results ul').append('<li>No groups found</li>');
                            }
                        },
                        error: function (err) {
                            console.error('검색 실패', err);
                        }
                	}); 
                }
            });
        });
    </script>
</body>
</html>
