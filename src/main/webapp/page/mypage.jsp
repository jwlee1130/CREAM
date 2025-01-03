
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.cream.dto.UserDTO" %>
<%
    // 세션에서 사용자 정보 가져오기
    UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
%>
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
        if(page==="mypage_sell"){
           fetchAllSales();
        }else if(page === "mypage_main"){
           fetchLatestPurchaseOrBid();
            fetchSales();
            fetchWishlist();
        }else if(page==="mypage_parchase"){
           fetchAllPurchasesAndBids();
        }else if(page ==="mypage_user"){
           loadUser();
           bindChangeButtons();
        }else if(page==="mypage_rank"){
           fetchUserRank();
        }
      })
      .catch(error => {
        contentDiv.innerHTML = `<h2>페이지를 찾을 수 없습니다.</h2>`;
        showError(`페이지를 찾을 수 없습니다: ${error.message}`);
      });
    }

    function bindChangeButtons() {
      const changeButtons = contentDiv.querySelectorAll('.change-btn');

      changeButtons.forEach(button => {
        button.addEventListener('click', () => {
          const infoItem = button.closest('.info-item');
          const input = infoItem.querySelector('input');

          input.disabled = false;
          input.focus();

          button.style.display = 'none';

          // '저장' 및 '취소' 버튼 생성
          const saveBtn = document.createElement('button');
          saveBtn.textContent = '저장';
          saveBtn.classList.add('save-btn');

          const cancelBtn = document.createElement('button');
          cancelBtn.textContent = '취소';
          cancelBtn.classList.add('cancel-btn');

          // 버튼 추가
          infoItem.appendChild(saveBtn);
          infoItem.appendChild(cancelBtn);

          // '저장' 버튼 클릭 이벤트
          saveBtn.addEventListener('click', () => {
            const newValue = input.value.trim();
            if (newValue === '') {
              showError('값을 입력해주세요.');
              return;
            }

            // 비밀번호인 경우 실제 값을 표시하지 않음
            if (infoItem.getAttribute('data-field') === 'password') {
              input.value = '********';
            }
            saveClick(infoItem, newValue);
            // 입력 필드 비활성화
            input.disabled = true;

            // '저장' 및 '취소' 버튼 제거
            saveBtn.remove();
            cancelBtn.remove();

            // '변경' 버튼 다시 표시
            button.style.display = 'inline-block';

            //여기서 부터 ajax로 수정된거 보내면 됨 /////
          });

          cancelBtn.addEventListener('click', () => {
            input.value = input.defaultValue;

            input.disabled = true;

            saveBtn.remove();
            cancelBtn.remove();

            button.style.display = 'inline-block';
          });
        });
      });

      const changeAddressBtn = contentDiv.querySelector('.change-address-btn');
      if (changeAddressBtn) {
        changeAddressBtn.addEventListener('click', () => {
          alert('주소 변경 구현해야함.');
        });
      }
    }
    
    
    
    let currentWishlistIndex = 0;
    const itemsPerPage = 3;
    let uniqueProducts = [];

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
                const productMap = {};
                uniqueProducts = [];
                $.each(result, function(index, product) {
                    if (!productMap[product.no]) {
                        productMap[product.no] = product;
                        uniqueProducts.push(product);
                    }
                });

                uniqueProducts.reverse();
                currentWishlistIndex = 0;
                $('#wishlist-container').html('');

                loadMoreWishlistItems();

                if (uniqueProducts.length > itemsPerPage) {
                    $('#wishlist-container').after(
                        '<button id="load-more" class="load-more" style="display: block; margin: 20px auto;">더보기</button>'
                    );
                    $('#load-more').show();
                    $('#load-more').off('click').on('click', loadMoreWishlistItems);
                } else {
                    $('#load-more').remove();
                }
            },
            error: function() {
                showError("관심 상품을 불러오는 중 오류가 발생했습니다");
            }
        });
    }

    function loadMoreWishlistItems() {
        let wishlistHtml = '';

        for (let i = currentWishlistIndex; i < currentWishlistIndex + itemsPerPage && i < uniqueProducts.length; i++) {
            const product = uniqueProducts[i];
            wishlistHtml += '<li class="wish-item">';
            wishlistHtml += '<div class="wish-item-img">';
            wishlistHtml += '<a href="${pageContext.request.contextPath}/front?key=product&methodName=detail&no=' + product.no + '">';
            wishlistHtml += '<img style="width:200px; height:200px;" src="' + product.productImg.filePath + '" alt="' + product.engName + '">';
            wishlistHtml += '</a>';
            wishlistHtml += '</div>';
            wishlistHtml += '<h2>' + product.engName + '</h2>';
            wishlistHtml += '<h3>' + product.korName + '</h3>';
            wishlistHtml += '</li>';
        }

        $('#wishlist-container').append(wishlistHtml);
        currentWishlistIndex += itemsPerPage;

        if (currentWishlistIndex >= uniqueProducts.length) {
            $('#load-more').hide();
        }
    }




   
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
                    if (sale.salesStatus === 0 || sale.salesStatus === 1) {
                        inProgressCount++;
                    } else if (sale.salesStatus === 2) {
                        completedCount++;
                    }
                });

                result.slice(0, 2).forEach(function(sale) {
                    salesHtml += '<div class="parchase-item">';

                    salesHtml += '<input type="hidden" name="userNo" value="' + sale.userNo + '">';
                    salesHtml += '<input type="hidden" name="productNo" value="' + sale.productNo + '">';

                    salesHtml += '<div class="item-img">';

                    if (sale.salesStatus === 2) {
                        salesHtml += '<a href="${pageContext.request.contextPath}/front?key=purchase&methodName=sellInfo&saleUserNo=' + sale.userNo + '&salesNo=' + sale.no + '">';
                    } else if (sale.salesStatus === 1) {
                        salesHtml += '<a href="${pageContext.request.contextPath}/front?key=sales&methodName=salesDetail&salesNo=' + sale.no + '">';
                    } else {
                        salesHtml += '<div style="pointer-events: none; cursor: not-allowed;">';
                    }

                    salesHtml += '<img style="width:100px; height:100px;" src="' + sale.filePath + '" alt="">';

                    if (sale.salesStatus === 0) {
                        salesHtml += '</div>'; // 승인 대기 상태일 때 div 태그 종료
                    } else {
                        salesHtml += '</a>'; // 링크일 경우 a 태그 종료
                    }

                    salesHtml += '</div>';

                    salesHtml += '<div class="item-name">';
                    salesHtml += '<h2>' + sale.engName + '</h2>';
                    salesHtml += '<h3>' + sale.shoesSize + '</h3>';
                    salesHtml += '</div>';

                    salesHtml += '<div class="item-date">';
                    salesHtml += '<h2>' + sale.regdate + '</h2>';
                    salesHtml += '</div>';

                    salesHtml += '<div class="item-status">';
                    if (sale.salesStatus === 0) {
                        salesHtml += '승인대기';
                    } else if (sale.salesStatus === 1) {
                        salesHtml += '진행중';
                    } else if (sale.salesStatus === 2) {
                        salesHtml += '정산 완료';
                    }
                    salesHtml += '</div>';
                    salesHtml += '</div>';
                });

                $('#total-sales-count').text(totalSales);
                $('#in-progress-count').text(inProgressCount);
                $('#completed-count').text(completedCount);

                $('#sales-container').html(salesHtml);
            },
            error: function() {
                showError("판매 내역을 불러오는 중 오류가 발생했습니다");
            }
        });
    }

    
    function fetchAllSales() {
        let sales = [];
        let currentIndex = 0;
        const itemsPerPage = 5;

        $('#sales-container').html(''); // 초기화
        $('#load-more-sales').hide(); // 초기에는 숨김 상태로 설정하고 이후 로딩 결과에 따라 표시

        $('#load-more-sales').off('click').on('click', loadMoreSales);

        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'GET',
            data: {
                key: 'userAjax',
                methodName: 'salesByUserNo'
            },
            dataType: 'json',
            success: function(result) {
                sales = result;
                updateSalesCounts(sales); // 카운트 업데이트
                loadMoreSales(); // 첫 로드 시 5개씩 보여줌

                if (sales.length > itemsPerPage) {
                    $('#load-more-sales').show(); // 데이터가 충분히 있는 경우에만 더보기 버튼을 표시
                }
            },
            error: function() {
                showError("판매 내역을 불러오는 중 오류가 발생했습니다");
            }
        });

        function loadMoreSales() {
            let salesHtml = '';
            const salesItems = sales.slice(currentIndex, currentIndex + itemsPerPage);

            salesItems.forEach(function(sale) {
                salesHtml += '<div class="parchase-item">';
                salesHtml += '<div class="item-img">';

                if (sale.salesStatus === 2) {
                    salesHtml += '<a href="${pageContext.request.contextPath}/front?key=purchase&methodName=sellInfo&saleUserNo=' + sale.userNo + '&salesNo=' + sale.no + '">';
                } else if (sale.salesStatus === 1) {
                    salesHtml += '<a href="${pageContext.request.contextPath}/front?key=sales&methodName=salesDetail&salesNo=' + sale.no + '">';
                } else {
                    salesHtml += '<div style="pointer-events: none; cursor: not-allowed;">';
                }

                salesHtml += '<img style="width:100px; height:100px;" src="' + sale.filePath + '" alt="">';

                if (sale.salesStatus === 0) {
                    salesHtml += '</div>';
                } else {
                    salesHtml += '</a>';
                }

                salesHtml += '</div>';
                salesHtml += '<div class="item-name">';
                salesHtml += '<h2>' + sale.engName + '</h2>';
                salesHtml += '<h3>' + sale.shoesSize + '</h3>';
                salesHtml += '</div>';
                salesHtml += '<div class="item-date">';
                salesHtml += '<h2>' + sale.regdate + '</h2>';
                salesHtml += '</div>';
                salesHtml += '<div class="item-status">';

                if (sale.salesStatus === 0) {
                    salesHtml += '승인대기';
                } else if (sale.salesStatus === 1) {
                    salesHtml += '진행중';
                } else if (sale.salesStatus === 2) {
                    salesHtml += '정산 완료';
                }
                
                salesHtml += '</div>';
                salesHtml += '</div>';
            });

            $('#sales-container').append(salesHtml);
            currentIndex += itemsPerPage;

            if (currentIndex >= sales.length) {
                $('#load-more-sales').hide();
            }
        }

        function updateSalesCounts(sales) {
            let inProgressCount = 0;
            let completedCount = 0;

            sales.forEach(function(sale) {
                if (sale.salesStatus === 0 || sale.salesStatus === 1) {
                    inProgressCount++;
                } else if (sale.salesStatus === 2) {
                    completedCount++;
                }
            });

            $('#total-sales-count').text(sales.length);
            $('#in-progress-count').text(inProgressCount);
            $('#completed-count').text(completedCount);
        }
    }







    
    function fetchAllPurchasesAndBids() {
        let purchases = [];
        let bids = [];
        let currentPurchaseIndex = 0;
        let currentBidIndex = 0;
        const itemsPerPage = 5;

        $('#purchase-container').html(''); // 초기화
        $('#load-more').hide(); // 초기에는 숨김 상태로 설정하고 이후 로딩 결과에 따라 표시

        $('#load-more').off('click').on('click', loadMoreItems);

        $.when(
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'GET',
                data: { key: 'purchase', methodName: 'selectPurchase' },
                dataType: 'json'
            }),
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'GET',
                data: { key: 'bidAjax', methodName: 'findBidsByUserNo', userNo: '<%= loginUser.getNo() %>' },
                dataType: 'json'
            })
        ).done(function(purchaseResult, bidResult) {
            purchases = purchaseResult[0];
            bids = bidResult[0];

            updateCounts(); // 카운트 업데이트
            loadMoreItems(); // 첫 로드 시 5개씩 보여줌

            if (purchases.length + bids.length > 0) {
                $('#load-more').show(); // 데이터가 있는 경우에만 더보기 버튼을 표시
            }
        }).fail(function() {
            showError("구매 또는 입찰 내역을 불러오는 중 오류가 발생했습니다");
        });

        function loadMoreItems() {
            let purchaseHtml = '';
            
            // 구매와 입찰 항목을 합쳐 총 5개를 추출
            let displayedItems = 0;

            while (displayedItems < itemsPerPage && (currentPurchaseIndex < purchases.length || currentBidIndex < bids.length)) {
                if (currentPurchaseIndex < purchases.length && displayedItems < itemsPerPage) {
                    let purchase = purchases[currentPurchaseIndex++];
                    purchaseHtml += '<div class="parchase-item">';
                    purchaseHtml += '<div class="item-img">';
                    purchaseHtml += '<a href="${pageContext.request.contextPath}/front?key=purchase&methodName=buyInfo&buyUserNo=' + purchase.buyUserNo + '&salesNo=' + purchase.salesNo + '">';
                    purchaseHtml += '<img style="width:100px; height:100px;" src="' + purchase.filePath + '" alt="">';
                    purchaseHtml += '</a>';
                    purchaseHtml += '</div>';
                    purchaseHtml += '<div class="item-name">';
                    purchaseHtml += '<h2>' + purchase.engName + '</h2>';
                    purchaseHtml += '<h3>' + purchase.shoeSize + '</h3>';
                    purchaseHtml += '</div>';
                    purchaseHtml += '<div class="item-date">';
                    purchaseHtml += '<h2>' + purchase.regdate + '</h2>';
                    purchaseHtml += '</div>';
                    purchaseHtml += '<div class="item-status">결제 완료</div>';
                    purchaseHtml += '</div>';
                    displayedItems++;
                }

                if (currentBidIndex < bids.length && displayedItems < itemsPerPage) {
                    let bid = bids[currentBidIndex++];
                    purchaseHtml += '<div class="parchase-item">';
                    purchaseHtml += '<div class="item-img">';
                    purchaseHtml += '<img style="width:100px; height:100px;" src="' + bid.filePath + '" alt="">';
                    purchaseHtml += '</div>';
                    purchaseHtml += '<div class="item-name">';
                    purchaseHtml += '<h2>' + bid.engName + '</h2>';
                    purchaseHtml += '<h3>' + bid.shoesSize + '</h3>';
                    purchaseHtml += '</div>';
                    purchaseHtml += '<div class="item-date">';
                    purchaseHtml += '<h2>' + bid.regdate + '</h2>';
                    purchaseHtml += '</div>';
                    purchaseHtml += '<div class="item-status">입찰 진행 중</div>';
                    purchaseHtml += '</div>';
                    displayedItems++;
                }
            }

            $('#purchase-container').append(purchaseHtml);

            // 모든 항목이 다 로드되면 버튼 숨기기
            if (currentPurchaseIndex >= purchases.length && currentBidIndex >= bids.length) {
                $('#load-more').hide();
            }
        }

        function updateCounts() {
            $('#total-purchases-count').text(purchases.length + bids.length);
            $('#in-progress-purchases-count').text(bids.length);
            $('#completed-purchases-count').text(purchases.length);
        }
    }





    
    
    

    
    function fetchPurchases() {
        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'GET',
            data: {
                key: 'purchase',
                methodName: 'selectPurchase'
            },
            dataType: 'json',
            success: function(result) {
                let purchaseHtml = '';
                let totalPurchases = result.length;
                let inProgressCount = 0;
                let completedCount = 0;

                if (result.length > 0) {
                    let purchase = result[0]; 
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
                }

                $.each(result, function(index, purchase) {
                    completedCount++;
                });

                $('#total-purchases-count').text(totalPurchases);
                $('#in-progress-purchases-count').text(inProgressCount);  // 기본값 유지
                $('#completed-purchases-count').text(completedCount);

                $('#purchase-container').html(purchaseHtml);
            },
            error: function() {
               showError("구매 내역을 불러오는 중 오류가 발생했습니다");
            }
        });
    }
    
    
    
    function fetchLatestPurchaseOrBid() {
        let purchases = [];
        let bids = [];

        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'GET',
            data: {
                key: 'purchase',
                methodName: 'selectPurchase'
            },
            dataType: 'json',
            success: function(result) {
                purchases = result;
                fetchLatestBids();
            },
            error: function(error) {
                showError("구매 내역을 불러오는 중 오류가 발생했습니다");
            }
        });

        function fetchLatestBids() {
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'GET',
                data: {
                    key: 'bidAjax',
                    methodName: 'findBidsByUserNo',
                    userNo: '<%= loginUser.getNo() %>' // 세션에서 사용자의 ID를 사용합니다.
                },
                dataType: 'json',
                success: function(result) {
                    bids = result;
                    renderLatestPurchaseOrBid(purchases, bids);
                },
                error: function(error) {
                    showError("입찰 내역을 불러오는 중 오류가 발생했습니다");
                }
            });
        }

        function renderLatestPurchaseOrBid(purchases, bids) {
            let purchaseHtml = '';

            // 최신 구매 내역
            const latestPurchase = purchases[0];
            if (latestPurchase) {
                purchaseHtml += '<div class="parchase-item">';
                purchaseHtml += '<div class="item-img">';
                purchaseHtml += '<a href="${pageContext.request.contextPath}/front?key=purchase&methodName=buyInfo&buyUserNo=' + latestPurchase.buyUserNo + '&salesNo=' + latestPurchase.salesNo + '">';
                purchaseHtml += '<img style="width:100px; height:100px;" src="' + latestPurchase.filePath + '" alt="">';
                purchaseHtml += '</a>';
                purchaseHtml += '</div>';
                purchaseHtml += '<div class="item-name">';
                purchaseHtml += '<h2>' + latestPurchase.engName + '</h2>';
                purchaseHtml += '<h3>' + latestPurchase.shoeSize + '</h3>';
                purchaseHtml += '</div>';
                purchaseHtml += '<div class="item-date">';
                purchaseHtml += '<h2>' + latestPurchase.regdate + '</h2>';
                purchaseHtml += '</div>';
                purchaseHtml += '<div class="item-status">결제 완료</div>';
                purchaseHtml += '</div>';
            }

            // 최신 입찰 내역
            const latestBid = bids[0];
            if (latestBid) {
                purchaseHtml += '<div class="parchase-item">';
                purchaseHtml += '<div class="item-img">';
                purchaseHtml += '<img style="width:100px; height:100px;" src="' + latestBid.filePath + '" alt="">';
                purchaseHtml += '</div>';
                purchaseHtml += '<div class="item-name">';
                purchaseHtml += '<h2>' + latestBid.engName + '</h2>';
                purchaseHtml += '<h3>' + latestBid.shoesSize + '</h3>';
                purchaseHtml += '</div>';
                purchaseHtml += '<div class="item-date">';
                purchaseHtml += '<h2>' + latestBid.regdate + '</h2>';
                purchaseHtml += '</div>';
                purchaseHtml += '<div class="item-status">입찰 진행 중</div>';
                purchaseHtml += '</div>';
            }

            // 내역 업데이트
            $('#purchase-container').html(purchaseHtml);

            // 카운트 업데이트
            $('#total-purchases-count').text(purchases.length + bids.length);
            $('#in-progress-purchases-count').text(bids.length);
            $('#completed-purchases-count').text(purchases.length);
        }
    }



    
    
    
    function loadUser(){
       $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'GET',
            dataType: 'json',
            data: {
                key: 'userAjax',
                methodName: 'selectUserById'
            },
            success: function(response) {
                $('#userEmail').val(response.userEmail);
                $('#nickname').val(response.nickname);
                $('#phone').val(response.hp);
                $('#shoeSize').val(response.shoesSize);
                $('#address').val(response.address);
            },
            error: function() {
               showError("사용자 정보를 불러오는 중 오류가 발생했습니다");
            }
        });
    }
    
    
    
    
    

   function saveClick(infoItem, newValue){      
        const $infoItem = $(infoItem);
        console.log($infoItem);
        const field = $infoItem.data('field');
        console.log(field);

           $.ajax({
               url: '${pageContext.request.contextPath}/ajax',
               method: 'POST',
               data: {
                   key: 'userAjax',
                   methodName: `update${'${field}'}`,
                   value: newValue
               },
               success: function(response) {
                   if (response === 'success') {
                       $infoItem.find('input').prop('disabled', true);
                       $infoItem.find('.save-btn, .cancel-btn').remove();
                       $infoItem.find('.change-btn').show();
                   } else {
                       showError("정보가 성공적으로 업데이트되었습니다");
                   }
               },
               error: function() {
                  showError("사용자 정보를 업데이트하지 못했습니다 다시 시도해주세요");
               }
           });
   }
   
   
   function fetchUserRank() {
       $.ajax({
           url: '${pageContext.request.contextPath}/ajax',
           method: 'GET',
           data: {
               key: 'userAjax',
               methodName: 'getUserRank'
           },
           dataType: 'json',
           success: function(rank) {
               if (rank) {
                   const commission = ((1 - rank.discount) * 100).toFixed(1);
                   $('.current-rank h2').text(rank.rank);
                   $('.current-commission h2').text(commission + '%');
               } else {
                  showError("랭크 정보를 불러올 수 없습니다");
               }
           },
           error: function(error) {
              showError("랭크 정보를 불러오는 중 오류가 발생했습니다");
           }
       });
   }

    


    
    

    // 해시 변경 시 콘텐츠 로드
    window.addEventListener('hashchange', loadContent);

    // 초기 콘텐츠 로드
    loadContent();
  });
</script>
<script src="../js/script.js"></script>

</body>
</html>
