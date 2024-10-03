package com.cream.controller;

import java.sql.SQLException;

import org.apache.tomcat.websocket.AuthenticationException;

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
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", checkUser);
				
				return new ModelAndView("index.jsp");
			
		}catch(Exception e) {
			request.setAttribute("errorMsg", "로그인 실패");
			return new ModelAndView("error/error.jsp",true);

		}
		
	}
	
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession();
			session.invalidate();
			return new ModelAndView("index.jsp",true);
		
	}
}
