
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>등급</title>
</head>
<body>
<div class="rank-container">
  <div class="rank-wrapper">
    <h1>판매자,구매자 등급</h1>
    <div class="rank-box">
      <div class="rank-commission">
        <div class="current-rank">
          <h3>현재등급</h3>
          <!--            여기가 등급 들어오는곳 -->
          <h2>LV.1</h2>
        </div>
        <div class="current-commission">
          <h3>판매 수수료</h3>
          <h2 style="color: #d84cff">4.0%</h2>
        </div>
      </div>
      <p> 적용 기간 2024.10.02 - 2024.11.01 </p>
      <p> 다음 등급 업데이트 2024.11.02 </p>
    </div>
    <div class="rank-reward">
      <h2>등급별 혜택</h2>
      <div class="level-container">
        <div class="level-box" data-fee="7%">
          <div class="level">레벨 1</div>
          <div class="fee">수수료: 7%</div>
        </div>
        <div class="level-box" data-fee="5%">
          <div class="level">레벨 2</div>
          <div class="fee">수수료: 5%</div>
        </div>
        <div class="level-box" data-fee="3%">
          <div class="level">레벨 3</div>
          <div class="fee">수수료: 3%</div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
