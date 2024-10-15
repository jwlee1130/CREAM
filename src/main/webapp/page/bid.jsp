
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>입찰</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bid.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script type="text/javascript">
  	$(function(){
  		
  		$("#a-bid").click(function(){
  			
  		
  			window.location.href="front?key=bid&methodName=bid&productNo=${sale.productNo}&buyUserNo=${loginUser.no}&salesNo=${sale.no}&price="+$("#bid").val();
  			
  		
  		
  		
  		});
  	
  	
  	});
  	
  	
  
  
  </script>
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
        <h2>${sale.product.modelNumber}</h2>
        <h3>${sale.product.engName}</h3>
        <h3>${sale.product.korName}</h3>
        <h4>${sale.shoesNo}</h4>
      </div>
    </div>
    <div class="item-price">
      <div class="current-price">
        <p>현재 입찰가</p>
        <span>${sale.bidAccount.price}원</span>        
      </div>
      <div class="instant-price">
        <p>즉시 구매가</p>
        <span>${sale.nowPrice}원</span></div>
    </div>
    <div class="time-left">
      <p>남은 시간 : </p>
      <p>
		${sale.regdate}</p>
    </div>
    <div class="tabs-container">
      <div class="tabs">
        <button class="tab-button active" data-tab="instantPurchase">즉시 구매</button>
        <button class="tab-button" data-tab="bidding">구매 입찰</button>
      </div>
      <div class="tab-content active" id="instantPurchase">
        <div class="tab-content-parchase">
          <p>즉시 구매가</p>
          <p>${sale.nowPrice}</p>
        </div>
        <div class="tab-content-total">
          <p>총 결제금액</p>
          <p>다음 화면에서 확인</p>
        </div>
        <a href="front?key=sales&methodName=nowBuyDetail&salesNo=${sale.no}&userNo=${loginUser.no}&nowPrice=${sale.nowPrice}"><div class="instant-parchase-btn">
          <span>즉시 구매 계속</span>
        </div>
        </a>
      </div>


      <div class="tab-content" id="bidding">
        <div class="tab-content-parchase">
          <p>구매 희망가</p>
          <div style="display: flex"><input type="text" id="bid"><span style="align-self: flex-end">원</span></div>
        </div>
        <div class="tab-content-total">
          <p>총 결제금액</p>
          <p>다음 화면에서 확인</p>
        </div>
        <a href="front?key=sales&methodName=nowBuyDetail&salesNo=${sale.no}"><div class="instant-parchase-btn">
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
