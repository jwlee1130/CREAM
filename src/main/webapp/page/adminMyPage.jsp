<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>관리자 마이페이지</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/adminMyPage.css">
    <!-- jQuery -->
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>

<jsp:include page="../includes/header.jsp" />

<div class="container">
    <div class="main">
        <div class="main-side">
            <h1 style="margin-bottom:10px;">관리자</h1>
            <h1>마이페이지</h1>
            <ul>
                <h2>검수</h2>
                <li><a href="#inspectionList">검수 목록</a></li>
            </ul>
            <ul>
                <h2>회원 관리</h2>
                <li><a href="#userManagement">회원 관리</a></li>
            </ul>
            <ul>
                <h2>통계</h2>
                <li><a href="#statistics">통계 보기</a></li>
            </ul>
        </div>
        <div class="main-content" id="content">
            <h2>관리자 마이페이지</h2>
        </div>
    </div>
</div>

<jsp:include page="../includes/footer.jsp" />

<script>
    document.addEventListener('DOMContentLoaded', () => {
        const contentDiv = document.getElementById('content');

        function loadContent() {
            const hash = window.location.hash.substring(1);
            if (hash === 'inspectionList') {
                // 검수 목록을 로드
                $('#content').load('inspectionList.jsp');
            } else if (hash === 'userManagement') {
                // userManagement.jsp 로드 (필요 시 생성)
                $('#content').load('userManagement.jsp');
            } else if (hash === 'statistics') {
                // statistics.jsp 로드 (필요 시 생성)
                $('#content').load('statistics.jsp');
            } else {
                contentDiv.innerHTML = '<h2>관리자 마이페이지</h2>';
            }
        }

        window.addEventListener('hashchange', loadContent);

        loadContent();
    });
</script>

</body>
</html>
