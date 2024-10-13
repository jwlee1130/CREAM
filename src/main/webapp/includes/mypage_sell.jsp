
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>sell</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage_main.css">
</head>
<body>
<div class="mypage-container">
    <div class="wrap">
        <div class="sell">
            <h1>판매 내역</h1>
            <div class="parchase-area">
                <div class="parchase-all">
                    <h2>전체</h2>
                    <h2 style="color: #41B979">1</h2>
                </div>
                <div class="parchase-all">
                    <h2>진행중</h2>
                    <h2>0</h2>
                </div>
                <div class="parchase-all">
                    <h2>종료</h2>
                    <h2>0</h2>
                </div>
            </div>
            <div class="parchase-item">
                <div class="item-img">
                    <img src="../a4.webp" alt="">
                </div>
                <div class="item-name">
                    <h2>This is Shoes English name</h2>
                    <h3>265</h3>
                </div>
                <div class="item-date">
                    <h2>24/10/22</h2>
                </div>
                <div class="item-status">
                    정산 완료
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
