<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    <h2> A Minor Upgrade </h2>

	   <ul class="nav navbar-nav">

      <li><a href="${path}/user/login.jsp">Login</a></li>
      <li><a href="${pageContext.request.contextPath}/front">Board</a></li>
     </ul>
     <c:if test="${not empty loginUser}">
	     <ul class="nav navbar-nav navbar-right">
	     	<li class="active"><a href="#">${loginUser} / ${loginUser.userId}!</a></li>
	      	<li><a href="${pageContext.request.contextPath}/front?key=user&methodName=logout" class="btn btn-danger">Logout</a></li>
	     </ul>
     </c:if>
	
	

	<!-- 상품검색 -->
	<form method="post" action="${pageContext.request.contextPath}/front">
	<input type="hidden" name="key" value="product">
	<input type="hidden" name="methodName" value="selectAll">
	<input type="text">
	<button>검색</button>
	</form>
	

</body>
</html>