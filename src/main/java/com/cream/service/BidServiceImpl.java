package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dao.BidDAO;
import com.cream.dao.BidDAOImpl;
import com.cream.dto.BidAccountDTO;
import com.cream.dto.BidDTO;

public class BidServiceImpl implements BidService{
	BidDAO dao =new BidDAOImpl();
	
	public void bidTransaction(BidDTO bid, int productNO) throws SQLException {
		System.out.println(productNO);
		dao.bidTransaction(bid, productNO);
	}
	
	public List<BidAccountDTO> getActiveBids(int salesNo) throws SQLException {
		List<BidAccountDTO> list = dao.selectActiveBids(salesNo);
	     return list;
	}

	@Override
	public List<BidAccountDTO> findBidsByUserNo(int userNo) throws SQLException {
		List<BidAccountDTO> list = dao.findBidsByUserNo(userNo);
		return list;
	}
	

}
