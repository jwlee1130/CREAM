<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>통계 대시보드</title>

  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

  <style>
    body {
      font-family: 'Noto Sans', sans-serif;
      background-color: #fff;
      margin: 0;
      padding: 20px;
    }
    .stat-chart-container {
      max-width: 100%;
      margin: 0 auto 50px;
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
    }
    .stat-chart-frame {
      border: 2px solid #d1d1d1;
      border-radius: 10px;
      padding: 20px;
      background-color: white;
      width: 48%;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
      margin-top: 10px;
      margin-bottom: 20px;
    }
    .full-width {
      width: 100%;
    }
    .stat-canvas {
      width: 100% !important;
      height: 300px !important;
    }
    .stat-chart-frame h2 {
      text-align: center;
      margin-bottom: 20px;
      font-family:'Poppins',sans-serif;
      font-weight: 600;
    }
  </style>
</head>
<body>

<div class="stat-chart-container">
  <div class="stat-chart-frame">
    <h2>남자 인기 품목 Top 3</h2>
    <canvas id="top3MaleItemsChart" class="stat-canvas"></canvas>
  </div>

  <div class="stat-chart-frame">
    <h2>여자 인기 품목 Top 3</h2>
    <canvas id="top3FemaleItemsChart" class="stat-canvas"></canvas>
  </div>
</div>

<div class="stat-chart-container">
  <div class="stat-chart-frame full-width">
    <h2>일일 판매액(일주일)</h2>
    <canvas id="totalSalesChart" class="stat-canvas"></canvas>
  </div>
</div>

<div class="stat-chart-container">
  <div class="stat-chart-frame">
    <h2>설문조사 인기 브랜드 Top 3</h2>
    <canvas id="top3BrandsChart" class="stat-canvas"></canvas>
  </div>

  <div class="stat-chart-frame">
    <h2>설문조사 인기 색상 Top 3</h2>
    <canvas id="top3ColorsChart" class="stat-canvas"></canvas>
  </div>
</div>

<script>
  $(document).ready(function() {
    const contextPath = '${pageContext.request.contextPath}';
    const period = 7;

    loadTop3Items('남', period, contextPath + '/ajax', '#top3MaleItemsChart');
    loadTop3Items('여', period, contextPath + '/ajax', '#top3FemaleItemsChart');
    loadTotalSalesData(contextPath + '/ajax', '#totalSalesChart', period);
    loadTop3SurveyData('getTop3BrandsFromSurvey', contextPath + '/ajax', '#top3BrandsChart');
    loadTop3SurveyData('getTop3ColorsFromSurvey', contextPath + '/ajax', '#top3ColorsChart');
  });

  function loadTop3Items(gender, period, url, canvasId) {
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
        const backgroundColors = generateGenderColors(labels.length);

        new Chart(document.querySelector(canvasId), {
          type: 'doughnut',
          data: {
            labels: labels,
            datasets: [{
              label: '판매량',
              data: values,
              backgroundColor: backgroundColors,
              borderColor: 'rgba(255, 255, 255, 1)',
              borderWidth: 1
            }]
          },
          options: {
            responsive: true,
            plugins: {
              legend: {
                display: true,
                position: 'bottom',
                labels: {
                  usePointStyle: true,
                  // padding: 10,
                  // boxWidth: 10,
                  align: 'start',

                }
              }
            },
            scales: {}
          }
        });
      },
      error: function(error) {
    	  showError('차트 데이터 로드 오류');
      }
    });
  }

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
        const sortedDates = Object.keys(data).sort((a, b) => new Date(a) - new Date(b));

        const labels = sortedDates.map(dateString => {
          const date = new Date(dateString);
          const month = String(date.getMonth() + 1);
          const day = String(date.getDate());
          return month + '/' + day;
        });

        const values = sortedDates.map(dateString => data[dateString]);

        new Chart(document.querySelector(canvasId), {
          type: 'line',
          data: {
            labels: labels,
            datasets: [{
              label: '최근 일주일간 일일 판매액',
              data: values,
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
      error: function(error) {
    	  showError('일일 판매액 로드 오류');
      }
    });
  }

  function loadTop3SurveyData(methodName, url, canvasId) {
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
        const backgroundColors = generateSurveyColors(labels.length);

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
              legend: {
                position: 'bottom',
                labels: {
                  usePointStyle: true,
                  padding: 20,
                  boxWidth: 10,
                  align: 'start',
                }
              }
            }
          }
        });
      },
      error: function() {
        showError('설문조사 데이터 로드 오류');
      }
    });
  }

  function generateGenderColors(num) {
    const colors = [
      'rgba(255, 99, 132, 0.6)',
      'rgba(54, 162, 235, 0.6)',
      'rgba(255, 206, 86, 0.6)'
    ];
    const extendedColors = [];
    for(let i=0; i<num; i++) {
      extendedColors.push(colors[i % colors.length]);
    }
    return extendedColors;
  }

  function generateSurveyColors(num) {
    const colors = [
      'rgba(75, 192, 192, 0.6)',
      'rgba(153, 102, 255, 0.6)',
      'rgba(255, 159, 64, 0.6)'
            ];
    const extendedColors=[];
    for(let i=0; i<num; i++) {
      extendedColors.push(colors[i % colors.length]);
    }
    return extendedColors;
  }
</script>

</body>
</html>
