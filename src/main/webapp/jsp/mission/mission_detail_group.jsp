<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ include file="/jsp/layout/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${gname} 팀의 성과</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mission/mission_page.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://d3js.org/d3.v7.min.js"></script>
</head>
<body>

<div class="table-container">
    <h2 class="table-title">${gname} 팀의 달성한 과제</h2>
    <table>
        <thead>
            <tr>
                <th>Contents</th>
                <th>Points</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="mvo" items="${MLIST}">
                <tr>
                    <td>${mvo.contents}</td>
                    <td class="table-points">+${mvo.points}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<div class="table-container">
    <h2 class="table-title">${gname} 팀의 과제 히스토리</h2>
    <!-- 과제 히스토리 내용을 동적으로 추가 -->
     <div id="calendar"></div>
</div>

<div class="table-container">
    <h2 class="table-title">${gname} 팀의 획득한 뱃지</h2>
    <!-- 획득한 뱃지 내용을 동적으로 추가 -->
    <c:forEach var="mvo" items="${BLIST}">
           <img src="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}${mvo.badgeLink}" 
             alt="Badge Image" 
             style="width: 100px; height: 100px;">
   	</c:forEach>
</div>

<div class="table-container">
    <h2 class="table-title">${gname} 팀의 수행 과제 통계</h2>
    <div class="chart-table-wrapper" style = "float:left;">
        <!-- 차트 캔버스 -->
        <canvas id="myPieChart" width="400" height="400"></canvas>
	</div>
        <!-- 테스트용 테이블 -->
    <div class="table-mini">
        <table>
            <thead>
                <tr>
                    <th>Category</th>
                    <th>Points</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="mvo" items="${MLIST}">
                <tr>
                    <td>${mvo.contents}</td>
                    <td class="table-points">+${mvo.points}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script>

const groupId = new URLSearchParams(window.location.search).get("groupid");
console.log(groupId);
$(document).ready(function () {
    // AJAX로 JSON 데이터 가져오기
    $.ajax({
    	url: '/missionGroup?groupid=' + groupId, // JSON 데이터를 반환하는 Servlet의 URL
        method: 'POST',
        data: {action: 'chart'},
        dataType: 'json',
        success: function (data) {
        	console.log("Received data:", data);
            // JSON 데이터를 파싱하여 labels와 data 생성
            const labels = data.map(item => item.contents); // 그룹 이름을 labels로 사용
            const count = data.map(item => item.count); // 점수를 data로 사용

            // Chart.js 파이 차트 생성
            const ctx = document.getElementById('myPieChart').getContext('2d');
            new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: labels,
                    datasets: [{
                        label: '과제 분포',
                        data: count,
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)',
                            'rgba(153, 102, 255, 0.2)',
                            'rgba(255, 159, 64, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)',
                            'rgba(153, 102, 255, 1)',
                            'rgba(255, 159, 64, 1)'
                        ],
                        borderWidth: 1
                    }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top'
                        },
                        title: {
                            display: true,
                            text: '그룹별 점수 분포'
                        }
                    }
                }
            });
        },
        error: function (err) {
            console.error("Error fetching data:", err);
        }
    });
    
    $.ajax({
    	 url: '/missionGroup?groupid=' + groupId, // JSON 데이터를 반환하는 Servlet의 URL
         method: 'POST',
         data: {action: 'history'},
         dataType: 'json',
         success: function (data) {
        	 console.log("Received data:", data);
        	 const datetime = data.map(data => data.success_date)
        	 console.log("ddd:", datetime);
        	 const highlightedDates = data;
        	 
        	 const svg = d3.select("svg");
        	 
        	// 날짜별 블록 업데이트
            svg.selectAll(".day")
                 .filter(function () {
                     // data-date와 JSON 데이터 비교
                     const date = d3.select(this).attr("data-date");
                     return highlightedDates.some(h => h.success_date === date);
                 })
                 .attr("fill", function () {
                     // JSON 데이터에서 해당 날짜의 색상을 가져오기
                     const date = d3.select(this).attr("data-date")
                     const match = highlightedDates.find(h => h.success_date === date);
                     return match ? "#b3ff43" : d3.select(this).attr("fill");
                 })
                 .attr("count", function () {
                	 const date = d3.select(this).attr("data-date")
                     const match = highlightedDates.find(h => h.success_date === date);
                     return match ? match.count : d3.select(this).attr("count");
                 })
         },
         error: function (err) {
             console.error("Error fetching data:", err);
         }
     });
});

</script>

<script>

const cellSize = 15; // 블록 크기
const margin = { top: 10, right: 0, bottom: 10, left: 0 };

