<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    body, html {
        background-color: #fff;
        margin: 0;
        padding: 0;
    }
    .transaction-chart-container {
        font-family: 'Noto Sans', sans-serif;
        background-color: #fff;
        padding: 20px;
    }
    .transaction-chart-table-container {
        max-width: 600px;
        margin: 20px auto;
    }
    .transaction-chart-table {
        border: 1px solid #ddd;
        border-collapse: separate;
        border-radius: 10px;
        width: 100%;
        overflow: hidden;
        background-color: #fff;
    }
    .transaction-chart-table th, .transaction-chart-table td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: center;
        background-color: #fff;
    }
    .transaction-chart-table th {
        background-color: #fff;
        font-weight: bold;
    }
</style>

<div class="transaction-chart-container">
    <div class="transaction-chart-table-container">
        <table class="transaction-chart-table">
            <thead>
            <tr>
                <th>날짜</th>
                <th>금액</th>
            </tr>
            </thead>
            <tbody id="transactionChartTableBody">
            </tbody>
        </table>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    var tcjQuery = jQuery.noConflict(true);
    (function($) {
        function loadRecentTransactions() {
            $.ajax({
                url: '/ajax',
                type: 'GET',
                dataType: 'json',
                data: {
                    key: 'statistics',
                    methodName: 'getPurchaseData',
                    productNo: ${productDetail.no},
                    period: 5
                },
                success: function(data) {
                    var transactionTableBody = '';

                    data.sort(function(a, b) {
                        return new Date(b.regdate) - new Date(a.regdate);
                    });

                    for (var i = 0; i < data.length; i++) {
                        var transaction = data[i];
                        var regdateRaw = transaction.regdate ? transaction.regdate.split(' ')[0].trim() : '';
                        var price = transaction.price.toLocaleString() + '원';

                        if (regdateRaw) {
                            transactionTableBody += '<tr>' +
                                '<td>' + regdateRaw + '</td>' +
                                '<td>' + price + '</td>' +
                                '</tr>';
                        }
                    }

                    $('#transactionChartTableBody').html(transactionTableBody);
                },
                error: function(xhr, status, error) {
                    console.error('데이터 로딩 오류 발생:', error);
                }
            });
        }

        $(document).ready(function() {
            loadRecentTransactions();
        });
    })(tcjQuery);
</script>
