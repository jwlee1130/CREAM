
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="../css/login.css">
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="login-container">
    <div class="login-main">
        <div class="login-box">
            <div class="logo">
                <img src="https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/logo.png" alt="로고">
            </div>
            <form class="login-form" action="" method="post">
                <div class="form-group">
                    <label for="username">아이디</label>
                    <input type="text" id="username" class="form-control" placeholder="아이디를 입력하세요" required>
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" id="password" class="form-control" placeholder="비밀번호를 입력하세요" required>
                </div>
                <button type="submit" class="btn btn-primary">로그인</button>
            </form>

            <div class="register-link">
                <p>아직 회원이 아니신가요? <a href="${pageContext.request.contextPath}/page/register.jsp">회원가입하기</a></p>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp" />
</body>

</html>