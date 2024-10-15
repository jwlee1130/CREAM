
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="user-container">
    <div class="user-wrapper">
        <h1>회원 정보</h1>

        <!-- 내 계정 -->
        <div class="user-info-wrapper">
            <h2>내 계정</h2>

            <!-- 이메일 주소 -->
            <div class="info-item" data-field="email">
                <label>이메일 주소</label>
                <input type="email" value="Email@email.com" disabled>
                <button class="change-btn">변경</button>
            </div>

            <!-- 비밀번호 -->
            <div class="info-item" data-field="password">
                <label>비밀번호</label>
                <input type="password" value="********" disabled>
                <button class="change-btn">변경</button>
            </div>
            <!-- 닉네임 -->
            <div class="info-item" data-field="nickname">
                <label>닉네임</label>
                <input type="text" value="윤성바오" disabled>
                <button class="change-btn">변경</button>
            </div>
        </div>

        <!-- 개인 정보 -->
        <div class="user-info-wrapper">
            <h2>개인 정보</h2>

            <!-- 휴대폰 번호 -->
            <div class="info-item" data-field="phone">
                <label>휴대폰 번호</label>
                <input type="tel" value="010-1111-2234" disabled>
                <button class="change-btn">변경</button>
            </div>

            <!-- 신발 사이즈 -->
            <div class="info-item" data-field="shoeSize">
                <label>신발 사이즈</label>
                <input type="number" value="270" disabled>
                <button class="change-btn">변경</button>
            </div>
        </div>

        <!-- 주소 -->
        <div class="user-info-wrapper">
            <h2>주소</h2>
            <div class="item-address">
                <div class="receiver">
                    <span>우편번호</span>
                    <p>13221</p>
                </div>
                <div class="receiver">
                    <span>주소</span>
                    <p>경기도 남양주시 화곡동 105-2</p>
                </div>
                <div class="receiver">
                    <span>상세주소</span>
                    <p>!!상세주소 들어가는영역!!</p>
                </div>
                <button class="change-address-btn" style="width: 70%;">주소 변경</button>
            </div>
        </div>

    </div>
</div>

