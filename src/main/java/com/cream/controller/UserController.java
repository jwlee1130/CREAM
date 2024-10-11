package com.cream.controller;


import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.websocket.AuthenticationException;

import com.cream.dto.ProductDTO;
import com.cream.dto.UserDTO;
import com.cream.exception.AuthenticationException;
import com.cream.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.SQLException;


public class UserController implements Controller {
	UserServiceImpl service = new UserServiceImpl();
	public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws SQLException ,AuthenticationException{
		String userId = request.getParameter("userId");
		String nickname = request.getParameter("nickname");
		String userEmail = request.getParameter("userEmail");
		String userPw = request.getParameter("userPw");
		String gender = request.getParameter("gender");
		
		int shoesSize = 0;
		int age = 0;
		
		try {
	        // shoesSize 값이 null이 아니고 빈 값도 아닌지 확인
	        String shoesSizeStr = request.getParameter("shoesSize");
	        if (shoesSizeStr != null && !shoesSizeStr.isEmpty()) {
	            shoesSize = Integer.parseInt(shoesSizeStr);  // String을 int로 변환
	        } else {
	            throw new IllegalArgumentException("신발 사이즈를 선택하지 않았습니다.");
	        }
	    
	    
		 String ageStr = request.getParameter("age");
		 if(ageStr != null && !ageStr.isEmpty()) {
			 age= Integer.parseInt(ageStr);
		 }else {
			 throw new IllegalArgumentException("나이를 입력하지 않았습니다.");
		 }
	} catch (NumberFormatException e) {
        throw new IllegalArgumentException("잘못된 신발 사이즈 형식입니다.");
        
	}
		
		UserDTO user = new UserDTO();
		
		user.setUserId(userId);
        user.setNickname(nickname);
        user.setUserEmail(userEmail);
        user.setUserPw(userPw);
        user.setGender(gender);
        user.setShoesSize(shoesSize);
        user.setAge(age);
        
		UserDTO registerUser = service.register(user);
		if(registerUser!=null) {
			//성공시 뷰
		}else {
			//실패시 뷰
		}
		
		return new ModelAndView("page/register.jsp");
		
		
	}
	
	
	
	

	
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws SQLException, AuthenticationException {

		String userId = request.getParameter("userId");
	    String pwd = request.getParameter("pwd");

	    try {
	        UserDTO user = new UserDTO(userId, pwd);    
	        UserDTO checkUser = service.loginCheck(user);

	        if (checkUser != null) {
	            HttpSession session = request.getSession();
	            session.setAttribute("loginUser", checkUser);
	            return new ModelAndView("index.jsp");
	        } else {
	            return new ModelAndView("page/login.jsp", true); 
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ModelAndView("error/error.jsp", true);
	    }
	    
		
	}
	
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
			session.invalidate();
			return new ModelAndView("index.jsp",true);
		
	}
	

	public ModelAndView addToWishlist(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
		
		if (loginUser == null) {
	        return new ModelAndView("user/login.jsp",true);
		}
		try {
	        int product_no = Integer.parseInt(request.getParameter("product_no"));
	        int result = service.addToWishlist(loginUser.getNo(), product_no);

	        if (result > 0) {
	            return new ModelAndView("page/mypage.jsp");
	        } else {
	            return new ModelAndView("error/error.jsp");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ModelAndView("error/error.jsp");
	    }
	}


}
