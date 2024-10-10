<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Cream</title>
    <link rel="stylesheet" href="./css/reset.css" />
    <link rel="stylesheet" href="./css/style.css" />
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
    />
</head>
<body>
<div class="container">
    <div class="header">
        <div class="header-wrapper">
            <div class="header-top">
                <a href="/">고객</a>
                <a href="./mypage.html">마이페이지</a>
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
    <main>
        <div class="main-wrapper">
            <section class="main-slider">
                <div class="main-swiper-container">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                            <div class="image-placeholder">슬라이드 1</div>
                        </div>
                        <div class="swiper-slide">
                            <div class="image-placeholder">슬라이드 2</div>
                        </div>
                        <div class="swiper-slide">
                            <div class="image-placeholder">슬라이드 3</div>
                        </div>
                        <!-- 추가 슬라이드가 필요하면 여기에 추가 -->
                    </div>
                    <!-- 좌우 네비게이션 버튼 -->
                    <div class="swiper-button-next"></div>
                    <div class="swiper-button-prev"></div>
                    <!-- 페이지네이션 불릿 -->
                    <div class="swiper-pagination"></div>
                </div>
            </section>
            <section class="brand-slider">
                <!-- Swiper -->
                <div class="swiper-container">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                            <div class="brand-card">
                                <div class="image-placeholder">이미지 1</div>
                                <div class="brand-name">브랜드명 1</div>
                                <div class="brand-description">브랜드 설명 1</div>
                                <div class="brand-price">가격 1</div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="brand-card">
                                <div class="image-placeholder">이미지 2</div>
                                <div class="brand-name">브랜드명 2</div>
                                <div class="brand-description">브랜드 설명 2</div>
                                <div class="brand-price">가격 2</div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="brand-card">
                                <div class="image-placeholder">이미지 3</div>
                                <div class="brand-name">브랜드명 3</div>
                                <div class="brand-description">브랜드 설명 3</div>
                                <div class="brand-price">가격 3</div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="brand-card">
                                <div class="image-placeholder">이미지 4</div>
                                <div class="brand-name">브랜드명 4</div>
                                <div class="brand-description">브랜드 설명 4</div>
                                <div class="brand-price">가격 4</div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="brand-card">
                                <div class="image-placeholder">이미지 5</div>
                                <div class="brand-name">브랜드명 5</div>
                                <div class="brand-description">브랜드 설명 5</div>
                                <div class="brand-price">가격 5</div>
                            </div>
                        </div>
                        <!-- 필요에 따라 추가 슬라이드 -->
                    </div>
                    <!-- 좌우 네비게이션 버튼 -->

                    <div class="swiper-button-next" aria-label="다음 슬라이드"></div>
                    <div class="swiper-button-prev" aria-label="이전 슬라이드"></div>
                    <!-- 페이지네이션 불릿 -->
                    <!--              <div class="swiper-pagination"></div>-->
                </div>
            </section>

            <section class="most-popular">
                <h2>Most Popular</h2>
                <h4>인기 상품</h4>
                <div class="popular-list-wrapper">
                    <ul>
                        <li>
                            <a href="">
                                <div class="popular-item">
                                    <div class="item-image">이미지</div>
                                    <div class="item-brand">브랜드명</div>
                                    <p class="item-description">브랜드 설명 들어가는곳</p>
                                    <div class="item-price">가격쓰는곳</div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="popular-item">
                                    <div class="item-image">이미지</div>
                                    <div class="item-brand">브랜드명</div>
                                    <p class="item-description">브랜드 설명 들어가는곳</p>
                                    <div class="item-price">가격쓰는곳</div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="popular-item">
                                    <div class="item-image">이미지</div>
                                    <div class="item-brand">브랜드명</div>
                                    <p class="item-description">브랜드 설명 들어가는곳</p>
                                    <div class="item-price">가격쓰는곳</div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="">
                                <div class="popular-item">
                                    <div class="item-image">이미지</div>
                                    <div class="item-brand">브랜드명</div>
                                    <p class="item-description">브랜드 설명 들어가는곳</p>
                                    <div class="item-price">가격쓰는곳</div>
                                </div>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="more-button">
                    <button>더보기</button>
                </div>
            </section>
        </div>
    </main>
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
<script src="./script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
</body>
</html>