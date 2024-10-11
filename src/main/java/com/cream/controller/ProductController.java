package com.cream.controller;

import java.sql.SQLException;

import com.cream.dto.ProductDTO;
import com.cream.exception.AuthenticationException;
import com.cream.service.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ProductController implements Controller {
		ProductServiceImpl service = new ProductServiceImpl();
	
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
}
