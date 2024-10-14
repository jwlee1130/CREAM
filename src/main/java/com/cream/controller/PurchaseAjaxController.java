package com.cream.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.cream.dto.PurchaseDTO;
import com.cream.dto.UserDTO;
import com.cream.service.PurchaseService;
import com.cream.service.PurchaseServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PurchaseAjaxController implements RestController{
	PurchaseService service = new PurchaseServiceImpl();
	
	public Object selectPurchase(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
	    UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
        List<PurchaseDTO> list = null;

        response.setContentType("application/json; charset=UTF-8");
        try {
            list = service.selectPurchase(loginUser.getNo());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
