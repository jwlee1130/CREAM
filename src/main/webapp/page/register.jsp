
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 가입</title>

    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/register.css"></head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="register-container">
    <div class="register-main">
        <div class="register-box">
            <h2>
                회원 가입
            </h2>
            <form class="register-form" action="" method="post" id="registerForm">
                <div class="form-group">
                    <label for="username">아이디</label>
                    <input type="text" class="form-control" id="username" placeholder="아이디를 입력하세요" required>
                </div>

                <div class="form-group">
                    <label for="nickname">별명</label>
                    <input type="text" class="form-control" id="nickname" placeholder="별명을 입력하세요" required>
                </div>

                <div class="form-group">
                    <label for="email">이메일</label>
                    <input type="email" class="form-control" id="email" placeholder="you@example.com" required>
                </div>

                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password" placeholder="비밀번호를 입력하세요" required>
                </div>

                <div class="form-group">
                    <label for="confirmPassword">비밀번호 확인</label>
                    <input type="password" class="form-control" id="confirmPassword" placeholder="비밀번호를 확인하세요" required>
                    <div class="invalid-feedback" id="passwordFeedback">
                        비밀번호가 일치하지 않습니다.
                    </div>
                </div>

                <div class="form-group">
                    <label for="shoeSize">신발 사이즈</label>
                    <select class="form-control" id="shoeSize" required>
                        <option value="">사이즈를 선택하세요</option>
                        <option value="240">240</option>
                        <option value="260">260</option>
                        <option value="280">280</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="gender">성별</label>
                    <select class="form-control" id="gender" required>
                        <option value="">성별을 선택하세요</option>
                        <option value="male">남성</option>
                        <option value="female">여성</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="age">나이</label>
                    <input type="number" class="form-control" id="age" placeholder="나이를 입력하세요" required>
                </div>

                <button type="submit" class="btn btn-primary">회원가입</button>
            </form>

            <div class="register-link">
                <p>이미 회원이신가요? <a href="${pageContext.request.contextPath}/page/login.jsp">로그인하기</a></p>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp" />
<script>
  document.getElementById('registerForm').addEventListener('submit', function(event) {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const passwordFeedback = document.getElementById('passwordFeedback');

    if (password !== confirmPassword) {
      event.preventDefault(); // 폼 제출 방지
      passwordFeedback.style.display = 'block'; // 비밀번호 불일치 메시지 표시
    } else {
      passwordFeedback.style.display = 'none'; // 비밀번호 불일치 메시지 숨김
    }
  });
</script>
<script src="../js/script.js"></script>

</body>
</html>
