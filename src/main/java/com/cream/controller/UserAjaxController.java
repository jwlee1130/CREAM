package com.cream.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cream.dto.ProductDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.ShoesSizeDTO;
import com.cream.dto.UserDTO;
import com.cream.service.UserService;
import com.cream.service.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserAjaxController implements RestController {
    UserService service = new UserServiceImpl();

    public Object selectWishlist(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        List<ProductDTO> list = new ArrayList<ProductDTO>();
        try {
            list = service.selectWishlist(loginUser.getNo());
            
            System.out.println(list.size());
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Object deleteWishlist(HttpServletRequest request, HttpServletResponse response) {
    	 HttpSession session = request.getSession();
    	    UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
    	    
    	    int result=0;
    	    try {
    	        int product_no = Integer.parseInt(request.getParameter("product_no"));
    	        
    	        result = service.deleteWishlist(loginUser.getNo(), product_no);
    	        
    			
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
			return result;
    }
    public Object salesByUserNo(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        List<SalesDTO> list = new ArrayList<SalesDTO>();
        
        try {
        	list= service.salesByUserNo(loginUser.getNo());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Object insertSales(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        
        int result = 0;
        try {
            int productNo = Integer.parseInt(request.getParameter("productNo"));
            int startingPrice = Integer.parseInt(request.getParameter("startingPrice"));
            int nowPrice = Integer.parseInt(request.getParameter("nowPrice"));
            int salesStatus = Integer.parseInt(request.getParameter("salesStatus"));
            String regdate = request.getParameter("regdate");
            char grade = request.getParameter("grade").charAt(0);
            int shoesNo = Integer.parseInt(request.getParameter("shoesNo"));

            SalesDTO sales = new SalesDTO();
            sales.setUserNo(loginUser.getNo());
            sales.setProductNo(productNo);
            sales.setStartingPrice(startingPrice);
            sales.setNowPrice(nowPrice);
            sales.setSalesStatus(salesStatus);
            sales.setRegdate(regdate);
            sales.setGrade(grade);
            sales.setShoesSize(new ShoesSizeDTO(shoesNo, 0));

            result = service.insertSales(sales);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    
    
    public Object addToWishlist(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        
        int result = 0;
        try {
            int productNo = Integer.parseInt(request.getParameter("product_no"));
            result = service.addToWishlist(loginUser.getNo(), productNo);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public Object toggleWishlist(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        Map<String, Object> resultMap = new HashMap<>();
        String status = "error";
        try {
            int productNo = Integer.parseInt(request.getParameter("product_no"));
            boolean isWishlistItem = service.isProductInWishlist(loginUser.getNo(), productNo);

            if (isWishlistItem) {
                int result = service.deleteWishlist(loginUser.getNo(), productNo);
                status = (result > 0) ? "removed" : "error";
            } else {
                int result = service.addToWishlist(loginUser.getNo(), productNo);
                status = (result > 0) ? "added" : "error";
            }
            resultMap.put("status", status);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }
    
    
    
    public Object selectUserById(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        UserDTO user = null;
        
        try {
        	user = service.selectUserById(loginUser.getNo());
        	System.out.println("User Data: " + user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return user;
    }
}
