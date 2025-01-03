<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="https://d3js.org/d3.v7.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mypage/mypage_user_info.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mypage/mypage_info_list.css" />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mypage/mypage_ranking_point_chart.css" />


<div id="mypage-container">
	<article class="contents__article">
		<article class="contents__article--fragment"
			id="contents__article--fragment-ranking">
			<div id="user-ranking-info">
				<p class="user-ranking-info__p">
					<img
						src="${pageContext.request.contextPath}/assets/main/grade-star.svg" />
					<span id="user-ranking-info__grade">Pirate Turtle</span>
				</p>
				<img id="user-ranking-info__img"
					src="${pageContext.request.contextPath}/assets/turtle_grade/pirates_junior_turtle.png" />
				<p class="user-ranking-info__p">
					<span class="user-ranking-info__p--title">내 랭킹 포인트</span> <span
						class="user-ranking-info__p--point">2030p</span>
				</p>
				<ul id="user-ranking-info__ul">
					<li class="user-ranking-info__li">
						<p class="user-ranking-info__p">커밋</p>
						<p class="user-ranking-info__p">1000p</p>
					</li>
					<li class="user-ranking-info__li">
						<p class="user-ranking-info__p">PR</p>
						<p class="user-ranking-info__p">300p</p>
					</li>
					<li class="user-ranking-info__li">
						<p class="user-ranking-info__p">코테</p>
						<p class="user-ranking-info__p">1000p</p>
					</li>
					<li class="user-ranking-info__li">
						<p class="user-ranking-info__p">출석</p>
						<p class="user-ranking-info__p">30p</p>
					</li>
					<li class="user-ranking-info__li">
						<p class="user-ranking-info__p">미션</p>
						<p class="user-ranking-info__p">300p</p>
					</li>
				</ul>

				<div id="user-ranking-info__point-bar">
					<p id="next-point">다음 등급까지 10,000점</p>
					<div id="point-bar__fragment">
						<div id="point-bar__back"></div>
						<div id="point-bar__front"></div>
					</div>
				</div>
			</div>
		</article>
		<article class="contents__article--fragment"
			id="contents__article--fragment-basic">
			<form class="user-basic-info__form">
				<label>닉네임</label> <input type="text" value="TEST" />
				<button class="update-btn">수정</button>
			</form>
			<form class="user-basic-info__form">
				<label>Github</label> <input type="text" value="gitgit" /> <a><img
					src="${pageContext.request.contextPath}/assets/main/github.svg" /></a>
			</form>
			<form class="user-basic-info__form">
				<label>solved.ac</label> <input type="text" value="solsol" /> <a><img
					src="${pageContext.request.contextPath}/assets/main/solvedac.svg" /></a>
			</form>
			<form class="user-basic-info__bio">
				<div id="user-basic-info__bio-header">
					<label>자기소개</label>
					<button class="update-btn">수정</button>

				</div>
				<textarea>나는 해적 거북이가 될거야!</textarea>
			</form>
		</article>
	</article>

	<div id="user-info-list">
		<button class="user-info-list__btn">
			<div class="user-info-list__title">
				<img
					src="${pageContext.request.contextPath}/assets/main/ranking.svg" />
				<p id="user-info-list__ranking-desc">랭킹</p>
			</div>
			<p>499,555 rd / 500,000</p>
		</button>
		<button class="user-info-list__btn">
			<div class="user-info-list__title">
				<img
					src="${pageContext.request.contextPath}/assets/main/misson.svg" />
				<p>미션포인트</p>
			</div>
			<p>500p</p>
		</button>
		<button class="user-info-list__btn">
			<div class="user-info-list__title">
				<img
					src="${pageContext.request.contextPath}/assets/main/follower.svg" />
				<p>팔로워</p>
			</div>
			<p>14</p>
		</button>
		<button class="user-info-list__btn">
			<div class="user-info-list__title">
				<img
					src="${pageContext.request.contextPath}/assets/main/group.svg" />
				<p>그룹</p>
			</div>
			<p>3</p>
		</button>
	</div>
	
	<h1 id="ranking-point-chart__title">
		<span>내 랭킹 포인트</span>
		<span>현재 <strong>3000p</strong></span>	
	</h1>
	
	<div id="my_dataviz"></div>
</div>

<script>
  const data = [
    { date: "2023-01-01", value: 10 },
    { date: "2023-02-01", value: 15 },
    { date: "2023-03-01", value: 15 },
    { date: "2023-04-01", value: 25 },
    { date: "2023-05-01", value: 30 }
  ];

  // 날짜를 파싱
  const parsedData = data.map(d => ({
    date: d3.timeParse("%Y-%m-%d")(d.date),
    value: +d.value
  }));

  // 그래프 크기와 여백 설정
  var margin = { top: 10, right: 30, bottom: 30, left: 60 },
      width = 1400 - margin.left - margin.right,
      height = 400 - margin.top - margin.bottom;

  // SVG 객체 생성
  var svg = d3.select("#my_dataviz")
    .append("svg")
      .attr("width", width + margin.left + margin.right)
      .attr("height", height + margin.top + margin.bottom)
    .append("g")
      .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

  // X축 생성
  var x = d3.scaleTime()
    .domain(d3.extent(parsedData, function(d) { return d.date; }))
    .range([0, width]);

  // 날짜 포맷 지정
  const dateFormat = d3.timeFormat("%m/%d");

  svg.append("g")
    .attr("transform", "translate(0," + height + ")")
    .call(d3.axisBottom(x).tickFormat(dateFormat)); // 여기에서 포맷 지정

  // Y축 생성
  var y = d3.scaleLinear()
    .domain([0, d3.max(parsedData, function(d) { return d.value; })])
    .range([height, 0]);

  svg.append("g").call(d3.axisLeft(y));

  // 꺾은선 추가
  svg.append("path")
    .datum(parsedData)
    .attr("fill", "none")
    .attr("stroke", "#b3ff43")
    .attr("stroke-width", 3)
    .attr("d", d3.line()
      .x(function(d) { return x(d.date); })
      .y(function(d) { return y(d.value); })
    );
</script>
