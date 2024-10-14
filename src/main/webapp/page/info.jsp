
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>입찰</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bid.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="bid-container">
  <div class="bid-wrapper">
    <div class="item-info">
      <div class="item-logo">
        <img src="${sale.product.productImg.filePath}" alt="">
      </div>
      <div class="item-description">
        <h2>${purchase.no}</h2>
        <h3>${purchase.salesNo}</h3>
        <h3>${purchase.productNo}</h3>
        <h4>${purchase.price}</h4>
        <h5>${purchase.regdate}</h5>
      </div>
</body>
</html>
