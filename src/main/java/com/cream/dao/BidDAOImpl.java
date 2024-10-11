package com.cream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cream.dto.BidDTO;
import com.cream.dto.NotifyDTO;
import com.cream.dto.UserDTO;
import com.cream.util.DbUtil;

public class BidDAOImpl implements BidDAO {
	NotifyDAO notifyDAO = new NotifyFDAOImpl();
	//최고가 비교부터 트랜잭션 시작
	@Override
	public BidDTO getHighestBid(BidDTO newBidder) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//현재 입찰중인 유저 정보랑 가격 정보( 환불,알람용,입금액이 더 큰지)
		String sql = "SELECT USER_NO, SALSE_NO, PRICE from BIDACCOUNT WHERE SALES_NO = ? AND FLAG =0";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, newBidder.getSalesNO());
			
			con.setAutoCommit(false);
			
			//최고가 들고 오기
			rs= ps.executeQuery();
			
			
			if(rs.next()) {
					//기존 입찰자
					BidDTO currentBidder = new BidDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3));
					
					setBidFlagOne(con,newBidder.getSalesNO()); //flag =1 설정 다른 유저 접근 못하게
					checkBalance(con ,newBidder.getUserNo(), newBidder.getProductPrice());  //입찰유저가 돈 있는지 체크하면서 돈빼기
					refundBidAmount(con,currentBidder.getUserNo(), currentBidder.getProductPrice());//현재 입찰자한테 돈 돌려주기
					//알람주기
					int checkExistBidUserNo = checkBidExists(con, newBidder.getUserNo(), newBidder.getSalesNO()); // 입찰중인 유저인지
					//입찰정보 넣기
					if(checkExistBidUserNo==0) {//유저 정보 없으면 0리턴 insert
						insertBid(con, newBidder.getUserNo(), newBidder.getSalesNO(), newBidder.getProductPrice());
					}else {//있으면  update
						updateBid(con, newBidder.getUserNo(), newBidder.getSalesNO(), newBidder.getProductPrice());
					}
					//관리계좌 현재입찰자로 교체
					updateAdminAccount(con, newBidder.getUserNo(), newBidder.getSalesNO(), newBidder.getProductPrice());
					notifyDAO.insertNotify(new NotifyDTO(currentBidder.getUserNo(),currentBidder.getSalesNO(),"새로운 상위 입찰자가 등장했습니다. 다시 입찰해보세요!"));
					setBidFlagZero(con,newBidder.getSalesNO()); //flag =0 설정 다른 유저 접근 하게
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
	public int setBidFlagOne(Connection con, int salesNO) throws SQLException {
		PreparedStatement ps = null;
		String sql = "UPDATE  BIDACCOUNT SET  FLAG=1 WEHERE SALES_NO = ? AND FLAG = 0";	
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
	
	/*
	 * Flag ==1인거 0으로 해서 접근 가능하게 하기
	 */
	public int setBidFlagZero(Connection con, int salesNO) throws SQLException {
		PreparedStatement ps = null;
		String sql = "UPDATE  BIDACCOUNT SET  FLAG=0 WEHERE SALES_NO = ? AND FLAG = 1";	
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
	/*
	 * 입찰 정보자 dto 리턴해서 없으면 insert 
	 * 잇으면 update 할꺼
	 */
	public int  checkBidExists(Connection con,int userNO,int salesNo) throws SQLException {
		int userNo = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select user_no from BID WHERE USER_NO = ? AND SALES_NO = ?";	

		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, userNO);
			ps.setInt(2, salesNo);

			rs = ps.executeQuery();
			
			if(rs.next()) {
				 userNo = rs.getInt(1);
			}else {
				 throw new SQLException("입찰자 정보가 없습니다");
			}
		}catch(SQLException e){
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(ps,rs);
		}
		return userNo;
	}
	
	@Override
	public String getTimeBid(int sales_no) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int bid(Connection con, BidDTO bid) throws SQLException {
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
	}

	@Override
	public int refundBidAmount(Connection con, int user_no, int price) throws SQLException {
		PreparedStatement ps = null;
		String sql = "UPDATE USERS SET SHOECREAM=SHOECREAM+? WHRER USER_NO = ?";	
		int result = 0;
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, user_no);
			
			result = ps.executeUpdate();
			
			if(result==0)
				 throw new SQLException("유저에 돈이 없습니다.");
			
		}catch(SQLException e){
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(ps);
		}
		return result;
	}

	public int updateBid(Connection con, int userNo, int salesNo, int price) throws SQLException{ 
		PreparedStatement ps = null;
		String sql = "UPDATE BID SET  PRICE = ? , REGDATE = SYSDATE() WHERE USER_NO=? AND SALES_NO=?";	
		int result = 0;
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, userNo);
			ps.setInt(3, salesNo);
		
			result = ps.executeUpdate();
			
			if(result==0)
				 throw new SQLException("입찰 실패");
			
		}catch(SQLException e){
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(ps);
		}
		return result;
	}
	
	public int insertBid(Connection con, int userNo,int salesNo, int price) throws SQLException{ 
		PreparedStatement ps = null;
		String sql = "INSERT INTO BID(USER_NO,SALES_NO,PRICE,REGDATE) VALUES(?,?,?,SYSDATE())";	
		int result = 0;
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, userNo);
			ps.setInt(2, salesNo);
			ps.setInt(3, price);
		
			
			result = ps.executeUpdate();
			
			if(result==0)
				 throw new SQLException("입찰 실패");
			
		}catch(SQLException e){
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(ps);
		}
		return result;
	}
	
	public int updateAdminAccount(Connection con, int userNo, int salesNo, int price) throws SQLException{
		PreparedStatement ps = null;
		String sql = "UPDATE BIDACCOUNT SET  PRICE = ?, USER_NO = ? WHERE SALES_NO=?";
		int result = 0;
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, userNo);
			ps.setInt(3, salesNo);
		
			
			result = ps.executeUpdate();
			
			if(result==0)
				 throw new SQLException("입찰 실패");
			
		}catch(SQLException e){
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(ps);
		}
		return result;
		
	}
}
