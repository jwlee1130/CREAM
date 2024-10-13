<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .transaction-chart-container {
        font-family: 'Noto Sans', sans-serif;
        background-color: #f8f9fa;
        margin: 0;
        padding: 20px;
    }
    .transaction-chart-table-container {
        max-width: 800px;
        margin: 0 auto;
        margin-top: 20px;
    }
    .transaction-chart-table {
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        overflow: hidden;
        width: 100%;
    }
    .transaction-chart-table th, .transaction-chart-table td {
        vertical-align: middle;
        text-align: center;
        padding: 12px;
    }
    .transaction-chart-table th {
        background-color: #f7f7f7;
        font-weight: bold;
    }
    .transaction-chart-table tbody tr:nth-child(odd) {
        background-color: #f2f2f2;
    }
</style>

<div class="transaction-chart-container">
    <div class="transaction-chart-table-container">
        <table class="table transaction-chart-table table-hover">
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
                        var dateA = new Date(a.regdate);
                        var dateB = new Date(b.regdate);
                        return dateB - dateA;
                    });

                    for (var i = 0; i < data.length; i++) {
                        var transaction = data[i];
                        var regdateRaw = transaction.regdate ? transaction.regdate.split(' ')[0].trim() : '';
                        var price = (transaction.price || transaction.price === 0) ? transaction.price.toLocaleString() + '원' : '';

                        if (regdateRaw && price) {
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
