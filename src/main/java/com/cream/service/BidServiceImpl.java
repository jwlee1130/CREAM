package com.cream.service;

import java.sql.SQLException;

import com.cream.dao.BidDAO;
import com.cream.dao.BidDAOImpl;
import com.cream.dto.BidDTO;

public class BidServiceImpl {
	BidDAO dao =new BidDAOImpl();
	public void bidTransaction(BidDTO bid, int productNO) throws SQLException {
		System.out.println(productNO);
		dao.bidTransaction(bid, productNO);
	}



}
