package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.BrandDTO;
import com.cream.dto.ProductDTO;

public interface ProductService {

	
	/**
	 * 전체 상품 검색
	 */
	List<ProductDTO> selectAllProduct()throws SQLException;

	/**
	 * 상품 ID로 검색
	 */
	ProductDTO searchByProductId(String productId) throws SQLException;
	
	/**
	 * 검색어가 한국어인 경우
	 */
	List<ProductDTO> searchProductKor(String keyword)throws SQLException;
	
	/**
	 * 검색어가 영어인 경우
	 */
	List<ProductDTO> searchProductEng(String keyword)throws SQLException;
	
	/**
	 * 상품 카테고리로 검색
	 */
	List<ProductDTO> searchProductByCategory(String productCategory)throws SQLException;
	
	/**
	 * 상품 브랜드로 검색
	 */
	List<ProductDTO> searchProductByBrand(String productBrand)throws SQLException;
	
	/**
	 * 상품 필터로 검색
	 */
	List<ProductDTO> searchProductByFilter(String[] categoryArr, String[] brandArr, String[] colorArr) throws SQLException;
	
	
	/*
	 * 상품 상세보기
	 */
	ProductDTO detail(int productNo) throws SQLException;
}
