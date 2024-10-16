	package com.cream.dao;
	
	import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cream.dto.AdminDTO;
import com.cream.dto.BidDTO;
import com.cream.dto.NotifyDTO;
import com.cream.dto.ProductImgDTO;
import com.cream.dto.ProductViewDTO;
import com.cream.dto.RankDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.SalesViewDTO;
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
				
				System.out.println("query.selectAllProduct = " +proFile.getProperty("query.selectAllProduct"));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		@Override
		public int register(UserDTO user) throws SQLException {
			//  db에 사용자 등록 하자
			PreparedStatement ps = null;
			Connection con=null;
			
			int result =0;
			
			String insertQuery = proFile.getProperty("query.register");
			try {
				con=DbUtil.getConnection();
				ps = con.prepareStatement(insertQuery); 
				ps.setString(1,user.getUserId());
				ps.setString(2, user.getName());
				ps.setString(3, user.getUserEmail());
				ps.setString(4, user.getUserPw());
				ps.setString(5, user.getHp());
				ps.setString(6, user.getNickname());
				ps.setInt(7,user.getShoesSize());
				ps.setString(8, user.getGender());
				ps.setInt(9, user.getAge());
				ps.setString(10,user.getAddress());
				
				result = ps.executeUpdate();
				
				
			}catch(SQLException e) {
				e.printStackTrace();
				throw e;
			}finally {
				DbUtil.dbClose(con,ps);   
			}
			return result;
			
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
		                rs.getInt(1),
		                rs.getInt(2),
		                rs.getString(3),
		                rs.getString(4),
		                rs.getString(5),
		                rs.getString(6),
		                rs.getString(7),
		                rs.getString(8),
		                rs.getInt(9),
		                rs.getString(10),
		                rs.getInt(11),
		                rs.getString(12),
		                rs.getInt(13),
		                rs.getString(14)
		            );
		        }
				
			}finally {
				DbUtil.dbClose(con, ps, rs);
			}
			return dbDTO;
		}
		
		
		
		
		
	
		@Override
		public AdminDTO loginAdminCheck(String adminId, String adminPw) throws SQLException {
			Connection con = null;
	        PreparedStatement ps = null;
	        ResultSet rs = null;
	        AdminDTO admin = null;

	        String sql = proFile.getProperty("query.adminLogin");

	        try {
	            con = DbUtil.getConnection();
	            ps = con.prepareStatement(sql);
	            ps.setString(1, adminId);
	            ps.setString(2, adminPw);

	            rs = ps.executeQuery();
	            if (rs.next()) {
	                admin = new AdminDTO(
	                    rs.getInt("NO"),
	                    rs.getString("ADMIN_ID"),
	                    rs.getString("ADMIN_PW"),
	                    rs.getInt("SHOECREAM")
	                );
	            }
	        } finally {
	            DbUtil.dbClose(con, ps, rs);
	        }
	        return admin;
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
		public int updateCash(int user_no, int cash) throws SQLException {
			Connection con = null;
		    PreparedStatement ps = null;
		    int result = 0;

		    try {
		        con = DbUtil.getConnection();
		        String sql = proFile.getProperty("query.updateCash");
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, cash);
		        ps.setInt(2, user_no);
		        
		        result = ps.executeUpdate();
		    } finally {
		        DbUtil.dbClose(con, ps);
		    }
		    
		    return result;
		}
	
		@Override
		public RankDTO getUserRank(String user_no) throws SQLException {
			Connection con = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    RankDTO rankDTO = null;

		    String sql = proFile.getProperty("query.getUserRank");

		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setString(1, user_no);

		        rs = ps.executeQuery();

		        if (rs.next()) {
		            int no = rs.getInt("NO");
		            String rank = rs.getString("RANK");
		            double discount = rs.getDouble("DISCOUNT");

		            rankDTO = new RankDTO(no, rank, discount);
		        }
		    } finally {
		        DbUtil.dbClose(con, ps, rs);
		    }

		    return rankDTO;
		}
	
		@Override
		public int deleteUser(String user_Id, String pwd) throws SQLException {
			Connection con = null;
		    PreparedStatement ps = null;
		    int result = 0;

		    String sql = proFile.getProperty("query.deleteUser");
		    
		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        
		        ps.setString(1, user_Id);
		        ps.setString(2, pwd);
		        
		        result = ps.executeUpdate();
		        
		    } finally {
		        DbUtil.dbClose(con, ps);
		    }
		    
		    return result;
		}
	
		@Override
		public int updateUser(UserDTO user) throws SQLException {
			Connection con = null;
		    PreparedStatement ps = null;
		    int result = 0;

		    String sql = proFile.getProperty("query.updateUser");

		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        
		        ps.setString(1, user.getUserPw());
		        ps.setString(2, user.getNickname());
		        ps.setString(3, user.getAddress());
		        ps.setInt(4, user.getShoesSize());
		        ps.setInt(5, user.getNo());
		        
		        result = ps.executeUpdate();
		        
		    } finally {
		        DbUtil.dbClose(con, ps);
		    }
		    
		    return result;
		}
	
		@Override
		public UserDTO selectUserById(int user_no) throws SQLException {
			Connection con = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    UserDTO user = null;

		    String sql = proFile.getProperty("query.selectUserById");
		    
		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, user_no);
		        rs = ps.executeQuery();
		        
		        if (rs.next()) {
		            user = new UserDTO();
		            user.setNo(rs.getInt("NO"));
		            user.setRankNo(rs.getInt("RANK_NO"));
		            user.setUserId(rs.getString("USER_ID"));
		            user.setName(rs.getString("NAME"));
		            user.setUserEmail(rs.getString("USER_EMAIL"));
		            user.setUserPw(rs.getString("USER_PW"));
		            user.setHp(rs.getString("HP"));
		            user.setNickname(rs.getString("NICKNAME"));
		            user.setShoesSize(rs.getInt("SHOES_SIZE"));
		            user.setRegdate(rs.getString("REGDATE"));
		            user.setCash(rs.getInt("SHOECREAM"));
		            user.setGender(rs.getString("GENDER"));
		            user.setAge(rs.getInt("AGE"));
		            user.setAddress(rs.getString("ADDRESS"));
		        }
		    } finally {
		        DbUtil.dbClose(con, ps, rs);
		    }
		    
		    return user;
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
		public List<ProductViewDTO> selectWishlist(int user_no) throws SQLException {
			Connection con = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    List<ProductViewDTO> list = new ArrayList<>();

		    String sql = proFile.getProperty("query.selectWishlist");
		    
		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, user_no);
		        rs = ps.executeQuery();

		        while (rs.next()) {
		        	ProductViewDTO product = new ProductViewDTO();

		            product.setNo(rs.getInt("NO"));
		            product.setEngName(rs.getString("ENG_NAME"));
		            product.setKorName(rs.getString("KOR_NAME"));
		            product.setReleasePrice(rs.getInt("RELEASE_PRICE"));

		            ProductImgDTO productImg = new ProductImgDTO();
		            productImg.setFilePath(rs.getString("FILE_PATH"));
		            product.setProductImg(productImg); 

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
		public List<SalesViewDTO> salesByUserNo(int user_no) throws SQLException {
			Connection con = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    List<SalesViewDTO> list = new ArrayList<>();

		    String sql = proFile.getProperty("query.salesByUserNo");

		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, user_no);
		        rs = ps.executeQuery();

		        while (rs.next()) {
		            SalesViewDTO sales = new SalesViewDTO();
		            sales.setNo(rs.getInt("NO"));
		            sales.setUserNo(rs.getInt("USER_NO"));
		            sales.setProductNo(rs.getInt("PRODUCT_NO"));
		            sales.setRegdate(rs.getString("REGDATE"));
		            sales.setSalesStatus(rs.getInt("SALES_STATUS"));            
		            sales.setShoesSize(rs.getInt("SHOES_SIZE"));
		            sales.setEngName(rs.getString("ENG_NAME"));
		            sales.setFilePath(rs.getString("FILE_PATH"));
		            
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
		        ps.setInt(7, sales.getShoesSize().getNo());

		        result = ps.executeUpdate();

		    } finally {
		        DbUtil.dbClose(con, ps);
		    }

		    return result;
		}
		
		
		public List<NotifyDTO> getNotifyList(int user_no) throws SQLException{
			List<NotifyDTO> list = new ArrayList<NotifyDTO>();
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
		    String sql = proFile.getProperty("query.getNotifyList");
			
		    try {
		    	con = DbUtil.getConnection();
		    	ps = con.prepareStatement(sql);
		    	ps.setInt(1, user_no);
		    	
		    	rs=ps.executeQuery();
		    	
		    	while(rs.next()) {
		    		NotifyDTO notify = new NotifyDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3) , rs.getInt(4),rs.getString(5), rs.getInt(6), rs.getString(user_no));
		    		list.add(notify);
		    	}
		    	
		    }catch(SQLException e){
		    	e.printStackTrace();
		    	throw new SQLException("오류 발생");
		    }finally {
		    	DbUtil.dbClose(con, ps, rs);
		    }
			
			return list;
		}

		@Override
		public int updateNotify(int userNo, int notifyNo) throws SQLException {
			Connection con = null;
			PreparedStatement ps=null;
			int result=0;
			String sql = proFile.getProperty("query.updateNotify");
			try {
				con=DbUtil.getConnection();
				ps=con.prepareStatement(sql);
				ps.setInt(1, userNo);
				ps.setInt(2, notifyNo);
				
				result = ps.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				DbUtil.dbClose(con, ps);
			}
				
			return result;
		}


		public int deleteNotify(int userNo, int notifyNo) {
			Connection con = null;
			PreparedStatement ps=null;
			int result=0;
			String sql = proFile.getProperty("query.deleteNotify");
			System.out.println("notify번호:"+notifyNo);
			try {
				con=DbUtil.getConnection();
				ps=con.prepareStatement(sql);
				ps.setInt(1, notifyNo);
				ps.setInt(2, userNo);
				
				result = ps.executeUpdate();
				if(result==0)
					throw new SQLException("삭제 안됨");
			}catch(SQLException e){
				e.printStackTrace();
			}finally {
				DbUtil.dbClose(con, ps);
			}
				
			return result;

		}
		
		@Override
		public boolean isProductInWishlist(int userNo, int productNo) throws SQLException {
		    Connection con = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    boolean exist = false;

		    String sql = proFile.getProperty("query.isProductInWishlist");

		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, userNo);
		        ps.setInt(2, productNo);
		        rs = ps.executeQuery();

		        if (rs.next() && rs.getInt(1) > 0) {
		            exist = true;
		        }
		    } finally {
		        DbUtil.dbClose(con, ps, rs);
		    }
		    return exist;
		}


		// 이메일 업데이트 메소드
		@Override
		public int updateEmail(int userNo, String email) throws SQLException {
		    Connection con = null;
		    PreparedStatement ps = null;
		    int result = 0;
		    String sql = "UPDATE USERS SET USER_EMAIL = ? WHERE NO = ?";
		    
		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setString(1, email);
		        ps.setInt(2, userNo);
		        result = ps.executeUpdate();
		    } finally {
		        DbUtil.dbClose(con, ps);
		    }
		    return result;
		}

		// 비밀번호 업데이트 메소드
		@Override
		public int updatePassword(int userNo, String password) throws SQLException {
		    Connection con = null;
		    PreparedStatement ps = null;
		    int result = 0;
		    String sql = "UPDATE USERS SET USER_PW = ? WHERE NO = ?";
		    
		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setString(1, password);
		        ps.setInt(2, userNo);
		        result = ps.executeUpdate();
		    } finally {
		        DbUtil.dbClose(con, ps);
		    }
		    return result;
		}

		// 닉네임 업데이트 메소드
		@Override
		public int updateNickname(int userNo, String nickname) throws SQLException {
		    Connection con = null;
		    PreparedStatement ps = null;
		    int result = 0;
		    String sql = "UPDATE USERS SET NICKNAME = ? WHERE NO = ?";
		    
		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setString(1, nickname);
		        ps.setInt(2, userNo);
		        result = ps.executeUpdate();
		    } finally {
		        DbUtil.dbClose(con, ps);
		    }
		    return result;
		}

		// 휴대폰 번호 업데이트 메소드
		@Override
		public int updatePhone(int userNo, String phone) throws SQLException {
		    Connection con = null;
		    PreparedStatement ps = null;
		    int result = 0;
		    String sql = "UPDATE USERS SET HP = ? WHERE NO = ?";
		    
		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setString(1, phone);
		        ps.setInt(2, userNo);
		        result = ps.executeUpdate();
		    } finally {
		        DbUtil.dbClose(con, ps);
		    }
		    return result;
		}

		// 신발 사이즈 업데이트 메소드
		@Override
		public int updateShoeSize(int userNo, int shoeSize) throws SQLException {
		    Connection con = null;
		    PreparedStatement ps = null;
		    int result = 0;
		    String sql = "UPDATE USERS SET SHOES_SIZE = ? WHERE NO = ?";
		    
		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setInt(1, shoeSize);
		        ps.setInt(2, userNo);
		        result = ps.executeUpdate();
		    } finally {
		        DbUtil.dbClose(con, ps);
		    }
		    return result;
		}

		// 주소 업데이트 메소드
		@Override
		public int updateAddress(int userNo, String address) throws SQLException {
		    Connection con = null;
		    PreparedStatement ps = null;
		    int result = 0;
		    String sql = "UPDATE USERS SET ADDRESS = ? WHERE NO = ?";
		    
		    try {
		        con = DbUtil.getConnection();
		        ps = con.prepareStatement(sql);
		        ps.setString(1, address);
		        ps.setInt(2, userNo);
		        result = ps.executeUpdate();
		    } finally {
		        DbUtil.dbClose(con, ps);
		    }
		    return result;
		}

		

		
}
	
	
	
	
	
	
	
	
	
	
	
	