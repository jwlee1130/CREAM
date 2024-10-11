package com.cream.dao;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.ProductDTO;

public interface ProductDAO {
	/**
	 * 	상품 전체 출력
	 * */
	List<ProductDTO> selectAllProduct() throws SQLException;
	
	/**
	 * 	상품 ID로 검색
	 * */
	ProductDTO searchByProductId(int productId) throws SQLException;
	
	/**
	 * 	상품 한글검색
	 * */
	List<ProductDTO> searchProductKor(String productName) throws SQLException;
	
	
	/**
	 * 	상품 영어검색
	 * */
	List<ProductDTO> searchProductEng(String productName) throws SQLException;
	
}
