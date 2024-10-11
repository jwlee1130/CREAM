package com.cream.dao;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;

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
		String sql = proFile.getProperty("query.selectAllProduct"); //SELECT * FROM PRODUCT
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				ProductDTO product = new ProductDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), 
													rs.getString(6), rs.getString(7), rs.getString(8), 
													rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12));
				productList.add(product);
			}
	
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return productList;
	}

	
	@Override
	public ProductDTO searchByProductId(int productId) throws SQLException {
		// 상품 ID로 검색
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ProductDTO product = null;
		String sql = proFile.getProperty("query.searchByProductId"); //SELECT * FROM PRODUCT WHERE NO=?
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				product = new ProductDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), 
													rs.getString(6), rs.getString(7), rs.getString(8), 
													rs.getInt(9), rs.getString(10), rs.getString(11), rs.getInt(12));
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
		return null;
	}

	public ProductDTO detail(int productNo) throws SQLException{
		ProductDTO product =null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select p.NO, p.BRAND_NO, p.CATEGORY_NO, p.SHOES_NO, p.COLOR_NO, p.ENG_NAME, p.KOR_NAME, p.RELEASE, p.RELEASE_PRICE, p.MODELNUMBER, p. REGDATE, p.SALES_QUANTITY, i.FILE_PATH, i.FILE_SIZE from PRODUCT p join PRODUCT_IMG i on p.NO = i.PRODUCT_NO WHERE p.NO =?";

		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, productNo);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				 product = new ProductDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
						 rs.getInt(5),rs.getString(6),rs.getString(7),rs.getString(8), rs.getInt(9), 
						 rs.getString(10),rs.getString(11), rs.getInt(12),new ProductImgDTO(rs.getString(13),rs.getString(14)));
			}
		}catch(SQLException e){
			  throw new SQLException("sql 오류");
		}finally {
			DbUtil.dbClose(con, ps,rs);
		}

		return product;
	}
}
