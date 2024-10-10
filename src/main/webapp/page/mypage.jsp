
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Document</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/mypage.css">
</head>
<body>
<div class="header-wrapper">
    <div class="header-top">
        <a href="/">고객</a>
        <a href="">마이페이지</a>
        <a href="">알림</a>
        <a href="">로그인</a>
    </div>
    <div class="header-main">
        <div class="logo">
            <a href="">
                <img height="100px" src="./Cream850_550.png">
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
</div>
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

        </div>
    </div>
</div>
<footer>
    <div class="footer-content">
        <div class="footer-section about">
            <h2>About Us</h2>
            <p>
                제육볶음은 매콤하고 달콤한 양념에 돼지고기를 볶아낸 한국의 대표적인 가정식 요리입니다. 고추장과 고춧가루로 만든 양념이 고기에 배어들어 밥과 함께 먹을 때 최고의 궁합을 자랑합니다. 잘 익은 김치와 함께 곁들이면 한층 더 깊은 풍미를 즐길 수 있습니다.
            </p>
        </div>
        <div class="footer-section links">
            <h2>Quick Links</h2>
            <ul>
                <li><a href="#">Home</a></li>
                <li><a href="#">Services</a></li>
                <li><a href="#">Contact</a></li>
                <li><a href="#">Blog</a></li>
            </ul>
        </div>
        <div class="footer-section contact">
            <h2>Contact Us</h2>
            <p>Email: Kosta5005@kosta.co.kr</p>
            <p>Phone: 031-719-4932</p>
        </div>
    </div>
    <div class="footer-bottom">
        &copy; 2024 조이름 뭐였더라 | All Rights Reserved
    </div>
</footer>
<script>
  document.addEventListener('DOMContentLoaded', () => {
    const contentDiv = document.getElementById('content');

    // 페이지 로드 시 현재 해시에 따라 콘텐츠 로드
    function loadContent() {
      const hash = window.location.hash.substring(1) || 'test1';
      fetchContent(hash);
    }

    // 콘텐츠를 가져와서 contentDiv에 삽입
    function fetchContent(page) {
      fetch(`./include/${page}.html`)
      .then(response => {
        if (!response.ok) {
          throw new Error('페이지를 불러올 수 없습니다.');
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

    // 해시 변경 시 콘텐츠 로드
    window.addEventListener('hashchange', loadContent);

    // 초기 콘텐츠 로드
    loadContent();
  });
</script>
<script src="./script.js"></script>
</body>
</html>
