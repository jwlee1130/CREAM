
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
                <li><a href="#test3">회원 관리</a></li>
                <li><a href="#test4">판매자 등급</a></li>
                <li><a href="#test5">포인트</a></li>
            </ul>
        </div>
        <div class="main-content" id="content">
            <jsp:include page="../includes/test1.html" /> <!-- 기본 콘텐츠 -->
            <jsp:include page="../includes/test2.html" />
        </div>
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
    
    
    loadUserInfo();
    Wishlist();
    Sales();
    
    
 // 찜목록 리스트
	function Wishlist() {
	    $.ajax({
	        url: '${pageContext.request.contextPath}/ajax',
	        method: 'GET',
	        data: {
	            key: 'userAjax',
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
        console.log("Deleting product no:", productNo);
        $.ajax({
            url: '${pageContext.request.contextPath}/ajax',
            method: 'POST',
            data: {
                key: 'userAjax',
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
            key: 'userAjax',
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
                salesContent += '<td>' + sale.shoesSize.shoesSize + '</td>';            // 신발 사이즈
                salesContent += '</tr>';
            });
            $("#salesTable tbody").empty().append(salesContent);
        },
	        error: function(error) {
	            console.error("판매 조회 오류: ", error);
	        }
	    });
	}
 	
 	
    
    
    
    $(document).on('click', '#enableEdit', function() {
        // 사용자 정보 뷰 숨기고, 수정 폼 보여주기
        $('#userInfoView').hide();
        $('#userEditForm').show();

        // 수정 폼에 기본 정보 넣기
        $('#userPw').val(''); // 비밀번호는 공란으로 유지
        $('#editNickname').val($('#nickname').text());
        $('#editShoesSize').val($('#shoesSize').text());
        $('#editAddress').val($('#address').text());
      });
    
    function loadUserInfo() {
        $.ajax({
          url: '${pageContext.request.contextPath}/ajax',
          method: 'GET',
          dataType: 'json',
          data: {
            key: 'userAjax',
            methodName: 'selectUserById'
          },
          success: function(response) {
            $('#rankNo').text(response.rankNo);
            $('#userId').text(response.userId);
            $('#name').text(response.name);
            $('#userEmail').text(response.userEmail);
            $('#cash').text(response.cash);
            $('#gender').text(response.gender);
            $('#age').text(response.age);
            $('#hp').text(response.hp);
            $('#nickname').text(response.nickname);
            $('#shoesSize').text(response.shoesSize);
            $('#address').text(response.address);
            $('#regdate').text(response.regdate);
          },
          error: function() {
            alert("사용자 정보를 불러오는 데 실패했습니다.");
          }
        });
      }
    
      


 	


    // 해시 변경 시 콘텐츠 로드
    window.addEventListener('hashchange', loadContent);

    // 초기 콘텐츠 로드
    loadContent();
    
    
  });
</script>
</body>
</html>
