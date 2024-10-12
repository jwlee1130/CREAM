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

        function loadInspectionList() {
            contentDiv.innerHTML = `
                <h2>검수 목록</h2>
                <table id="inspectionTable">
                    <thead>
                        <tr>
                            <th>상품 번호</th>
                            <th>상품명</th>
                            <th>검수 상태</th>
                            <th>등록 날짜</th>
                            <th>승인/반려</th>
                            <th>등급</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            `;

            // 검수할 상품 목록을 가져오는 AJAX 요청
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'GET',
                data: {
                    key: 'admin',
                    methodName: 'getUnapprovedProducts'
                },
                dataType: "json",
                success: function(result) {
                    let tb = "";
                    $.each(result, function(index, inspection) {
                        tb += '<tr>';
                        tb += '<td>' + inspection.productNo + '</td>';
                        // ID 대신 클래스와 data-attribute 사용
                        tb += '<td class="productNameCell" data-product-no="' + inspection.productNo + '">로딩 중...</td>';
                        tb += '<td>' + inspection.salesStatus + '</td>';
                        tb += '<td>' + inspection.regdate + '</td>';
                        tb += '<td>';
                        // 버튼과 셀렉트 박스에 data-id 대신 data-index 사용
                        tb += '<button class="approve-btn" data-index="' + index + '">승인</button>';
                        tb += '<button class="reject-btn" data-index="' + index + '">반려</button>';
                        tb += '</td>';
                        tb += '<td>';
                        tb += '<select class="grade-select" data-index="' + index + '">';
                        tb += '<option value="A">A</option>';
                        tb += '<option value="B">B</option>';
                        tb += '<option value="C">C</option>';
                        tb += '<option value="D">D</option>';
                        tb += '</select>';
                        tb += '</td>';
                        tb += '</tr>';
                    });
                    $("#inspectionTable tbody").empty().append(tb);

                    // 테이블이 렌더링된 후에 각 productNo에 대해 AJAX 요청하여 상품명 로딩
                    $.each(result, function(index, inspection) {
                        $.ajax({
                            url: '${pageContext.request.contextPath}/ajax',
                            method: 'GET',
                            data: {
                                key: 'admin',
                                methodName: 'getProductName',
                                productNo: inspection.productNo
                            },
                            dataType: "json",
                            success: function(response) {
                                console.log("Product Name for productNo " + inspection.productNo + ": " + response.productName);
                                // 동일한 productNo를 가진 모든 요소 업데이트
                                $('.productNameCell[data-product-no="' + inspection.productNo + '"]').text(response.productName);
                            },
                            error: function(error) {
                                console.error("ProductName 로드 오류: ", error);
                            }
                        });
                    });
                },
                error: function(error) {
                    console.error("검수 목록 로드 오류: ", error);
                }
            });
        }

        // 승인 버튼 클릭 이벤트 핸들러
        $(document).on('click', '.approve-btn', function() {
            let index = $(this).data('index');
            let productNo = $('#inspectionTable tbody tr').eq(index).find('td').eq(0).text();
            approveProduct(productNo);
        });

        $(document).on('click', '.reject-btn', function() {
            let index = $(this).data('index');
            let productNo = $('#inspectionTable tbody tr').eq(index).find('td').eq(0).text();
            rejectProduct(productNo);
        });

        $(document).on('change', '.grade-select', function() {
            let index = $(this).data('index');
            let productNo = $('#inspectionTable tbody tr').eq(index).find('td').eq(0).text();
            let selectedGrade = $(this).val();
            updateProductGrade(productNo, selectedGrade);
        });

        function approveProduct(productNo) {
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'POST',
                data: {
                    key: 'admin',
                    methodName: 'approveProduct',
                    productNo: productNo
                },
                success: function(response) {
                    alert("상품이 승인되었습니다.");
                    loadInspectionList();
                },
                error: function(error) {
                    console.error("승인 실패:", error);
                    alert("승인에 실패했습니다.");
                }
            });
        }

        function rejectProduct(productNo) {
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'POST',
                data: {
                    key: 'admin',
                    methodName: 'rejectProduct',
                    productNo: productNo
                },
                success: function(response) {
                    alert("상품이 반려되었습니다.");
                    loadInspectionList();
                },
                error: function(error) {
                    console.error("반려 실패:", error);
                    alert("반려에 실패했습니다.");
                }
            });
        }

        function updateProductGrade(productNo, grade) {
            $.ajax({
                url: '${pageContext.request.contextPath}/ajax',
                method: 'POST',
                data: {
                    key: 'admin',
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

        window.addEventListener('hashchange', loadContent);

        loadContent();
    });
</script>

</body>
</html>
