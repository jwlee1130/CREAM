package com.cream.dao;

import java.sql.SQLException;
import java.util.List;

import com.cream.dto.AdminDTO;
import com.cream.dto.BidDTO;
import com.cream.dto.NotifyDTO;
import com.cream.dto.ProductViewDTO;
import com.cream.dto.RankDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.SalesViewDTO;
import com.cream.dto.UserDTO;

public interface UserDAO {
	/**
	 * 	회원가입
	 * */
	int register(UserDTO user) throws SQLException;
	
  /**
   * 로그인 기능
   * select user_id , pwd, name from users where user_id=? and pwd=?
   * */
	UserDTO loginCheck(UserDTO userDTO)throws SQLException;
	
	AdminDTO loginAdminCheck(String adminId, String adminPw) throws SQLException;
	
	/*
	 * UserNo를 통해 bid(입찰)테이블에 입찰중이던 회원인지 체크한다.
	 * null이면 insert into로 생성을 null이 아니면 update() 메소드를 한다.
	 * 
	 */
	BidDTO findBidByUserNo(int no) throws SQLException;
	
	/**
	 * 	현금 충전
	 * */
	int updateCash(int user_no,int cash) throws SQLException;
	
	/**
	 * 	회원등급 확인
	 * */
	RankDTO getUserRank(String user_no) throws SQLException;
	
	/**
	 * 	회원 탈퇴
	 * */
	int deleteUser(String user_Id,String pwd) throws SQLException;
	
	/**
	 * 	회원정보 수정
	 * */
	int updateUser(UserDTO user) throws SQLException;
	
	/**
	 * 	회원정보 조회
	 * */
	UserDTO selectUserById(int user_no) throws SQLException;
	
	/**
	 * 	찜추가
	 * */
	int addToWishlist(int user_no, int product_no) throws SQLException;
	
	/**
	 * 	찜 조회
	 * */
	List<ProductViewDTO> selectWishlist(int user_no) throws SQLException;
	
	/**
	 * 	찜 삭제
	 * */
	int deleteWishlist(int user_no, int product_no) throws SQLException;

	// 누락된 항목 있음->노션 보고 추가하기
	
	/**
	 * 	판매등록 조회
	 * */
	List<SalesViewDTO> salesByUserNo(int user_no) throws SQLException;
	
	/**
	 * 	판매 등록
	 * */
	int insertSales(SalesDTO sales) throws SQLException;
	/*
	 * 알림 저장된거 갖고오기
	 */
	List<NotifyDTO> getNotifyList(int user_no) throws SQLException;

	/*
	 * 알림 상태 업데이트
	 */
	int updateNotify(int userNo, int notifyNo) throws SQLException;
	
	/*
	 * 알림 삭제
	 */
	int deleteNotify(int no, int notifyNo) throws SQLException;	
	
	/**
	 * 	관심상품 추가 및 해제
	 * */
	boolean isProductInWishlist(int userNo, int productNo) throws SQLException;

	int updateEmail(int userNo, String email) throws SQLException;

	int updatePassword(int userNo, String password) throws SQLException;

	int updateNickname(int userNo, String nickname) throws SQLException;

	int updatePhone(int userNo, String phone) throws SQLException;

	int updateShoeSize(int userNo, int shoeSize) throws SQLException;

	int updateAddress(int userNo, String address) throws SQLException;
}
