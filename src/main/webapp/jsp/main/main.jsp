<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
	<article class="contents__article--fragment" id="contents__article--fragment-ranking">
		<div id="user-ranking-info">
			<img id="user-ranking-info__img" src="${pageContext.request.contextPath}/assets/turtle_grade/pirates_junior_turtle.png"/>
			<div id="user-ranking-info__divs">
				<p class="user-ranking-info__p">
					<img src="${pageContext.request.contextPath}/assets/main/grade-star.svg" />
					<span id="user-ranking-info__grade" >Pirate Turtle</span>
				</p>
				<p class="user-ranking-info__p">
					<span class="user-ranking-info__p--title">닉네임</span>
					<span class="user-ranking-info__p--desc">TEST</span>		
				</p>
				<p class="user-ranking-info__p">
					<span class="user-ranking-info__p--title">랭킹포인트</span>
					<span class="user-ranking-info__p--desc">200,000</span>		
				</p>
				<p class="user-ranking-info__p">
					<span class="user-ranking-info__p--title">내 랭킹</span>
					<span class="user-ranking-info__p--desc">499,555 rd / 500,000</span>		
				</p>
			</div>
		</div>
		<div id="user-ranking-info__point-bar">
			<p id="next-point">다음 등급까지 10,000점</p>
			<div id="point-bar__fragment">
				<div id="point-bar__back"></div>
				<div id="point-bar__front"></div>
			</div>
		</div>
	</article>
	<article class="contents__article--fragment" id="contents__article--fragment-basic">
		<div id="user-basic-info">
			<span id="user-basic-info__name">최성관</span>
			<p id="user-basic-info__link">
				<img src="${pageContext.request.contextPath}/assets/main/github.svg" />
				<img src="${pageContext.request.contextPath}/assets/main/solvedac.svg"/>
			</p>
		</div>
		<div id="user-basic-info__bio">
			꾸준히 성장하는 개발자 군말하지 않는 개발자, 그게 바로 나
		</div>
		<div id="user-basic-info__group-header">
			<span id="user-basic-info__group-header-title">성관’s GROUP</span>
			<button class="see-more-btn">더보기</button>
		</div>
		<ul id="user-basic-info__group-ul">
			<li class="user-basic-info__group-li">
				<span class="user-basic-info__group-li-title">토마토</span>
				<span class="user-basic-info__group-li-score">
					<img class="user-basic-info__group-li-icon" src="${pageContext.request.contextPath}/assets/main/ranking.svg"/>
					<span class="user-basic-info__group-li-point">3rd / 200</span>
				</span>
			</li>
			<li class="user-basic-info__group-li">
				<span class="user-basic-info__group-li-title">현대</span>
				<span class="user-basic-info__group-li-score">
					<img class="user-basic-info__group-li-icon" src="${pageContext.request.contextPath}/assets/main/ranking.svg"/>
					<span class="user-basic-info__group-li-point">1rd / 200</span>
				</span>
			</li>
		</ul>
	</article>
</article>

<article class="contents__article">
	<article class="ranking__article--fragment">
		<div class="ranking__header--div">
			<h1 class="ranking__header--title">
			👑 그룹랭킹
			</h1>
			<button class="see-more-btn">더 보기</button>
		</div>
		<ul class="ranking__ul">
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_badge/attendance_badge.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">멋져부러 토마토</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_badge/attendance_badge.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">멋져부러 토마토</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_badge/attendance_badge.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">멋져부러 토마토</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_badge/attendance_badge.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">멋져부러 토마토</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_badge/attendance_badge.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">멋져부러 토마토</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_badge/attendance_badge.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">멋져부러 토마토</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
		</ul>

	</article>
	<article class="ranking__article--fragment">
		<div class="ranking__header--div">
			<h1 class="ranking__header--title">
			👑 사용자랭킹
			</h1>
			<button class="see-more-btn">더 보기</button>
		</div>
		<ul class="ranking__ul">
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_grade/pirates_turtle.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">이효경</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_grade/pirates_turtle.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">이효경</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_grade/pirates_turtle.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">이효경</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_grade/pirates_turtle.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">이효경</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_grade/pirates_turtle.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">이효경</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
			<li class="ranking__li">
				<div class="ranking__li--div">
					<span class="ranking__li--rank">1위</span>
					<img class="ranking__li--img" src="${pageContext.request.contextPath}/assets/turtle_grade/pirates_turtle.png"/>
					<p class="ranking__li--desc">
						<span class="ranking__li--desc-title">이효경</span>
						<span>203,234p</span>
					</p>
				</div>
				<button class="see-more-btn">상세</button>
			</li>
		</ul>

	</article>
	
</article>

</div>
