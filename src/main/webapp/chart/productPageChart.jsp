<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Sales Data</title>
    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body {
            font-family: 'Noto Sans', sans-serif;
            background-color: #fff;
            margin: 0;
            padding: 20px;
        }

        .chart-container {
            max-width: 800px;
            margin: 0 auto;
        }

        .chart-frame {
            border: 2px solid #d1d1d1;
            border-radius: 10px;
            padding: 20px;
            background-color: white;
            position: relative;
            width: 100%;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-top: 20px;
        }

        #chartSalesChart {
            width: 100%;
            height: 400px;
        }

        .chart-button-group {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }

        .chart-button-group button {
            padding: 10px 30px;
            border: none;
            background-color: #e0e0e0;
            color: #333;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s, color 0.3s;
            border-radius: 20px; /* 둥글기 변경 */
        }

        .chart-button-group button.active {
            background-color: #555555;
            color: #fff;
        }

        .chart-button-group button:not(:last-child) {
            margin-right: 10px;
        }

        /* 버튼 호버 효과 추가 */
        .chart-button-group button:hover {
            background-color: #555555;
            color: #fff;
        }
    </style>
</head>
<body>

<div class="chart-container">
    <div class="chart-button-group">
        <button id="chartBtn7Days" class="active" onclick="loadChartSalesData(7, this)">일주일</button>
        <button id="chartBtn30Days" onclick="loadChartSalesData(30, this)">1개월</button>
    </div>

    <div class="chart-frame">
        <canvas id="chartSalesChart"></canvas>
    </div>
</div>

<script>
    let chartInstance = null;

    function loadChartSalesData(period, button) {
        $('.chart-button-group button').removeClass('active');
        $(button).addClass('active');

        $.ajax({
            url: '/ajax',
            type: 'GET',
            dataType: 'json',
            data: {
                key: 'statistics',
                methodName: 'getSalesData',
                productNo: ${productDetail.no},
                period: period
            },
            success: function(data) {
                let uniqueData = {};
                for (let dateTime in data) {
                    let date = dateTime.split(' ')[0];
                    if (!uniqueData.hasOwnProperty(date)) {
                        uniqueData[date] = data[dateTime];
                    }
                }

                let sortedDates = Object.keys(uniqueData).sort((a, b) => new Date(a) - new Date(b));

                let labels = sortedDates.map(dateString => {
                    const date = new Date(dateString);
                    const month = String(date.getMonth() + 1);
                    const day = String(date.getDate());
                    return month + '/' + day;
                });

                let sales = sortedDates.map(dateString => uniqueData[dateString]);

                const ctx = document.getElementById('chartSalesChart').getContext('2d');

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
                                ticks: {
                                    color: '#333'
                                },
                                grid: {
                                    color: 'rgba(0, 0, 0, 0.1)'
                                }
                            },
                            y: {
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
                console.error('데이터 로딩 오류 발생:', error);
                console.log('응답 내용:', xhr.responseText);
            }
        });
    }

    $(document).ready(function() {
        loadChartSalesData(7, $('#chartBtn7Days')[0]);
    });
</script>

</body>
</html>
