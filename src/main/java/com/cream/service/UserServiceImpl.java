package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dao.UserDAOImpl;
import com.cream.dto.AdminDTO;
import com.cream.dto.BidDTO;
import com.cream.dto.NotifyDTO;
import com.cream.dto.ProductViewDTO;
import com.cream.dto.RankDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.SalesViewDTO;
import com.cream.dto.UserDTO;
import com.cream.exception.AuthenticationException;

public class UserServiceImpl implements UserService {
	UserDAOImpl dao = new UserDAOImpl();

	@Override
	public int register(UserDTO user) throws SQLException, AuthenticationException {
	    // DAO에 저장하는 로직
	    int result = dao.register(user); // DAO 호출하여 영향을 받은 행 수를 반환 받음
	    if (result == 0) { // 영향을 받은 행이 없으면 예외 발생
	        throw new SQLException("회원가입 실패: 사용자가 등록되지 않았습니다.");
	    }
	    return result; // 성공적으로 저장된 경우 행 수 반환
	}

	@Override
	public UserDTO loginCheck(UserDTO userDTO) throws SQLException{
		UserDTO checkUser = dao.loginCheck(userDTO);
		return checkUser;
		
	}
	
	@Override
	public AdminDTO loginAdminCheck(String adminId, String adminPw) throws SQLException {
		AdminDTO admin=dao.loginAdminCheck(adminId, adminPw);
		return admin;
	}

	@Override
	public BidDTO findBidByUserNo(int no) throws SQLException, AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateCash(int user_no, int cash) throws SQLException {
		int result = dao.updateCash(user_no,cash);
		return result;
	}

	@Override
	public RankDTO getUserRank(String user_no) throws SQLException {
		RankDTO result = dao.getUserRank(user_no);
		return result;
	}

	@Override
	public int deleteUser(String user_Id, String pwd) throws SQLException {
		int result = dao.deleteUser(user_Id,pwd);
		return result;
	}

	@Override
	public int updateUser(UserDTO user) throws SQLException {
		int result = dao.updateUser(user);
		return result;
	}

	@Override
	public UserDTO selectUserById(int user_no) throws SQLException {
		UserDTO user = dao.selectUserById(user_no);
		return user;
	}

	@Override
	public int addToWishlist(int user_no, int product_no) throws SQLException {
		int result = dao.addToWishlist(user_no, product_no);
		return result;
	}

	@Override
	public List<ProductViewDTO> selectWishlist(int user_no) throws SQLException {
		List<ProductViewDTO> list = dao.selectWishlist(user_no);
		return list;
	}

	@Override
	public int deleteWishlist(int user_no, int product_no) throws SQLException {
		int result = dao.deleteWishlist(user_no, product_no);
		return result;
	}
	
	

	@Override
	public List<SalesViewDTO> salesByUserNo(int user_no) throws SQLException {
		List<SalesViewDTO> list= dao.salesByUserNo(user_no);
		return list;
	}

	@Override
	public int insertSales(SalesDTO sales) throws SQLException {
		int result = dao.insertSales(sales);
		return result;
	}

	@Override
	public List<NotifyDTO> getNotifyList(int user_no) throws SQLException {
		List<NotifyDTO> list = dao.getNotifyList(user_no);
		return list;
	}

	public int updateNotify(int userNo, int notifyNo) throws SQLException {
		int result = dao.updateNotify(userNo, notifyNo);
		if(result ==0) {
			throw new SQLException("오류");
		}
		return result;
	}

	@Override
	public int deleteNotify(int userNo, int notifyNo)  {
		int result = dao.deleteNotify(userNo,notifyNo);
		return result;
	}
	
	@Override
	public boolean isProductInWishlist(int userNo, int productNo) throws SQLException {
		boolean result = dao.isProductInWishlist(userNo, productNo);
		return result;
	}

	@Override
    public int updateEmail(int userNo, String email) throws SQLException {
        return dao.updateEmail(userNo, email);
    }

    @Override
    public int updatePassword(int userNo, String password) throws SQLException {
        return dao.updatePassword(userNo, password);
    }

    @Override
    public int updateNickname(int userNo, String nickname) throws SQLException {
        return dao.updateNickname(userNo, nickname);
    }

    @Override
    public int updatePhone(int userNo, String phone) throws SQLException {
        return dao.updatePhone(userNo, phone);
    }

    @Override
    public int updateShoeSize(int userNo, int shoeSize) throws SQLException {
        return dao.updateShoeSize(userNo, shoeSize);
    }

    @Override
    public int updateAddress(int userNo, String address) throws SQLException {
        return dao.updateAddress(userNo, address);
    }

	
	
	
	
	
	
}