<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>관리자 마이페이지</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/style.css">
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
                            <th>승인/반려</th>
                            <th>등급</th>
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
                        // 승인 및 반려 버튼
                        tb += '<td>';
                        tb += '<button class="approve-btn" data-id="' + inspection.productNo + '">승인</button>';
                        tb += '<button class="reject-btn" data-id="' + inspection.productNo + '">반려</button>';
                        tb += '</td>';
                        // 등급 선택박스 추가
                        tb += '<td>';
                        tb += '<select class="grade-select" data-id="' + inspection.productNo + '">';
                        tb += '<option value="A">A</option>';
                        tb += '<option value="B">B</option>';
                        tb += '<option value="C">C</option>';
                        tb += '<option value="D">D</option>';
                        tb += '</select>';
                        tb += '</td>';
                        tb += '</tr>';
                    });
                    $("#inspectionTable tbody").empty().append(tb);
                },
                error: function(error) {
                    console.error("검수 목록 로드 오류: ", error);
                }
            });
        }

        // 승인 버튼 클릭 이벤트 핸들러
        $(document).on('click', '.approve-btn', function() {
            let productNo = $(this).data('id');
            approveProduct(productNo);
        });

        // 반려 버튼 클릭 이벤트 핸들러
        $(document).on('click', '.reject-btn', function() {
            let productNo = $(this).data('id');
            rejectProduct(productNo);
        });

        // 등급 선택 변경 이벤트 핸들러
        $(document).on('change', '.grade-select', function() {
            let productNo = $(this).data('id');
            let selectedGrade = $(this).val();
            updateProductGrade(productNo, selectedGrade);
        });

        // 승인 처리 함수
        function approveProduct(productNo) {
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'POST',
                data: {
                    key: 'adminAjax',
                    methodName: 'approveProduct',
                    productNo: productNo
                },
                success: function(response) {
                    alert("상품이 승인되었습니다.");
                    loadInspectionList(); // 검수 목록 새로고침
                },
                error: function(error) {
                    console.error("승인 실패:", error);
                    alert("승인에 실패했습니다.");
                }
            });
        }

        // 반려 처리 함수
        function rejectProduct(productNo) {
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'POST',
                data: {
                    key: 'adminAjax',
                    methodName: 'rejectProduct',
                    productNo: productNo
                },
                success: function(response) {
                    alert("상품이 반려되었습니다.");
                    loadInspectionList(); // 검수 목록 새로고침
                },
                error: function(error) {
                    console.error("반려 실패:", error);
                    alert("반려에 실패했습니다.");
                }
            });
        }

        // 등급 업데이트 함수
        function updateProductGrade(productNo, grade) {
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'POST',
                data: {
                    key: 'adminAjax',
                    methodName: 'updateProductGrade',
                    productNo: productNo,
                    grade: grade
                },
                success: function(response) {
                    alert("등급이 업데이트되었습니다.");
                },
                error: function(error) {
                    console.error("등급 업데이트 실패:", error);
                    alert("등급 업데이트에 실패했습니다.");
                }
            });
        }

        // 해시 변경 시 콘텐츠 로드
        window.addEventListener('hashchange', loadContent);

        // 초기 콘텐츠 로드
        loadContent();
    });
</script>

</body>
</html>
