package com.cream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cream.dto.BidAccountDTO;
import com.cream.dto.BidDTO;
import com.cream.dto.NotifyDTO;
import com.cream.util.DbUtil;

public class BidDAOImpl implements BidDAO {
	NotifyDAO notifyDAO = new NotifyFDAOImpl();
	
	/*	//최고가 비교부터 트랜잭션 시작
	 * FLAG=1 설정으로 입찰 겹치는거 방지
	 * 유저한테 돈 있는지 확인후 빼기
	 * 기존입찰자한테 돈 돌려주기(존재하면)
	 * 기존입찰자한테 알림(상위 입찰자 등장)
	 * 입찰관리계좌에 입잘차번호랑 가격 갱신
	 * FLAG=0설정하기
	 */
	@Override
	public void bidTransaction(BidDTO newBidder,int productNO) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		//현재 입찰중인 유저 정보랑 가격 정보( 환불,알람용,입금액이 더 큰지)
		String sql = "SELECT  SALES_NO, COALESCE(BUY_USER_NO,0), PRICE from BIDACCOUNT WHERE SALES_NO = ? AND FLAG =0 AND PRICE < ?";
		System.out.println(productNO+"제품번호");
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, newBidder.getSalesNo());
			ps.setInt(2, newBidder.getPrice());
			//최고가 들고 오기
			rs= ps.executeQuery();			

			setBidFlagOne(con,newBidder.getSalesNo()); //flag =1 설정 다른 유저 접근 못하게

			con.setAutoCommit(false);

			if(rs.next()) {
					//기존 입찰자
					BidAccountDTO currentBidder = new BidAccountDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3));					
					checkBalance(con ,newBidder.getBuyUserNo(), newBidder.getPrice());  //입찰유저가 돈 있는지 체크하면서 돈빼기
					if(currentBidder.getBuyUserNo()!=0) {
						refundBidAmount(con,currentBidder.getBuyUserNo(), currentBidder.getPrice());//현재 입찰자한테 돈 돌려주기
						notifyDAO.insertNotify(con, new NotifyDTO(currentBidder.getBuyUserNo(),currentBidder.getSalesNo(),productNO,"새로운 상위 입찰자가 등장했습니다. 다시 입찰해보세요!"));
					}
					//입찰 테이블에 입찰한 유저인지 신규 입찰자인지 판단
					int checkExistBidUserNo = checkBidExists(con, newBidder.getBuyUserNo(), newBidder.getSalesNo()); // 입찰중인 유저인지
					
					if(checkExistBidUserNo==0) {//유저 정보 없으면 0리턴 insert
						insertBid(con, newBidder.getBuyUserNo(), newBidder.getSalesNo(), newBidder.getPrice());
					}else {//있으면  update
						updateBid(con, newBidder.getBuyUserNo(), newBidder.getSalesNo(), newBidder.getPrice());
					}
					//관리계좌 현재입찰자로 교체
					updateAdminAccount(con, newBidder.getBuyUserNo(), newBidder.getSalesNo(), newBidder.getPrice());
					setBidFlagZero(con,newBidder.getSalesNo()); //flag =0 설정 다른 유저 접근 하게
					con.commit();

			}else {
				  throw new SQLException("입찰가 보다 높게 입력하세요.");
			}
			
		}catch(SQLException e){
			  con.rollback();
			  setBidFlagZero(con,newBidder.getSalesNo()); //flag =0 설정 다른 유저 접근 하게

			  e.printStackTrace();
			  throw new SQLException("sql 오류");
		}finally {
			
			con.setAutoCommit(true);
			DbUtil.dbClose(con, ps, rs);
		}
		
		
	}
	
	@Override
	public BidAccountDTO getHighestBid(Connection con, int salesNo) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		BidAccountDTO	bidAccount = null;	
		//현재 입찰중인 유저 정보랑 가격 정보( 환불,알람용,입금액이 더 큰지)
			String sql = "SELECT COALESCE(BUY_USER_NO,0),PRICE from BIDACCOUNT WHERE SALES_NO = ?";
		
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, salesNo);
			
			
			//입찰자 있으면 가격 갖고오기 
			rs= ps.executeQuery();
			
			if(rs.next()) {
				bidAccount = new BidAccountDTO(rs.getInt(1),rs.getInt(2));
			}
			
		}catch(SQLException e){
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(ps, rs);
		}
		
		
		return bidAccount;
	}
	/*
	 * Flag ==1로 해서 1이면 접근 못하게
	 */
	public int setBidFlagOne(Connection con, int salesNO) throws SQLException {
		PreparedStatement ps = null;
		String sql = "UPDATE  BIDACCOUNT SET  FLAG=1 WHERE SALES_NO = ? AND FLAG = 0";	
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
		String sql = "UPDATE  BIDACCOUNT SET  FLAG=0 WHERE SALES_NO = ? AND FLAG = 1";	
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
	public int  checkBidExists(Connection con,int buyUserNo,int salesNo) throws SQLException {
		int userNo = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select BUY_USER_NO from BID WHERE BUY_USER_NO = ? AND SALES_NO = ?";	

		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, buyUserNo);
			ps.setInt(2, salesNo);

			rs = ps.executeQuery();
			
			if(rs.next()) {
				 userNo = rs.getInt(1);
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
	public int checkBalance(Connection con, int userNo, int price) throws SQLException {
		PreparedStatement ps = null;
		String sql = "UPDATE  USERS  SET SHOECREAM=SHOECREAM - ? WHERE  NO = ? AND SHOECREAM >= ?";	
		int result = 0;
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, userNo);
			ps.setInt(3, price);

			result = ps.executeUpdate();
			
			if(result==0)
				 throw new SQLException("포인트가 부족합니다.");
			
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
		String sql = "UPDATE USERS SET SHOECREAM=SHOECREAM+? WHERE NO = ?";	
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
		String sql = "UPDATE BID SET  PRICE = ? , REGDATE = SYSDATE() WHERE BUY_USER_NO=? AND SALES_NO=?";	
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
		String sql = "INSERT INTO BID(BUY_USER_NO,SALES_NO,PRICE) VALUES(?,?,?)";	
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
	
	public int updateAdminAccount(Connection con, int buyUserNo, int salesNo, int price) throws SQLException{
		PreparedStatement ps = null;
		String sql = "UPDATE BIDACCOUNT SET  PRICE = ?, BUY_USER_NO = ? WHERE SALES_NO=?";
		int result = 0;
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, buyUserNo);
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
	
	public int insertAdminAccount(Connection con, int salesNo, int price) throws SQLException{
		PreparedStatement ps = null;
		String sql = "INSERT INTO BIDACCOUNT(`SALES_NO`,`PRICE`) VALUES(?,?)";
		int result = 0;
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, salesNo);
			ps.setInt(2, price);
		
			
			result = ps.executeUpdate();
			
			if(result==0)
				 throw new SQLException("입찰 실패");
			
		}catch(SQLException e){
			  e.printStackTrace();
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(ps);
		}
		return result;
		
	}

	@Override
	public List<BidAccountDTO> selectActiveBids(int salesNo) throws SQLException {
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<BidAccountDTO> activeBids = new ArrayList<>();

	    String sql = "SELECT * FROM BID_ACCOUNT WHERE SALES_NO = ? AND BUY_USER_NO IS NOT NULL";

	    try {
	        con = DbUtil.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, salesNo);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            BidAccountDTO bid = new BidAccountDTO();
	            bid.setSalesNo(rs.getInt("SALES_NO"));
	            bid.setBuyUserNo(rs.getInt("USER_NO"));
	            bid.setPrice(rs.getInt("PRICE"));
	            activeBids.add(bid);
	        }
	    } finally {
	        DbUtil.dbClose(con, ps, rs);
	    }

	    return activeBids;
	}

		public List<BidAccountDTO> findBidsByUserNo(int userNo) throws SQLException {
		    Connection con = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    List<BidAccountDTO> bids = new ArrayList<>();
	
		    String sql = "SELECT BA.SALES_NO,BA.BUY_USER_NO,BA.PRICE,PR.ENG_NAME,PR.REGDATE,SS.SHOES_SIZE,PI.FILE_PATH FROM BIDACCOUNT BA JOIN USERS_SALES US ON BA.SALES_NO = US.NO JOIN PRODUCT PR ON US.PRODUCT_NO = PR.NO JOIN SHOES_SIZE SS ON US.SHOES_NO = SS.NO JOIN PRODUCT_IMG PI ON PR.NO = PI.PRODUCT_NO WHERE BA.BUY_USER_NO = ? ORDER BY BA.SALES_NO DESC";
	
		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, userNo);
		        rs = ps.executeQuery();
	
		        while (rs.next()) {
		            BidAccountDTO bid = new BidAccountDTO(
		                rs.getInt("SALES_NO"),
		                rs.getInt("BUY_USER_NO"),
		                rs.getInt("PRICE"),
		                rs.getString("ENG_NAME"),
		                rs.getString("REGDATE"),
		                rs.getInt("SHOES_SIZE"),
		                rs.getString("FILE_PATH")
		            );
		            bids.add(bid);
		        }
		    } finally {
		        DbUtil.dbClose(con, ps, rs);
		    }
	
		    return bids;
		}


	


}
