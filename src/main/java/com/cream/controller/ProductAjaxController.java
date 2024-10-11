package com.cream.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cream.dto.ProductDTO;
import com.cream.service.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductAjaxController implements RestController {
	ProductServiceImpl productService = new ProductServiceImpl();
	List<ProductDTO> productList = new ArrayList<ProductDTO>();
	
	
	public Object selectAllProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		System.out.println("Ajax 상품 전체 검색 메소드...!!!");

		productList = productService.selectAllProduct();	
		
		System.out.println("Ajax 상품 전체 검색 메소드 controller로 다시 돌아왔니?");
		System.out.println(productList);
		PrintWriter out = response.getWriter();
		out.print(productList);

		return productList;

		
	}//selectAll 끝
	
}
