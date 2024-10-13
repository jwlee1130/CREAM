
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
            <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/shoes1.png" style="width: 525px; height: 525px;">
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
                <a href="#" id="show-modal">
                    <div class="item-parchase">
                        <span>구매</span>
                        <p>217,000원 <br> 즉시 구매가</p>
                    </div>
                </a>
                <a href="${pageContext.request.contextPath}/page/sell.jsp">
                    <div class="item-sell">
                        <span>판매</span>
                        <p>237,000원 <br> 즉시 판매가</p>
                    </div>
                </a>

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

<div id="modal" class="modal">
    <div class="modal-content">
        <span class="close-button">&times;</span>
        <div class="tabs-container">
            <div class="tabs">
                <button class="tab-button active" data-tab="240">240</button>
                <button class="tab-button" data-tab="260">260</button>
                <button class="tab-button" data-tab="280">280</button>
            </div>
            <div class="tab-content active" id="240">
                <div class="tab-content-list">
                    <ul>
                        <li>
                            <div class="list-inner">
                                <span class="rank-a">A</span>
                                <span>남은 시간 : 24:24:24</span>
                                <span>즉시 구매 : 190,000 원</span>
                                <span>현재 입찰가 : 150,000 </span>
                                <button>구매/입찰</button>
                            </div>
                        </li>
                        <li>
                            <div class="list-inner">
                                <span class="rank-b">B</span>
                                <span>남은 시간 : 24:24:24</span>
                                <span>즉시 구매 : 190,000 원</span>
                                <span>현재 입찰가 : 150,000 </span>
                                <button>구매/입찰</button>
                            </div>
                        </li>
                        <li><div class="list-inner">
                            <span class="rank-c">C</span>
                            <span>남은 시간 : 24:24:24</span>
                            <span>즉시 구매 : 190,000 원</span>
                            <span>현재 입찰가 : 150,000 </span>
                            <button>구매/입찰</button>
                        </div></li>
                    </ul>
                </div>
            </div>
            <div class="tab-content" id="260">
                <div class="tab-content-list">
                    <ul>
                        <li><div class="list-inner">
                            <span class="item-none">현재 해당 사이즈의 제품이 없습니다</span>
                        </div></li>
                    </ul>
                </div>
            </div>
            <div class="tab-content" id="280">
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
</script>
<script src="../js/script.js"></script>
</body>
</html>
