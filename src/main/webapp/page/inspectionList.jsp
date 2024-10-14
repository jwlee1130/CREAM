<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

<h1 style="font-size:2.5rem;">검수 목록</h1>
<table id="inspectionTable">
  <thead>
  <tr>
    <th>판매 번호</th>
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

<script>
  $(document).ready(function() {
    loadInspectionList();
  });

  function loadInspectionList() {
    // 검수할 상품 목록을 가져오는 ajax
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
          tb += '<td>' + inspection.no + '</td>';
          tb += '<td class="productNameCell" data-product-no="' + inspection.productNo + '">로딩 중...</td>';

          // 검수 상태가 0이면 N, 그 외는 그대로 표시
          const inspectionStatus = inspection.salesStatus === 0 ? 'N' : inspection.salesStatus;
          tb += '<td>' + inspectionStatus + '</td>';

          tb += '<td>' + inspection.regdate + '</td>';
          tb += '<td>';
          tb += '<button class="btn mr-2 approve-btn" data-index="' + index + '" data-sales-no="' + inspection.no + '" data-sales-status="' + inspection.salesStatus + '" data-start-price="'+ inspection.startingPrice+'" style="background-color: #41B979; border-color: #41B979; color: white;">승인</button>';
          tb += '<button class="btn reject-btn" data-index="' + index + '" data-sales-no="' + inspection.no + '" style="background-color: #EF6253; border-color: #EF6253; color: white;">반려</button>';

          tb += '</td>';
          tb += '<td>';
          tb += '<select class="grade-select" data-index="' + index + '" data-sales-no="' + inspection.no + '">';
          tb += '<option value="U" selected>U</option>'; // 기본값을 U(Unknown)로 설정
          tb += '<option value="A">A</option>';
          tb += '<option value="B">B</option>';
          tb += '<option value="C">C</option>';
          tb += '</select>';
          tb += '</td>';
          tb += '</tr>';
        });
        $("#inspectionTable tbody").empty().append(tb);

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

  $(document).on('click', '.approve-btn', function() {
    let salesNo = $(this).data('sales-no');
    let grade = $('.grade-select[data-sales-no="' + salesNo + '"]').val();
	let price = $(this).data('start-price');
    if (grade === 'U') {
      alert("등급을 선택해주세요.");
      return;
    }

    approveProduct(salesNo, grade, price);
  });

  $(document).on('click', '.reject-btn', function() {
    let salesNo = $(this).data('sales-no');
    rejectProduct(salesNo);
  });

  $(document).on('change', '.grade-select', function() {
    let salesNo = $(this).data('sales-no');
    let selectedGrade = $(this).val();
    updateProductGrade(salesNo, selectedGrade);
  });

  function approveProduct(salesNo, grade, price) {
    $.ajax({
      url: '${pageContext.request.contextPath}/ajax',
      method: 'POST',
      data: {
        key: 'admin',
        methodName: 'updateSalesStatus',
        salesNo: salesNo,
        salesStatus: 1, // 승인: sales_status를 1로 설정
        grade: grade,
        startingPrice : price
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

  function rejectProduct(salesNo) {
    $.ajax({
      url: '${pageContext.request.contextPath}/ajax',
      method: 'POST',
      data: {
        key: 'admin',
        methodName: 'updateSalesStatus',
        salesNo: salesNo,
        salesStatus: 2 // 반려: sales_status를 2로 설정
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

  function updateProductGrade(salesNo, grade) {
    $.ajax({
      url: '${pageContext.request.contextPath}/ajax',
      method: 'POST',
      data: {
        key: 'admin',
        methodName: 'updateSalesGrade',
        salesNo: salesNo,
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
</script>
