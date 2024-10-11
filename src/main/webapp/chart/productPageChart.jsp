<%--
  Created by IntelliJ IDEA.
  User: swift
  Date: 2024-10-11
  Time: 오전 11:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html lang="ko">
<head>
    <title>Sales Data</title>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
            background-color: #f5f5f5;
        }
        .container {
            transform: scale(1.2);
            transform-origin: top left;
        }
        .frame {
            border: 2px solid #d1d1d1;
            border-radius: 10px;
            padding: 20px;
            background-color: white;
            position: relative;
            width: 650px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        #salesChart {
            max-width: 600px;
            margin-top: 10px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="frame">
        <canvas id="salesChart"></canvas>
    </div>
</div>

<script>
    let chartInstance = null;

    function loadSalesData(productNo, period) {
        $.ajax({
            <%--url: `/ajax?key=statistics&methodName=getSalesData&productNo=${productNo}&period=${period}`,--%>
            url: `/ajax?key=statistics&methodName=getSalesData&productNo=1&period=10`,
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                const labels = Object.keys(data); // 날짜를 라벨로 사용
                const sales = Object.values(data); // 판매 데이터를 값으로 사용

                const ctx = document.getElementById('salesChart').getContext('2d');

                // 기존 차트가 있으면 삭제
                if (chartInstance) {
                    chartInstance.destroy();
                }

                chartInstance = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: labels, // 날짜 레이블
                        datasets: [{
                            label: 'Sales',
                            data: sales, // 판매량 데이터
                            borderColor: 'rgba(34, 34, 34, 1)',
                            borderWidth: 2,
                            pointRadius: 3,
                            fill: false // 차트 아래에 색상 채우기 비활성
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: true,
                        scales: {
                            x: {
                                title: {
                                    display: true,
                                    text: 'Date'
                                }
                            },
                            y: {
                                title: {
                                    display: true,
                                    text: 'Sales'
                                }
                            }
                        }
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error('데이터 로드 중 오류 발생:', error);
            }
        });
    }

    // 페이지 로드 시 기본 데이터를 로드 (예: productNo = 1, 기간 = 30일)
    $(document).ready(function() {
        loadSalesData(1, 30);
    });
</script>

</body>
</html>
