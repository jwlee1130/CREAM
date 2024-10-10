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
	
	<c:if test="${empty wishlist}">
        <p>관심상품이 없습니다.</p>
    </c:if>
    
    <c:if test="${not empty wishlist}">
        <table border="1">
            <thead>
                <tr>
                    <th>브랜드명</th>
                    <th>영어 이름</th>
                    <th>발매가</th>
                    <th>이미지</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${wishlist}">
                    <tr>
                        <td>${product.brandNo}</td>
                        <td>${product.engName}</td>
                        <td>${product.releasePrice}</td>
                        <td>
                            <c:if test="${not empty product.productImgDTO.filePath}">
                                <img src="${pageContext.request.contextPath}/images/${product.productImgDTO.filePath}" alt="${product.engName}" width="100" height="100">
                            </c:if>
                            <c:if test="${empty product.productImgDTO.filePath}">
                                <p>이미지 없음</p>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
	
	
</body>
</html>