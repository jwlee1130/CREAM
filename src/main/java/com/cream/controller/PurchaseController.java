package com.cream.controller;

import java.sql.SQLException;

import com.cream.dto.ProductDTO;
import com.cream.dto.PurchaseDTO;
import com.cream.service.PurchaseService;
import com.cream.service.PurchaseServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PurchaseController implements Controller {
	PurchaseService service = new PurchaseServiceImpl();
	
	public ModelAndView nowBuy(HttpServletRequest request, HttpServletResponse response){
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		int salesUserNo = Integer.parseInt(request.getParameter("salesUserNo"));
		int buyUserNo = Integer.parseInt(request.getParameter("buyUserNo"));
		int salesNo = Integer.parseInt(request.getParameter("salesNo"));
		int price = Integer.parseInt(request.getParameter("price"));
		String address = request.getParameter("address");

		try {
			service.nowBuy(new PurchaseDTO(salesNo, salesUserNo,buyUserNo, productNo, price, address));
			System.out.println("Z");
			return new ModelAndView("front?key=purchase&methodName=purchaseDetail&buyUserNo="+buyUserNo+"&salesNo="+salesNo);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ModelAndView("front?key=sales&methodName=salesDetail&salesNo="+salesNo, false);
		
	}
	
	public ModelAndView purchaseDetail(HttpServletRequest request, HttpServletResponse response){
		try {
			int buyUserNo = Integer.parseInt(request.getParameter("buyUserNo"));
			int salesNo = Integer.parseInt(request.getParameter("salesNo"));

			PurchaseDTO purchase =  service.purchaseDetail(buyUserNo, salesNo);
			request.setAttribute("purchase", purchase);
		
			return new ModelAndView("/page/info.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ModelAndView("/page/product.jsp", false);
		
	}
}
