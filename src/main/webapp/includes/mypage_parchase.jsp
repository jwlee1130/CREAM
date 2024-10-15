
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>parchase</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage_main.css">
</head>
<body>
<div class="mypage-container">
  <div class="wrap">
    <div class="sell">
            <h1>구매 내역</h1>
            <div class="parchase-area">
                <div class="parchase-all">
                    <h2>전체</h2>
                    <h2 id="total-purchases-count" style="color: red">0</h2>
                </div>
                <div class="parchase-all">
                    <h2>진행중</h2>
                    <h2 id="in-progress-purchases-count">0</h2>
                </div>
                <div class="parchase-all">
                    <h2>종료</h2>
                    <h2 id="completed-purchases-count">0</h2>
                </div>
            </div>
            <div id="purchase-container" style="display: flex; flex-direction: column;">
                <!-- 구매 내역 항목들이 여기에 추가됩니다 -->
            </div>
        </div>
  </div>
</div>
</body>
</html>
