<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.cream.dto.UserDTO" %>
<%
    UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
%>
<div class="point-container">
    <div class="point-wrapper">
        <h1>포인트</h1>
        <div class="point-box">
            <div>
                <h3>사용 가능한 포인트</h3>
                <h2 id="currentCash"><%= loginUser.getCash() %> 원</h2>
            </div>
            <!-- 포인트 충전 폼 -->
            <form action="${pageContext.request.contextPath}/front?key=user1&methodName=updateCash" method="post">
                <input type="number" id="point" name="cash" min="1" required placeholder="포인트 금액">
                <button type="submit" class="point-btn">+ 포인트 충전하기</button>
            </form>
        </div>
    </div>
</div>
