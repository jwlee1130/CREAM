package com.cream.dao;

import com.cream.dto.ProductDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;

import java.sql.SQLException;
import java.util.List;
//  주석 테스트
public interface AdminDAO {
	/**
	 * 	회원아이디삭제
	 * */
	int deleteUserById(int user_no) throws SQLException;
	
	/**
	 * 	판매글 삭제
	 * */
	int deleteProductById(int product_no) throws SQLException;
	
	/**
	 * 	판매 승인받지 않은 글 조회
	 * */
	List<SalesDTO> getUnapprovedProducts() throws SQLException;

	/**
	 * 	판매 승인
	 * */
	int updateSalesStatus(int sales_no,int sales_status, char grade) throws SQLException;
	
	/**
	 * 	설문조사 제출
	 * */
	int submitSurvey(SurveyDTO surveyData) throws SQLException;

	/**
	 *  설문조사 마지막에 제품 추천 해주는 코드
	 */
	public ProductDTO getRecommendedProduct(String brand,String  color,int maxPrice) throws SQLException;
}
