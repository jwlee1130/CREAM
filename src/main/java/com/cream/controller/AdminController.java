package com.cream.controller;

import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;
import com.cream.service.AdminService;
import com.cream.service.AdminServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class AdminController implements RestController {

    private final AdminService adminService = new AdminServiceImpl();

    public int deleteUserById(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int userNo = Integer.parseInt(request.getParameter("userNo"));
        return adminService.deleteUserById(userNo);
    }

    public int deleteProductById(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int productNo = Integer.parseInt(request.getParameter("productNo"));
        return adminService.deleteProductById(productNo);
    }

    public List<SalesDTO> getUnapprovedProducts(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        List<SalesDTO> unapprovedProducts = adminService.getUnapprovedProducts();

//        System.out.println("Unapproved Products List: ");
//        for (SalesDTO product : unapprovedProducts) {
//            System.out.println("product = " + product);
//        }
        return unapprovedProducts;
    }

    public int updateSalesStatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int salesNo = Integer.parseInt(request.getParameter("salesNo"));
        int salesStatus = Integer.parseInt(request.getParameter("salesStatus"));
        int price = Integer.parseInt(request.getParameter("startingPrice"));
        System.out.println("AdminController.updateSalesStatus");
        System.out.println(price);
        return adminService.updateSalesStatus(salesNo, salesStatus,price);
    }

    public int updateSalesGrade(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        int salesNo = Integer.parseInt(request.getParameter("salesNo"));
        char grade = request.getParameter("grade").charAt(0);
        return adminService.updateSalesGrade(salesNo, grade);
    }

    public int submitSurvey(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        SurveyDTO survey = new SurveyDTO();
        survey.setUserNo(Integer.parseInt(request.getParameter("userNo")));
        survey.setCategory(request.getParameter("category"));
        survey.setBrand(request.getParameter("brand"));
        survey.setColor(request.getParameter("color"));
        survey.setPrice(Integer.parseInt(request.getParameter("price")));

        return adminService.submitSurvey(survey);
    }

//    public Map<String, Object> getRecommendedProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException {
//        String brand = request.getParameter("brand");
//        String color = request.getParameter("color");
//        int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("recommendedProduct", adminService.getRecommendedProduct(brand, color, maxPrice));
//        return result;
//
//    }

    public Map<String, String> getProductName(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        System.out.println("AdminController.getProductName");
        int productNo = Integer.parseInt(request.getParameter("productNo"));
        Map<String, String> result = adminService.getProductName(productNo);
//        System.out.println("Product Name for productNo " + productNo + ": " + result.get("productName"));
        return result;
    }
}
