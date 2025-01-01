<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/jsp/layout/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>터틀니 팀의 성과</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mission/mission_page.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>

<div class="table-container">
    <h2 class="table-title">[터틀니] 팀의 달성한 과제</h2>
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
    <h2 class="table-title">[터틀니] 팀의 과제 히스토리</h2>
    <!-- 과제 히스토리 내용을 동적으로 추가 -->
</div>

<div class="table-container">
    <h2 class="table-title">[터틀니] 팀의 획득한 뱃지</h2>
    <!-- 획득한 뱃지 내용을 동적으로 추가 -->
</div>

<div class="table-container">
    <h2 class="table-title">[터틀니] 팀의 수행 과제 통계</h2>
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
                <tr>
                    <td>룰루</td>
                    <td>45</td>
                </tr>
                <tr>
                    <td>랄라</td>
                    <td>25</td>
                </tr>
                <tr>
                    <td>롤로</td>
                    <td>30</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
<script>
    const labels = ["룰루", "랄라", "롤로"];
    const data = [45, 25, 30];

    const ctx = document.getElementById('myPieChart').getContext('2d');
    const myPieChart = new Chart(ctx, {
        type: 'pie',
        data: {
            labels: labels,
            datasets: [{
                label: '과제 분포',
                data: data,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)'
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
                    text: '가라 데이터로 그린 과제 통계'
                }
            }
        }
    });
</script>

<%@ include file="/jsp/layout/footer.jsp" %>
</body>
</html>
