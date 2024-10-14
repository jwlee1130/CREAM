
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage_main.css">
    
    <title>마이 페이지</title>
</head>
<body>
<div class="mypage-container">
    <div class="wrap">
        <div class="user-profile">
            <div class="profile-img">
                <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/%EC%9C%A4%EC%84%B1%EB%B0%94%EC%98%A41.jpg" alt="" width="100px" height="100px">
            </div>
            <div class="user-identify">
                <h2><%= loginUser.getNickname() %></h2>
                <h3><%= loginUser.getUserEmail() %></h3>
            </div>
            <div class="user-point">
                <i>🅿️</i>
                <h2><%= loginUser.getCash() %></h2>
            </div>
            <div class="user-rank">
                <i>🅰️</i>
                <h2><%= loginUser.getRankNo() %></h2>
            </div>
            <div class="user-setting">
                <button>회원 관리</button>
            </div>
        </div>
        <div class="purchase">
		    <h1>구매 내역</h1>
		    <div class="parchase-area">
		        <div class="parchase-all">
		            <h2>전체</h2>
		            <h2 id="total-purchases-count" style="color: red">0</h2>
		        </div>
		        <div class="parchase-all">
		            <h2>진행중</h2>
		            <h2 id="in-progress-purchases-count">0</h2>
		        </div>
		        <div class="parchase-all">
		            <h2>종료</h2>
		            <h2 id="completed-purchases-count">0</h2>
		        </div>
		    </div>
		    <div id="purchase-container" style="display: flex; flex-direction: column;">
		        <!-- 구매 내역 항목들이 여기에 추가됩니다 -->
		    </div>
		</div>
        <div class="sell">
	    <h1>판매 내역</h1>
	    <div class="parchase-area">
        	<div class="parchase-all">
            <h2>전체</h2>
		            <h2 id="total-sales-count" style="color: #41B979">0</h2>
		        </div>
		        <div class="parchase-all">
		            <h2>진행중</h2>
		            <h2 id="in-progress-count">0</h2>
		        </div>
		        <div class="parchase-all">
		            <h2>종료</h2>
		            <h2 id="completed-count">0</h2>
		        </div>
		    </div>
		    <div class="parchase-item" id="sales-container" style="display: flex; flex-direction: column">
		        <!-- 판매 내역 항목들이 여기에 추가됩니다 -->
		    </div>
		</div>
        <div class="wish-list">
            <h2>관심 상품</h2>
            <div class="wish-item-list">
                <ul id="wishlist-container">
                    <!-- Ajax로 동적으로 데이터가 추가될 부분 -->
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
