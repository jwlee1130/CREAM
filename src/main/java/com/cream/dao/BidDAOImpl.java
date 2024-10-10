package com.cream.dao;

import java.sql.SQLException;

import com.cream.dto.BidDTO;

public class BidDAOImpl implements BidDAO {

	@Override
	public BidDTO getHighestBid(int sales_no) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTimeBid(int sales_no) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bid(BidDTO bid) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkBalance(int user_no) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int refundBidAmount(int sales_no, int user_no) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
