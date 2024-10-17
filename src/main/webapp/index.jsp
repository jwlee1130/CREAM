<%@ page import="com.cream.dto.UserDTO" %>
<%@ page import="com.cream.dto.AdminDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Cream</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css"
    />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<script type="text/javascript">
	$(function(){
		
		let currentIndex=0; //현재까지 로드된 상품의 수
		const itemsPerPage = 4; //한번에 보여줄 상품의 수
				
		//전체검색
		   function productSelectAll(){
			   $.ajax({
				url :"ajax" , //서버요청주소
				type:"get", //요청방식(method방식 : get | post | put | delete )
				dataType:"json"  , //서버가 보내온 데이터(응답)타입(text | html | xml | json )
				data: {key:"product" , methodName : "selectAllProduct"}, //서버에게 보낼 데이터정보(parameter정보)
				success :function(result){
					//console.log(result);
					let totalProducts = result.length; //전체 상품 수
					displayProducts(result, currentIndex, itemsPerPage);
					
					//더보기 버튼 클릭 시 4개씩 더 보여주기
					$(".more-button").on("click", function(){
						currentIndex += itemsPerPage;
						if(currentIndex < totalProducts){
							displayProducts(result, currentIndex, itemsPerPage);
						}
						
						if(currentIndex + itemsPerPage >= totalProducts){
							$(".more-button").hide(); //마지막 상품까지 표시되면 버튼 숨김 
						}
					});
					
					
				} , //성공했을때 실행할 함수 
				error : function(err){  
					alert(err+"상품 조회에서 에러 발생했어요.");
				}  //실팽했을때 실행할 함수 
			});//ajax끝
			
		   }//selectAll 함수끝
		   /////////////////////////////////////////////////////////////
		  
		   function displayProducts(result, startIndex, count){
			   
			   let str="";
			   for(let i= startIndex; i< startIndex+count && i < result.length; i++){
				   let product = result[i];
					str+="<li>";
				    str+=`<a href="front?key=product&methodName=detail&no=${"${product.no}"}">`;
				    str+=`<div class="popular-item">`;
				    str+=`<div class="item-image"><img width=250px height=250px src="${'${product.productImg[0].filePath}'}"></div>`;
				    str+=`<div class="item-brand">${"${product.brandName.brand}"}</div>`;
				    str+=`<p class="item-description">${"${product.engName}"}</p>`;
				    str+=`<div class="item-price">${"${product.releasePrice.toLocaleString()}"}원</div>`;
				    str+=`</div>`;
				    str+=`</a>`;
				    str+="</li>";
			   }

				$("#popular-list-wrapper-ul").append(str); //기존 목록에 추가
			   
		   }//displayProducts 함수끝
		   /////////////////////////////////////////////////////////////
		  
			productSelectAll();

		
	}); //ready End


</script>
</head>
<body>
<div class="container">
    <jsp:include page="./includes/header.jsp" />
<%--    <div class="header-bottom" style="position: relative; top: -2rem; font-weight: bolder">--%>
<%--        <ul>--%>
<%--            <li>--%>
<%--                <span><a href="${pageContext.request.contextPath}/front?key=product&methodName=searchProductByCategory&productCategory=111">스니커즈</a></span>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <span><a href="${pageContext.request.contextPath}/front?key=product&methodName=searchProductByCategory&productCategory=222">슬리퍼</a></span>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <span><a href="${pageContext.request.contextPath}/front?key=product&methodName=searchProductByCategory&productCategory=333">구두</a></span>--%>
<%--            </li>--%>

