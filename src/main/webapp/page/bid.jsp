
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>입찰</title>
  <link rel="stylesheet" href="../css/reset.css">
  <link rel="stylesheet" href="../css/bid.css">
  <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="bid-container">
  <div class="bid-wrapper">
    <div class="item-info">
      <div class="item-logo">
        <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/shoes1.png" alt="">
      </div>
      <div class="item-description">
        <h2>신발 모델번호</h2>
        <h3>This is Shoes English name</h3>
        <h3>여기는 신발 한글 제품명 오는곳</h3>
        <h4>Size : ???</h4>
      </div>
    </div>
    <div class="item-price">
      <div class="current-price">
        <p>현재 입찰가</p>
        <span>269,000원</span>
      </div>
      <div class="instant-price">
        <p>즉시 구매가</p>
        <span>319,000원</span></div>
    </div>
    <div class="time-left">
      <p>남은 시간 : </p>
      <p>
        24:24:24
      </p>
    </div>
    <div class="tabs-container">
      <div class="tabs">
        <button class="tab-button active" data-tab="instantPurchase">즉시 구매</button>
        <button class="tab-button" data-tab="bidding">구매 입찰</button>
      </div>
      <div class="tab-content active" id="instantPurchase">
        <div class="tab-content-parchase">
          <p>즉시 구매가</p>
          <p>400,000</p>
        </div>
        <div class="tab-content-total">
          <p>총 결제금액</p>
          <p>다음 화면에서 확인</p>
        </div>
        <a href=""><div class="instant-parchase-btn">
          <span>즉시 구매 계속</span>
        </div>
        </a>
      </div>


      <div class="tab-content" id="bidding">
        <div class="tab-content-parchase">
          <p>구매 희망가</p>
          <div style="display: flex"><input type="text" name="" id=""><span style="align-self: flex-end">원</span></div>
        </div>
        <div class="tab-content-total">
          <p>총 결제금액</p>
          <p>다음 화면에서 확인</p>
        </div>
        <a href=""><div class="instant-parchase-btn">
          <span>구매 입찰 계속</span>
        </div>
        </a>
      </div>
    </div>

  </div>
</div>
<jsp:include page="../includes/footer.jsp" />

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const tabButtons = document.querySelectorAll(".tab-button");
    const tabContents = document.querySelectorAll(".tab-content");

    tabButtons.forEach(button => {
      button.addEventListener("click", function () {
        const targetTab = this.getAttribute("data-tab");

        // 모든 탭 버튼과 콘텐츠 비활성화
        tabButtons.forEach(btn => btn.classList.remove("active"));
        tabContents.forEach(content => content.classList.remove("active"));

        // 클릭한 탭 버튼 활성화
        this.classList.add("active");
        document.getElementById(targetTab).classList.add("active");
      });
    });
  });

</script>
<script src="../js/script.js"></script>
</body>
</html>
