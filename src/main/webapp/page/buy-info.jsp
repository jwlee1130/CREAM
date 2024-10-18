
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>구매</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/buy-now.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="transaction-container">
  <div class="transaction-wrapper">
    <div class="item-info">
      <div class="item-img">
        <img src="${sale.salesImg.filePath}" alt="" width="120px" height="120px">
      </div>
      <div class="item-description">
        <h2>${sale.product.engName}</h2>
        <h3>${sale.product.modelNumber}</h3>
        <h3>${sale.product.korName}</h3>
        <div style="display:flex; justify-content: space-between">
          <h2>${sale.shoesSize.shoesSize}</h2>
          <h2>${sale.nowPrice}원</h2>
        </div>
      </div>
    </div>
    <div class="item-shipping">
      <div class="shipping-insert">
        <h2>배송 주소</h2>
      </div>
      <div class="shipping-address">
        <p>${loginUser.address}</p>
      </div>
      <div class="item-address">
        <div class="reciever">
          <span>받는분</span>
          <p>${loginUser.name}</p>
        </div>
        <div class="reciever">
          <span>연락처</span>
          <p>${loginUser.hp}</p>
        </div>
        <div class="reciever">
          <span style="margin-right: 3.8rem;">주소</span>
          <p>${loginUser.address}</p>
        </div>
      </div>
    </div>
    <div class="item-receipt">
      <h1>구매 내역</h1>
      <div>
        <h2>최종 내역</h2>
        <h2 style="font-weight: bold">${purchase.price}원</h2>
      </div>
    </div>
  </div>
</div>
<jsp:include page="../includes/footer.jsp" />
</body>
</html>