<%--        </ul>--%>
<%--    </div>--%>
</div>
    <main style="position: relative; top: -31px">
        <div class="main-wrapper">
            <section class="main-slider">
                <div class="main-swiper-container">
                    <div class="swiper-wrapper">
                        <div class="swiper-slide">
                            <div class="image-placeholder" style="background-color: #d8dde1">
                                <div class="slider-inner">
                                <div class="inner-img">
                                    <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/%EB%A0%88%EB%93%9C1.webp" alt="" style="top:-50px">
                                    <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/%EB%A0%88%EB%93%9C2.webp" alt="" style="top:-50px">
                                </div>
                                <h2 style="color: #555">Adidas Yeezy Boost 350 V2 Black Red 2020</h2>
                                <h3 style="color: #555">24/25 F/W 시즌 중국으로 간 푸바오도 그리워하는 바로 그 신발</h3>
                                <button>지금 보러가기</button>
                                </div>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="image-placeholder" style="background-color: #69644b;">
                                <div class="slider-inner">
                                    <div class="inner-img">
                                        <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/%EB%AF%B9%EC%8A%A41.webp" alt="" style="top:-50px">
                                        <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/%EB%AF%B9%EC%8A%A42.webp" alt="" style="top:-50px">
                                    </div>
                                    <h2>Adidas Yeezy Slide Glow Green 2022/2023</h2>
                                    <h3>여름철 최고의 선택</h3>
                                    <button>지금 보러가기</button>
                                </div>
                            </div>
                        </div>x
                        <div class="swiper-slide">
                            <div class="image-placeholder" style="background-color:#c4b2af">
                                <div class="slider-inner">
                                    <div class="inner-img">
                                        <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/%EB%A0%88%EB%93%9C1.webp" alt="" style="top:-50px">
                                        <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/%EB%A0%88%EB%93%9C2.webp" alt="" style="top:-50px">
                                    </div>
                                    <h2 style="color: #221e1d">Adidas Yeezy Boost 350 V2 Black Red 2020</h2>
                                    <h3 style="color: #221e1d">24/25 F/W 시즌 중국으로 간 푸바오도 그리워하는 바로 그 신발</h3>
                                    <button>지금 보러가기</button>
                                </div>
                            </div>
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
                            	<a href="${pageContext.request.contextPath}/front?key=product&methodName=searchProductByBrand&productBrand=1000">
                            	<img width=200px height=120px src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/NIKE_LOGO.png">
                            	</a>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="brand-card">
                            	<a href="${pageContext.request.contextPath}/front?key=product&methodName=searchProductByBrand&productBrand=2000">
                                <img width=200px height=150px src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/ADIDAS_LOGO.png">
                            	</a>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="brand-card">
                                <a href="${pageContext.request.contextPath}/front?key=product&methodName=searchProductByBrand&productBrand=3000">
                                <img width=200px height=200px src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/PUMA_LOGO.png">
                                </a>                          
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="brand-card">
                                <a href="${pageContext.request.contextPath}/front?key=product&methodName=searchProductByBrand&productBrand=4000">
                                <img width=200px height=200px src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/JORDAN_LOGO.png">
                                </a>
                            </div>
                        </div>
                        <div class="swiper-slide">
                            <div class="brand-card">
                            	<a href="${pageContext.request.contextPath}/front?key=product&methodName=searchProductByBrand&productBrand=5000">
                                <img width=200px height=200px src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/GUCCI_LOGO.png">
                                </a>
                            </div>
                        </div>
                         <div class="swiper-slide">
                            <div class="brand-card">
                            <a href="${pageContext.request.contextPath}/front?key=product&methodName=searchProductByBrand&productBrand=6000">
                                <img width=200px height=200px src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/HERMES_LOGO.png">
                                </a>
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
                    <ul id="popular-list-wrapper-ul">
                    </ul>
                </div>
                <div class="more-button">
                    <button style="font-size: 20px">더보기</button>
                </div>
            </section>
        </div>
    </main>
</div>
<jsp:include page="./includes/footer.jsp" />
<script src="js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>



<%
    UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
    AdminDTO adminUser=(AdminDTO) session.getAttribute("adminUser"); // 아직 정해지지 않은 부분
%>

<script type="text/javascript">
    $(document).ready(function(){
        <% if (loginUser != null && adminUser == null) { %>

        $.ajax({
            url: "ajax",
            type: "get",
            dataType: "json",
            data: {
                key: "admin",
                methodName: "hasUserCompletedSurvey",
                <%--userNo: "<%= loginUser.getNo() %>"--%>
                // 서버에서도 이 값을 세션에서 가지고 올 수 있음
            },
            success: function(result){
                console.log(result);
                if (!result) { // boolean 형식으로 올거임
                    window.open('${pageContext.request.contextPath}/page/surveyPopup.jsp', 'surveyPopup', 'width=450,height=650');
                }
            },
            error: function(err){
                console.error("설문조 확인 중 오류 발생:", err);
                ;
            }
        });
        <% } %>
    });
</script>


</body>
</html>