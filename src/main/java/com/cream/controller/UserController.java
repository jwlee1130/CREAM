package com.cream.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.websocket.AuthenticationException;

import com.cream.dto.ProductDTO;
import com.cream.dto.UserDTO;
import com.cream.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class UserController implements Controller {
	UserServiceImpl service = new UserServiceImpl();
	
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws SQLException, AuthenticationException {
		String id = request.getParameter("userId");
	    String pw = request.getParameter("pwd");

	    try {
	        UserDTO user = new UserDTO(id, pw);    
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
	            return new ModelAndView("index.jsp");
	        } else {
	            return new ModelAndView("error/error.jsp");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ModelAndView("error/error.jsp");
	    }
	}
	
	public ModelAndView selectWishlist(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
	    UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

	    if (loginUser == null) {
	        return new ModelAndView("user/login.jsp", true);
	    }

	    try {
	        List<ProductDTO> wishlist = service.selectWishlist(loginUser.getNo());
	        request.setAttribute("wishlist", wishlist);
	        return new ModelAndView("index.jsp");
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return new ModelAndView("error/error.jsp", true);
	    }
	}

}
