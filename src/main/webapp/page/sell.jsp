
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>판매</title>
  <link rel="stylesheet" href="../css/reset.css">
  <link rel="stylesheet" href="../css/style.css">
  <link rel="stylesheet" href="../css/sell.css">
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
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
        <h2>YUN-SUNG-BAO-1557</h2>
        <h3>This is product English name</h3>
        <h3>여기는 제품 한글명이 들어갈 곳</h3>
        <h2>280</h2>
      </div>
    </div>
    <div class="item-shipping">
      <div>
        <h2>반송 주소</h2>
        <button>주소 변경</button>
      </div>
      <a href="#"><div class="shipping-address">
        <p>주소를 추가해주세요 > </p>
      </div></a>
    </div>
    <div class="item-receipt">
      <h1>최종 주문정보</h1>
      <div>
      	<h2>시작 입찰가</h2>
      	<h2 style="font-weight: bold"> <input type="text" id="startingPrice" placeholder="160,000원"></h2>
      </div>
      <div>
        <h2>판매 희망가</h2>
        <h2 style="font-weight: bold"> <input type="text" id="desiredPrice" placeholder="160,000원"></h2>
      </div>
      <div>
        <h2>검수비</h2>
        <h2>무료</h2>
      </div>
      <div>
        <h2><a href="#">수수료 💡</a></h2>
        <h2>12,500원</h2>
      </div>
      <div>
        <h2>입찰 마감 기한</h2>
        <h2>등록일로부터 1주일</h2>
      </div>
      <div class="item-last-price">
        <h2>정산 금액</h2>
        <h1>147,000원</h1>
      </div>
    </div>
    <div class="item-sell-btn">
      <button type="submit" id="sellBtn">판매하기</button>
    </div>
  </div>
</div>
<jsp:include page="../includes/footer.jsp" />
</body>
<script>
$(document).ready(function() {
    $('#sellBtn').on('click', function() {
        const startingPrice = $('#startingPrice').val();
        const desiredPrice = $('#desiredPrice').val();
        
        if (!startingPrice || !desiredPrice) {
            alert("시작 입찰가와 판매 희망가를 모두 입력해 주세요.");
            return;
        }

        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'POST',
            data: {
                key: 'userAjax',
                methodName: 'insertSales',
                productNo: "${productDetail.productNo}",
                startingPrice: startingPrice,
                nowPrice: desiredPrice,
                salesStatus: 0,
                regdate: new Date().toISOString().slice(0, 10),
                grade: 'U'
            },
            success: function(response) {
                alert("판매가 등록되었습니다.");
            },
            error: function(error) {
                console.error("판매 등록 오류:", error);
                alert("등록에 실패했습니다. 다시 시도해 주세요.");
            }
        });
    });
});

</script>
</html>
