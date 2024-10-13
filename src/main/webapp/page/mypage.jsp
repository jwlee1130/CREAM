
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Document</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/mypage.css">
</head>
<body>
<jsp:include page="../includes/header.jsp" />
<div class="container">
    <div class="main">
        <div class="main-side">
            <h1>마이 페이지</h1>
            <ul>
                <h2>쇼핑 정보</h2>
                <li><a href="#test1">구매내역</a></li>
                <li><a href="#test2">판매내역</a></li>
            </ul>
            <ul>
                <h2>회원 정보</h2>
                <li><a href="">회원 관리</a></li>
                <li><a href="">판매자 등급</a></li>
                <li><a href="">포인트</a></li>
            </ul>
        </div>
        <div class="main-content" id="content">
            <jsp:include page="../includes/test1.html" /> <!-- 기본 콘텐츠 -->
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp" />
<script>
  document.addEventListener('DOMContentLoaded', () => {
    const contentDiv = document.getElementById('content');

    // 초기 콘텐츠 로드 함수
    function loadContent() {
      const hash = window.location.hash.substring(1) || 'test1';
      console.log(hash)
      fetchContent(hash);
    }

    // Fetch API를 사용하여 콘텐츠를 가져오는 함수
    function fetchContent(page) {
      fetch("${pageContext.request.contextPath}/includes/"+page+".html")
      .then(response => {
        if (!response.ok) {
          throw new Error(`Could not fetch ${page}.html: ${response.statusText}`);
        }
        return response.text();
      })
      .then(data => {
        contentDiv.innerHTML = data;
      })
      .catch(error => {
        contentDiv.innerHTML = `<h2>페이지를 찾을 수 없습니다.</h2>`;
        console.error(error);
      });
    }

    // 해시 변경 시 콘텐츠 로드
    window.addEventListener('hashchange', loadContent);

    // 초기 콘텐츠 로드
    loadContent();
  });
</script>
<script src="../js/script.js"></script>

</body>
</html>
