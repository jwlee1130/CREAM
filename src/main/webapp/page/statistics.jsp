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
    /* 통계 대시보드와 충돌 방지를 위한 클래스 접두사 추가 */
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
    .stat-chart-frame:nth-child(2n) {
      margin-right: 0;
    }
    .stat-canvas {
      width: 100% !important;
      height: 300px !important;
    }
    .stat-list-inner {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 10px;
      border-bottom: 1px solid #eee;
    }
    .stat-rank-a, .stat-rank-b, .stat-rank-c {
      font-weight: bold;
      margin-right: 10px;
    }
    .stat-buy-button {
      padding: 5px 10px;
      background-color: #28a745;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
    }
    .stat-buy-button:hover {
      background-color: #218838;
    }
    .stat-tab-content-list {
      padding: 10px;
    }
    .stat-item-none {
      color: red;
      font-weight: bold;
    }
    .stat-chart-frame h2 {
      text-align: center;
      margin-bottom: 20px;
    }
    /* 모달 스타일 */
    .stat-modal {
      display: none;
      position: fixed;
      z-index: 1000;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgba(0,0,0,0.5);
    }
    .stat-modal-content {
      background-color: #fefefe;
      margin: 10% auto;
      padding: 20px;
      border: 1px solid #888;
      width: 80%;
      border-radius: 10px;
    }
    .stat-close-button {
      color: #aaa;
      float: right;
      font-size: 28px;
      font-weight: bold;
      cursor: pointer;
    }
    .stat-close-button:hover,
    .stat-close-button:focus {
      color: black;
      text-decoration: none;
      cursor: pointer;
    }
    .stat-tabs-container {
      margin-top: 20px;
    }
    .stat-tabs {
      display: flex;
      justify-content: center;
      margin-bottom: 20px;
    }
    .stat-tab-button {
      background-color: #f1f1f1;
      border: none;
      padding: 10px 20px;
      cursor: pointer;
      margin: 0 5px;
      border-radius: 5px;
      transition: background-color 0.3s;
    }
    .stat-tab-button.active {
      background-color: #007bff;
      color: white;
    }
    .stat-tab-content {
      display: none;
    }
    .stat-tab-content.active {
      display: block;
    }
  </style>
</head>
<body>

<div class="stat-chart-container">
  <!-- 남자 인기 품목 Top 3 (막대 차트) -->
  <div class="stat-chart-frame">
    <canvas id="top3MaleItemsChart" class="stat-canvas"></canvas>
  </div>

  <!-- 여자 인기 품목 Top 3 (막대 차트) -->
  <div class="stat-chart-frame">
    <h2>여자 인기 품목 Top 3</h2>
    <canvas id="top3FemaleItemsChart" class="stat-canvas"></canvas>
  </div>
</div>

<div class="stat-chart-container">
  <!-- 일일 판매액 (꺾은선 차트) -->
  <div class="stat-chart-frame">
    <h2>일일 판매액</h2>
    <canvas id="totalSalesChart" class="stat-canvas"></canvas>
  </div>

  <!-- 설문조사 인기 브랜드 Top 3 (원형 차트) -->
  <div class="stat-chart-frame">
    <h2>설문조사 인기 브랜드 Top 3</h2>
    <canvas id="top3BrandsChart" class="stat-canvas"></canvas>
  </div>
</div>

<script>
  $(document).ready(function() {
    const contextPath = '${pageContext.request.contextPath}';
    const period = 7; // 7일 기준으로 데이터 로드

    // 남자 인기 품목 Top 3 (막대 차트)
    loadTop3Items('남', period, contextPath + '/ajax', '#top3MaleItemsChart', '남자 인기 품목 Top 3');

    // 여자 인기 품목 Top 3 (막대 차트)
    loadTop3Items('여', period, contextPath + '/ajax', '#top3FemaleItemsChart', '여자 인기 품목 Top 3');

    // 일일 판매액 (꺾은선 차트)
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

  // 일일 판매액 (꺾은선 차트)
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

        // 날짜 형식 변환
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
              label: '일일 판매액',
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
                text: '일일 판매액'
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
        console.error("일일 판매액 로드 오류:", error);
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
