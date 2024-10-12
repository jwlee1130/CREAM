package com.cream.dao;

import com.cream.dto.ProductDTO;
import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;
import com.cream.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO {

    @Override
    public int deleteUserById(int userNo) throws SQLException {
        String sql = "delete from users where no=?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userNo);
            return ps.executeUpdate(); // delete 의 결과를 반환
        } finally {
            DbUtil.dbClose(conn, ps);
        }
    }

    @Override
    public int deleteProductById(int productNo) throws SQLException {
        String sql = "delete from product where id=?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productNo);
            return ps.executeUpdate();
        } finally {
            DbUtil.dbClose(conn, ps);
        }
    }

    @Override
    public List<SalesDTO> getUnapprovedProducts() throws SQLException {
        String sql = "SELECT * FROM USERS_SALES WHERE SALES_STATUS = 0";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SalesDTO> list = new ArrayList<SalesDTO>();

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SalesDTO product = new SalesDTO();

                product.setNo(rs.getInt("no"));
                product.setUserNo(rs.getInt("user_no"));
                product.setProductNo(rs.getInt("product_no"));
                product.setStartingPrice(rs.getInt("starting_price"));
                product.setNowPrice(rs.getInt("now_price"));
                product.setSalesStatus(rs.getInt("sales_status"));
                product.setRegdate(rs.getString("regdate"));
                product.setGrade(rs.getString("grade").charAt(0));
                list.add(product);
            }
        } finally {
            DbUtil.dbClose(conn, ps, rs);
        }
        return list;
    }

    @Override
    public int updateSalesStatus(int sales_no, int sales_status, char grade) throws SQLException {
        String sql = "update users_sales set sales_status=?,grade=? where no=?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sales_status);
            ps.setString(2, String.valueOf(grade));
            ps.setInt(3, sales_no);

            return ps.executeUpdate();
        } finally {
            DbUtil.dbClose(conn, ps);
        }
    }

    @Override
    public int submitSurvey(SurveyDTO surveyData) throws SQLException {
        String sql = "insert into survey (user_no,category,brand,color,price) values (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, surveyData.getUserNo());
            ps.setString(2, surveyData.getCategory());
            ps.setString(3, surveyData.getBrand());
            ps.setString(4, surveyData.getColor());
            ps.setInt(5, surveyData.getPrice());
            return ps.executeUpdate();
        } finally {
            DbUtil.dbClose(conn, ps);
        }
    }

    public ProductDTO getRecommendedProduct(String brand, String color, int maxPrice) throws SQLException {
        String sql = "SELECT * FROM product " +
                "WHERE brand_no = (SELECT no FROM brand WHERE brand = ?) " +
                "AND color_no = (SELECT no FROM color WHERE color = ?) " +
                "AND release_price <= ? " +
                "ORDER BY release_price DESC " +
                "LIMIT 1";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProductDTO recommendedProduct = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, brand);
            ps.setString(2, color);
            ps.setInt(3, maxPrice);

            rs = ps.executeQuery();

            if (rs.next()) {
                // ProductDTO 객체에 결과 세팅
                recommendedProduct = new ProductDTO();
                recommendedProduct.setNo(rs.getInt("no"));
                recommendedProduct.setBrandNo(rs.getInt("brand_no"));
                recommendedProduct.setCategoryNo(rs.getInt("category_no"));
                recommendedProduct.setShoesNo(rs.getInt("shoes_no"));
                recommendedProduct.setColorNo(rs.getInt("color_no"));
                recommendedProduct.setEngName(rs.getString("eng_name"));
                recommendedProduct.setKorName(rs.getString("kor_name"));
                recommendedProduct.setRelease(rs.getString("release"));
                recommendedProduct.setReleasePrice(rs.getInt("release_price"));
                recommendedProduct.setModelNumber(rs.getString("modelnumber"));
                recommendedProduct.setRegdate(rs.getString("regdate"));
                recommendedProduct.setSalesQuantity(rs.getInt("sales_quantity"));
            }
        } finally {
            DbUtil.dbClose(conn, ps, rs);
        }

        return recommendedProduct;
    }

    @Override
    public String getProductName(int productNo) throws SQLException {
        String sql = "SELECT KOR_NAME FROM PRODUCT WHERE NO = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String productName = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productNo);

            rs = ps.executeQuery();

            if (rs.next()) {
                productName = rs.getString("kor_name");
            }
        } finally {
            DbUtil.dbClose(conn, ps, rs);
        }

        return productName;
    }
}

