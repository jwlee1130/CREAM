package com.cream.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cream.dto.SalesDTO;
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
    
    public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        try {
            String password = request.getParameter("password");
            String nickname = request.getParameter("nickname");
            String address = request.getParameter("address");
            int shoesSize = Integer.parseInt(request.getParameter("shoesSize"));

            loginUser.setUserPw(password);
            loginUser.setNickname(nickname);
            loginUser.setAddress(address);
            loginUser.setShoesSize(shoesSize);

            int result = service.updateUser(loginUser);

            if (result > 0) {
                UserDTO updatedUser = service.selectUserById(loginUser.getNo());
                session.setAttribute("loginUser", updatedUser);

                return new ModelAndView("page/mypage.jsp#test3", true);
            } else {
                return new ModelAndView("error/error.jsp", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("error/error.jsp", true);
        }
    }
    
    public ModelAndView updateCash(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        try {
            String cash = request.getParameter("cash");
            System.out.println("Received cash parameter: " + cash);

            int newCash = Integer.parseInt(cash); 
            int userId = loginUser.getNo();

            int result = service.updateCash(userId, newCash);

            if (result > 0) {
                UserDTO updatedUser = service.selectUserById(loginUser.getNo());
                session.setAttribute("loginUser", updatedUser);

                return new ModelAndView("page/mypage.jsp", true);
            } else {
                return new ModelAndView("error/error.jsp", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("error/error.jsp", true);
        }
    }

    public ModelAndView deleteUser(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        try {
            String userId = loginUser.getUserId();
            String password = request.getParameter("password");

            int result = service.deleteUser(userId, password);

            if (result > 0) {
                session.invalidate();
                return new ModelAndView("index.jsp", true);
            } else {
                return new ModelAndView("error/error.jsp", true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("error/error.jsp", true);
        }
    }



}