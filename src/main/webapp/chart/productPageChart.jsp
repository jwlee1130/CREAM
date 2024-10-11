<%@ page contentType="text/html;charset=UTF-8" language="java" %>


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

                const labels = Object.keys(data).map(dateString => {
                    const date = new Date(dateString);

                    const month = date.getMonth() + 1;
                    const day = date.getDate();


                    return month + '/' + day;
                });


                console.log(labels);


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
                            label: 'Sales',
                            data: sales,
                            borderColor: 'rgba(34, 34, 34, 1)',
                            borderWidth: 2,
                            pointRadius: 3,
                            fill: false
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

    $(document).ready(function() {
        loadSalesData(1, 30); // productNo=1, 기간=30일로 기본 데이터 로드
    });
</script>

</body>
</html>
