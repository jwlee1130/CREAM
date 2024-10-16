package com.cream.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cream.dto.BrandDTO;
import com.cream.dto.ProductDTO;
import com.cream.service.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductAjaxController implements RestController {
	ProductServiceImpl productService = new ProductServiceImpl();
	List<ProductDTO> productList = new ArrayList<ProductDTO>();
	
	public Object selectAllProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		//System.out.println("Ajax 상품 전체 검색 메소드...!!!");

		productList = productService.selectAllProduct();	
		return productList;		
		
	}//selectAll 끝
	
	public Object searchProductByFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		System.out.println("Ajax 필터로 검색 메소드...!!!");

		String searchKeyword = request.getParameter("checkIndex");
		//String searchKeyword = "Asics";
		System.out.println(searchKeyword);
		if(getType(searchKeyword)==2) { //한글이다
			System.out.println("입력값은 한글");
			productList = productService.searchProductKor(searchKeyword);
			
		} else if(getType(searchKeyword)==1) { //영문이다
			System.out.println("입력값은 영문");
			productList = productService.searchProductEng(searchKeyword);
		} else 
			System.out.println("무언가 문제가 있다...ajax Controller");

		System.out.println(productList);
		return productList;		
		
	}//searchProductByKeyword 끝
	
	public static int getType(String word) { //검색 입력값 형태 확인(숫자0, 영문1, 한글2)
		int re=0;
		
		for(int i=0; i < word.length(); i++) {
			int index = word.charAt(i);
			
			if(index >=48 && index <=57) { //숫자
				re= 0;
			} else if(index >=65 && index <= 122) { //영문
				re=1;
			} else { //한글
				re=2;
			}
		}
		return re;
		
	}//getType End

	
	
	
}
