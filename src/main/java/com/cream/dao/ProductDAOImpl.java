package com.cream.dao;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cream.dto.BrandDTO;
import com.cream.dto.ProductDTO;
import com.cream.dto.ProductImgDTO;
import com.cream.util.DbUtil;

public class ProductDAOImpl implements ProductDAO {

	private Properties proFile = new Properties();
	
	public ProductDAOImpl() {
		System.out.println("DAOImpl 생성자 호출...");	
		try {
			InputStream is = getClass().getClassLoader().getResourceAsStream("dbQuery.properties");
			proFile.load(is);
			System.out.println("query.userlogin = " +proFile.getProperty("query.userlogin"));	
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public List<ProductDTO> selectAllProduct() throws SQLException {
		// 상품 전체 출력
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		String sql = proFile.getProperty("query.selectAllProduct"); 
		//sql = SELECT P.*, FILE_PATH, FILE_SIZE, B.BRAND FROM PRODUCT P JOIN (SELECT * FROM PRODUCT_IMG GROUP BY PRODUCT_NO) PI ON P.NO=PI.PRODUCT_NO JOIN BRAND B ON P.BRAND_NO=B.NO;
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				List<ProductImgDTO> list = new ArrayList<ProductImgDTO>();
				list.add(new ProductImgDTO(rs.getString(12), rs.getString(13)));
				ProductDTO product = new ProductDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
													rs.getString(5), rs.getString(6), rs.getString(7), 
													rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), 
													list, new BrandDTO(rs.getString(14)));
			
				productList.add(product);
			}
	
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		
		return productList;
	}

	
	
	@Override
	public ProductDTO searchByProductId(String productId) throws SQLException {
		// 상품 ID로 검색
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductDTO product = null;
		String sql = proFile.getProperty("query.searchByProductId"); //SELECT * FROM PRODUCT WHERE NO=?
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, productId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				product = new ProductDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
													rs.getString(5), rs.getString(6), rs.getString(7), 
													rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11));
			}
	
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return product;
	}
	
	
	
	@Override
	public List<ProductDTO> searchProductKor(String productName) throws SQLException {
		// 상품 한글 검색
		return null;
	}

	@Override
	public List<ProductDTO> searchProductEng(String productName) throws SQLException {
		// 상품 영어 검색
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = proFile.getProperty("query.searchProductByEng"); 
			List<ProductDTO> productList = new ArrayList<ProductDTO>();
			//sql = SELECT P.*, FILE_PATH, FILE_SIZE, B.BRAND FROM PRODUCT P 
			         //JOIN (SELECT * FROM PRODUCT_IMG GROUP BY PRODUCT_NO) PI ON P.NO=PI.PRODUCT_NO 
			         //JOIN BRAND B ON P.BRAND_NO=B.NO WHERE ENG_NAME LIKE ?
			System.out.println("여기는 DAO = "+ productName);	
			try {
				con = DbUtil.getConnection();
				ps = con.prepareStatement(sql);
				ps.setString(1, "%"+productName+"%");
				rs = ps.executeQuery();		
				
				while(rs.next()) {
					List<ProductImgDTO> list = new ArrayList<ProductImgDTO>();
					list.add(new ProductImgDTO(rs.getString(12), rs.getString(13)));
					ProductDTO product = new ProductDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
														rs.getString(5), rs.getString(6), rs.getString(7), 
														rs.getInt(8), rs.getString(9), rs.getString(10), rs.getInt(11), 
														list, new BrandDTO(rs.getString(14)));
					
					productList.add(product);
				}
			
			} finally {
				DbUtil.dbClose(con, ps, rs);
			}
				
			return productList;
	}

	public ProductDTO detail(int productNo) throws SQLException{
		ProductDTO product =null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select NO, BRAND_NO, CATEGORY_NO, COLOR_NO, ENG_NAME, KOR_NAME, `RELEASE`, RELEASE_PRICE, MODELNUMBER,  REGDATE, SALES_QUANTITY from PRODUCT WHERE NO =?";

		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, productNo);
			rs = ps.executeQuery();
			List<ProductImgDTO> list = getFilePath(con,productNo);
			if(rs.next()) {
				 product = new ProductDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
						 rs.getString(5),rs.getString(6),rs.getString(7), rs.getInt(8), 
						 rs.getString(9),rs.getString(10), rs.getInt(11),list);
				 		
			}
		}catch(SQLException e){
			e.printStackTrace();
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(con, ps,rs);
		}

		return product;
	}
	
	public List<ProductImgDTO> getFilePath(Connection con, int productNo) throws SQLException{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductImgDTO> list= new ArrayList<ProductImgDTO>();
		
		String sql = "select FILE_PATH, FILE_SIZE FROM PRODUCT_IMG WHERE PRODUCT_NO = ? ";
			

		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, productNo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new ProductImgDTO(rs.getString(1), rs.getString(2)));
				 		
			}
		}catch(SQLException e){
			e.printStackTrace();
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(ps,rs);
		}

		return list;
	}

	public int getRecentPrice(int productNo) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int price=0;
		String sql = "SELECT PRICE FROM PURCHASE WHERE PRODUCT_NO = ? order by REGDATE DESC LIMIT 1";

		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, productNo);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				 price = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(con, ps,rs);
		}

		return price;
	}


	public int getBidPricing(int productNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int price=0;
		String sql = "SELECT b.PRICE FROM BIDACCOUNT b  JOIN USERS_SALES u ON b.SALES_NO = u.NO AND u.SALES_STATUS=1 and u.PRODUCT_NO = ? order by PRICE ASC LIMIT 1";

		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, productNo);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				 price = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(con, ps,rs);
		}

		return price;
	}


	public int getNowPricing(int productNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int price=0;
		String sql = "SELECT NOW_PRICE FROM USERS_SALES u JOIN BIDACCOUNT b ON b.SALES_NO = u.NO WHERE PRODUCT_NO = ? AND SALES_STATUS = 1 order by NOW_PRICE ASC LIMIT 1";

		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, productNo);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				 price = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(con, ps,rs);
		}

		return price;
	}

}
