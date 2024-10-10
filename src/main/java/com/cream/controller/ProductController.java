package com.cream.controller;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.ProductDTO;
import com.cream.service.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductController implements Controller {
	ProductServiceImpl productService = new ProductServiceImpl();
	List<ProductDTO> productList;
	
	public List<ProductDTO> selectAll(HttpServletRequest request, HttpServletResponse response){
		System.out.println("상품 전체 검색 메소드...여기로 와야해!!!");
		try {
			
			productList = productService.selectAllProduct();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return productList;
		
	}//selectAll 끝
	
	
}
