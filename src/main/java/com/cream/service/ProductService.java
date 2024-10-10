package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.ProductDTO;

public interface ProductService {

	
	/**
	 * 전체 상품 검색
	 */
	List<ProductDTO> selectAllProduct()throws SQLException;
	
	/**
	 * 검색어가 한국어인 경우
	 */
	List<ProductDTO> searchProductKor(String keyword)throws SQLException;
	
	/**
	 * 검색어가 영어인 경우
	 */
	List<ProductDTO> searchProductEng(String keyword)throws SQLException;
	
}
