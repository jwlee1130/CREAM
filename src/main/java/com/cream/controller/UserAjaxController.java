package com.cream.controller;

import java.io.PrintWriter;
import java.util.List;

import com.cream.dto.ProductDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.UserDTO;
import com.cream.service.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserAjaxController implements RestController {
    UserServiceImpl service = new UserServiceImpl();

    public void selectWishlist(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        try {
            List<ProductDTO> wishlist = service.selectWishlist(loginUser.getNo());
            
            System.out.println(wishlist.size());
            
            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String jsonArr = gson.toJson(wishlist);
            
            PrintWriter out = response.getWriter();
            out.println(jsonArr);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteWishlist(HttpServletRequest request, HttpServletResponse response) {
    	 HttpSession session = request.getSession();
    	    UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

    	    try {
    	        int product_no = Integer.parseInt(request.getParameter("product_no"));
    	        
    	        int result = service.deleteWishlist(loginUser.getNo(), product_no);
    	        
    	        PrintWriter out = response.getWriter();
    			out.println(result);
    			
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    }
    public void salesByUserNo(HttpServletRequest request, HttpServletResponse response) {
    	HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        try {
            List<SalesDTO> salesList = service.salesByUserNo(loginUser.getNo());

            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String jsonArr = gson.toJson(salesList);
            
            PrintWriter out = response.getWriter();
            out.println(jsonArr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void insertSales(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");

        try {
            int productNo = Integer.parseInt(request.getParameter("productNo"));
            int startingPrice = Integer.parseInt(request.getParameter("startingPrice"));
            int nowPrice = Integer.parseInt(request.getParameter("nowPrice"));
            int salesStatus = Integer.parseInt(request.getParameter("salesStatus"));
            String regdate = request.getParameter("regdate");
            char grade = request.getParameter("grade").charAt(0);

            SalesDTO sales = new SalesDTO();
            sales.setUserNo(loginUser.getNo());
            sales.setProductNo(productNo);
            sales.setStartingPrice(startingPrice);
            sales.setNowPrice(nowPrice);
            sales.setSalesStatus(salesStatus);
            sales.setRegdate(regdate);
            sales.setGrade(grade);

            int result = service.insertSales(sales);

            PrintWriter out = response.getWriter();
            out.println(result);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
