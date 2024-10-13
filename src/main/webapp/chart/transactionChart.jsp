<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>전체 거래 목록</title>
    <style>
        /* transaction- 접두사를 사용하여 스타일 범위 제한 */
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
            padding: 12px; /* 추가적인 패딩 */
        }
        .transaction-chart-table th {
            background-color: #f7f7f7;
            font-weight: bold;
        }
        .transaction-chart-table tbody tr:nth-child(odd) {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

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

<script>
    $(document).ready(function() {
        loadRecentTransactions();
    });

    function loadRecentTransactions() {
        $.ajax({
            url: '/ajax',
            type: 'GET',
            dataType: 'json',
            data: {
                key: 'statistics',
                methodName: 'getPurchaseData',
                productNo: 1,
                period: 5 // 필요한 기간 설정
            },
            success: function(data) {
                console.log("전체 데이터:", data);

                if (!Array.isArray(data)) {
                    console.error('받은 데이터가 배열이 아닙니다.');
                    return;
                }

                // 받은 데이터를 가장 최근 날짜부터 오랜된 날짜 순으로 정렬
                data.sort(function(a, b) {
                    const dateA = new Date(a.regdate);
                    const dateB = new Date(b.regdate);
                    return dateB - dateA;
                });

                console.log("정렬된 데이터:", data);

                let transactionTableBody = '';

                // 받은 모든 데이터를 처리
                for (let i = 0; i < data.length; i++) {
                    let transaction = data[i];

                    // 각 트랜잭션의 필드와 타입 확인
                    console.log('Transaction:', i, transaction);
                    console.log('regdate:', transaction.regdate, typeof transaction.regdate);
                    console.log('price:', transaction.price, typeof transaction.price);

                    // regdate와 price가 null 또는 undefined인지 확인 후 기본값 설정
                    const regdateRaw = transaction.regdate ? transaction.regdate.split(' ')[0].trim() : '날짜 없음';
                    const price = (transaction.price || transaction.price === 0) ? transaction.price : '가격 없음';

                    console.log(`Raw Date: ${regdateRaw}, Price: ${price}`);

                    // price가 숫자인지 확인 후 포맷팅
                    let displayPrice = '가격 없음';
                    if (typeof price === 'number') {
                        displayPrice = price.toLocaleString() + '원';
                    } else if (typeof price === 'string' && !isNaN(Number(price))) {
                        displayPrice = Number(price).toLocaleString() + '원';
                    } else {
                        displayPrice = price + '원';
                    }

                    // regdate가 유효한 날짜 형식인지 확인
                    let displayDate = '날짜 없음';
                    if (regdateRaw !== '날짜 없음') {
                        if (dateParts.length === 3) {
                            const dateObj = new Date(regdateRaw);
                            if (!isNaN(dateObj.getTime())) {
                                displayDate = regdateRaw;
                            } else {
                                console.warn(`유효하지 않은 날짜 형식: ${regdateRaw}`);
                            }
                        } else {
                            console.warn(`잘못된 날짜 형식: ${regdateRaw}`);
                        }
                    }

                    console.log(`Final Date: ${displayDate}, Final Price: ${displayPrice}`);
                    if (displayDate !== '날짜 없음') {
                        transactionTableBody += '<tr>' +
                            '<td>' + displayDate + '</td>' +
                            '<td>' + displayPrice + '</td>' +
                            '</tr>';
                    } else {
                        console.warn(`트랜잭션 ${i}의 날짜 정보가 유효하지 않아 제외됩니다.`);
                    }
                }

                console.log("생성된 테이블 HTML:", transactionTableBody);

                $('#transactionChartTableBody').html(transactionTableBody);
            },
            error: function(xhr, status, error) {
                console.error('데이터 로딩 오류 발생:', error);
            }
        });
    }
</script>

</body>
</html>
