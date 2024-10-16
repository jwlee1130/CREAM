
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Product</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
    <script type="text/javascript">
      $(function(){
        $("[name=btn]").click(function(){
          const dataTab = $(this).attr("data-tab");

          $.ajax({
            url: "ajax",
            type: "post",
            dataType: "json",
            data: {
              key: "sales",
              methodName: "selectAll",
              productNo: "${productDetail.no}",
              shoesNo: dataTab
            },

            success: function(data){
              let str = "";
              $.each(data, function(index, sales){
                console.log(sales.grade + "z");
                console.log(sales.nowPrice);

                const countdownId = 'countdown-' + sales.no;

                str += "<div class='list-inner'>";
                str += "<span class='rank-a'> 등급: " + sales.grade + "</span>   ";
                str += "<span>남은 시간 : <span class='countdown' id='" + countdownId + "'>00:00:00</span></span>   ";
                str += "<span>즉시 구매 : " + sales.nowPrice + "원</span>   ";
                str += "<span>현재 입찰가 : " + sales.bidAccount.price + "원</span>   ";
                str += "<span>판매상태 : " + sales.salesStatus + "</span>";
                str += "<button value='구매' data-info='" + sales.no + "'>구매/입찰</button>";
                str += "</div>";
              });

              $("#" + dataTab + " .tab-content-list").html(str);

              data.forEach(function(sales) {
                initializeCountdown(sales.no, parseInt(sales.regdate, 10));
              });
            },

            error: function(err){
              let str = "판매중인 사이즈가 없습니다.";
              $("#" + dataTab + " .tab-content-list").html(str);
            }
          });
        }); // 끝

        $(document).on("click", "button[value=구매]", function(){
          window.location.href = "front?key=sales&methodName=salesDetail&salesNo=" + encodeURIComponent($(this).attr("data-info"));
        });

        function initializeCountdown(saleNo, remainingTime) {
          const countdownElement = document.getElementById('countdown-' + saleNo);

          if (!countdownElement) return;

          const updateCountdown = () => {
            if (remainingTime < 0) {
              countdownElement.textContent = '시간 종료';
              return;
            }

            const hours = Math.floor(remainingTime / 3600);
            const minutes = Math.floor((remainingTime % 3600) / 60);
            const seconds = remainingTime % 60;

            function formattedTime(hours, minutes, seconds) {
              return [
                String(hours).padStart(2, '0'),
                String(minutes).padStart(2, '0'),
                String(seconds).padStart(2, '0')
              ].join(':');
            }
            countdownElement.textContent = formattedTime(hours, minutes, seconds);

            if (remainingTime > 0) {
              remainingTime--;
              setTimeout(updateCountdown, 1000);
            } else {
              countdownElement.textContent = '시간 종료';
              const purchaseButton = countdownElement.closest('.list-inner').querySelector('button[value=구매]');
              if (purchaseButton) {
                purchaseButton.disabled = true;
                purchaseButton.textContent = '입찰 종료';
              }
            }
          };

          updateCountdown();
        }

      });
    
    </script>
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container">
    <div class="item-wrapper">
        <div class="item-image">
            <img src="${productDetail.productImg[0].filePath}"  style="width: 525px; height: 525px;">
        </div>
        <c:if test="${productDetail.productImg[1].filePath !=null}">
        	 <div class="item-image">
            <img src="${productDetail.productImg[1].filePath}"  style="width: 525px; height: 525px;">
        	</div>
        </c:if>

        <div class="item-description">
            <div class="item-price">
                <p>최저 입찰가</p>
                <span>${bidPricing}</span>
                 <p>최저 즉시구매가</p>
                <span>${nowPricing}</span>
            </div>
            <div class="item-name">
                <h2>${productDetail.engName}</h2>
                <h2>${productDetail.korName}</h2>
            </div>
            <div class="item-detail">
                <ul>
                    <li class="item-detail-content">
                        <p>최근 거래가</p>
                        <span>${recentPrice}</span>
                    </li>
                    <li class="item-detail-content">
                        <p>발매가</p>
                        <span>${productDetail.releasePrice}</span>
                    </li>
                    <li class="item-detail-content">
                        <p>모델 번호</p>
                        <span>${productDetail.modelNumber}</span>
                    </li>
                    <li class="item-detail-content">
                        <p>출시일</p>
                        <span>${productDetail.release}</span>
                    </li>
                </ul>
            </div>
            <div class="item-transaction">
                <a href="#" id="show-modal">
                    <div class="item-parchase">
                        <span>구매</span>
                        <p>217,000원 <br> 즉시 구매가</p>
                    </div>
                </a>
                <a href="${pageContext.request.contextPath}/page/sell.jsp?productNo=${productDetail.no}&brandName=${productDetail.brandName.name}&engName=${productDetail.engName}&korName=${productDetail.korName}">
                    <div class="item-sell">
                        <span>판매</span>
                        <p>237,000원 <br> 즉시 판매가</p>
                    </div>
                </a>

            </div>
            <div class="item-wish">
                <a href="javascript:void(0);" id="add-to-wishlist" data-id="${productDetail.no}" class="wishlist-link"><p>관심상품</p></a>
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
<%--                this is chart area--%>
                <jsp:include page="../chart/productPageChart.jsp"/>
            </div>
        </div>
        <div class="item-concluded">
            <h2>체결거래</h2>
            <div class="concluded-transaction">
