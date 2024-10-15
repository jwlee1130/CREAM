package com.cream.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cream.dto.BidAccountDTO;
import com.cream.dto.NotifyDTO;
import com.cream.dto.PurchaseDTO;
import com.cream.util.DbUtil;

public class PurchaseDAOImpl implements PurchaseDAO {
	SalesDAO salesDAO = new SalesDAOImpl();
	BidDAO bidDAO = new BidDAOImpl();
	NotifyDAO notifyDAO = new NotifyFDAOImpl();	
	
	private Properties proFile = new Properties();
	
	public PurchaseDAOImpl() {
		try {
			//dbQuery를 준비한 ~.properties파일을 로딩해서 Properties 자료구조에 저장한다.
			
			//현재 프로젝트가 런타임(실행)될때, 즉 서버가 실행될때 classes폴더의 위치를
			//동적으로 가져와서 경로를 설정해야한다.
			InputStream is = getClass().getClassLoader().getResourceAsStream("dbQuery.properties");
			proFile.load(is);
			
			System.out.println("query.selectAllProduct = " +proFile.getProperty("query.selectAllProduct"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 * 구매자 잔고 확인후 잔액 빼기
	 * 구매테이블에 정보 넣기
	 * 판매 상태2로 바꿔서 노출 안되게 바꾸기
	 * ?할지말지 관리자돈 채우기
	 * 입찰관리계좌에서 구매자번호랑 가격 들고오기 (없으면 0반환)
	 * 입찰자 있으면 돈 보내주기
	 * 상위 입찰자가 있으면 알림 보내기
	 * 수수료 계산
	 * 판매자한테 수수료 지급
	 * 관리자한테 판매가격 - 수수료 입금
	 * 입찰 못하게 flag = 1로 설정
	 * 판매자한테 판매 알림보내기
 	 *
	 */
	@Override
	public int nowBuy(PurchaseDTO purchase) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result =0;
		String sql = proFile.getProperty("query.nowBuy");
		try {
			con=DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, purchase.getSalesNo());
			ps.setInt(2, purchase.getSalesUserNo());
			ps.setInt(3, purchase.getBuyUserNo());
			ps.setInt(4, purchase.getProductNo());
			ps.setInt(5, purchase.getPrice());
			ps.setString(6,purchase.getAddress());
			con.setAutoCommit(false);
			result = bidDAO.checkBalance(con, purchase.getBuyUserNo(), purchase.getPrice());
			if(result==0) {
	            throw new SQLException("잔액 부족");
			}
			result = ps.executeUpdate();
	        if (result == 0) {
	            throw new SQLException("구매 처리 실패");
	        }
			//입찰자 있으면 돈 돌려주기..
			BidAccountDTO bidAccount = bidDAO.getHighestBid(con, purchase.getSalesNo());
			if(bidAccount==null)
				throw new SQLException("오류");
			
			if(bidAccount.getBuyUserNo()!=0) {
				bidDAO.refundBidAmount(con, bidAccount.getBuyUserNo(),bidAccount.getPrice());
				if(notifyDAO.insertNotify(con, new NotifyDTO(bidAccount.getBuyUserNo(),purchase.getSalesNo(),purchase.getProductNo(),"입찰하신 상품이 즉시구매 되어 입찰이 취소 되었습니다"))==0)
					throw new SQLException("알림 전송 실패");
				
			}
			int commision = calculateCommission(con,purchase.getSalesUserNo(), purchase.getPrice());
			if(commision ==0) throw new SQLException("계산실패");
			depositCreamToSellerAccount(con,purchase.getSalesUserNo(),commision+3000);
			depositCreamToAdminAccount(con, purchase.getPrice()-commision);
			
			if(notifyDAO.insertNotify(con,new NotifyDTO(purchase.getSalesUserNo(),purchase.getSalesNo(),purchase.getProductNo(),"등록하신 상품이 판매되었습니다"))==0)
				throw new SQLException("알림 전송 실패");
			
			
			bidDAO.setBidFlagOne(con, purchase.getSalesNo()); //판매됐으니 플래그1 입찰 시도 방지
			int close = salesDAO.closeSale(con,purchase.getSalesNo()); //판매 상태 끝났으므로 판매종료
			if(close ==0) {	
	            throw new SQLException("판매 상태 변경 실패");
			}
			
			
			con.commit();
			
		}catch(SQLException e) {
			e.printStackTrace();
				con.rollback();
			throw new SQLException("구매 실패");
		}
			con.setAutoCommit(true);
			DbUtil.dbClose(con, ps);
		return result;
	}

	@Override
	public PurchaseDTO purchaseDetail(int buyUserNo, int salesNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		PurchaseDTO purchase = null;
		String sql = proFile.getProperty("query.purchaseDetail");
		try {
			con=DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, buyUserNo);
			ps.setInt(2, salesNo);
			
			rs = ps.executeQuery();
			
	        if (rs.next()) {
	        	purchase = new PurchaseDTO(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getString(8));
	        }
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("구매 실패");
		}
			DbUtil.dbClose(con, ps,rs);
		return purchase;
	}
	public int calculateCommission(Connection con,int userNo, int price) throws SQLException {
		int commission =0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = proFile.getProperty("query.calculateCommission");
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, userNo);
			
			rs = ps.executeQuery();
			
	        if (rs.next()) {
	        	commission = rs.getInt(1);
	        }
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("구매 실패");
		}finally {
			DbUtil.dbClose(ps,rs);
		}
		return commission;
	}
	
	public int calculateCommission(int userNo, int price) throws SQLException {
		int commission =0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = proFile.getProperty("query.calculateCommission");
		try {
			con=DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, userNo);
			
			rs = ps.executeQuery();
			
	        if (rs.next()) {
	        	commission = rs.getInt(1);
	        }
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("구매 실패");
		}finally {
			DbUtil.dbClose(ps,rs);
		}
		return commission;
	}
	
	public int depositCreamToSellerAccount(Connection con,int userNo,int price) throws SQLException {
		int result =0;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("query.depositCreamToSellerAccount");
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, price);
			ps.setInt(2, userNo);
			
			result = ps.executeUpdate();
			if(result==0) throw new SQLException("판매자한테 수수료 넣기 실패");
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("판매자한테 수수료 넣기 실패");
		}finally {
			DbUtil.dbClose(ps);
		}
		return result;
	}
	
	public int depositCreamToAdminAccount(Connection con,int price) throws SQLException {
		int result =0;
		PreparedStatement ps = null;
		String sql = proFile.getProperty("query.depositCreamToAdminAccount");
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, price);			
			result = ps.executeUpdate();
			if(result==0) throw new SQLException("관리자 수수료 넣기 실패");
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("관리자 수수료 넣기 실패");
		}finally {
			DbUtil.dbClose(ps);
		}
		return result;
	}
	
	@Override
	public List<PurchaseDTO> selectPurchase(int buyUserNo) throws SQLException {
	    List<PurchaseDTO> purchases = new ArrayList<>();
	    Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    String sql = proFile.getProperty("query.selectPurchase");

	    try {
	        con = DbUtil.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, buyUserNo);

	        rs = ps.executeQuery();

	        while (rs.next()) {
	            PurchaseDTO purchase = new PurchaseDTO(
	                rs.getInt("NO"),
	                rs.getInt("SALES_NO"),
	                rs.getInt("SALES_USER_NO"),
	                rs.getInt("BUY_USER_NO"),
	                rs.getInt("PRODUCT_NO"),
	                rs.getInt("PRICE"),
	                rs.getString("REGDATE"),
	                rs.getString("ADDRESS")
	            );
	            purchase.setEngName(rs.getString("ENG_NAME"));
	            purchase.setShoeSize(rs.getInt("SHOES_SIZE"));
	            purchase.setFilePath(rs.getString("FILE_PATH"));

	            purchases.add(purchase);
	        }
	    }finally {
	        DbUtil.dbClose(con, ps, rs);
	    }
	    return purchases;
	}
	
	
}
