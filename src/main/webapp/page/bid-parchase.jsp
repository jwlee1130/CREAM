
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>입찰</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bid-parchase.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

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
          <h2><fmt:formatNumber>${sale.nowPrice}</fmt:formatNumber>원</h2>
        </div>
      </div>
    </div>
    <div class="item-shipping">
      <div class="shipping-insert">
        <h2>배송 주소</h2>
      </div>
      <a href="#"><div class="shipping-address">
        <p>${loginUser.address}</p>
      </div></a>
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
    <div class="point">
      <h2>포인트</h2>
      <div class="point-inner">
        <div>
          <span><fmt:formatNumber>${loginUser.cash}</fmt:formatNumber></span>
        </div>
        <a href="${pageContext.request.contextPath}/page/mypage.jsp#mypage_point">충전하러가기</a>
      </div>
    </div>
    <div class="item-receipt">
      <h1>최종 주문정보</h1>
      <div>
        <h2>희망 입찰가</h2>
        <h2 style="font-weight: bold"><fmt:formatNumber>${bidPrice}</fmt:formatNumber>원</h2>
      </div>
    </div>
    <a href="front?key=bid&methodName=bid&productNo=${sale.productNo}&buyUserNo=${loginUser.no}&salesNo=${sale.no}&price=${bidPrice}"><div class="item-sell-btn">
      <p>${bidPrice}원 입찰하기</p>
    </div></a>
  </div>
</div>
<jsp:include page="../includes/footer.jsp" />
</body>
</html>