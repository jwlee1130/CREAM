
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="user-container">
    <div class="user-wrapper">
        <h1>회원 정보</h1>
        <!-- 내 계정 -->
        <div class="user-info-wrapper">
            <h2>내 계정</h2>
            <div class="info-item" data-field="email">
                <label>이메일 주소</label>
                <input type="email" id="userEmail" value="" disabled>
                <button class="change-btn" data-field="email">변경</button>
            </div>
            <div class="info-item" data-field="password">
                <label>비밀번호</label>
                <input type="password" id="userPw" value="" disabled>
                <button class="change-btn" data-field="password">변경</button>
            </div>
            <div class="info-item" data-field="nickname">
                <label>닉네임</label>
                <input type="text" id="nickname" value="" disabled>
                <button class="change-btn" data-field="nickname">변경</button>
            </div>
        </div>
        <!-- 개인 정보 -->
        <div class="user-info-wrapper">
            <h2>개인 정보</h2>
            <div class="info-item" data-field="phone">
                <label>휴대폰 번호</label>
                <input type="tel" id="phone" value="" disabled>
                <button class="change-btn" data-field="phone">변경</button>
            </div>
            <div class="info-item" data-field="shoeSize">
                <label>신발 사이즈</label>
                <input type="number" id="shoeSize" value="" disabled>
                <button class="change-btn" data-field="shoeSize">변경</button>
            </div>
        </div>
        <!-- 주소 -->
        <div class="user-info-wrapper">
            <h2>주소</h2>
            <div class="info-item" data-field="address">
                <label>주소</label>
                <input type="text" id="address" value="" disabled>
                <button class="change-btn" data-field="address">변경</button>
            </div>
        </div>
    </div>
</div>

