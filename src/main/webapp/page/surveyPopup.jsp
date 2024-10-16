<%@ page import="com.cream.dto.UserDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>설문조사 팝업</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            margin: 0;
            padding: 21.6px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 120vh;
        }
        #surveyContainer {
            width: 100%;
            max-width: 432px;
        }
        h2 {
            text-align: center;
            font-size: 27.6px;
            color: #4a4a4a;
        }
        form {
            background-color: white;
            padding: 21.6px;
            border-radius: 9.6px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2); /* 섀도우 범위 확대 */
            max-width: 108%;
            margin: 0 auto;
            text-align: center;
        }
        label {
            font-size: 19.2px;
            color: #333;
            display: block;
            margin-bottom: 10.8px;
            text-align: left;
        }
        input[type="radio"] {
            margin-right: 10.8px;
            transform: scale(1.62);
        }
        .question {
            margin-bottom: 21.6px;
            text-align: left;
        }
        button {
            display: block;
            width: 100%; /* 버튼 너비를 100%로 설정 */
            padding: 12px; /* 버튼 높이를 줄임 */
            font-size: 19.2px;
            color: white;
            background-color: #41B979;
            border: none;
            border-radius: 4.8px;
            cursor: pointer;
            margin: 21.6px auto 0 auto;
        }
        button:hover {
            background-color: #00A86B;
        }
        #recommendedProduct {
            background-color: white;
            padding: 21.6px;
            border-radius: 9.6px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2); /* 섀도우 범위 확대 */
            width: 100%;
            max-width: 432px;
            text-align: center;
        }
        #recommendedProduct h3 {
            font-size: 25.2px;
            color: #4a4a4a;
            margin-bottom: 21.6px;
        }
        #recommendedProduct p {
            font-size: 18px;
            color: #333;
            margin-bottom: 10.8px;
            text-align: left;
        }
        #recommendedProduct img {
            width: 216px;
            height: auto;
            margin: 21.6px auto;
            display: block;
            border-radius: 9.6px;
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
    <% UserDTO loginUser=(UserDTO) session.getAttribute("loginUser");%>
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
                    userNo: '<%= loginUser.getNo() %>',
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
                    '<img src="' + product.productImg[0].filePath + '" alt="' + product.engName + '" style="width:216px; height:auto;" />' +
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
