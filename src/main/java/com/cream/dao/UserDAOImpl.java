package com.cream.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cream.dto.BidDTO;
import com.cream.dto.ProductDTO;
import com.cream.dto.ProductImgDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.UserDTO;
import com.cream.util.DbUtil;

public class UserDAOImpl implements UserDAO {
	private Properties proFile = new Properties();
	
	public UserDAOImpl() {
		try {
			//dbQuery를 준비한 ~.properties파일을 로딩해서 Properties 자료구조에 저장한다.
			
			//현재 프로젝트가 런타임(실행)될때, 즉 서버가 실행될때 classes폴더의 위치를
			//동적으로 가져와서 경로를 설정해야한다.
			InputStream is = getClass().getClassLoader().getResourceAsStream("dbQuery.properties");
			proFile.load(is);
			
			System.out.println("query.userlogin = " +proFile.getProperty("query.userlogin"));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public int register(UserDTO user) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDTO loginCheck(UserDTO userDTO) throws SQLException {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserDTO dbDTO =null;
		
		String sql= proFile.getProperty("query.userlogin");//select * from users where user_id=? and pwd=?
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userDTO.getUserId());
			ps.setString(2, userDTO.getUserPw());
			
			rs = ps.executeQuery();
			if (rs.next()) {
	            dbDTO = new UserDTO(
	                rs.getInt("NO"),
	                rs.getInt("RANK_NO"),
	                rs.getString("USER_ID"),
	                rs.getString("NAME"),
	                rs.getString("USER_EMAIL"),
	                rs.getString("USER_PW"),
	                rs.getString("HP"),
	                rs.getString("NICKNAME"),
	                rs.getInt("SHOES_SIZE"),
	                rs.getDate("REGDATE"),
	                rs.getInt("SHOECREAM"),
	                rs.getString("GENDER"),
	                rs.getInt("AGE"),
	                rs.getString("ADDRESS")
	            );
	        }
			
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return dbDTO;
	}

	public BidDTO findBidByUserNo(int no) throws SQLException{
		Connection con =null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BidDTO bid = null;
		
		String sql= proFile.getProperty("query.findBidByUserNo");//select * from users where user_id=? and pwd=?
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, no);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
			}
			
		}catch(Exception e) {
			
		}finally {
			DbUtil.dbClose(con, ps);
		}
		
		return bid;
	}

	@Override
	public int updateCash(String user_Id, int cash) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getUserRank(String user_Id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleUser(String user_Id, String pwd) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateUser(UserDTO user) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDTO selectUserById(int user_no) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addToWishlist(int user_no, int product_no) throws SQLException {
		Connection con = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
        	con = DbUtil.getConnection();
            
            String sql =proFile.getProperty("query.addToWishlist");
            
            ps = con.prepareStatement(sql);

            ps.setInt(1, user_no);
            ps.setInt(2, product_no);

            result = ps.executeUpdate();
        } finally {
        	DbUtil.dbClose(con, ps);
        }

        return result;
    }


	@Override
	public List<ProductDTO> selectWishlist(int user_no) throws SQLException {
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<ProductDTO> list = new ArrayList<>();

	    String sql = proFile.getProperty("query.selectWishlist");
	    
	    try {
	        con = DbUtil.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, user_no);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            ProductDTO product = new ProductDTO();
	            product.setNo(rs.getInt("NO"));
	            product.setEngName(rs.getString("ENG_NAME"));
	            product.setReleasePrice(rs.getInt("RELEASE_PRICE"));

	            list.add(product);
	        }
	    } finally {
	        DbUtil.dbClose(con, ps, rs);
	    }
	    return list;
	}

	@Override
	public int deleteWishlist(int user_no, int product_no) throws SQLException {
	    Connection con = null;
	    PreparedStatement ps = null;
	    int result = 0;

	    String sql = proFile.getProperty("query.deleteWishlist");
	    try {
	        con = DbUtil.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, user_no);
	        ps.setInt(2, product_no);
	        
	        System.out.println("Executing query: " + sql);
	        System.out.println("User no: " + user_no + ", Product no: " + product_no);

	        result = ps.executeUpdate();
	        System.out.println("Rows affected: " + result);
	    }finally {
	        DbUtil.dbClose(con, ps);
	    }
	    return result;
	}
	
	
	

	@Override
	public List<SalesDTO> salesByUserNo(int user_no) throws SQLException {
		Connection con = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<SalesDTO> list = new ArrayList<>();

	    String sql = proFile.getProperty("query.salesByUserNo");

	    try {
	        con = DbUtil.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, user_no);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            SalesDTO sales = new SalesDTO();
	            sales.setNo(rs.getInt("NO"));
	            sales.setUserNo(rs.getInt("USER_NO"));
	            sales.setProductNo(rs.getInt("PRODUCT_NO"));
	            sales.setStartingPrice(rs.getInt("STARTING_PRICE"));
	            sales.setNowPrice(rs.getInt("NOW_PRICE"));
	            sales.setSalesStatus(rs.getInt("SALES_STATUS"));
	            sales.setRegdate(rs.getString("REGDATE"));
	            sales.setGrade(rs.getString("GRADE").charAt(0));
	            
	            ProductDTO product = new ProductDTO();
	            product.setEngName(rs.getString("ENG_NAME"));
	            sales.setProduct(product);

	            list.add(sales);
	        }
	    } finally {
	        DbUtil.dbClose(con, ps, rs);
	    }

	    return list;
	}


	@Override
	public int insertSales(SalesDTO sales) throws SQLException {
		Connection con = null;
	    PreparedStatement ps = null;
	    int result = 0;

	    String sql = proFile.getProperty("query.insertSales");
	    
	    try {
	        con = DbUtil.getConnection();
	        ps = con.prepareStatement(sql);

	        ps.setInt(1, sales.getUserNo());
	        ps.setInt(2, sales.getProductNo());
	        ps.setInt(3, sales.getStartingPrice());
	        ps.setInt(4, sales.getNowPrice());
	        ps.setInt(5, sales.getSalesStatus());
	        ps.setString(6, String.valueOf(sales.getGrade()));

	        result = ps.executeUpdate();
	        System.out.println("Sales record inserted successfully with result: " + result);

	    } finally {
	        DbUtil.dbClose(con, ps);
	    }

	    return result;
	}
	
}












