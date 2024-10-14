package com.cream.service;

import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AdminService {
    int deleteUserByNo(int userNo) throws SQLException; // 사용자 삭제

    int deleteUsersSalesByNo(int salesNo) throws SQLException; // 상품 삭제

    List<SalesDTO> getUnapprovedProducts() throws SQLException; // 승인되지 않은 상품 목록 가져오기(검수용)

    int updateSalesStatus(int salesNo, int salesStatus, int price) throws SQLException; // 판매 검수 태 업데이트

    int updateSalesGrade(int salesNo, char grade) throws SQLException; // 판매 등급 업데이트

    int submitSurvey(SurveyDTO surveyData) throws SQLException; // 설문 제출

    Map<String, String> getProductName(int productNo) throws SQLException; // 상품명 가져오기
}
