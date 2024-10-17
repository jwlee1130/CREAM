package com.cream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cream.dto.BidAccountDTO;
import com.cream.dto.ProductDTO;
import com.cream.dto.ProductImgDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.SalesImgDTO;
import com.cream.util.DbUtil;

public class SalesDAOImpl implements SalesDAO {

	@Override
	public List<SalesDTO> selectAll(int shoesNo, int productNo) throws SQLException {
		List<SalesDTO> list = new ArrayList<SalesDTO>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
			String sql = "select NO, USER_NO, PRODUCT_NO, STARTING_PRICE, NOW_PRICE, SALES_STATUS, TIMESTAMPDIFF(SECOND, SYSDATE(), REGDATE), GRADE, SHOES_NO, price from USERS_SALES u join BIDACCOUNT b on u.NO = b.SALES_NO  WHERE SHOES_NO=? AND PRODUCT_NO = ? AND SALES_STATUS=1";
					
		try {
			con=DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, shoesNo);
			ps.setInt(2, productNo);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				SalesDTO sale = new SalesDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),rs.getInt(5), rs.getInt(6), String.valueOf(rs.getInt(7)),rs.getString(8).charAt(0),rs.getInt(9));
				sale.setBidAccount(new BidAccountDTO(rs.getInt(10)));
				list.add(sale);
			}
		}catch(SQLException e) {
				e.printStackTrace();
				throw new SQLException("sql오류");
		}
		finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public SalesDTO salesDetail(int salesNo) throws SQLException {
		SalesDTO sale = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select s.NO, s.USER_NO, s.PRODUCT_NO, s.STARTING_PRICE, s.NOW_PRICE, s.SALES_STATUS, TIMESTAMPDIFF(SECOND, SYSDATE(), s.REGDATE), s.GRADE, s.SHOES_NO , i.FILE_PATH,  p.MODELNUMBER, p.ENG_NAME, p.KOR_NAME, b.PRICE from USERS_SALES s JOIN SALES_IMG i ON s.NO = i.SALES_NO JOIN PRODUCT p ON s.PRODUCT_NO = p.NO JOIN BIDACCOUNT b ON b.SALES_NO = s.NO WHERE s.NO=?";
				
		try {
			con=DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, salesNo);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				 sale = new SalesDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),rs.getInt(5), rs.getInt(6), String.valueOf(rs.getInt(7)),rs.getString(8).charAt(0),rs.getInt(9));
				 sale.setProduct(new ProductDTO(rs.getString(11),rs.getString(12),rs.getString(13)));
				 sale.setBidAccount(new BidAccountDTO(rs.getInt(14)));
				 String imgUrl = rs.getString(10);
				 if(imgUrl==null) {
					 sale.setSalesImg(new SalesImgDTO(salesNo,"https://kosta-286-cream.s3.ap-northeast-2.amazonaws.com/img/no-shoes.webp"));
				 }else {
					 sale.setSalesImg(new SalesImgDTO(salesNo,imgUrl));
				 }
			}
			
		}catch(SQLException e) {
				e.printStackTrace();
				throw new SQLException("saleDeai 오류");
		}
		finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return sale;
	}

	@Override
	public int closeSale(Connection con,int salesNo) throws SQLException {
		PreparedStatement ps = null;
		int result = 0;
		String sql = "UPDATE USERS_SALES SET SALES_STATUS = 2 WHERE NO = ? AND SALES_STATUS = 1";	
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, salesNo);
			
			result = ps.executeUpdate();
			
		}catch(SQLException e) {
				e.printStackTrace();
				throw new SQLException("sql오류");
		}
		finally {
			DbUtil.dbClose(ps);
		}
		return result;
	}

	@Override
	public int insertSalesImg(int salesNo, String fileName, long fileSize) {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql = "INSERT INTO  SALES_IMG(`SALES_NO`, `FILE_PATH`, `FILE_SIZE`) VALUES(?, ?, ?)";	
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, salesNo);
			ps.setString(2, fileName);
			ps.setString(3, String.valueOf(fileSize));

			result = ps.executeUpdate();
			
		}catch(SQLException e) {
				e.printStackTrace();
		}
		finally {
			//DbUtil.dbClose(con,ps);
			DbUtil.dbClose(con,ps);
		}
		return result;
	}
	


}
