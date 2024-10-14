<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>통계 대시보드</title>

  <!-- jQuery CDN -->
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

  <!-- Chart.js CDN -->
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

  <style>
    body {
      font-family: 'Noto Sans', sans-serif;
      background-color: #fff;
      margin: 0;
      padding: 20px;
    }
    .chart-container {
      max-width: 100%;
      margin: 0 auto 50px;
    }
    .chart-frame {
      border: 2px solid #d1d1d1;
      border-radius: 10px;
      padding: 20px;
      background-color: white;
      width: 100%;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      margin-top: 10px;
    }
    canvas {
      width: 100% !important;
      height: 400px !important;
    }
    h2 {
      text-align: center;
      margin-bottom: 20px;
    }
  </style>
</head>
<body>

<h1 style="text-align: center;">통계 대시보드</h1>

<!-- 남자 인기 품목 Top 3 (막대 차트) -->
<div class="chart-container">
  <div class="chart-frame">
    <h2>남자 인기 품목 Top 3</h2>
    <canvas id="top3MaleItemsChart"></canvas>
  </div>
</div>

<!-- 여자 인기 품목 Top 3 (막대 차트) -->
<div class="chart-container">
  <div class="chart-frame">
    <h2>여자 인기 품목 Top 3</h2>
    <canvas id="top3FemaleItemsChart"></canvas>
  </div>
</div>

<!-- 누적 판매액 (꺾은선 차트) -->
<div class="chart-container">
  <div class="chart-frame">
    <h2>누적 판매액</h2>
    <canvas id="totalSalesChart"></canvas>
  </div>
</div>

<!-- 설문조사 인기 브랜드 Top 3 (원형 차트) -->
<div class="chart-container">
  <div class="chart-frame">
    <h2>설문조사 인기 브랜드 Top 3</h2>
    <canvas id="top3BrandsChart"></canvas>
  </div>
</div>

<script>
  $(document).ready(function() {
    const contextPath = '${pageContext.request.contextPath}';
    const period = 90;

    // 남자 인기 품목 Top 3 (막대 차트)
    loadTop3Items('남', period, contextPath + '/ajax', '#top3MaleItemsChart', '남자 인기 품목 Top 3');

    // 여자 인기 품목 Top 3 (막대 차트)
    loadTop3Items('여', period, contextPath + '/ajax', '#top3FemaleItemsChart', '여자 인기 품목 Top 3');

    // 누적 판매액 (꺾은선 차트)
    loadTotalSalesData(contextPath + '/ajax', '#totalSalesChart', period);

    // 설문조사 인기 브랜드 Top 3 (원형 차트)
    loadTop3SurveyData('getTop3BrandsFromSurvey', contextPath + '/ajax', '#top3BrandsChart', '설문조사 인기 브랜드 Top 3');
  });

  // 남자/여자 인기 품목 Top 3 (막대 차트)
  function loadTop3Items(gender, period, url, canvasId, chartTitle) {
    $.ajax({
      url: url,
      method: 'GET',
      data: {
        key: 'statistics',
        methodName: 'getTop3ItemsByGender',
        gender: gender,
        period: period
      },
      dataType: "json",
      success: function(data) {
        const labels = Object.keys(data);
        const values = Object.values(data);

        new Chart(document.querySelector(canvasId), {
          type: 'bar',
          data: {
            labels: labels,
            datasets: [{
              label: '판매량',
              data: values,
              backgroundColor: 'rgba(54, 162, 235, 0.6)',
              borderColor: 'rgba(54, 162, 235, 1)',
              borderWidth: 1
            }]
          },
          options: {
            responsive: true,
            plugins: {
              title: {
                display: true,
                text: chartTitle
              },
              legend: {
                display: false
              }
            },
            scales: {
              y: {
                beginAtZero: true,
                title: {
                  display: true,
                  text: '판매량'
                }
              },
              x: {
                title: {
                  display: true,
                  text: '품목'
                }
              }
            }
          }
        });
      },
      error: function(error) {
        console.error(chartTitle + " 로드 오류:", error);
      }
    });
  }

  // 누적 판매액 (꺾은선 차트)
  function loadTotalSalesData(url, canvasId, period) {
    $.ajax({
      url: url,
      method: 'GET',
      data: {
        key: 'statistics',
        methodName: 'getTotalSalesData',
        period: period
      },
      dataType: "json",
      success: function(data) {
        const labels = Object.keys(data);
        const values = Object.values(data);

        new Chart(document.querySelector(canvasId), {
          type: 'line',
          data: {
            labels: labels,
            datasets: [{
              label: '누적 판매액',
              data: values,
              borderColor: 'rgba(255, 99, 132, 1)',
              borderWidth: 2,
              pointRadius: 0,
              fill: false
            }]
          },
          options: {
            responsive: true,
            plugins: {
              title: {
                display: true,
                text: '누적 판매액'
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
      error: function(error) {
        console.error("누적 판매액 로드 오류:", error);
      }
    });
  }

  // 설문조사 인기 데이터 (브랜드) Top 3 (원형 차트)
  function loadTop3SurveyData(methodName, url, canvasId, chartTitle) {
    $.ajax({
      url: url,
      method: 'GET',
      data: {
        key: 'statistics',
        methodName: methodName
      },
      dataType: "json",
      success: function(data) {
        const labels = Object.keys(data);
        const values = Object.values(data);
        const backgroundColors = generateColors(labels.length);

        new Chart(document.querySelector(canvasId), {
          type: 'pie',
          data: {
            labels: labels,
            datasets: [{
              data: values,
              backgroundColor: backgroundColors,
              borderColor: 'rgba(255, 255, 255, 1)',
              borderWidth: 1
            }]
          },
          options: {
            responsive: true,
            plugins: {
              title: {
                display: true,
                text: chartTitle
              },
              legend: {
                position: 'bottom'
              }
            }
          }
        });
      },
      error: function(error) {
        console.error(chartTitle + " 로드 오류:", error);
      }
    });
  }

  // 색상 생성 함수
  function generateColors(num) {
    const colors = [
      'rgba(255, 99, 132, 0.6)',
      'rgba(54, 162, 235, 0.6)',
      'rgba(255, 206, 86, 0.6)',
      'rgba(75, 192, 192, 0.6)',
      'rgba(153, 102, 255, 0.6)',
      'rgba(255, 159, 64, 0.6)'
    ];
    return colors.slice(0, num);
  }
</script>

</body>
</html>
