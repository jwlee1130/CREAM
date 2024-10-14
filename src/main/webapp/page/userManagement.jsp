<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>관리자 - 사용자 및 판매글 삭제</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      font-family: 'Noto Sans', sans-serif;
      background-color: #f9f9f9;
      padding: 20px;
    }
    .container {
      max-width: 800px;
      background-color: white;
      padding: 30px;
      border-radius: 10px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }
    h1 {
      text-align: center;
      margin-bottom: 30px;
    }
    .btn-custom {
      background-color: #ff5a5a;
      color: white;
    }
    .btn-custom:hover {
      background-color: #ff3a3a;
    }
    .alert {
      display: none;
      margin-top: 20px;
    }
    .form-control {
      width: 100%;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>사용자 및 판매글 삭제</h1>

  <div class="mb-5">
    <h3>사용자 삭제</h3>
    <form id="deleteUserForm" class="form-inline">
      <label for="userNo" class="sr-only">사용자 번호</label>
      <input type="text" id="userNo" name="userNo" class="form-control mb-2 mr-sm-2" placeholder="사용자 번호 입력">
      <button type="button" id="deleteUserBtn" class="btn btn-custom mb-2">사용자 삭제</button>
    </form>
    <div id="userDeleteAlert" class="alert alert-info"></div>
  </div>

  <div>
    <h3>판매글 삭제</h3>
    <form id="deleteSalesForm" class="form-inline">
      <label for="salesNo" class="sr-only">판매 번호</label>
      <input type="text" id="salesNo" name="salesNo" class="form-control mb-2 mr-sm-2" placeholder="판매글 번호 입력">
      <button type="button" id="deleteSalesBtn" class="btn btn-custom mb-2">판매글 삭제</button>
    </form>
    <div id="salesDeleteAlert" class="alert alert-info"></div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
  $(document).ready(function() {
    $('#deleteUserBtn').click(function() {
      const userNo = $('#userNo').val().trim();
      if (userNo === '') {
        $('#userDeleteAlert').text('사용자 번호를 입력하세요.').addClass('alert-danger').show();
        return;
      }

      $.ajax({
        url: '${pageContext.request.contextPath}/ajax',
        method: 'POST',
        data: {
          key: 'admin',
          methodName: 'deleteUserById',
          userNo: userNo
        },
        success: function(response) {
          console.log(response);
          $('#userDeleteAlert').text('사용자가 성공적으로 삭제되었습니다.').removeClass('alert-danger').addClass('alert-success').show();
        },
        error: function() {
          $('#userDeleteAlert').text('사용자 삭제에 실패했습니다.').removeClass('alert-success').addClass('alert-danger').show();
        }
      });
    });

    $('#deleteSalesBtn').click(function() {
      const salesNo = $('#salesNo').val().trim();
      if (salesNo === '') {
        $('#salesDeleteAlert').text('판매 번호를 입력하세요.').addClass('alert-danger').show();
        return;
      }

      $.ajax({
        url: '${pageContext.request.contextPath}/ajax',
        method: 'POST',
        data: {
          key: 'admin',
          methodName: 'deleteSalesById',
          salesNo: salesNo
        },
        success: function(response) {
          console.log(response);
          $('#salesDeleteAlert').text('판매글이 성공적으로 삭제되었습니다.').removeClass('alert-danger').addClass('alert-success').show();
        },
        error: function() {
          $('#salesDeleteAlert').text('판매글 삭제에 실패했습니다.').removeClass('alert-success').addClass('alert-danger').show();
        }
      });
    });
  });
</script>
</body>
</html>
