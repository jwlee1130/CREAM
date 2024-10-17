package com.cream.controller;

import java.sql.SQLException;

import com.cream.dto.ProductDTO;
import com.cream.dto.SalesDTO;
import com.cream.exception.AuthenticationException;
import com.cream.service.PurchaseService;
import com.cream.service.PurchaseServiceImpl;
import com.cream.service.SalesService;
import com.cream.service.SalesServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SalesController implements Controller {
	SalesService service = new SalesServiceImpl();
	PurchaseService purchaseService = new PurchaseServiceImpl();
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
		return new ModelAndView("page/bid.jsp?error=salesDetailError");

	}
	
	public ModelAndView nowBuyDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, AuthenticationException {
		
		String no = request.getParameter("salesNo");
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int nowPrice = Integer.parseInt(request.getParameter("nowPrice"));
		System.out.println(no);
		
		try {
				SalesDTO sale = service.salesDetail(Integer.parseInt(no));
				int commission = nowPrice-purchaseService.calculateCommission(userNo,nowPrice);
				request.setAttribute("sale", sale);
				request.setAttribute("commission", commission);
				request.setAttribute("sell",  nowPrice+commission+3000);
				return new ModelAndView("page/buy-now.jsp");
	
		}catch(Exception e) {
				e.printStackTrace();
		}
		request.setAttribute("errorMsg", "상품보기 실패");
		return new ModelAndView("page/buy-now.jsp?error=nowBuyError");

	}
	
		public ModelAndView bidDetail(HttpServletRequest request, HttpServletResponse response) throws SQLException, AuthenticationException {
		
			String no = request.getParameter("salesNo");
			int userNo = Integer.parseInt(request.getParameter("userNo"));
			int bidPrice = Integer.parseInt(request.getParameter("bidPrice"));
			System.out.println(no);

			try {
				SalesDTO sale = service.salesDetail(Integer.parseInt(no));
				int commission = bidPrice-purchaseService.calculateCommission(userNo,bidPrice);
				request.setAttribute("sale", sale);
				request.setAttribute("commission", commission);
				request.setAttribute("bidPrice", bidPrice);
				request.setAttribute("sell",  bidPrice+commission+3000);
				
				return new ModelAndView("page/bid-parchase.jsp");

			}catch(Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("errorMsg", "상품보기 실패");
			return new ModelAndView("page/bid-parchase.jsp?error=bidDtailError");

	}


}
