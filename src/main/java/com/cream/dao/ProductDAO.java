package com.cream.dao;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.BrandDTO;
import com.cream.dto.ProductDTO;
import com.cream.dto.PurchaseDTO;

public interface ProductDAO {
	/**
	 * 	상품 전체 출력
	 * */
	List<ProductDTO> selectAllProduct() throws SQLException;
	
	
	/**
	 * 	상품 ID로 검색
	 * */
	ProductDTO searchByProductId(String productId) throws SQLException;
	
	/**
	 * 	상품 한글검색
	 * */
	List<ProductDTO> searchProductKor(String productName) throws SQLException;
	
	
	/**
	 * 	상품 영어검색
	 * */
	List<ProductDTO> searchProductEng(String productName) throws SQLException;

	/*
	 * 상품 상세보기
	 */
	ProductDTO detail(int productNo) throws SQLException;
	
	
}
