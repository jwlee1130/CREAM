package com.cream.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cream.dto.ProductDTO;
import com.cream.service.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProductAjaxController implements RestController {
	ProductServiceImpl productService = new ProductServiceImpl();
	List<ProductDTO> productList = new ArrayList<ProductDTO>();
	
	public Object selectAllProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		//System.out.println("Ajax 상품 전체 검색 메소드...!!!");

		productList = productService.selectAllProduct();	
		return productList;		
		
	}//selectAll 끝
	
	public Object searchByProductId(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		//System.out.println("Ajax 상품 ID 검색 메소드...!!!");
		
		String inputProductId = request.getParameter("productId");
		ProductDTO product = productService.searchByProductId(inputProductId);
		return product;		
		
	}//searchByProductId 끝
	
	
//	@ResponseBody
//	@RequestMapping(value = "/ajax", method = RequestMethod.POST)
//	public Object checkedList(HttpSession session, HttpServletRequest request) throws IOException, SQLException {
//
//		String[] checkArr = request.getParameterValues("checkedArr");
//		System.out.println(checkArr);
//		
//		for( String checkId : checkArr) {
//			System.out.println(checkId);
//			
//		}
//		return null;
//	}
	
	
	public Object searchProductByFilter(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		System.out.println("Ajax 필터로 검색 메소드...!!!");

		//String[] categoryArr = request.getParameter("categoryArr");
		//String[] brandArr = request.getParameter("brandArr");
		String[] categoryArr = {"111"};
		String[] brandArr = {"1000","2000"};
		System.out.println("선택된 카테고리" + categoryArr + "선택된 브랜드" + brandArr);
		productList = productService.searchProductByFilter(categoryArr, brandArr);

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
