<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>설문조사 팝업</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            margin: 0;
            padding: 18px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        #surveyContainer {
            width: 100%;
            max-width: 360px;
        }
        h2 {
            text-align: center;
            font-size: 23px;
            color: #4a4a4a;
        }
        form {
            background-color: white;
            padding: 18px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 90%;
            margin: 0 auto;
        }
        label {
            font-size: 16px;
            color: #333;
            display: block;
            margin-bottom: 9px;
        }
        input[type="radio"] {
            margin-right: 9px;
            transform: scale(1.35);
        }
        .question {
            margin-bottom: 18px;
        }
        button {
            display: block;
            width: 90%;
            padding: 14px;
            font-size: 16px;
            color: white;
            background-color: #41B979;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 18px;
        }
        button:hover {
            background-color: #00A86B;
        }
        #recommendedProduct {
            background-color: white;
            padding: 18px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 360px;
            text-align: center;
        }
        #recommendedProduct h3 {
            font-size: 21px;
            color: #4a4a4a;
            margin-bottom: 18px;
        }
        #recommendedProduct p {
            font-size: 15px;
            color: #333;
            margin-bottom: 9px;
            text-align: left;
        }
        #recommendedProduct img {
            width: 180px;
            height: auto;
            margin: 18px auto;
            display: block;
            border-radius: 8px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

<div id="surveyContainer">
    <form id="surveyForm">
        <h2>신발 취향 설문조사</h2>
        <div class="question">
            <label>1. 당신이 선호하는 신발 브랜드는?</label>
            <input type="radio" name="brand" value="1000"> 나이키
            <input type="radio" name="brand" value="2000"> 아디다스
            <input type="radio" name="brand" value="3000"> 퓨마
            <input type="radio" name="brand" value="4000"> 구찌
            <input type="radio" name="brand" value="5000"> 조던
            <input type="radio" name="brand" value="6000"> 에르메스
        </div>

        <div class="question">
            <label>2. 당신이 선호하는 신발의 색깔은?</label>
            <input type="radio" name="color" value="101"> 검정색
            <input type="radio" name="color" value="202"> 흰색
            <input type="radio" name="color" value="303"> 유채색
        </div>

        <div class="question">
            <label>3. 당신이 선호하는 신발 카테고리는?</label>
            <input type="radio" name="category" value="111"> 스니커즈
            <input type="radio" name="category" value="222"> 슬리퍼
            <input type="radio" name="category" value="333"> 구두
        </div>

        <div class="question">
            <label>4. 소장용 신발을 찾고 계십니까?</label>
            <input type="radio" name="collection" value="예"> 예
            <input type="radio" name="collection" value="아니오"> 아니오
        </div>

        <div class="question">
            <label>5. 50만원 이상의 신발을 구매할 의향이 있으십니까?</label>
            <input type="radio" name="price" value="55500000"> 예
            <input type="radio" name="price" value="500000"> 아니오
        </div>

        <button type="submit">제출</button>
    </form>
</div>

<script>
    $(document).ready(function(){
        $('#surveyForm').on('submit', function(e){
            e.preventDefault();

            const category = $('input[name="category"]:checked').val();
            const brand = $('input[name="brand"]:checked').val();
            const color = $('input[name="color"]:checked').val();
            const price = $('input[name="price"]:checked').val();

            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'POST',
                data: {
                    key: 'admin',
                    methodName: 'submitSurvey',
                    userNo: '5',
                    category: category,
                    brand: brand,
                    color: color,
                    price: price,
                },
                success: function(data){
                    getProduct(category, brand, color, price);
                },
                error: function(error){
                    console.log("submitSurvey 오류:", error);
                }
            });
        });

        function getProduct(categoryNo, brandNo, colorNo, releasePrice) {
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'POST',
                dataType: 'json',
                data: {
                    key: 'admin',
                    methodName: 'getProduct',
                    categoryNo: categoryNo,
                    brandNo: brandNo,
                    colorNo: colorNo,
                    releasePrice: releasePrice,
                },
                success: function(product){
                    displayProduct(product);
                },
                error: function(xhr, status, error){
                    console.error("getProduct 오류:", error);
                }
            });
        }

        function displayProduct(product){
            if (product && product.no) {
                var productHtml = '<div id="recommendedProduct">' +
                    '<h3>추천 상품 정보</h3>' +
                    '<p>상품 이름: ' + product.engName + '</p>' +
                    '<p>브랜드: ' + product.brandName.brand + '</p>' +
                    '<p>출시 가격: ' + product.releasePrice + '원</p>' +
                    '<img src="' + product.productImg.filePath + '" alt="' + product.engName + '" style="width:180px; height:auto;" />' +
                    '</div>';

                $('#surveyContainer').html(productHtml);
            } else {
                $('#surveyContainer').html('<p>추천 상품을 찾을 수 없습니다.</p>');
            }
        }
    });
</script>
</body>
</html>
