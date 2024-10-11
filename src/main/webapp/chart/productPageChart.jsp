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
            url: `/ajax?key=statistics&methodName=getSalesData&productNo=1&period=1`, // 현재는 1번 상품과 10일로 고정
            type: 'GET',
            dataType: 'json',
            success: function(data) {

                const labels = Object.keys(data)
                    .map(dateString => {
                        const date = new Date(dateString);
                        // const month = String(date.getMonth() + 1).padStart(2, '0');
                        // const day = String(date.getDate()).padStart(2, '0');

                        const month = String(date.getMonth());
                        const day = String(date.getDate());

                        return month + '/' + day;
                    })
                    .sort((a, b) => new Date(a) - new Date(b)); //  이거 없으면 날짜 순서가 엉망임


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
                            label: 'Price',
                            data: sales,
                            borderColor: 'rgba(255, 0, 0, 1)',
                            borderWidth: 2,
                            pointRadius: 0,
                            fill: false
                        }]
                    },
                    options: {

                        responsive: true,
                        maintainAspectRatio: true,
                        plugins: {
                            legend: {
                                display: false, // Price 옆에 네모 비활성화
                                labels: {
                                    color: '#333'
                                }
                            }
                        },
                        scales: {
                            // x: {
                            //     ticks: {
                            //         color: '#333'
                            //     },
                            //     grid: {
                            //         color: 'rgba(0, 0, 0, 0.1)'
                            //     }
                            // },
                            // y: {
                            //     ticks: {
                            //         color: '#333'
                            //     },
                            //     grid: {
                            //         color: 'rgba(0, 0, 0, 0.1)'
                            //     }
                            // }
                        }

                        // scales: {
                        //     x: {
                        //         title: {
                        //             display: true,
                        //             text: '날짜'
                        //         }
                        //     },
                        //     y: {
                        //         title: {
                        //             display: true,
                        //             text: '시세'
                        //         }
                        //     }
                        // }
                    }
                });
            },
            error: function(xhr, status, error) {
                console.error('데이터 로드 중 오류 발생', error);
            }
        });
    }

    $(document).ready(function() {
        loadSalesData(1, 30); // productNo=1, 기간=30일로 기본 데이터 로드
    });
</script>

</body>
</html>
