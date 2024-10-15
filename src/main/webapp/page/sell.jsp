
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.cream.dto.UserDTO" %>
<%
    // 세션에서 사용자 정보 가져오기
    UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
	String productNo = request.getParameter("productNo");
	String brandName = request.getParameter("brandName");
	String engName = request.getParameter("engName");
	String korName = request.getParameter("korName");
	String filePath = request.getParameter("filePath");

%>
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
        <img src="<%=filePath %>" alt="" width="120px" height="120px">
      </div>
      <div class="item-description">
        <h2><%= brandName %></h2>
    	<h3><%= engName %></h3>
    	<h3><%= korName %></h3>
        <h2>신발 사이즈 선택</h2>
		  <select id="shoesNo">
		    <option value="10">240</option>
		    <option value="20">260</option>
		    <option value="30">280</option>
		  </select>
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
      	<h2 style="font-weight: bold"> <input type="text" id="startingPrice"></h2>
      </div>
      <div>
        <h2>판매 희망가</h2>
        <h2 style="font-weight: bold"> <input type="text" id="desiredPrice"></h2>
      </div>
      <div>
        <h2>검수비</h2>
        <h2>무료</h2>
      </div>
      <div>
        <h2><a href="#">수수료 💡</a></h2>
        <h2 id="finalAmount">0원</h2>
      </div>
      <div>
        <h2>입찰 마감 기한</h2>
        <h2>등록일로부터 1주일</h2>
      </div>
      <div class="item-last-price">
        <h2>정산 금액</h2>
        <h1 id="commission">0원</h1>
      </div>
    </div>
    <div class="item-sell-btn">
      <a href="#" id="sellBtn" class="button-link"><p>판매하기</p></a>
    </div>
  </div>
</div>
<jsp:include page="../includes/footer.jsp" />
</body>
<script>
const productNo = '<%= productNo %>';
const userNo = '<%= loginUser.getNo() %>'
$(document).ready(function() {
    function calculateCommission(price) {
        // 서버에 수수료 계산 요청
        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'GET',
            data: {
                key: 'purchase',
                methodName: 'calculateCommission',
                userNo: userNo,	
                price: price
            },
            success: function(response) {
                const commission = response;
                const finalAmount = price - commission;

                $('#commission').text(commission + '원');
                $('#finalAmount').text(finalAmount + '원');
            },
            error: function(error) {
                console.error("수수료 계산 오류:", error);
                alert("수수료를 계산하는 중 오류가 발생했습니다.");
            }
        });
    }

    // 판매 희망가 입력 시마다 수수료와 최종 금액을 업데이트
    $('#desiredPrice').on('keyup', function() {
        const desiredPrice = parseInt($(this).val(), 10);

        if (!isNaN(desiredPrice)) {
            calculateCommission(desiredPrice);
        } else {
            $('#commission').text('0원');
            $('#finalAmount').text('0원');
        }
    });

    $('#sellBtn').on('click', function() {	
        const startingPrice = $('#startingPrice').val();
        const desiredPrice = $('#desiredPrice').val();
        const shoesNo  = $('#shoesNo').val();

        if (!startingPrice || !desiredPrice || !shoesNo) {
            alert("시작 입찰가와 판매 희망가, 신발 사이즈를 모두 선택해 주세요.");
            return;
        }
        if (startingPrice > desiredPrice) {
            alert("시작 입찰가는 판매 희망가보다 높을 수 없습니다. 다시 입력해 주세요.");
            return;
        }

        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'POST',
            data: {
                key: 'userAjax',
                methodName: 'insertSales',
                productNo: productNo,
                startingPrice: startingPrice,
                nowPrice: desiredPrice,
                shoesNo: shoesNo,
                salesStatus: 0,
                regdate: new Date().toISOString().slice(0, 10),
                grade: 'U'
            },
            success: function(response) {
                alert("판매가 등록되었습니다.");
                window.location.href = "../index.jsp";
            },
            error: function(error) {
                console.error(error);
                alert("등록에 실패했습니다. 다시 시도해 주세요.");
            }
        });
    });
});



</script>
</html>
