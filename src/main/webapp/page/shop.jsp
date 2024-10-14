
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>shop</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/shop.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="shop-container">
    <div class="shop-wrapper">
        <div class="shop-aside">
            <h2>필터</h2>
            <!-- 카테고리 섹션 -->
            <div class="shop-aside-content">
                <h3>카테고리</h3>
                <div>
                    <div class="filter-check">
                        <input type="checkbox" name="slippers" id="slippers">
                        <label for="slippers">슬리퍼</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="running-shoes" id="running-shoes">
                        <label for="running-shoes">운동화</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="rain-shoes" id="rain-shoes">
                        <label for="rain-shoes">장화</label>
                    </div>
                </div>
            </div>

            <!-- 브랜드 섹션 -->
            <div class="shop-aside-content">
                <h3>브랜드</h3>
                <div>
                    <div class="filter-check">
                        <input type="checkbox" name="nike" id="nike">
                        <label for="nike">나이키</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="adidas" id="adidas">
                        <label for="adidas">아디다스</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="puma" id="puma">
                        <label for="puma">퓨마</label>
                    </div>
                </div>
            </div>

            <!-- 성별 섹션 -->
            <div class="shop-aside-content">
                <h3>성별</h3>
                <div>
                    <div class="filter-check">
                        <input type="checkbox" name="남성" id="남성">
                        <label for="남성">남성</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="여성" id="여성">
                        <label for="여성">여성</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="키즈" id="키즈">
                        <label for="키즈">키즈</label>
                    </div>
                </div>
            </div>

            <!-- 사이즈 섹션 -->
            <div class="shop-aside-content">
                <h3>사이즈</h3>
                <div>
                    <div class="filter-check">
                        <input type="checkbox" name="240" id="240">
                        <label for="240">240</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="260" id="260">
                        <label for="260">260</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="280" id="280">
                        <label for="280">280</label>
                    </div>
                </div>
            </div>

            <!-- 색상 섹션 -->
            <div class="shop-aside-content">
                <h3>색상</h3>
                <div>
                    <div class="filter-check">
                        <input type="checkbox" name="black" id="black">
                        <label for="black">블랙</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="gray" id="gray">
                        <label for="gray">그레이</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="white" id="white">
                        <label for="white">화이트</label>
                    </div>
                </div>
            </div>

            <!-- 가격 섹션 -->
            <div class="shop-aside-content">
                <h3>가격</h3>
                <div>
                    <div class="filter-check">
                        <input type="checkbox" name="under10" id="under10">
                        <label for="under10">10만원 이하</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="between1030" id="between1030">
                        <label for="between1030">10만원~30만원</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="between3050" id="between3050">
                        <label for="between3050">30만원~50만원</label>
                    </div>
                </div>
            </div>

        </div>
        <div class="shop-main">
            <div class="shop-main-info">
                <p>상품수량 : 2222</p>
                <span>
          인기순 ⇞
        </span>
            </div>
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
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp" />
<script src="../js/script.js"></script>
</body>
</html>