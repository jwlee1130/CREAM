package com.cream.controller;

import java.sql.SQLException;

import com.cream.dto.BidDTO;
import com.cream.service.BidServiceImpl;
import com.cream.service.UserService;
import com.cream.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BidController implements Controller {
	BidServiceImpl  bidService = new BidServiceImpl();
	UserService  userService = new UserServiceImpl();

	public ModelAndView bid(HttpServletRequest request, HttpServletResponse response) {
		int buyUserNo = Integer.parseInt(request.getParameter("buyUserNo"));
		int salesNo = Integer.parseInt(request.getParameter("salesNo"));
		int productNo = Integer.parseInt(request.getParameter("productNo"));

		int price =  Integer.parseInt(request.getParameter("price"));
		
		//최고가 입찰가보다 낮으면 입찰하지 못함
		try {
			bidService.bidTransaction(new BidDTO(buyUserNo,salesNo,price),productNo);
			return new ModelAndView("front?error=bidSuccess&key=sales&methodName=salesDetail&salesNo="+salesNo,true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ModelAndView("front?key=sales&methodName=salesDetail&salesNo="+salesNo+"?error=bidError",true);
		
		
		
		
	
	
	}	
}
