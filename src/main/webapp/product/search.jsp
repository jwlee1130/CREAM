<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		const showProduct = ()=>{
			console.log("111");
		}
		
		showProduct();
		
	}); //ready끝


</script>

<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h3>여기는 search.jsp </h3>
<div class="item-image">
            <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/shoes1.png"  style="width: 525px; height: 525px;">
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
</body>
</html>