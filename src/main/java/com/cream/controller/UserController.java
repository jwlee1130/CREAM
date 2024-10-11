package com.cream.controller;

import java.sql.SQLException;

import com.cream.dto.UserDTO;
import com.cream.exception.AuthenticationException;
import com.cream.service.UserService;
import com.cream.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserController implements Controller {
   
    UserService service = new UserServiceImpl();
   
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws SQLException, AuthenticationException {
        String userId = request.getParameter("userId");
        String pwd = request.getParameter("pwd");

        try {
            UserDTO user = new UserDTO(userId, pwd);
            UserDTO checkUser = service.loginCheck(user);
            
            if (checkUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loginUser", checkUser);
                
                // 세션에 제대로 저장되었는지 확인하는 로그 추가
                UserDTO sessionUser = (UserDTO) session.getAttribute("loginUser");
                System.out.println("User stored in session: " + (sessionUser != null ? sessionUser.getUserId() : "null"));
                
                return new ModelAndView("index.jsp");
            } else {
                System.out.println("Login failed for user: " + userId);  // 로그인 실패 로그
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
        return new ModelAndView("index.jsp", true);
    }
   
    public ModelAndView addToWishlist(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
      
        if (loginUser == null) {
            return new ModelAndView("user/login.jsp", true);
        }

        try {
            int productNo = Integer.parseInt(request.getParameter("product_no"));
            int result = service.addToWishlist(loginUser.getNo(), productNo);

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
