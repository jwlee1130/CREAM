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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"/>
    <style>
        .wished {
          background-color: #222;
          color: white;
        }
        .swiper {
          max-width: 500px;
        }
        .swiper-button-next, .swiper-button-prev {
          color: #9a9a9a;
        }
        /* 비활성화된 버튼의 스타일 */
        .disabled-button {
          pointer-events: none;
          opacity: 0.5;
          cursor: not-allowed;
        }
    </style>
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container">
    <div class="item-wrapper">
        <div class="swiper">
            <div class="swiper-wrapper">
                <div class="swiper-slide">
                    <img src="${productDetail.productImg[0].filePath}" style="width: 525px; height: 525px;">
                </div>
                <c:if test="${productDetail.productImg[1].filePath!=null}">
                <div class="swiper-slide">
                    <img src="${productDetail.productImg[1].filePath}" style="width: 525px; height: 525px;">
                </div>
                </c:if>
            </div>
            <div class="swiper-button-next"></div>
            <div class="swiper-button-prev"></div>
            <div class="swiper-scrollbar"></div>
        </div>
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

            <!-- 구매, 판매 및 관심상품 버튼 -->
            <div class="item-transaction">
                <!-- 구매 버튼 -->
                <c:choose>
                    <c:when test="${sessionScope.isAdmin}">
                        <!-- 어드민인 경우 비활성화된 버튼, 동일한 CSS 클래스 유지 -->
                        <div class="item-parchase disabled-button">
                            <span>구매</span>
                            <p>217,000원 <br> 즉시 구매가</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <!-- 어드민이 아닌 경우 활성화된 버튼 -->
                        <a href="#" id="show-modal" class="item-parchase">
                            <span>구매</span>
                            <p>217,000원 <br> 즉시 구매가</p>
                        </a>
                    </c:otherwise>
                </c:choose>

                <!-- 판매 버튼 -->
                <c:choose>
                    <c:when test="${sessionScope.isAdmin}">
                        <!-- 어드민인 경우 비활성화된 버튼, 동일한 CSS 클래스 유지 -->
                        <div class="item-sell disabled-button">
                            <span>판매</span>
                            <p>237,000원 <br> 즉시 판매가</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <!-- 어드민이 아닌 경우 활성화된 버튼 -->
                        <a href="${pageContext.request.contextPath}/page/sell.jsp?productNo=${productDetail.no}&brandName=${productDetail.brandName.name}&engName=${productDetail.engName}&korName=${productDetail.korName}" class="item-sell">
                            <span>판매</span>
                            <p>237,000원 <br> 즉시 판매가</p>
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>

            <!-- 관심상품 버튼 -->
            <c:choose>
                <c:when test="${sessionScope.isAdmin}">
                    <div class="item-wish disabled-button">
                        <p>관심상품</p>
                    </div>
                </c:when>
                <c:otherwise>
                    <a href="javascript:void(0);" id="add-to-wishlist" data-id="${productDetail.no}" class="item-wish">
                        <p>관심상품</p>
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="item-info-wrapper">
        <div class="item-quote">
            <h2>시세</h2>
            <div class="item-quote-chart">
                <jsp:include page="../chart/productPageChart.jsp"/>
            </div>
        </div>
        <div class="item-concluded">
            <h2>체결거래</h2>
            <div class="concluded-transaction">
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

    showModalButton.addEventListener("click", function (event) {
      event.preventDefault(); 
      modal.style.display = "block"; 
    });

    closeButton.addEventListener("click", function () {
      modal.style.display = "none";
    });
  });

  $(document).ready(function() {
    const wishlistButton = $('#add-to-wishlist');
    wishlistButton.on('click', function() {
      const productNo = $(this).data('id');
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
<script>
  document.addEventListener('DOMContentLoaded', function () {
    const swiper = new Swiper('.swiper', {
      slidesPerView: 1,
      navigation: {
        nextEl: '.swiper .swiper-button-next',
        prevEl: '.swiper-button-prev',
      },
      scrollbar: {
        el: '.swiper-scrollbar',
      },
      speed: 600,
    });
  });
</script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
</body>
</html>
