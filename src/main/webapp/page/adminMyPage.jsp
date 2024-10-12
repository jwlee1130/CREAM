<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>관리자 마이페이지</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
<%--    <link rel="stylesheet" href="../css/adminMyPage.css">--%>
    <link rel="stylesheet" href="../css/adminMyPage.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body>

<jsp:include page="../includes/header.jsp" />

<div class="container">
    <div class="main">
        <div class="main-side">
            <h1>관리자 마이페이지</h1>
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

        // 콘텐츠 로드 함수
        function loadContent() {
            const hash = window.location.hash.substring(1);
            if (hash === 'inspectionList') {
                loadInspectionList();
            } else if (hash === 'userManagement') {
                loadUserManagement();
            } else if (hash === 'statistics') {
                loadStatistics();
            } else {
                // 기본 콘텐츠
                contentDiv.innerHTML = '<h2>관리자 마이페이지</h2>';
            }
        }

        // 검수 목록 로드 함수
        function loadInspectionList() {
            contentDiv.innerHTML = `
                <h2>검수 목록</h2>
                <table id="inspectionTable">
                    <thead>
                        <tr>
                            <th>상품 번호</th>
                            <th>상품명</th>
                            <th>검수 상태</th>
                            <th>검수 날짜</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- 검수 목록이 이곳에 추가됩니다 -->
                    </tbody>
                </table>
            `;

            // AJAX 요청으로 검수 목록 가져오기
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'GET',
                data: {
                    key: 'adminAjax',
                    methodName: 'getInspectionList'
                },
                dataType: "json",
                success: function(result) {
                    let tb = "";
                    $.each(result, function(index, inspection) {
                        tb += '<tr>';
                        tb += '<td>' + inspection.productNo + '</td>';
                        tb += '<td>' + inspection.productName + '</td>';
                        tb += '<td>' + inspection.status + '</td>';
                        tb += '<td>' + inspection.inspectionDate + '</td>';
                        tb += '</tr>';
                    });
                    $("#inspectionTable tbody").empty().append(tb);
                },
                error: function(error) {
                    console.error("검수 목록 로드 오류: ", error);
                }
            });
        }

        // 회원 관리 로드 함수
        function loadUserManagement() {
            contentDiv.innerHTML = '<h2>회원 관리 페이지</h2>';
            // 회원 관리 관련 내용 추가
        }

        // 통계 로드 함수
        function loadStatistics() {
            contentDiv.innerHTML = '<h2>통계 페이지</h2>';
            // 통계 관련 내용 추가
        }

        // 해시 변경 시 콘텐츠 로드
        window.addEventListener('hashchange', loadContent);

        // 초기 콘텐츠 로드
        loadContent();
    });
</script>

</body>
</html>
