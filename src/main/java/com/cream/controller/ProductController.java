package com.cream.controller;

import java.awt.Window;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.tools.DocumentationTool.Location;

import com.cream.dto.ProductDTO;
import com.cream.exception.AuthenticationException;
import com.cream.service.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductController implements Controller {
		ProductServiceImpl service = new ProductServiceImpl();
		List<ProductDTO> productList = new ArrayList<ProductDTO>();

		public ModelAndView detail(HttpServletRequest request, HttpServletResponse response) throws SQLException, AuthenticationException {
		
				String no = request.getParameter("no");
				System.out.println(no);
				
				try {
						ProductDTO product = service.detail(Integer.parseInt(no));
						int recentPrice = service.getRecentPrice(Integer.parseInt(no));
						int bidPricing = service.getBidPricing(Integer.parseInt(no));
						int nowPricing = service.getNowPricing(Integer.parseInt(no));
						System.out.println(product.getBrandNo());
						request.setAttribute("productDetail", product);
						request.setAttribute("recentPrice", recentPrice);
						request.setAttribute("bidPricing", bidPricing);
						request.setAttribute("nowPricing", nowPricing);
						
						return new ModelAndView("page/product.jsp");
			
				}catch(Exception e) {
						e.printStackTrace();
				}
				request.setAttribute("errorMsg", "상품보기 실패");
				return new ModelAndView("error/error.jsp",true);
		
		}
	
		public ModelAndView selectAllProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException{
			System.out.println("controller - 상품 전체 검색 메소드");

			
			productList = service.selectAllProduct();
			request.setAttribute("productList", productList);
			
			//System.out.println("controller(상품전체조회결과)= "+ productList);
			return new ModelAndView("page/shop.jsp", false);
		
		}//selectAll 끝
	
		public ModelAndView searchProductByKeyword(HttpServletRequest request, HttpServletResponse response) throws SQLException{
			System.out.println("controller - 상품 키워드 검색 메소드");
			String searchKeyword = request.getParameter("inputKeyword");
			searchKeyword = searchKeyword.toUpperCase();
			//System.out.println(searchKeyword);
			if(searchKeyword=="" || searchKeyword==null) {
				productList = service.selectAllProduct();
			} else if(getType(searchKeyword)==2) { //한글이다
				System.out.println("입력값은 한글");
				productList = service.searchProductKor(searchKeyword);
			
			} else if(getType(searchKeyword)==1) { //영문이다
				System.out.println("입력값은 영문");
				productList = service.searchProductEng(searchKeyword);
			} else 
				System.out.println("무언가 문제가 있다...controller");
			
			System.out.println(productList);
			request.setAttribute("productList", productList);
			return new ModelAndView("page/shop.jsp", false);
		
		}//searchProductByKeyword끝
		
		public ModelAndView searchProductByCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException{
			//System.out.println("controller - 상품 카테고리 검색 메소드");

			String categoryNum = request.getParameter("productCategory");
			//System.out.println("categoryNum: "+categoryNum);
			productList = service.searchProductByCategory(categoryNum);
			request.setAttribute("productList", productList);
			
			//System.out.println("controller(카테고리조회결과)= "+ productList);
			return new ModelAndView("page/shop.jsp", false);
		
		}//searchProductByCategory 끝
	
		public ModelAndView searchProductByBrand(HttpServletRequest request, HttpServletResponse response) throws SQLException{
			//System.out.println("controller - 상품 카테고리 검색 메소드");

			String brandNum = request.getParameter("productBrand");
			System.out.println("productBrand: "+brandNum);
			productList = service.searchProductByBrand(brandNum);
			request.setAttribute("productList", productList);
			
			System.out.println("controller(카테고리조회결과)= "+ productList);
			return new ModelAndView("page/shop.jsp", false);
		
		}//searchProductByCategory 끝
	
	
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
