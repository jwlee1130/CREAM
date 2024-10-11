package com.cream.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cream.dto.ProductDTO;
import com.cream.service.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductController implements Controller {
	ProductServiceImpl productService = new ProductServiceImpl();
	List<ProductDTO> productList = new ArrayList<ProductDTO>();
	
	
	public ModelAndView selectAllProduct(HttpServletRequest request, HttpServletResponse response){
		System.out.println("상품 전체 검색 메소드...여기로 와야해!!!");
		try {
			
			productList = productService.selectAllProduct();
			request.setAttribute("productList", productList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("controller로 다시 돌아왔니?");
		System.out.println(productList);
		return new ModelAndView("index.jsp", false);
		
	}//selectAll 끝
	
	public ModelAndView searchByProductId(HttpServletRequest request, HttpServletResponse response){
		System.out.println("controller - 상품 ID 검색 메소드");
		try {
			int productId = Integer.parseInt(request.getParameter("productId"));
			ProductDTO product = productService.searchByProductId(productId);
			request.setAttribute("product", product);
			System.out.println(product);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ModelAndView("/page/product.jsp", false);
		
	}//selectAll 끝
}
