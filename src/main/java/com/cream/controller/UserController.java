package com.cream.controller;

import java.sql.SQLException;

import com.cream.dto.AdminDTO;
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
            // Admin 로그인 여부 확인
            AdminDTO admin = service.loginAdminCheck(userId, pwd);
            if (admin != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loginAdmin", admin);
                System.out.println("Admin stored in session: " + admin.getAdminId());
                return new ModelAndView("index.jsp");
            }

            // 일반 사용자 로그인 확인
            UserDTO user = new UserDTO(userId, pwd);
            UserDTO checkUser = service.loginCheck(user);
            
            if (checkUser != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loginUser", checkUser);
                System.out.println("User stored in session: " + checkUser.getUserId());
                return new ModelAndView("index.jsp");
            } else {
                System.out.println("Login failed for user: " + userId);
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
    
    public ModelAndView updateNotify(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");        

        try {
            int notifyNo = Integer.parseInt(request.getParameter("no"));
            int salesNo = Integer.parseInt(request.getParameter("salesNo"));
            String msg = request.getParameter("msg");
            service.updateNotify(loginUser.getNo(),notifyNo);
            System.out.println(msg+":"+msg.length());
            if(msg.length()==16){//물품판매 상태->마이 페이지로
                return new ModelAndView("page/mypage.jsp",true);
            }else {//32 or 나머지는 판매 페이지로
                return new ModelAndView("front?key=sales&methodName=salesDetail&salesNo"+salesNo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return new ModelAndView("error/error.jsp");

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

                return new ModelAndView("page/mypage.jsp#mypage_point", true);
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
