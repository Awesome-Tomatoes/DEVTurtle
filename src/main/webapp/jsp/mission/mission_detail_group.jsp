<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/jsp/layout/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${gname} 팀의 성과</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mission/mission_page.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
    <!-- 과제 히스토리 내용을 동적으로 추가 (히트맵)-->
</div>

<div class="table-container">
    <h2 class="table-title">${gname} 팀의 획득한 뱃지</h2>
    <!-- 획득한 뱃지 내용을 동적으로 추가 -->
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
$(document).ready(function () {
    // AJAX로 JSON 데이터 가져오기
    $.ajax({
        url: '/DevTurtle/missionGroup', // JSON 데이터를 반환하는 Servlet의 URL
        method: 'POST',
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
});
</script>

<%@ include file="/jsp/layout/footer.jsp" %>
</body>
</html>
