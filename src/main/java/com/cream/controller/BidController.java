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
		try {
			BidDTO highestBid = bidService.getHighestBid(new BidDTO(user_no,sales_no,price));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("error.jsp",true);
		
		
		
		
	
	
	}	
}
