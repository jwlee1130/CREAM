package com.cream.dao;

import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface AdminDAO {

	/**
	 * 회원아이디 삭제
	 */
	int deleteUserById(int user_no) throws SQLException;

	/**
	 * 판매글 삭제
	 */
	int deleteProductById(int product_no) throws SQLException;

	/**
	 * 판매 승인받지 않은 글 조회
	 */
	List<SalesDTO> getUnapprovedProducts() throws SQLException;

	/**
	 * 판매 승인
	 */
	int updateSalesStatus(int sales_no, int sales_status, int price) throws SQLException; // char grade 파라미터 제거

	/**
	 * 판매 등급 업데이트
	 */
	int updateSalesGrade(int sales_no, char grade) throws SQLException; // grade 업데이트를 위한 메서드 추가

	/**
	 * 설문조사 제출
	 */
	int submitSurvey(SurveyDTO surveyData) throws SQLException;

	/**
	 * 설문조사 마지막에 제품 추천 해주는 코드
	 */
//	ProductDTO getRecommendedProduct(String brand, String color, int maxPrice) throws SQLException;

	/**
	 * productNo를 통해 productName을 가져오는 메서드
	 */
	Map<String, String> getProductName(int productNo) throws SQLException; // 반환 타입을 Map<String, String>으로 변경
}
