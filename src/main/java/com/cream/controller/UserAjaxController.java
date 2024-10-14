	package com.cream.controller;
	
	import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cream.dto.NotifyDTO;
import com.cream.dto.ProductViewDTO;
import com.cream.dto.RankDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.SalesViewDTO;
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
	        System.out.println("selectWishlist 메소드 호출됨. 사용자 ID: " + loginUser.getNo()); // 로그 추가
	        List<ProductViewDTO> list = new ArrayList<ProductViewDTO>();
	        try {
	            list = service.selectWishlist(loginUser.getNo());
	            System.out.println("관심 상품 리스트 크기: " + list.size());
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
	        List<SalesViewDTO> list = new ArrayList<SalesViewDTO>();
	        
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
	    
	   public Object notfiyList(HttpServletRequest request, HttpServletResponse response) {
	    	HttpSession session = request.getSession();
	        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
	        List<NotifyDTO> list = new ArrayList<NotifyDTO>();
	        try {
	            list = service.getNotifyList(loginUser.getNo());
	            
	            System.out.println(list.size());
	            
	            
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }
	   
	    public Object deleteNotify(HttpServletRequest request, HttpServletResponse response) {
	        HttpSession session = request.getSession();
	        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");        
	          int notifyNo = Integer.parseInt(request.getParameter("no"));
	          int result=0;
	          try {
	        	  	result = service.deleteNotify(loginUser.getNo(),notifyNo);
	          } catch (SQLException e) {
	        	  // TODO Auto-generated catch block
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
	    
	    public Object getUserRank(HttpServletRequest request, HttpServletResponse response) {
	        HttpSession session = request.getSession();
	        UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
	        RankDTO rank = null;

	        try {
	        	rank = service.getUserRank(String.valueOf(loginUser.getNo()));
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return rank;
	    }
	}
