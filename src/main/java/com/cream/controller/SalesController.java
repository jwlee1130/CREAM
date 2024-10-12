package com.cream.controller;

import java.sql.SQLException;

import com.cream.dto.ProductDTO;
import com.cream.dto.SalesDTO;
import com.cream.exception.AuthenticationException;
import com.cream.service.SalesService;
import com.cream.service.SalesServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SalesController implements Controller {
	SalesService service = new SalesServiceImpl();
	
	public ModelAndView salesDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, AuthenticationException {
		
		String no = request.getParameter("salesNo");
		System.out.println(no);
		
		try {
				SalesDTO sale = service.salesDetail(Integer.parseInt(no));
				request.setAttribute("sale", sale);
				return new ModelAndView("page/bid.jsp");
	
		}catch(Exception e) {
				e.printStackTrace();
		}
		request.setAttribute("errorMsg", "상품보기 실패");
		return new ModelAndView("error/error.jsp",true);

}

}
