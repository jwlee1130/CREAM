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

    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
	    String userId = request.getParameter("userId");
	    String name = request.getParameter("name");
	    
	    String userEmail = request.getParameter("userEmail");
	    String userPw = request.getParameter("userPw");
	    String hp = request.getParameter("hp");
	    String nickname = request.getParameter("nickname");
	    String gender = request.getParameter("gender");
	    String address =request.getParameter("address");

	    int shoesSize = 0;
	    int age = 0;

	    try {
	        String shoesSizeStr = request.getParameter("shoesSize");
	        if (shoesSizeStr != null && !shoesSizeStr.isEmpty()) {
	            shoesSize = Integer.parseInt(shoesSizeStr);
	        } else {
	            throw new IllegalArgumentException("신발 사이즈를 선택하지 않았습니다.");
	        }

	        String ageStr = request.getParameter("age");
	        if (ageStr != null && !ageStr.isEmpty()) {
	            age = Integer.parseInt(ageStr);
	        } else {
	            throw new IllegalArgumentException("나이를 입력하지 않았습니다.");
	        }
	    } catch (NumberFormatException e) {
	        throw new IllegalArgumentException("잘못된 신발 사이즈 형식입니다.");
	    }  

	    UserDTO user = new UserDTO();
	    user.setUserId(userId);
	    user.setName(name);
	    user.setUserEmail(userEmail);
	    user.setUserPw(userPw);
	    user.setHp(hp);
	    user.setNickname(nickname);
	     user.setAddress(address);
	    user.setGender(gender);
	    user.setShoesSize(shoesSize); 
	   user.setAge(age);
	    
	    

	    try {
	        int result = service.register(user);
	        if (result > 0) {
	            request.setAttribute("successMessage", "회원가입 성공");
	            return new ModelAndView("page/login.jsp");
	        } else {
	            request.setAttribute("errorMessage", "회원가입 실패.");
	            return new ModelAndView("page/register.jsp");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "회원정보 저장 실패.");
	        return new ModelAndView("page/register.jsp");
	    } catch (IllegalArgumentException e) {
	        request.setAttribute("errorMessage", e.getMessage());
	        return new ModelAndView("page/register.jsp");
	    } catch (AuthenticationException e) {
	    	request.setAttribute("errorMessage", e.getMessage());
	        return new ModelAndView("page/register.jsp");
		}
	
	}

}
