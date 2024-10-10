package com.cream.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import com.cream.dto.ProductDTO;
import com.cream.dto.ProductImgDTO;
import com.cream.util.DbUtil;

public class ProductDAOImpl implements ProductDAO {
	private Properties proFile = new Properties();
	
	public ProductDAOImpl() {
		
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
		List<ProductDTO> productList = null;
		String sql = proFile.getProperty("query.selectAllProduct"); //SELECT * FROM PRODUCT
		
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				ProductDTO product = new ProductDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), 
													rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), 
													rs.getInt(10), rs.getString(11), rs.getString(12), rs.getInt(13));
				productList.add(product);
			}
	
		} finally {
			DbUtil.dbClose(con, ps, rs);
		}
		System.out.println(productList);
		return productList;
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

}
