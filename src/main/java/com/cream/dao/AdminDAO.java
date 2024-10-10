package com.cream.dao;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.ProductDTO;
import com.cream.dto.SurveyDTO;

public interface AdminDAO {
	/**
	 * 	판매 승인
	 * */
	int updateSalesStatus(int sales_no,int sales_status, String grade) throws SQLException;
	
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
	List<ProductDTO> getUnapprovedProducts() throws SQLException;
	
	/**
	 * 	검수 후 상태 변경 및 등급 부여
	 * */
	int inspectAndChangeStatus(int product_no,  char grade) throws SQLException;
	
	/**
	 * 	설문조사 제출
	 * */
	int submitSurvey(SurveyDTO surveyData) throws SQLException;
	
}
