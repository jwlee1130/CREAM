<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Sales Data</title>
    <meta charset="UTF-8">
    <!-- Chart.js 및 jQuery 로드 -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: 'Noto Sans', sans-serif;
            background-color: #fff;
            margin: 0;
            padding: 20px;
        }

        /* 컨테이너 스타일 */
        .container {
            max-width: 800px;
            margin: 0 auto;
        }

        /* 프레임 스타일 */
        .frame {
            border: 2px solid #d1d1d1;
            border-radius: 10px;
            padding: 20px;
            background-color: white;
            position: relative;
            width: 100%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        /* 차트 캔버스 스타일 */
        #salesChart {
            width: 100%;
            height: 400px;
        }

        /* 버튼 그룹 스타일 */
        .button-group {
            display: flex;
            justify-content: center; /* 버튼 중앙 정렬 */
            margin-bottom: 20px;
        }

        .button-group button {
            padding: 10px 30px; /* 버튼 패딩 조정 */
            border: none;
            background-color: #e0e0e0; /* 기본 버튼 색상: 옅은 회색 */
            color: #333;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s, color 0.3s;
        }

        .button-group button.active {
            background-color: #555555; /* 클릭된 버튼: 진한 회색 */
            color: #fff;
        }

        .button-group button:not(:last-child) {
            margin-right: 10px; /* 버튼 간격 조정 */
        }
    </style>
</head>
<body>

<div class="container">
    <div class="button-group">
        <button id="btn-7days" class="active" onclick="loadSalesData(7, this)">일주일</button>
        <button id="btn-30days" onclick="loadSalesData(30, this)">1개월</button>
    </div>

    <div class="frame">
        <canvas id="salesChart"></canvas>
    </div>
</div>

<script>
    let chartInstance = null;
    const productNo = 1; // productNo를 1로 고정, 테스트용

    function loadSalesData(period, button) {
        $('.button-group button').removeClass('active');
        $(button).addClass('active');

        $.ajax({
            url: '/ajax?key=statistics&methodName=getSalesData&productNo=' + productNo + '&period=' + period,
            type: 'GET',
            dataType: 'json',
            success: function(data) {
                const labels = Object.keys(data)
                    .sort((a, b) => new Date(a) - new Date(b))
                    .map(dateString => {
                        const date = new Date(dateString);
                        const month = String(date.getMonth() + 1);
                        const day = String(date.getDate());
                        return month + '/' + day;
                    });

                const sales = Object.values(data);

                const ctx = document.getElementById('salesChart').getContext('2d');

                if (chartInstance) {
                    chartInstance.destroy();
                }

                chartInstance = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: labels,
                        datasets: [{
                            label: '가격',
                            data: sales,
                            borderColor: 'rgba(255, 0, 0, 1)',
                            borderWidth: 2,
                            pointRadius: 0,
                            pointBackgroundColor: 'rgba(75, 192, 192, 1)',
                            fill: false
                        }]
                    },
                    options: {
                        responsive: true,
                        plugins: {
                            legend: {
                                display: false
                            }
                        },
                        scales: {
                            x: {
                                title: {
                                    display: true,
                                    text: ''
                                },
                                ticks: {
                                    color: '#333'
                                },
                                grid: {
                                    color: 'rgba(0, 0, 0, 0.1)'
                                }
                            },
                            y: {
                                title: {
                                    display: true,
                                    text: ''
                                },
                                ticks: {
                                    color: '#333'
                                },
                                grid: {
                                    color: 'rgba(0, 0, 0, 0.1)'
                                }
                            }
                        }
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error('데이터 로드 중 오류 발생:', error);
                console.log('응답 내용:', xhr.responseText);
            }
        });
    }

    $(document).ready(function() {
        loadSalesData(7, $('#btn-7days')[0]);
    });
</script>

</body>
</html>
