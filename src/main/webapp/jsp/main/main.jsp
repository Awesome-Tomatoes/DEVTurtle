<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.devturtle.user.UserRankText" %>

<link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/main/main_user_info.css"
    />
<link
      rel="stylesheet"
      type="text/css"
      href="${pageContext.request.contextPath}/css/main/main_ranking_list.css"
    />


<div id="main-container">
<article class="contents__article">
	<%
        Integer userId = (Integer) session.getAttribute("SESS_USER_ID");
        if (userId == null) {
    %>
    <p id="login-induce">DevTurtle에 로그인하면 내 <strong>개발자 취업 준비 랭킹</strong>을 확인할 수 있어요 😊</p>
    <%
        } else {
    %>
	<article class="contents__article--fragment" id="contents__article--fragment-ranking">
		<div id="user-ranking-info">
			<img id="user-ranking-info__img" src="${pageContext.request.contextPath}/userImage?userid=${USER_INFO.userID}"/>
			<div id="user-ranking-info__divs">
				<p class="user-ranking-info__p">
					<img src="${pageContext.request.contextPath}/assets/main/grade-star.svg" />
					<span id="user-ranking-info__grade" >${USER_RANK_CODE}</span>
				</p>
				<p class="user-ranking-info__p">
					<span class="user-ranking-info__p--title">닉네임</span>
					<span class="user-ranking-info__p--desc">${USER_INFO.nickname}</span>		
				</p>
				<p class="user-ranking-info__p">
					<span class="user-ranking-info__p--title">랭킹포인트</span>
					<span class="user-ranking-info__p--desc">${USER_INFO.totalScore}</span>		
				</p>
				<p class="user-ranking-info__p">
					<span class="user-ranking-info__p--title">내 랭킹</span>
					<span class="user-ranking-info__p--desc">${USER_RANKING.rank} rd / ${USER_COUNT}</span>		
				</p>
			</div>
		</div>
		<div id="user-ranking-info__point-bar">
			<p id="next-point">다음 등급까지 ${USER_REMAINING_SCORE}점</p>
			<div id="point-bar__fragment">
				<div id="point-bar__back"></div>
				<div id="point-bar__front" style="width: ${USER_REMAINING_PERCENT}%;"></div>
			</div>
		</div>
	</article>
	<article class="contents__article--fragment" id="contents__article--fragment-basic">
		<div id="user-basic-info">
			<span id="user-basic-info__name">${USER_INFO.userName}</span>
			<p id="user-basic-info__link">
				<a href="https://github.com/${USER_INFO.gitID}" target="_blank">
					<img src="${pageContext.request.contextPath}/assets/main/github.svg" />
				</a>
				<a href="https://solved.ac/profile/${USER_INFO.solvedID}" target="_blank">
					<img src="${pageContext.request.contextPath}/assets/main/solvedac.svg"/>
				</a>
			</p>
		</div>
		<div id="user-basic-info__bio">
			${USER_INFO.userBio}
		</div>
		<div id="user-basic-info__group-header">
			<span id="user-basic-info__group-header-title">${USER_INFO.userName}’s GROUP</span>
			<a class="see-more-btn" href="${pageContext.request.contextPath}/grouplist">더 보기</a>
		</div>
		<ul id="user-basic-info__group-ul">
		<c:forEach var="glist" items="${USER_GROUP_LIST}" begin="0" end="2">
			<li class="user-basic-info__group-li">
				<span class="user-basic-info__group-li-title">${glist.name}</span>
				<span class="user-basic-info__group-li-score">
					<img class="user-basic-info__group-li-icon" src="${pageContext.request.contextPath}/assets/main/ranking.svg"/>
					<span class="user-basic-info__group-li-point">${glist.rankScore} / ${ALL_GROUP_SIZE}</span>
				</span>
			</li>
		</c:forEach>
		</ul>
	</article>
	<%
        }
    %>
</article>

<article class="contents__article">
	<article class="ranking__article--fragment">
		<div class="ranking__header--div">
			<h1 class="ranking__header--title">
			👑 이번 달 그룹랭킹
			</h1>
			<a class="see-more-btn" href="${pageContext.request.contextPath}/rankGroup">더 보기</a>
		</div>
		<ul class="ranking__ul">
			<c:forEach var="grlist" items="${GROUP_RANK_LIST}" varStatus="status">
				<li class="ranking__li">
					<div class="ranking__li--div">
						<span class="ranking__li--rank">${status.index + 1}위</span>
						<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_badge/attendance_badge.png"/>
						<p class="ranking__li--desc">
							<span class="ranking__li--desc-title">${grlist.name}</span>
							<span>${grlist.totalScore}p</span>
						</p>
					</div>
					<button class="see-more-btn">상세</button>
				</li>
			</c:forEach>
		</ul>

	</article>
	<article class="ranking__article--fragment">
		<div class="ranking__header--div">
			<h1 class="ranking__header--title">
			👑 이번 달 사용자랭킹
			</h1>
			<a class="see-more-btn" href="${pageContext.request.contextPath}/rankUser">더 보기</a>
		</div>
		<ul class="ranking__ul">
			<c:forEach var="urlist" items="${USER_RANK_LIST}" varStatus="status">
				<li class="ranking__li">
					<div class="ranking__li--div">
						<span class="ranking__li--rank">${status.index + 1}위</span>
						<img class="ranking__li--img" src="${pageContext.request.contextPath}/userImage?userid=${urlist.userID}"/>
						<p class="ranking__li--desc">
							<span class="ranking__li--desc-title">${urlist.userName}</span>
							<span>${urlist.totalScore}p</span>
						</p>
					</div>
					<button class="see-more-btn">상세</button>
				</li>
			</c:forEach>
		</ul>

	</article>
	
</article>

</div>
