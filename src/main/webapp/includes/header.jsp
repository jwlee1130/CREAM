
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square-round.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css">
</head>
<body>
<div class="header">
    <div class="header-wrapper">
        <div class="header-top">
       
            <a href="#">고객</a>
            <c:choose>
		        <c:when test="${sessionScope.loginAdmin != null}">
		            <!-- Admin 사용자 -->
		            <a href="${pageContext.request.contextPath}/page/adminMyPage.jsp">관리자 마이페이지</a>
		        </c:when>
		        <c:when test="${sessionScope.loginUser != null}">
		            <!-- Regular 사용자 -->
		            <a href="${pageContext.request.contextPath}/page/mypage.jsp">마이페이지</a>
		        </c:when>
		        <c:otherwise>
		            <!-- 로그인이 되어 있지 않은 경우 로그인 페이지로 이동 -->
		            <a href="${pageContext.request.contextPath}/page/login.jsp">마이페이지</a>
		        </c:otherwise>
		    </c:choose>
            <a href="#">알림</a>
                     
            <c:choose>
		    <c:when test="${sessionScope.loginAdmin != null}">
		        <!-- Content for Admin users -->
		        <a>${sessionScope.loginAdmin.adminId}, Welcome!</a>
		        <a href="${pageContext.request.contextPath}/front?key=user1&methodName=logout" class="btn btn-danger">Logout</a>
		    </c:when>
		    <c:when test="${sessionScope.loginUser != null}">
		        <!-- Content for Regular users -->
		        <a>${sessionScope.loginUser.nickname}님, 환영합니다!</a>
		        <a href="${pageContext.request.contextPath}/front?key=user1&methodName=logout" class="btn btn-danger">Logout</a>
		    </c:when>
		    <c:otherwise>
		        <!-- Content for guests -->
		        <a href="${pageContext.request.contextPath}/page/login.jsp">로그인</a>
		    </c:otherwise>
		</c:choose>

        </div>
        <div class="header-main">
            <div class="logo">
                <a href="${pageContext.request.contextPath}">
                    <img height="100px" src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/logo.png">
                </a>
            </div>
            <div class="header-searchbar">
                <div class="search-container">
                    <input type="text" class="search-bar" id="searchBar" placeholder="Search..." maxlength="16"  onkeypress="enterKey();" />
                    <a class="search-button"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <circle cx="11" cy="11" r="8" stroke="currentColor" stroke-width="2"/>
                        <line x1="17" y1="17" x2="22" y2="22" stroke="currentColor" stroke-width="2" stroke-linecap="round"/>
                    </svg>
                    </a>
                </div>
            </div>
<%--            ${not empty sessionScope.loginUser.notification ? 'bell-icon-active' : ''}--%>
            <div class="header-right">
                <div class="bell-container">
                    <svg xmlns="http://www.w3.org/2000/svg" width="21" height="21" class="bell-icon"
                         viewBox="0 0 16 16">
                        <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2m.995-14.901a1 1 0 1 0-1.99 0A5 5 0 0 0 3 6c0 1.098-.5 6-2 7h14c-1.5-1-2-5.902-2-7 0-2.42-1.72-4.44-4.005-4.901"/>
                    </svg>
                    <div class="tooltip">
            		<c:if test="${sessionScope.loginUser != null}">
                        <ul>
                            
                        </ul>
                    </c:if>    
                    </div>
                    
                </div>
                <span><a href="">Home</a></span>
                <span><a href="${pageContext.request.contextPath}/front?key=product&methodName=selectAllProduct">Shop</a></span>
                <span><a href="${pageContext.request.contextPath}/page/login.jsp">Login</a></span>
            </div>
        </div>
</div>
<script type="text/javascript">
	// 판매 조회 함수

	function notify() {
		$.ajax({
			url : '${pageContext.request.contextPath}/ajax',
			method : 'GET',
			data : {
				key : 'userAjax',
				methodName : 'notfiyList'
			},
			dataType : "json",
			success : function(result) {
				let str = "";
				$.each(result, function(index, notify) {
					if(notify.isRead==0){
                        $(".bell-icon").addClass("bell-icon-active");
						str += "<li><a href='${pageContext.request.contextPath}/front?key=user1&methodName=updateNotify&salesNo="+notify.salesNo+"&no="+notify.no+"&msg="+notify.msg+"'>"+notify.msg+"</a>"; // 영어 이름
					}else{
						str += "<li><a href='${pageContext.request.contextPath}/front?key=user1&methodName=updateNotify&salesNo="+notify.salesNo+"&no="+notify.no+"&msg="+notify.msg+"' style='color: gray;'>"+notify.msg+"</a>"; // 영어 이름
					}
					str+="<input type='button' class='delete' data-info = '"+notify.no+"' value='X' ></li>";
				});
				$(".tooltip ul").empty().append(str);
			},
			error : function(error) {
				console.error("알람이 없습니다.", error);
			}
		});
	}
	notify();
	$(document).on("click",".delete",function(){
		 $.ajax({
 			url:"ajax",
				type: "post",
				dataType: "json" ,
				data: {key:"userAjax" , methodName : "deleteNotify", no : $(this).attr("data-info") }, //서버에게 보낼 데이터정보(parameter정보)
			
				success : function(data){
					notify();
				},
				error : function(err){
				}			
 		
 		});
	});
</script>
<script type="text/javascript">

		$(".search-button").click(function(){
			console.log("123");
			var inputKeyword = document.getElementById("searchBar").value;
			console.log(inputKeyword);
			location.href = "${pageContext.request.contextPath}/front?key=product&methodName=searchProductByKeyword&inputKeyword="+inputKeyword;
		})

		//검색 함수
		function enterKey(){
			if(window.event.keyCode ==13){
				console.log("123");
				var inputKeyword = document.getElementById("searchBar").value;
				console.log(inputKeyword);
				location.href = "${pageContext.request.contextPath}/front?key=product&methodName=searchProductByKeyword&inputKeyword="+inputKeyword;
			} else
				console.log("111222333");

		}//enterKey End
	

</script>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</body>
</html>
