
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
            <h1>마이 페이지</h1>
            <ul>
                <h2>쇼핑 정보</h2>
                <li><a href="#test1">구매내역</a></li>
                <li><a href="#test2">판매내역</a></li>
            </ul>
            <ul>
                <h2>회원 정보</h2>
                <li><a href="">회원 관리</a></li>
                <li><a href="">판매자 등급</a></li>
                <li><a href="">포인트</a></li>
            </ul>
        </div>
        <div class="main-content" id="content">
            <jsp:include page="../includes/test1.html" /> <!-- 기본 콘텐츠 -->
        </div>
        <div>
        	 <div>
			    <h2>관심상품 목록</h2>
			    <table border="1" id="wishlistTable">
			        <thead>
			            <tr>
			                <th>영어 이름</th>
			                <th>발매가</th>
			                <th>삭제</th>
			            </tr>
			        </thead>
			        <tbody>
			            <!-- 목록이 이곳에 동적으로 추가됩니다 -->
			        </tbody>
			    </table>
			</div>
        </div>
        <button id="addSaleBtn">판매 등록</button>
    </div>
</div>
<jsp:include page="../includes/footer.jsp" />
<script>
  document.addEventListener('DOMContentLoaded', () => {
    const contentDiv = document.getElementById('content');

    // 초기 콘텐츠 로드 함수
    function loadContent() {
      const hash = window.location.hash.substring(1) || 'test1';
      console.log(hash)
      fetchContent(hash);
    }

    // Fetch API를 사용하여 콘텐츠를 가져오는 함수
    function fetchContent(page) {
      fetch("${pageContext.request.contextPath}/includes/"+page+".html")
      .then(response => {
        if (!response.ok) {
          throw new Error(`Could not fetch ${page}.html: ${response.statusText}`);
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
    
    // 찜목록 리스트
	function Wishlist() {
	    $.ajax({
	        url: '${pageContext.request.contextPath}/ajax',
	        method: 'GET',
	        data: {
	            key: 'user',
	            methodName: 'selectWishlist'
	        },
	        dataType: "json",
	        success: function(result) {
	            let tb = "";
	            $.each(result, function(index, product) {
	                tb += '<tr>';
	                tb += '<td><input type="hidden" value="' + product.no + '"></td>';
	                tb += '<td>' + product.engName + '</td>';
	                tb += '<td>' + product.releasePrice + '</td>';
	                tb += '<td><button class="delete-btn" data-id="' + product.no + '">삭제</button></td>';
	                tb += '</tr>';
	            });
	            $("#wishlistTable tbody").empty().append(tb);
	        },
	        error: function(error) {
	            console.error("Error: ", error);
	        }
	    });
	}

    
    //찜목록 삭제
    $(document).on("click", ".delete-btn", function() {
        let productNo = $(this).data("id");
        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'POST',
            data: {
                key: 'user',
                methodName: 'deleteWishlist',
                product_no: productNo
            },
            success: function(response) {
                alert("관심 상품이 삭제되었습니다.");
                Wishlist();
            },
            error: function(error) {
                console.error("Error: ", error);
                alert("삭제에 실패했습니다. 다시 시도해 주세요.");
            }
        });
    });
    
    
 	// 판매 조회 함수
    function Sales() {
    $.ajax({
        url: '${pageContext.request.contextPath}/ajax',
        method: 'GET',
        data: {
            key: 'user',
            methodName: 'salesByUserNo'
        },
        dataType: "json",
        success: function(result) {
            console.log(result); // 데이터 출력 확인
            let salesContent = "";
            $.each(result, function(index, sale) {
                salesContent += '<tr>';
                salesContent += '<td>' + sale.product.engName + '</td>';  // 영어 이름
                salesContent += '<td>' + sale.startingPrice + '</td>';    // 시작가
                salesContent += '<td>' + sale.nowPrice + '</td>';         // 현재 입찰가
                salesContent += '<td>' + sale.regdate + '</td>';          // 등록일
                salesContent += '<td>' + sale.grade + '</td>';            // 등급
                salesContent += '</tr>';
            });
            $("#salesTable tbody").empty().append(salesContent);
        },
	        error: function(error) {
	            console.error("판매 조회 오류: ", error);
	        }
	    });
	}
 	
 	
 	
    function getCurrentDate() {
        const today = new Date();
        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0');
        const day = String(today.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
      }

      // 판매 데이터 삽입 버튼 클릭 이벤트
      document.getElementById("addSaleBtn").addEventListener("click", function() {
        const salesData = {
          productNo: 1,              
          startingPrice: 200000,     
          nowPrice: 210000,          
          salesStatus: 1,            
          regdate: getCurrentDate(), 
          grade: 'A'                 
        };

        // 판매 데이터 삽입 AJAX 요청
        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'POST',
            data: {
                key: 'user',
                methodName: 'insertSales',
                productNo: salesData.productNo,
                startingPrice: salesData.startingPrice,
                nowPrice: salesData.nowPrice,
                salesStatus: salesData.salesStatus,
                regdate: salesData.regdate,
                grade: salesData.grade
            },
            success: function(response) {
                console.log("판매 등록 성공:", response);
                alert("판매가 등록되었습니다.");
                Sales(); // 판매 목록 갱신
            },
            error: function(error) {
                console.error("판매 등록 실패:", error);
                alert("등록에 실패했습니다. 다시 시도해 주세요.");
            }
        });
      });


    // 해시 변경 시 콘텐츠 로드
    window.addEventListener('hashchange', loadContent);

    // 초기 콘텐츠 로드
    loadContent();
    Wishlist();
    Sales();
  });
</script>
</body>
</html>
