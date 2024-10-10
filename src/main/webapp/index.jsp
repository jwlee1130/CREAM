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

    <jsp:include page="./includes/header.jsp" />
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
                            <a href="${pageContext.request.contextPath}/page/product.jsp">
                                <div class="popular-item">
                                    <div class="item-image">이미지</div>
                                    <div class="item-brand">브랜드명</div>
                                    <p class="item-description">브랜드 설명 들어가는곳</p>
                                    <div class="item-price">가격쓰는곳</div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/page/product.jsp">
                                <div class="popular-item">
                                    <div class="item-image">이미지</div>
                                    <div class="item-brand">브랜드명</div>
                                    <p class="item-description">브랜드 설명 들어가는곳</p>
                                    <div class="item-price">가격쓰는곳</div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/page/product.jsp">
                                <div class="popular-item">
                                    <div class="item-image">이미지</div>
                                    <div class="item-brand">브랜드명</div>
                                    <p class="item-description">브랜드 설명 들어가는곳</p>
                                    <div class="item-price">가격쓰는곳</div>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/page/product.jsp">
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
<jsp:include page="./includes/footer.jsp" />
<script src="js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
</body>
</html>