<%--                this is concluded-transaction area--%>
                <jsp:include page="../chart/transactionChart.jsp"/>
            </div>
        </div>
    </div>
</div>

<div id="modal" class="modal">
    <div class="modal-content">
        <span class="close-button">&times;</span>
        <div class="tabs-container">
            <div class="tabs">
                <button class="tab-button active" name="btn" data-tab="10">240</button>
                <button class="tab-button" name="btn"data-tab="20">260</button>
                <button class="tab-button" name="btn" data-tab="30">280</button>
            </div>
            <div class="tab-content active" id="10">
                <div class="tab-content-list">
                    <ul>
                    </ul>
                </div>
            </div>
            <div class="tab-content" id="20">
                <div class="tab-content-list">
                    <ul>
                        <li><div class="list-inner">
                            <span class="item-none">현재 해당 사이즈의 제품이 없습니다</span>
                        </div></li>
                    </ul>
                </div>
            </div>
            <div class="tab-content" id="30">
                <div class="tab-content-list">
                    <!--            여기 뿌려야함-->
                    <ul>
                        <li>
                            <div class="list-inner">
                                <span class="rank-a">A</span>
                                <span>남은 시간 : 24:24:24</span>
                                <span>즉시 구매 : 190,000 원 /</span>
                                <span>현재 입찰가 : 150,000 </span>
                                <button>구매/입찰</button>
                            </div>
                        </li>
                        <li>
                            <div class="list-inner">
                                <span class="rank-a">A</span>
                                <span>남은 시간 : 24:24:24</span>
                                <span>즉시 구매 : 190,000 원 /</span>
                                <span>현재 입찰가 : 150,000 </span>
                                <button>구매/입찰</button>
                            </div>
                        </li>
                        <li><div class="list-inner">
                            <span class="rank-a">A</span>
                            <span>남은 시간 : 24:24:24</span>
                            <span>즉시 구매 : 190,000 원 /</span>
                            <span>현재 입찰가 : 150,000 </span>
                            <button>구매/입찰</button>
                        </div></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp" />
<script>
  document.addEventListener("DOMContentLoaded", function () {
    const modal = document.getElementById("modal");
    const showModalButton = document.getElementById("show-modal");
    const closeButton = document.querySelector(".close-button");

    // 모달을 보여주는 함수
    showModalButton.addEventListener("click", function (event) {
      event.preventDefault(); // 링크 기본 동작 방지
      modal.style.display = "block"; // 모달 표시
    });

    // 모달 닫기 버튼
    closeButton.addEventListener("click", function () {
      modal.style.display = "none"; // 모달 숨김
    });



    // 모달 외부 클릭 시 닫기
    // window.addEventListener("click", function (event) {
    //   if (event.target == modal) {
    //     modal.style.display = "none"; // 모달 숨김
    //   }
    // });
  });

</script>
<script !src="">
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
  document.addEventListener('DOMContentLoaded', () => {
	    const wishlistButton = document.getElementById('add-to-wishlist');
	    
	    wishlistButton.addEventListener('click', function() {
	        const productNo = this.getAttribute('data-id');
	        $.ajax({
	            url: '${pageContext.request.contextPath}/ajax',
	            method: 'POST',
	            data: {
	                key: 'userAjax',
	                methodName: 'toggleWishlist',
	                product_no: productNo
	            },
	            dataType: 'json',
	            success: function(response) {
	                if (response.status === 'added') {
	                    alert("관심상품이 추가되었습니다.");
	                } else if (response.status === 'removed') {
	                    alert("관심상품이 해제되었습니다.");
	                } else {
	                    alert("처리에 실패했습니다. 다시 시도해 주세요.");
	                }
	            },
	            error: function(error) {
	                console.error("Error: ", error);
	                alert("오류가 발생했습니다. 다시 시도해 주세요.");
	            }
	        });
	    });
	});
</script>

<script src="../js/script.js"></script>
</body>
</html>
