<%@ page import="com.cream.dto.UserDTO" %>
<%@ page import="com.cream.dto.AdminDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>설문조사 팝업</title>
    <style>
        html, body {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
            overflow: hidden;
        }
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            color: #333;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
        }
        #surveyContainer {
            width: 81%;
            max-width: 432px;
            background-color: white;
            padding: 18px;
            border-radius: 9px;
            box-shadow: 0 7.2px 14.4px rgba(0, 0, 0, 0.2);
            overflow: auto;
            height: 100%;
        }
        h2 {
            text-align: center;
            font-size: 21.6px;
            color: #4a4a4a;
            margin-bottom: 54px;
        }
        form {
            width: 100%;
            text-align: center;
        }
        label {
            font-size: 14.4px;
            color: #333;
            display: block;
            margin-bottom: 13.5px;
            text-align: left;
        }
        input[type="radio"] {
            margin-right: 7.2px;
            transform: scale(1.35);
        }
        .question {
            margin-bottom: 36px;
            text-align: left;
        }
        button {
            display: block;
            width: 100%;
            padding: 9px;
            font-size: 14.4px;
            color: white;
            background-color: #41B979;
            border: none;
            border-radius: 4.5px;
            cursor: pointer;
            margin-top: 18px;
        }
        button:hover {
            background-color: #00A86B;
        }
        #recommendedProduct {
            display: none;
            width: 100%;
            text-align: center;
            height:100%;
        }
        #recommendedProduct h3 {
            font-size: 18px;
            color: #4a4a4a;
            margin-bottom: 18px;

        }
        #recommendedProduct p {
            font-size: 18px;
            color: #333;
            margin: 27px 0;
            text-align: left;
        }
        #recommendedProduct img {
            width: 315px;
            height: auto;
            margin: 18px auto;
            display: block;
            border-radius: 7.2px;
        }
        #closeBtn{
            background-color: #EF6253;
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

    <div id="recommendedProduct">
        <h2>추천 상품 정보</h2>
        <p>상품 이름: <span id="productName"></span></p>
        <p>브랜드: <span id="productBrand"></span></p>
        <p>출시 가격: <span id="productPrice"></span>원</p>
        <img id="productImage" src="" alt="상품 이미지" />
        <button type="button" class="btn btn-danger" id="closeBtn">창 닫기</button>
    </div>
</div>

<script>
    <%
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        AdminDTO adminUser = (AdminDTO) session.getAttribute("adminUser");
    %>
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
                    userNo: '<%= loginUser != null ? loginUser.getNo() : "" %>',
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
                $('#surveyContainer').find('.error-msg').remove();
                $('#productName').text(product.engName);
                $('#productBrand').text(product.brandName.brand);
                $('#productPrice').text(product.releasePrice.toLocaleString());
                $('#productImage').attr('src', product.productImg[0].filePath);
                $('#surveyForm').hide();
                $('#recommendedProduct').show();
            } else {
                $('#surveyContainer').find('.error-msg').remove();
                $('#surveyContainer').append('<p class="error-msg">추천 상품을 찾을 수 없습니다.</p>');
            }
        }

        $('#closeBtn').on('click',function(){
            window.close();
        });
    });
</script>
</body>
</html>
