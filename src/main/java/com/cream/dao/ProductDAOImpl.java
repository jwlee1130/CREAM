package com.cream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.cream.dto.ProductDTO;
import com.cream.dto.ProductImgDTO;
import com.cream.util.DbUtil;

public class ProductDAOImpl implements ProductDAO {

	@Override
	public List<ProductDTO> searchProudctEng(String productName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> searchProudctKor(String productName) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> selectAllProduct() throws SQLException {
		// TODO Auto-generated method stub
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
