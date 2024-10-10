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
		int user_no = Integer.parseInt(request.getParameter("user_no"));
		int sales_no = Integer.parseInt(request.getParameter("sales_no"));
		int price =  Integer.parseInt(request.getParameter("price"));
		
		//최고가 입찰가보다 낮으면 입찰하지 못함
		BidDTO highestBid = bidService.getHighestBid(sales_no);
		if(price < highestBid.getProductPrice()) {
				request.setAttribute("errorMSG", "입찰금액보다 적습니다");
				return new ModelAndView("error.jsp",true);
		}
		
		//투찰 할 돈 있는지 체크 후 바로 투찰 시키고 
		int userShoeCream = bidService.checkBalance(user_no);
		
		
		
		//기존 입찰자인지 확인	
		
		try {
			BidDTO bid = userService.findBidByUserNo(user_no);
			
			if(bid==null) {//null이면 insert
				bid = new BidDTO(user_no,sales_no,price);
				bidService.insertBidUser(bid);
			}else {//update
				bid.setProductPrice(price);
				service.updateBidUser(bid);
				
			}
			return new ModelAndView("index.jsp",true);
		} catch (NumberFormatException | SQLException | com.cream.exception.AuthenticationException e) {
			request.setAttribute("errorMSG", e.toString());
			return new ModelAndView("error.jsp",true);
		}
		
	
	
	}	
}
