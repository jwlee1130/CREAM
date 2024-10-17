
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
          <h2><fmt:formatNumber>${sale.nowPrice}</fmt:formatNumber>원</h2>
        </div>
      </div>
    </div>
    <div class="item-receipt">
      <h1>판매 내역</h1>
      <div>
        <h2>상품 가격</h2>
        <h2 style="font-weight: bold"><fmt:formatNumber>${purchase.price}</fmt:formatNumber>원</h2>
      </div>
      <div>
        <h2><a href="#">수수료 💡</a></h2>
        <h2><fmt:formatNumber>${commission}</fmt:formatNumber></h2>
      </div>
      <div class="item-last-price">
        <h2>총 판매 금액</h2>
        <h2><fmt:formatNumber>${purchase.price - commission}</fmt:formatNumber>원</h2>
      </div>
    </div>
  </div>
</div>
<jsp:include page="../includes/footer.jsp" />
</body>
</html>
