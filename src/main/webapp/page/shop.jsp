
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>shop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/shop.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
   
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
                        <input type="checkbox" name="category" id="111">
                        <label for="111">스니커즈</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="category" id="222">
                        <label for="222">슬리퍼</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="category" id="333">
                        <label for="333">구두/부츠</label>
                    </div>
                </div>
            </div>

            <!-- 브랜드 섹션 -->
            <div class="shop-aside-content">
                <h3>브랜드</h3>
                <div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="1000">
                        <label for="1000">나이키</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="2000">
                        <label for="2000">아디다스</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="3000">
                        <label for="3000">퓨마</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="4000">
                        <label for="4000">구찌</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="5000">
                        <label for="5000">조던</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="6000">
                        <label for="6000">에르메스</label>
                    </div>
                </div>
            </div>
            
            <!-- 색상 섹션 -->
            <div class="shop-aside-content">
                <h3>색상</h3>
                <div>
                    <div class="filter-check">
                        <input type="checkbox" name="color" id="101">
                        <label for="101">블랙</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="color" id="202">
                        <label for="202">화이트</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="color" id="303">
                        <label for="303">컬러</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="shop-main">
            <div class="shop-main-info">
                <p id="shop-main-total">상품 수량 : ${fn:length(requestScope.productList)} 개</p>
                <div>
                <span id="shop-main-sort">인기순 </span>
                 <span id="shop-main-sort-order">↓</span>
               </div>
            </div>
            <div class="popular-list-wrapper">
                <ul id="popular-list-wrapper-ul">
                <c:choose>
                <c:when test="${empty requestScope.productList}">
                  <li>
            		<div style="display:flex; justify-content:center; align-items: center; height: 30vh;">
            			<b><span style="font-size:20pt;">조회된 상품이 없습니다.</span></b>
            		</div>
            	  </li>
                </c:when>
                <c:otherwise>
                <c:forEach items="${requestScope.productList}" var="product">
                	<li>
						<a href="front?key=product&methodName=detail&no=${product.no}">
						<div class="popular-item">
						<div class="item-image"><img width="150px" height="150px" src='${product.productImg[0].filePath}'></div>
                         <h3 class="item-amount">거래 ${product.salesQuantity}</h3>
						<div class="item-brand">${product.brandName.brand}</div>
						<p class="item-description">${product.engName}</p>
						<div class="item-price"><fmt:formatNumber>${product.releasePrice}</fmt:formatNumber></div>
					   	</div>
					   	</a>
					 </li>
                </c:forEach>
                </c:otherwise>
                </c:choose>
                
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp" />
<script src="../js/script.js"></script>
<script type="text/javascript">
	$(function(){
	
		//필터 범위에서 체크박스를 선택하면
		document.querySelector("[class=shop-aside]").addEventListener("click", (e)=>{
			//alert(e.target.checked);
			selectByFilter();
			
		}); //체크박스 선택 Event 끝
			
		
		//정렬하기(인기순, 최신순)
		document.querySelector("#shop-main-sort").addEventListener("click", (e)=>{
			
			selectByFilter();
			
			if($("#shop-main-sort").text().trim() == "인기순"){
				$("#shop-main-sort").text("가격순");
				
			} else if($("#shop-main-sort").text().trim() == "가격순"){
				$("#shop-main-sort").text("인기순");
			}
		}); //정렬하기(인기순, 최신순) Event 끝
		
		
		//정렬하기(내림차순, 오름차순)
		document.querySelector("#shop-main-sort-order").addEventListener("click", (e)=>{
			
			selectByFilter();
			
			if($("#shop-main-sort-order").text().trim() == "↑"){
				$("#shop-main-sort-order").text("↓");
				
			} else if($("#shop-main-sort-order").text().trim() == "↓"){
				$("#shop-main-sort-order").text("↑");
			}
		}); //정렬하기(내림차순, 오름차순) Event 끝
		
		
		///////////////////////////////////////////////////////////////////////////////
		function selectByFilter(){
			let categoryArr=[]; //카테고리 필터 내용을 저장하는 배열
			let brandArr=[]; //브랜드 필터 배열
			let colorArr=[]; //색상 필터 배열
			
			//체크박스 전체를 돌면서 체크된 값 확인해서 배열에 담기
			document.querySelectorAll("[type=checkbox]:checked").forEach((item, index)=>{ 
				
				if(item.name=="category"){
					//console.log("카테고리 여기다 : "+item.id);
					categoryArr.push(item.id); //체크된 값들을 배열에 담는다
				}
				
				if(item.name=="brand"){
					//console.log("브랜드 여기다 : "+item.id);
					brandArr.push(item.id); //체크된 값들을 배열에 담는다
				}
				
				if(item.name=="color"){
					//console.log("색상 여기다 : "+item.id);
					colorArr.push(item.id); //체크된 값들을 배열에 담는다
				}
					
			});
			
			//console.log("categoryArr = " + categoryArr);
			//console.log("brandArr = " + brandArr);
			//console.log("colorArr = " + colorArr);
			
			$.ajax({
				url :"ajax" , //서버요청주소
				type:"POST", //요청방식(method방식 : get | post | put | delete )
				dataType:"json"  , //서버가 보내온 데이터(응답)타입(text | html | xml | json )
				traditional : true, 
				data: {
					key:"product" , 
					methodName : "searchProductByFilter",
					categoryArr : categoryArr,
					brandArr : brandArr,
					colorArr : colorArr
				}, //서버에게 보낼 데이터정보(parameter정보)					
				success :function(result){
					
					//console.log("정렬 이전 결과 = "+result)
					
					if($("#shop-main-sort").text().trim() == "인기순" && $("#shop-main-sort-order").text().trim() == "↓"){
						//console.log("인기순 + 내림차순으로 정렬하기");
						result.sort(function(a,b){
							return b.salesQuantity - a.salesQuantity;		
						});
						
					} else if($("#shop-main-sort").text().trim() == "인기순" && $("#shop-main-sort-order").text().trim() == "↑"){
						//console.log("인기순 + 오름차순으로 정렬하기");
						result.sort(function(a,b){
							return a.releasePrice - b.releasePrice;		
						});
					} else if($("#shop-main-sort").text().trim() == "가격순" && $("#shop-main-sort-order").text().trim() == "↓"){
						//console.log("가격순 + 내림차순으로 정렬하기");
						result.sort(function(a,b){
							return b.releasePrice - a.releasePrice;		
						});
					} else if($("#shop-main-sort").text().trim() == "가격순" && $("#shop-main-sort-order").text().trim() == "↑"){
						//console.log("가격순 + 오름차순으로 정렬하기");
						result.sort(function(a,b){
							return a.releasePrice - b.releasePrice;		
						});
					}
						
					let str="";
					
					if(result==""){ //조회된 값이 없으면
						
						str+=`<div style="display:flex; justify-content:center; align-items: center; height: 30vh;">`;
						str+=`<b><span style="font-size:20pt;">조회된 상품이 없습니다.</span></b>`;
						str+=`</div>`;
						
					} else{ //조회된 값이 있으면
						
						$.each(result, function(index, product){
							str+="<li>";
						    str+=`<a href="front?key=product&methodName=detail&no=${"${product.no}"}">`; 
					    	str+=`<div class="popular-item">`;
					    	str+=`<div class="item-image"><img width="150px" height="150px" src="${'${product.productImg[0].filePath}'}"></div>`;
					    	str+=`<h3 class="item-amount">거래 ${"${product.salesQuantity}"}</h3>`;
					    	str+=`<div class="item-brand">${"${product.brandName.brand}"}</div>`;
					    	str+=`<p class="item-description">${"${product.engName}"}</p>`;
					    	str+=`<div class="item-price">${"${product.releasePrice.toLocaleString()}"}</div>`;
					    	str+=`</div>`;
					    	str+=`</a>`;
					    	str+="</li>";
						}); //eachEnd
					}
						
					$("#popular-list-wrapper-ul").html(str);
					$("#shop-main-total").html("상품수량 : "+result.length + "개");
						
				} , //성공했을때 실행할 함수 
				error : function(err){  
				alert(err+" 상품 필터에서 에러 발생했어요.");
				}  //실패했을때 실행할 함수 
			});//ajax끝
			
		}//selectByFilter끝

	}); //ready End
	
	
	
