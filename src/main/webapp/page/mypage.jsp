
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Document</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/mypage.css">
    
   	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>

<jsp:include page="../includes/header.jsp" />
<div class="container">
    <div class="main">
        <div class="main-side">
            <a href="#mypage_main"><h1>마이 페이지</h1></a>
            <ul>
                <h2>쇼핑 정보</h2>
                <li><a href="#mypage_parchase">구매내역</a></li>
                <li><a href="#mypage_sell">판매내역</a></li>
            </ul>
            <ul>
                <h2>회원 정보</h2>
                <li><a href="#mypage_user">회원 관리</a></li>
                <li><a href="#mypage_rank">판매자 등급</a></li>
                <li><a href="#mypage_point">포인트</a></li>
            </ul>
        </div>
        <div class="main-content" id="content">

        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp" />
<script>
  document.addEventListener('DOMContentLoaded', () => {
    const contentDiv = document.getElementById('content');

    // 초기 콘텐츠 로드 함수
    function loadContent() {
      const hash = window.location.hash.substring(1) || 'mypage_main';
      console.log(hash)
      fetchContent(hash);
    }

    // Fetch API를 사용하여 콘텐츠를 가져오는 함수
    function fetchContent(page) {
      fetch("${pageContext.request.contextPath}/includes/"+page+".jsp")
      .then(response => {
        if (!response.ok) {
          throw new Error(`Could not fetch ${page}.jsp: ${response.statusText}`);
        }
        return response.text();
      })
      .then(data => {
        contentDiv.innerHTML = data;
      })
      .catch(error => {
        contentDiv.innerHTML = `<h2>페이지를 찾을 수 없습니다.</h2>`;
        console.error(error);
      });
    }
    
    function fetchWishlist() {
        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'GET',
            data: {
                key: 'userAjax',
                methodName: 'selectWishlist'
            },
            dataType: 'json',
            success: function(result) {
                // Ajax 성공 시 데이터를 동적으로 추가
                let wishlistHtml = '';
                $.each(result, function(index, product) {
                    wishlistHtml += '<li class="wish-item">';
                    wishlistHtml += '<div class="wish-item-img">';
                    wishlistHtml += '<img style="width:200px; height:200px;" src="' + product.productImg.filePath + '" alt="' + product.engName + '">';
                    wishlistHtml += '</div>';
                    wishlistHtml += '<h2>' + product.engName + '</h2>';
                    wishlistHtml += '<h3>' + product.korName + '</h3>';
                    wishlistHtml += '</li>';
                });
                $('#wishlist-container').html(wishlistHtml);
            },
            error: function(error) {
                console.error("관심 상품을 불러오는 중 오류 발생: ", error);
            }
        });
    }

    // 페이지 로드 시 관심 상품 데이터 가져오기
    fetchWishlist();

	
    function fetchSales() {
        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'GET',
            data: {
                key: 'userAjax',
                methodName: 'salesByUserNo'
            },
            dataType: 'json',
            success: function(result) {
                let salesHtml = '';
                let totalSales = result.length;
                let inProgressCount = 0;
                let completedCount = 0;

                $.each(result, function(index, sale) {
                    if (sale.salesStatus === 1) {
                        inProgressCount++;
                    } else if (sale.salesStatus === 2) {
                        completedCount++;
                    }
                });

                const recentSale = result[0];
                if (recentSale) {
                    salesHtml += '<div class="parchase-item">';
                    
                    salesHtml += '<input type="hidden" name="userNo" value="' + recentSale.userNo + '">';
                    salesHtml += '<input type="hidden" name="productNo" value="' + recentSale.productNo + '">';
                    
                    salesHtml += '<div class="item-img">';
                    salesHtml += '<img style="width:100px; height:100px;" src="' + recentSale.filePath + '" alt="">';
                    salesHtml += '</div>';
                    
                    salesHtml += '<div class="item-name">';
                    salesHtml += '<h2>' + recentSale.engName + '</h2>';
                    salesHtml += '<h3>' + recentSale.shoesSize + '</h3>';
                    salesHtml += '</div>';
                    
                    salesHtml += '<div class="item-date">';
                    salesHtml += '<h2>' + recentSale.regdate + '</h2>';
                    salesHtml += '</div>';

                    salesHtml += '<div class="item-status">';
                    if (recentSale.salesStatus === 0) {
                        salesHtml += '승인대기';
                    } else if (recentSale.salesStatus === 1) {
                        salesHtml += '진행중';
                    } else if (recentSale.salesStatus === 2) {
                        salesHtml += '정산 완료';
                    }
                    salesHtml += '</div>';
                    salesHtml += '</div>';
                }

                $('#total-sales-count').text(totalSales);
                $('#in-progress-count').text(inProgressCount);
                $('#completed-count').text(completedCount);

                $('#sales-container').html(salesHtml);
            },
            error: function(error) {
                console.error("판매 내역을 불러오는 중 오류 발생: ", error);
            }
        });
    }
    
    function fetchAllSales() {
        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'GET',
            data: {
                key: 'userAjax',
                methodName: 'salesByUserNo'
            },
            dataType: 'json',
            success: function(result) {
                let salesHtml = '';
                let totalSales = result.length;
                let inProgressCount = 0;
                let completedCount = 0;

                // 전체 판매 내역을 가져와 화면에 표시
                $.each(result, function(index, sale) {
                    salesHtml += '<div class="parchase-item">';
                    
                    // Hidden inputs for userNo and productNo
                    salesHtml += '<input type="hidden" name="userNo" value="' + sale.userNo + '">';
                    salesHtml += '<input type="hidden" name="productNo" value="' + sale.productNo + '">';
                    
                    // Product image
                    salesHtml += '<div class="item-img">';
                    salesHtml += '<img style="width:100px; height:100px;" src="' + sale.filePath + '" alt="">';
                    salesHtml += '</div>';
                    
                    // Product name and shoe size
                    salesHtml += '<div class="item-name">';
                    salesHtml += '<h2>' + sale.engName + '</h2>';
                    salesHtml += '<h3>' + sale.shoesSize + '</h3>';
                    salesHtml += '</div>';
                    
                    // Registration date
                    salesHtml += '<div class="item-date">';
                    salesHtml += '<h2>' + sale.regdate + '</h2>';
                    salesHtml += '</div>';
                    
                    // Sales status
                    salesHtml += '<div class="item-status">';
                    if (sale.salesStatus === 0) {
                        salesHtml += '승인대기';
                    } else if (sale.salesStatus === 1) {
                        salesHtml += '진행중';
                        inProgressCount++;
                    } else if (sale.salesStatus === 2) {
                        salesHtml += '정산 완료';
                        completedCount++;
                    }
                    salesHtml += '</div>';
                    salesHtml += '</div>';
                });

                // 상태별 카운트 업데이트
                $('#total-sales-count').text(totalSales);
                $('#in-progress-count').text(inProgressCount);
                $('#completed-count').text(completedCount);

                // 모든 판매 내역 표시
                $('#sales-container').html(salesHtml);
            },
            error: function(error) {
                console.error("판매 내역을 불러오는 중 오류 발생: ", error);
            }
        });
    }
    
    function fetchPurchases() {
        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'GET',
            data: {
                key: 'purchase',  // 구매 내역용
                methodName: 'selectPurchase'
            },
            dataType: 'json',
            success: function(result) {
                let purchaseHtml = '';
                let totalPurchases = result.length;
                let inProgressCount = 0; // 고정값, 상황에 맞게 변경 가능
                let completedCount = 0;

                $.each(result, function(index, purchase) {
                    purchaseHtml += '<div class="parchase-item">';
                    
                    purchaseHtml += '<div class="item-img">';
                    purchaseHtml += '<img style="width:100px; height:100px;" src="' + purchase.filePath + '" alt="">';
                    purchaseHtml += '</div>';
                    
                    purchaseHtml += '<div class="item-name">';
                    purchaseHtml += '<h2>' + purchase.engName + '</h2>';
                    purchaseHtml += '<h3>' + purchase.shoesSize + '</h3>';
                    purchaseHtml += '</div>';
                    
                    purchaseHtml += '<div class="item-date">';
                    purchaseHtml += '<h2>' + purchase.regdate + '</h2>';
                    purchaseHtml += '</div>';
                    
                    purchaseHtml += '<div class="item-status">';
                    purchaseHtml += '결제 완료';
                    purchaseHtml += '</div>';
                    
                    purchaseHtml += '</div>';
                    
                    completedCount++;
                });

                $('#total-purchases-count').text(totalPurchases);
                $('#in-progress-purchases-count').text(inProgressCount);  // 기본값 유지
                $('#completed-purchases-count').text(completedCount);

                $('#purchase-container').html(purchaseHtml);
            },
            error: function(error) {
                console.error("구매 내역을 불러오는 중 오류 발생: ", error);
            }
        });
    }



    fetchSales();
    fetchAllSales();
    fetchPurchases();

    // 해시 변경 시 콘텐츠 로드
    window.addEventListener('hashchange', loadContent);

    // 초기 콘텐츠 로드
    loadContent();
  });
</script>
<script src="../js/script.js"></script>

</body>
</html>
