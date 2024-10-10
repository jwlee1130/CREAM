package com.cream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cream.dto.BidDTO;
import com.cream.util.DbUtil;

public class BidDAOImpl implements BidDAO {
	UserDAOImpl userDAO = new UserDAOImpl();
	
	//최고가 비교부터 트랜잭션 시작
	@Override
	public BidDTO getHighestBid(BidDTO bid) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT  PRODUCT_PRICE from BID WHERE SALES_NO = ?";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, bid.getSalesNO());
			
			con.setAutoCommit(false);
			
			int result = ps.executeUpdate();
			
			if(result==1) {
					setBidFlag(con,bid.getSalesNO()); //flag =1 설정
					checkBalance(con ,bid.getUserNo(), bid.getProductPrice());  //유저가 돈 있는지 체크
					
					
			}else {
				throw new SQLException("입찰 할 수 없습니다.");
			}
			
		}catch(SQLException e){
			  throw new SQLException("sql 오류");
		}finally {
			con.setAutoCommit(true);
			DbUtil.dbClose(con, ps, rs);
		}
		
		
		return null;
	}
	/*
	 * Flag ==1로 해서 1이면 접근 못하게
	 */
	public int setBidFlag(Connection con, int salesNO) throws SQLException {
		PreparedStatement ps = null;
		String sql = "UPDATE  BID SET  FLAG=1 WEHERE SALES_NO = ? AND FLAG = 0";	
		int result = 0;
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, salesNO);
			
			result = ps.executeUpdate();
			
			if(result==0)
				 throw new SQLException("입찰중인 유저가 있습니다");
			
		}catch(SQLException e){
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(ps);
		}
		return result;
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
	/*
	 * 체크하는 동시에 되면 금액차감
	 */
	@Override
	public int checkBalance(Connection con, int user_no, int price) throws SQLException {
		PreparedStatement ps = null;
			String sql = "UPDATE  USERS  SET SHOECREAM=SHOECREAM - PRICE WHERE USER_NO = ? AND SHOECREAM >= ?";	
		int result = 0;
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_no);
			
			result = ps.executeUpdate();
			
			if(result==0)
				 throw new SQLException("유저에 돈이 없습니다.");
			
		}catch(SQLException e){
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(ps);
		}
		return result;
		return 0;
	}

	@Override
	public int refundBidAmount(int sales_no, int user_no) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
