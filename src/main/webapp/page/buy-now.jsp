
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>구매</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/buy-now.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="transaction-container">
  <div class="transaction-wrapper">
    <div class="item-info">
      <div class="item-img">
        <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/shoes1.png" alt="" width="120px" height="120px">
      </div>
      <div class="item-description">
        <h2>This is product English name</h2>
        <h3>YUN-SUNG-BAO-1557</h3>
        <h3>여기는 제품 한글명이 들어갈 곳</h3>
        <div style="display:flex; justify-content: space-between">
          <h2>280</h2>
          <h2>117,000원</h2>
        </div>
      </div>
    </div>
    <div class="item-shipping">
      <div class="shipping-insert">
        <h2>배송 주소</h2>
        <button>주소 변경</button>
      </div>
      <a href="#"><div class="shipping-address">
        <p>주소를 추가해주세요 > </p>
      </div></a>
      <div class="item-address">
        <div class="reciever">
          <span>받는분</span>
          <p>홍길동</p>
        </div>
        <div class="reciever">
          <span>연락처</span>
          <p>010-1555-1557</p>
        </div>
        <div class="reciever">
          <span style="margin-right: 3.8rem;">주소</span>
          <p>[12551]경기 남양주시 미금로 10 (다산동, 송라빌딩) !!상세주소 들어가는영역!!</p>
        </div>
        <button>요청사항 없음 > </button>
      </div>
    </div>
    <div class="point">
      <h2>포인트</h2>
      <div class="point-inner">
        <div>
          <span>포인트 잔액</span>
        </div>
        <a href="">충전하러가기</a>
      </div>
    </div>
    <div class="item-receipt">
      <h1>최종 주문정보</h1>
      <div>
        <h2>상품 가격</h2>
        <h2 style="font-weight: bold">160,000원</h2>
      </div>
      <div>
        <h2><a href="#">수수료 💡</a></h2>
        <h2>12,500원</h2>
      </div>
      <div>
        <h2>배송비</h2>
        <h2>3000원</h2>
      </div>
      <div class="item-last-price">
        <h2>총 결제 금액</h2>
        <h2>190,000원</h2>
      </div>
    </div>
    <a href="front?key=purchase&methodName=nowBuy&productNo=${sale.productNo}&salesUserNo=${sale.userNo}&buyUserNo=${loginUser.no}&salesNo=${sale.no}&price=${sale.nowPrice}&address=${loginUser.address}"><div class="item-sell-btn">
      <p>190,000원 구매하기</p>
    </div></a>
  </div>
</div>
<jsp:include page="../includes/footer.jsp" />
</body>
</html>
