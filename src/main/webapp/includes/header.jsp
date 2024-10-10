
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="header">
    <div class="header-wrapper">
        <div class="header-top">
            <a href="#">고객</a>
            <a href="${pageContext.request.contextPath}/page/mypage.jsp">마이페이지</a>
            <a href="#">알림</a>
            <a href="#">로그인</a>
        </div>
        <div class="header-main">
            <div class="logo">
                <a href="${pageContext.request.contextPath}">
                    <img height="100px" src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/logo.png">
                </a>
            </div>
            <div class="header-searchbar">
                <div class="search-container">
                    <input type="text" class="search-bar" placeholder="Search..." maxlength="16" />
                    <a class="search-button"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <circle cx="11" cy="11" r="8" stroke="currentColor" stroke-width="2"/>
                        <line x1="17" y1="17" x2="22" y2="22" stroke="currentColor" stroke-width="2" stroke-linecap="round"/>
                    </svg>
                    </a>
                </div>
            </div>
            <div class="header-right">
                <div class="bell-container">
                    <svg xmlns="http://www.w3.org/2000/svg" width="21" height="21" class="bell-icon" viewBox="0 0 16 16">
                        <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2m.995-14.901a1 1 0 1 0-1.99 0A5 5 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901"/>
                    </svg>
                    <div class="tooltip">

                        <ul>
                            <li><a href="#">이건 판매 등록 알림</a></li>
                            <li><a href="#">이건 상위 입찰자 등장 알림</a></li>
                            <li><a href="#">this is for Bid Success</a></li>
                            <li><a href="#">this is for Nothing</a></li>
                        </ul>
                    </div>
                </div>
                <span><a href="">Home</a></span>
                <span><a href="">Shop</a></span>
                <span><a href="">Login</a></span>
            </div>
        </div>
        <div class="header-bottom">
            <ul>
                <li>
                    <span><a href="">운동화</a></span>
                </li>
                <li>
                    <span><a href="">부츠</a></span>
                </li>
                <li>
                    <span><a href="">슬리퍼</a></span>
                </li>
                <li>
                    <span><a href="">스니커즈</a></span>
                </li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>
