package com.cream.controller;

import java.io.PrintWriter;
import java.util.List;

import com.cream.dto.ProductDTO;
import com.cream.dto.UserDTO;
import com.cream.service.UserServiceImpl;
import com.google.gson.Gson;

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
            
            System.out.println("Wishlist size: " + wishlist.size());

            Gson gson = new Gson();
    		String jsonArr = gson.toJson(wishlist);
    		
    		PrintWriter out = response.getWriter();
    		out.println(jsonArr);
    		
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