</script>
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
                        <input type="checkbox" name="category" id="111">
                        <label for="111">스니커즈</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="category" id="222">
                        <label for="222">슬리퍼</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="category" id="333">
                        <label for="333">구두</label>
                    </div>
                </div>
            </div>

            <!-- 브랜드 섹션 -->
            <div class="shop-aside-content">
                <h3>브랜드</h3>
                <div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="1000">
                        <label for="1000">나이키</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="2000">
                        <label for="2000">아디다스</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="3000">
                        <label for="3000">퓨마</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="4000">
                        <label for="4000">조던</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="5000">
                        <label for="5000">구찌</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="brand" id="6000">
                        <label for="6000">에르메스</label>
                    </div>
                </div>
            </div>
            
            <!-- 색상 섹션 -->
            <div class="shop-aside-content">
                <h3>색상</h3>
                <div>
                    <div class="filter-check">
                        <input type="checkbox" name="color" id="101">
                        <label for="101">블랙</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="color" id="202">
                        <label for="202">그레이</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="color" id="303">
                        <label for="303">화이트</label>
                    </div>
                </div>
            </div>
        </div>
        <div class="shop-main">
            <div class="shop-main-info">
                <p id="shop-main-total">상품 수량 : ${fn:length(requestScope.productList)} 개</p>
                <div>
                <span id="shop-main-sort" style="cursor :pointer;  ">인기순 </span>
                 <span id="shop-main-sort-order" style="cursor :pointer;  ">↓</span>
               </div>
            </div>
            <div class="popular-list-wrapper">
                <ul id="popular-list-wrapper-ul">
                <c:choose>
                <c:when test="${empty requestScope.productList}">
            		<div style="display:flex; justify-content:center; align-items: center; height: 30vh;">
            			<b><span style="font-size:20pt;">조회된 상품이 없습니다.</span></b>
            		</div>
                </c:when>
                <c:otherwise>
                <c:forEach items="${requestScope.productList}" var="product">
                	<li>
						<a href="front?key=product&methodName=detail&no=${product.no}">
						<div class="popular-item">
						<div class="item-image"><img width=150px height=150px src='${product.productImg[0].filePath}'></div>
                         <h3 class="item-amount">거래 ${product.salesQuantity}</h3>
						<div class="item-brand">${product.brandName.brand}</div>
						<p class="item-description">${product.engName}</p>
						<div class="item-price"><fmt:formatNumber>${product.releasePrice}</fmt:formatNumber></div>
					   	</div>
					   	</a>
					 </li>
                </c:forEach>
                </c:otherwise>
                </c:choose>
                
                </ul>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp" />
<script src="../js/script.js"></script>
</body>
</html>