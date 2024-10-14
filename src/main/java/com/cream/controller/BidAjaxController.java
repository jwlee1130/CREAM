package com.cream.controller;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.BidAccountDTO;
import com.cream.service.BidService;
import com.cream.service.BidServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BidAjaxController implements RestController {
	BidService service = new BidServiceImpl();
	public Object selectActiveBids(HttpServletRequest request, HttpServletResponse response) {
	    int salesNo = Integer.parseInt(request.getParameter("salesNo"));
	    List<BidAccountDTO> list = null;
	    
	    try {
	    	list = service.getActiveBids(salesNo);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
}