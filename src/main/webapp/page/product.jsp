
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/product.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container">
    <div class="item-wrapper">
        <div class="item-image">
            <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/shoes1.png"  style="width: 525px; height: 525px;">
        </div>
        <div class="item-description">
            <div class="item-price">
                <p>즉시 구매가</p>
                <h1>219,000</h1>
            </div>
            <div class="item-name">
                <h2>This area for shoes English product name</h2>
                <h2>여기는 신발 한글 제품명이 나오는곳이에요</h2>
            </div>
            <div class="item-detail">
                <ul>
                    <li class="item-detail-content">
                        <p>최근 거래가</p>
                        <span>200,000</span>
                    </li>
                    <li class="item-detail-content">
                        <p>발매가</p>
                        <span>180,000</span>
                    </li>
                    <li class="item-detail-content">
                        <p>모델 번호</p>
                        <span>YUNSUNG-BAO-1557</span>
                    </li>
                    <li class="item-detail-content">
                        <p>출시일</p>
                        <span>24/10/11</span>
                    </li>
                    <li class="item-detail-content">
                        <p>대표 색상</p>
                        <span>pale night</span>
                    </li>
                </ul>
            </div>
            <div class="item-transaction">
                <a href="./bid.jsp">
                    <div class="item-parchase">
                        <span>구매</span>
                        <p>217,000원 <br> 즉시 구매가</p>
                    </div>
                </a>
                <div class="item-sell">
                    <span>판매</span>
                    <p>237,000원 <br> 즉시 판매가</p>
                </div>
            </div>
            <div class="item-wish">
                <p>관심상품</p>
            </div>
        </div>




    </div>
    <div class="item-info-wrapper">
        <div class="item-quote">
            <h2>시세</h2>
            <div class="item-quote-button">
                <button class="one-week">1주일</button>
                <button class="one-month">1개월</button>
            </div>
            <div class="item-quote-chart">
                this is chart area
            </div>
        </div>
        <div class="item-concluded">
            <h2>체결거래</h2>
            <div class="concluded-transaction">
                this is concluded-transaction area
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp" />
</body>
</html>
