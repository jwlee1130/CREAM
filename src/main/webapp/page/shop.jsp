
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
   <script type="text/javascript">
	$(function(){
		
		//전체검색
		   function productSelectAll(){
			   $.ajax({
				url :"ajax" , //서버요청주소
				type:"get", //요청방식(method방식 : get | post | put | delete )
				dataType:"json"  , //서버가 보내온 데이터(응답)타입(text | html | xml | json )
				data: {key:"product" , methodName : "selectAllProduct"}, //서버에게 보낼 데이터정보(parameter정보)
				success :function(result){
					console.log(result);
					
					let str="";
					$.each(result, function(index, product){
						str+="<li>";
					    str+=`<a href="front?key=product&methodName=detail&no=${"${product.no}"}">`;
					    str+=`<div class="popular-item">`;
					    str+=`<div class="item-image"><img width=150px height=150px src="${'${product.productImg[0].filePath}'}"></div>`;
					    str+=`<div class="item-brand">${"${product.brandName.brand}"}</div>`;
					    str+=`<p class="item-description">${"${product.engName}"}</p>`;
					    str+=`<div class="item-price">${"${product.releasePrice.toLocaleString()}"}</div>`;
					    str+=`</div>`;
					    str+=`</a>`;
					    str+="</li>";
					}); //eachEnd

					$("#popular-list-wrapper-ul").html(str);
					$("#shop-main-total").html("상품수량 : "+result.length + "개");
					
					
				} , //성공했을때 실행할 함수 
				error : function(err){  
					alert(err+"상품 조회에서 에러 발생했어요.");
				}  //실팽했을때 실행할 함수 
			});//ajax끝
			
		   }//selectAll 함수끝
		   /////////////////////////////////////////////////////////////
		  
		   //검색시 상품 조회
			function selectProductByFilter(){
				$.ajax({
					url :"ajax" , //서버요청주소
					type:"get", //요청방식(method방식 : get | post | put | delete )
					dataType:"json"  , //서버가 보내온 데이터(응답)타입(text | html | xml | json )
					data: {key:"product" , methodName : "searchProductByKeyword"}, //서버에게 보낼 데이터정보(parameter정보)					
					success :function(result){
							//console.log(result)
							
						let str="";
						$.each(result, function(index, product){
							str+="<li>";
						    str+=`<a href="front?key=product&methodName=detail&no=1">`; 
					    	str+=`<div class="popular-item">`;
					    	str+=`<div class="item-image"><img width=150px height=150px src="${'${product.productImg[0].filePath}'}"></div>`;
					    	str+=`<div class="item-brand">${"${product.brandName.brand}"}</div>`;
					    	str+=`<p class="item-description">${"${product.engName}"}</p>`;
					    	str+=`<div class="item-price">${"${product.releasePrice.toLocaleString()}"}</div>`;
					    	str+=`</div>`;
					    	str+=`</a>`;
					    	str+="</li>";
						}); //eachEnd
							
						$("#popular-list-wrapper-ul").html(str);
						$("#shop-main-total").html("상품수량 : "+result.length + "개");
							
					} , //성공했을때 실행할 함수 
					error : function(err){  
					alert(err+" 상품 검색에서 에러 발생했어요.");
					}  //실패했을때 실행할 함수 
			});//ajax끝
		   }//productSearch 함수끝
		   
			//productSelectAll();
		   
		   
		   
		   
		   
		   
		   
		   
		   
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
                        <input type="checkbox" name="category" id="sneakers" value="sneakers">
                        <label for="sneakers">스니커즈</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="category" id="slippers">
                        <label for="slippers">슬리퍼</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="category" id="rain-shoes">
                        <label for="rain-shoes">구두</label>
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
                    <div class="filter-check">
                        <input type="checkbox" name="jordan" id="jordan">
                        <label for="jordan">조던</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="gucci" id="gucci">
                        <label for="gucci">구찌</label>
                    </div>
                    <div class="filter-check">
                        <input type="checkbox" name="hermes" id="hermes">
                        <label for="hermes">에르메스</label>
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
                <p id="shop-main-total">상품 수량 : ${fn:length(requestScope.productList)} 개</p>
                
                <span>
          인기순 ⇞
        </span>
            </div>
            <div class="popular-list-wrapper">
                <ul id="popular-list-wrapper-ul">
                <c:choose>
                <c:when test="${empty requestScope.productList}">
            		<p align="center"><b><span style="font-size:9pt;">조회된 상품이 없습니다.</span></b></p>
                </c:when>
                <c:when test="">
                	<h1>여기로 들어오게 해야해</h1>
                	
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
<script type="text/javascript">
	
	const users = [1,2,3];
	
	const re = users.filter((score)=>{
		score=1;
		return score
	});
	console.log(re)
	
	
	
	//필터 범위에서 체크박스를 선택하면
	document.querySelector("[class=shop-aside]").addEventListener("click", (e)=>{
		alert(e.target.checked);

		document.querySelectorAll("[type=checkbox]").forEach((item, index)=>{ //체크박스 전체를 돌면서 
			
			if(item.checked){ //체크가 되어있다면
				console.log(item.id);
				
			}
			
			//item.checked = e.target.checked;
			
		});
		
		selectProductByFilter();
	});


</script>
</body>
</html>