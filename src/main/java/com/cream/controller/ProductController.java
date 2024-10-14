package com.cream.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
						System.out.println(product.getBrandNo());
						request.setAttribute("productDetail", product);
						return new ModelAndView("page/product.jsp");
			
				}catch(Exception e) {
						e.printStackTrace();
				}
				request.setAttribute("errorMsg", "상품보기 실패");
				return new ModelAndView("error/error.jsp",true);
		
		}
	
	public ModelAndView selectAllProduct(HttpServletRequest request, HttpServletResponse response){
		System.out.println("상품 전체 검색 메소드...여기로 와야해!!!");
		try {
			
			productList = service.selectAllProduct();
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
			ProductDTO product = service.searchByProductId(productId);
			request.setAttribute("product", product);
			System.out.println(product);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ModelAndView("/page/product.jsp", false);
		
	}
}
