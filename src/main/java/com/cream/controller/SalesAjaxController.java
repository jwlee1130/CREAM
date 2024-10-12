package com.cream.controller;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.cream.dto.ProductDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.UserDTO;
import com.cream.service.SalesService;
import com.cream.service.SalesServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import netscape.javascript.JSObject;

public class SalesAjaxController implements RestController {
	SalesService service = new SalesServiceImpl();
	
	public Object selectAll(HttpServletRequest request, HttpServletResponse response) throws SQLException {
    	int shoesNo = Integer.parseInt(request.getParameter("shoesNo"));
    	int productNo = Integer.parseInt(request.getParameter("productNo"));
    	
    	List<SalesDTO> sales = service.selectAll(shoesNo,productNo);
            
        return sales;
            
       
    }
}
