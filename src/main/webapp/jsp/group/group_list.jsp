<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%@ taglib prefix="c" 	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" 	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" 	uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="x" 	uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sql" 	uri="http://java.sun.com/jsp/jstl/sql" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/group/group_list.css">

</head>
<body>

<div id="group-list-container">
	<div class="group-mylist-title-div">
		<div class="group-mylist-main-title">
			
			<div id="group-mylist-main-title"> 
			 <%
		        // 파라미터에서 닉네임을 받으려고 시도
		        String userNickName = request.getParameter("userid");
		
		        // 만약 파라미터가 없다면, 세션에서 닉네임을 가져옴
		        if (userNickName == null || userNickName.isEmpty()) {
		            userNickName = (String) session.getAttribute("SESS_USER_NICKNAME");
		        }
		
		        // 닉네임이 존재하면 출력, 없으면 기본 메시지 출력
		        if (userNickName != null && !userNickName.isEmpty()) {
		            out.println("[ " + userNickName + " ]님의 가입한 GROUP LIST");
		        } else {
		            out.println("닉네임 정보가 없습니다.");
		        }
		    %>
			</div>
		</div>
		
		<% 
			String userIdParam = request.getParameter("userid");
		%>
		<% if (userIdParam == null || userIdParam.isEmpty()) { %>
		    <div class="group-mylist-sub-title" id="group-btn-detail-info-div">
		        GROUP 생성하기    
		        <a class="group-yes-btn" id="create-group-btn" href="${pageContext.request.contextPath}/groupcreate">CREATE</a>
		    </div>
		<% } %>

		
	</div>


	<div class= "group-mylist-container-div">
			<div id="group-mylist-search-div">
				<p  class="group-search-p" >GROUP LIST</p>
				<% if (userIdParam == null || userIdParam.isEmpty()) { %>	 
				<div>
					<input class="group-search-input" type="text"  placeholder="그룹 이름을 입력하세요">
					<button class="group-search-btn">검색</button> 
				</div>
				<% } %>
			</div>
			<!-- <div class="group-mylist-join-info-div">
				<div id="group-mylist-per-div">
					<div id="group-mylist-image">
						img
					</div>
					<div id="group-detail-simple-info-div">
						<p>1-1. group title</p>	
						<p>1-2. group point</p>					
					</div>
				</div>
				<div class="group-mylist-btn-div">
					<button class="group-yes-btn">이동</button> 
					<button class="group-no-btn">탈퇴</button> 
				</div>
				
			</div> -->
			<div id="group-mylist-ajax-div">
			
			</div>
	</div>

</div>

</body>
</html>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script>

$( document ).ready(function() {
	loadGroupList(); 
	//paramDisableBtn();
});

/* function paramDisableBtn() {
	var groupUserCheck = document.getElementById("group-detail-info-div").getAttribute("data-group-user-check");

	groupUserCheck = (groupUserCheck === 'true');

	const createGroupButton = document.getElementById("create-group-btn");


	if (!groupUserCheck) {
	    createGroupButton.style.display = "none"; 
	} else {
	    createGroupButton.style.display = "inline"; 
	}
} */

function loadGroupList() {
	<%
    //String userIdParam = request.getParameter("userid");
    Integer userid = null;
    if (userIdParam != null && !userIdParam.isEmpty()) {
        userid = Integer.parseInt(userIdParam);
    } else {
        userid = (Integer) session.getAttribute("SESS_USER_ID");
        if (userid == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
    }
    %>
    
	$.ajax({
	        type: "GET",  
	        url: "/grouplist?userid=<%=userid%>" ,  
	        data: { type: "json"},  
	        dataType: "JSON", 
	        success: function(data) {
	            if (data) {
	                $('#group-mylist-ajax-div').empty(); 
					
	                
	                data.forEach(function(group) {
	                	var shouldAddButton = "<%= (userIdParam == null || userIdParam.isEmpty()) ? "true" : "false" %>";
	                	var groupDiv = 
	                        '<div class="group-mylist-join-info-div" id="group-' + group.groupId + '">' +
	                            '<div id="group-mylist-per-div">' +
	                                '<img id="group-mylist-image" src="${pageContext.request.contextPath}/groupImage?groupid=' + group.groupId + '" />' +
	                                '<div id="group-detail-simple-info-div">' +
	                                    '<p> [ ' + group.name + ' ]\'s GROUP' + '</p>' +
	                                    '<p> No. ' + group.rankScore + ' / ' + group.totalScore + 'p </p>' +
	                                '</div>' +
	                            '</div>' +
	                            '<div class="group-mylist-btn-div">'+
	                            	'<a class="group-yes-btn" href="${pageContext.request.contextPath}/groupdetail?groupid=' + group.groupId + '">이동</a>'
                    			;	                    
                    if (shouldAddButton === 'true') {
                        groupDiv += 
                            		'<button class="group-no-btn" onclick="leaveGroup(\'' + group.groupId + '\')">탈퇴</button>';
                    }
	                    groupDiv += 
	                            '</div>' +
	                        '</div>';
	                        
	                    $('#group-mylist-ajax-div').append(groupDiv);  // div 추가
	                });
	            } else {
	                alert("그룹 목록을 불러오는 데 실패했습니다.");
	            }
	        },
	        error: function(xhr, status, error) {
	            console.error("에러 발생: " + error);
	            alert("서버 요청에 실패했습니다.");
	        }
	    });   
}

function leaveGroup(groupId) {
	$.ajax({
	    type: "POST",  
	    url: "/groupdelete",  
	    data: { groupId: groupId },
	    dataType: "JSON", 
	    success: function(response) {
	        if (response.success) {
	            alert("그룹에서 탈퇴했습니다.");
	            $("#group-" + groupId).remove(); 
	            loadGroupList(); 
	        } else {
	            alert("그룹 탈퇴에 실패했습니다. 다시 시도해주세요.");
	        }
	        
	    },
	    error: function(xhr, status, error) {
            console.error("에러 발생: " + error);
            alert("서버 요청에 실패했습니다.");
        }
	});
}

</script>
