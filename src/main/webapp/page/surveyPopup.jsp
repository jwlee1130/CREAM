<%@ page import="com.cream.dto.UserDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>

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
            padding: 20px;
            width: 400px;
        }
        h2 {
            text-align: center;
            font-size: 26px;
            color: #4a4a4a;
        }
        form {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            max-width: 100%;
            margin: 0 auto;
        }
        label {
            font-size: 18px;
            color: #333;
            display: block;
            margin-bottom: 10px;
        }
        input[type="radio"] {
            margin-right: 10px;
            transform: scale(1.5);
        }
        .question {
            margin-bottom: 20px;
        }
        button {
            display: block;
            width: 100%;
            padding: 15px;
            font-size: 18px;
            color: white;
            background-color:#41B979;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 20px;
        }
        button:hover {
            background-color: #00A86B;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h2>신발 취향 설문조사</h2>
<form id="surveyForm">
    <div class="question">
        <label>1. 당신이 선호하는 신발 브랜드는?</label>
        <input type="radio" name="brand" value="1000"> 나이키
        <%--    BRAND_NO=1000--%>
        <input type="radio" name="brand" value="2000"> 아디다스
        <%--    BRAND_NO=2000--%>
        <input type="radio" name="brand" value="3000"> 퓨마
        <%--    BRAND_NO=3000--%>
        <input type="radio" name="brand" value="4000"> 구찌
        <%--    BRAND_NO=4000--%>
        <input type="radio" name="brand" value="5000"> 조던
        <%--    BRAND_NO=5000--%>
        <input type="radio" name="brand" value="6000"> 에르메스
        <%--    BRAND_NO=6000--%>
    </div>

    <div class="question">
        <label>2. 당신이 선호하는 신발의 색깔은?</label>
        <input type="radio" name="color" value="101"> 검정색
        <%--    COLOR_NO=101--%>
        <input type="radio" name="color" value="202"> 흰색
        <%--    COLOR_NO=202--%>
        <input type="radio" name="color" value="303"> 유채색
        <%--    COLOR_NO=303--%>
    </div>

    <div class="question">
        <label>3. 당신이 선호하는 신발 카테고리는?</label>
        <input type="radio" name="category" value="111"> 스니커즈
        <%--    CATEGORY_NO=111--%>
        <input type="radio" name="category" value="222"> 슬리퍼
        <%--    CATEGORY_NO=222--%>
        <input type="radio" name="category" value="333"> 구두
        <%--    CATEGORY_NO=333--%>
    </div>

    <div class="question">
        <%-- 아무 의미 X--%>
        <label>4. 소장용 신발을 찾고 계십니까?</label>
        <input type="radio" name="collection" value="예"> 예
        <input type="radio" name="collection" value="아니오"> 아니오
    </div>

    <div class="question">
        <label>5. 50만원 이상의 신발을 구매할 의향이 있으십니까?</label>
        <input type="radio" name="price" value="500000"> 예
        <input type="radio" name="price" value="0"> 아니오
    </div>
    <%--  나중에 디비에서 상품초천에서 사용 조건절에 50만원 이상 상품 컷할지 말지 결정--%>

    <button type="submit">제출</button>
</form>
</body>
</html>
<script>
    $(document).ready(function(){
        $('#surveyForm').on('submit',function(e){
            e.preventDefault();

            <%--const userNo=`<%= ((UserDTO)session.getAttribute("loginUser")).getNo() %>`;--%>
            const category=$('input[name="category"]:checked').val();
            const brand=$('input[name="brand"]:checked').val();
            const color=$('input[name="color"]:checked').val();
            const price=$('input[name="price"]:checked').val();

            $.ajax({
                url:'${pageContext.request.contextPath}/ajax',
                method:'POST',
                data:{
                    key:'admin',
                    methodName:'submitSurvey',
                    userNo:'3', // 나중에 userNo로 변경해야 함
                    category:category,
                    brand:brand,
                    color:color,
                    price:price,
                },
                success:function(data){
                    console.log("설문조사 제출이 완료 되었습니다");
                },
                error:function(error)
                {
                    console.log(error);
                }
            })
        })
    })

</script>
