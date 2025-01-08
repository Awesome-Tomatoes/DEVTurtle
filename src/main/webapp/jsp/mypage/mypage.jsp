<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="https://d3js.org/d3.v7.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mypage/mypage_user_info.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mypage/mypage_info_list.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mypage/mypage_ranking_point_chart.css" />

<%
    String userId = request.getParameter("userid");
    boolean isUserIdPresent = (userId != null && !userId.isEmpty());
%>

<div id="mypage-container">
	<article class="contents__article">
		<article class="contents__article--fragment"
			id="contents__article--fragment-ranking">
			<div id="user-ranking-info">
				<p class="user-ranking-info__p">
					<img
						src="${pageContext.request.contextPath}/assets/main/grade-star.svg" />
					<span id="user-ranking-info__grade">${USER_RANK_CODE}</span>
				</p>
				<img id="user-ranking-info__img"
					src="${pageContext.request.contextPath}/userImage?userid=${USER_INFO.userID}" />
				<p class="user-ranking-info__p">
					<span class="user-ranking-info__p--title">내 랭킹 포인트</span> <span
						class="user-ranking-info__p--point">${USER_INFO.totalScore}p</span>
				</p>
				<ul id="user-ranking-info__ul">
					<li class="user-ranking-info__li">
						<p class="user-ranking-info__p">커밋</p>
						<p class="user-ranking-info__p">${USER_INFO.gitScore}p</p>
					</li>
					<!-- <li class="user-ranking-info__li">
						<p class="user-ranking-info__p">PR</p>
						<p class="user-ranking-info__p">300p</p>
					</li> -->
					<li class="user-ranking-info__li">
						<p class="user-ranking-info__p">코테</p>
						<p class="user-ranking-info__p">${USER_INFO.solvedScore}p</p>
					</li>
					<!-- <li class="user-ranking-info__li">
						<p class="user-ranking-info__p">출석</p>
						<p class="user-ranking-info__p">30p</p>
					</li>  -->
					<li class="user-ranking-info__li">
						<p class="user-ranking-info__p">미션</p>
						<p class="user-ranking-info__p">${USER_MISSION_SCORE}p</p>
					</li>
				</ul>

				<div id="user-ranking-info__point-bar">
					<p id="next-point">다음 등급까지 ${USER_REMAINING_SCORE}점</p>
					<div id="point-bar__fragment">
						<div id="point-bar__back"></div>
						<div id="point-bar__front" style="width: ${USER_REMAINING_PERCENT}%;"></div>
					</div>
				</div>
			</div>
		</article>
		<article class="contents__article--fragment"
			id="contents__article--fragment-basic">
			<form class="user-basic-info__form" id="update-nickname-form">
				<label>닉네임</label> 
				<input type="text" value="${USER_INFO.nickname}" name="nickname"  <%= isUserIdPresent ? "disabled" : "" %>/>
				<input type="hidden" name="actionType" value="updateNickname" />
				 <% if (!isUserIdPresent) { %>
					<button type="button" class="update-btn" id="update-nickname-btn">수정</button>
				 <% } %>
			</form>
			<form class="user-basic-info__form">
				<label>Github</label> 
				<input type="text" value="${USER_INFO.gitID}" disabled/> 
				<a href="https://github.com/${USER_INFO.gitID}" target="_blank">
					<img src="${pageContext.request.contextPath}/assets/main/github.svg" />
				</a>
			</form>
			<form class="user-basic-info__form">
				<label>solved.ac</label> 
				<input type="text" value="${USER_INFO.solvedID}" disabled/> 
				<a href="https://solved.ac/profile/${USER_INFO.solvedID}" target="_blank">
					<img src="${pageContext.request.contextPath}/assets/main/solvedac.svg" />
				</a>
			</form>
			<form class="user-basic-info__bio" id="update-bio-form">
				<div id="user-basic-info__bio-header">
					<label>자기소개</label>
					<% if (!isUserIdPresent) { %>
						<button type="button" class="update-btn" id="update-bio-btn">수정</button>
					<% } %>
					<input type="hidden" name="actionType" value="updateBio" />
				</div>
				<textarea name="bio" <%= isUserIdPresent ? "disabled" : "" %>>${USER_INFO.userBio}</textarea>
			</form>
		</article>
	</article>

	<div id="user-info-list">
		<a class="user-info-list__btn" href="${pageContext.request.contextPath}/rankUser">
			<button>
				<div class="user-info-list__title">
					<img
						src="${pageContext.request.contextPath}/assets/main/ranking.svg" />
					<p id="user-info-list__ranking-desc">랭킹</p>
				</div>
				<p>${USER_RANKING.rank} rd / ${USER_COUNT}</p>
			</button>
		</a>
		<a class="user-info-list__btn" href="${pageContext.request.contextPath}/missionPersonal?userid=${USER_INFO.userID}">
			<button>
				<div class="user-info-list__title">
					<img
						src="${pageContext.request.contextPath}/assets/main/misson.svg" />
					<p>미션포인트</p>
				</div>
				<p>${USER_MISSION_SCORE}p</p>
			</button>
		</a>
		<% if (!isUserIdPresent) { %>
			<a class="user-info-list__btn" href="${pageContext.request.contextPath}/follow">
		<% } else {%>
			<a class="user-info-list__btn" href="${pageContext.request.contextPath}/follow?userid=${USER_INFO.userID}">
		<% } %>
			<button>
				<div class="user-info-list__title">
					<img
						src="${pageContext.request.contextPath}/assets/main/follower.svg" />
					<p>팔로워</p>
				</div>
				<p>${FOLLOWER_COUNT}</p>
			</button>
		</a>
		<% if (!isUserIdPresent) { %>
			<a class="user-info-list__btn" href="${pageContext.request.contextPath}/grouplist">
		<% } else {%>
			<a class="user-info-list__btn" href="${pageContext.request.contextPath}/grouplist?userid=${USER_INFO.userID}">
		<% } %>
			<button>
				<div class="user-info-list__title">
					<img
						src="${pageContext.request.contextPath}/assets/main/group.svg" />
					<p>그룹</p>
				</div>
				<p>${GROUP_COUNT}</p>
			</button>
		</a>
	</div>
	
	<h1 id="ranking-point-chart__title">
		<span>내 랭킹 포인트</span>
		<span>현재 <strong>${USER_INFO.totalScore}p</strong></span>	
	</h1>
	
	<div id="my_dataviz"></div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
	//-- 정보 수정 처리 로직 --
	$(document).ready(function () {
	    $("#update-nickname-btn").click(function () {
	    	$("#update-nickname-form").attr("method", "post");
	    	$("#update-nickname-form").attr("action", "${pageContext.request.contextPath}/mypage");
	    	$("#update-nickname-form").submit();
	    })
	    $("#update-bio-btn").click(function () {
	    	$("#update-bio-form").attr("method", "post");
	    	$("#update-bio-form").attr("action", "${pageContext.request.contextPath}/mypage");
	    	$("#update-bio-form").submit();
	    })
	});
	
	//-- 차트 처리 로직 --
	const rankData = ${RANK_CHART_DATA};
	
	// D3.js를 위한 데이터 변환
	const data = rankData.map(item => ({
	  date: d3.timeParse("%Y-%m-%d")(item.date.split(" ")[0]), // 날짜 형식 변환
	  value: +item.scoreSum // 점수 값
	}));
	
	// 차트 크기 및 여백 설정
	var margin = { top: 10, right: 30, bottom: 30, left: 60 },
	    width = 1400 - margin.left - margin.right,
	    height = 400 - margin.top - margin.bottom;
	
	// SVG 생성
	var svg = d3.select("#my_dataviz")
	  .append("svg")
	    .attr("width", width + margin.left + margin.right)
	    .attr("height", height + margin.top + margin.bottom)
	  .append("g")
	    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	
	// X축 생성
	var x = d3.scaleTime()
	  .domain(d3.extent(data, function(d) { return d.date; }))
	  .range([0, width]);
	
	svg.append("g")
	  .attr("transform", "translate(0," + height + ")")
	  .call(d3.axisBottom(x).tickFormat(d3.timeFormat("%m/%d")));
	
	// Y축 생성
	var y = d3.scaleLinear()
	  .domain([0, d3.max(data, function(d) { return d.value; })])
	  .range([height, 0]);
	
	svg.append("g").call(d3.axisLeft(y));
	
	// 꺾은선 추가
	svg.append("path")
	  .datum(data)
	  .attr("fill", "none")
	  .attr("stroke", "#b3ff43")
	  .attr("stroke-width", 3)
	  .attr("d", d3.line()
	    .x(function(d) { return x(d.date); })
	    .y(function(d) { return y(d.value); })
	  );
</script>