// 특정기간
const generateCalendar = (startDate, endDate) => {
      const start = new Date(startDate);
      const end = new Date(endDate);
      const allDates = [];

      for (let d = new Date(start); d <= end; d.setDate(d.getDate() + 1)) {
          allDates.push(new Date(d)); // 각 날짜를 Date 객체로 추가
      }
      console.log(allDates);
      return allDates;
  };

// 2023년 1년치 데이터 생성
const calendarData = generateCalendar("2025-01-01", "2025-12-31");

// x축: 주 단위, y축: 요일
const weekDays = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
const weeks = d3.timeWeeks(
    d3.timeSunday.floor(d3.min(calendarData)), // 첫 번째 주의 시작
    d3.timeSunday.ceil(d3.max(calendarData))  // 마지막 주의 끝
);

const width = weeks.length * cellSize; // x축: 주 개수 * 블록 크기
const height = weekDays.length * cellSize; // y축: 요일 개수 * 블록 크기

// 스케일 설정
const xScale = d3.scaleBand()
    .domain(weeks.map(d => d.toString())) // 문자열 키 사용
    .range([0, width])
    .padding(0.05);

const yScale = d3.scaleBand()
    .domain(weekDays)
    .range([0, height]);

// SVG 생성
const svg = d3.select("#calendar")
    .append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom + 30)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

// 색상 설정
const defaultColor = "#CEE3F6"; // 기본 색상: 밝은 하늘색
const weekendColor = "#CEE3F6"; // 주말 색상: 밝은 하늘색

var Tooltip = d3.select("body")
.append("div")
.style("opacity", 0)
.attr("class", "tooltip")
.style("position", "absolute")
.style("background-color", "white")
.style("border", "solid")
.style("border-width", "2px")
.style("border-radius", "5px")
.style("padding", "5px")

var mouseover = function(event, d) {
	const date = d3.select(this).attr("data-date");
    const count = d3.select(this).attr("count");
    Tooltip
        .style("opacity", 1)
        .html("날짜 : " + date + "<br>" +
        	"count:" + count); // count가 없으면 기본 메시지
    d3.select(this)
        .style("stroke", "black")
        .style("stroke-width", "2px")
    	.attr("shape-rendering", "crispEdges")
        .style("opacity", 1);
};

var mousemove = function(event, d) {
    //const [x, y] = d3.pointer(event);
    Tooltip
    	.style("left", (event.pageX + 20) + "px") // 브라우저 기준 오른쪽 20px
    	.style("top", (event.pageY + 20) + "px"); // 브라우저 기준 아래 20px
};

var mouseleave = function(event, d) {
    Tooltip
        .style("opacity", 0); // 툴팁 숨기기
    d3.select(this)
        .style("stroke", "#ccc")
        .style("stroke-width", "1px")
        .style("opacity", 1);
};

// 날짜별 블록 추가
svg.selectAll()
	.data(calendarData)
	.enter()
	.append("rect")
	.attr("class", "day")
	.attr("x", d => xScale(d3.timeSunday.floor(d).toString()))
	.attr("y", d => yScale(weekDays[d.getDay()]))
	.attr("width", cellSize)
	.attr("height", cellSize)
	.attr("fill", d => [0, 6].includes(d.getDay()) ? weekendColor : defaultColor) // 주말 강조
	.attr("data-date", d => d.toISOString().replace("T00:00:00.000Z", "")) // ISO 형식으로 날짜 저장
	.attr("count", 0)
	.on("mouseover", mouseover)
	.on("mousemove", mousemove)
	.on("mouseleave", mouseleave)

// 월 데이터 구분
const months = d3.timeMonths(
    d3.timeMonth.floor(d3.min(calendarData)), 
    d3.timeMonth.ceil(d3.max(calendarData))
);	
	
// 월 라벨 생성
svg.selectAll(".month-label")
    .data(months)
    .enter()
    .append("text")
    .attr("class", "month-label")
    .attr("x", d => {
        const firstDayOfMonth = d3.timeSunday.floor(d); // 월의 첫 번째 주 일요일
        return xScale(firstDayOfMonth.toString()) + cellSize / 2; // 월 시작 위치
    })
    .attr("y", height + 25)// y축 위에 배치
    .text(d => d3.timeFormat("%b")(d)) // "Jan", "Feb" 등으로 출력
    .attr("text-anchor", "start") // 시작점 기준 정렬
    .attr("fill", defaultColor);
</script>

<%@ include file="/jsp/layout/footer.jsp" %>
</body>
</html>
