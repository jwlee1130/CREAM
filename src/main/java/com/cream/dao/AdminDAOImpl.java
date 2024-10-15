package com.cream.dao;

import com.cream.dto.*;
import com.cream.util.DbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminDAOImpl implements AdminDAO {
	BidDAO bidDAO = new BidDAOImpl();
    /*
    DELETE FROM USERS WHERE NO = 1;
     */
    @Override
    public int deleteUserByNo(int userNo) throws SQLException {
        String sql = "DELETE FROM USERS WHERE NO=?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userNo);
            return ps.executeUpdate();
        } finally {
            DbUtil.dbClose(conn, ps);
        }
    }

    @Override
    public int deleteUsersSalesByNo(int salesNo) throws SQLException {
        String sql = "DELETE FROM USERS_SALES WHERE NO=?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, salesNo);
            return ps.executeUpdate();
        } finally {
            DbUtil.dbClose(conn, ps);
        }
    }


    public void deletePurchasesBySalesNo(int salesNo, Connection conn) throws SQLException {
        String sql = "DELETE FROM PURCHASE WHERE SALES_NO=?";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, salesNo);
            ps.executeUpdate();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }


    /*
    SELECT * FROM USERS_SALES WHERE SALES_STATUS = 0;
     */
    @Override
    public List<SalesDTO> getUnapprovedProducts() throws SQLException {
        String sql = "SELECT * FROM USERS_SALES WHERE SALES_STATUS = 0";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<SalesDTO> list = new ArrayList<>();

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                SalesDTO product = new SalesDTO();
                product.setNo(rs.getInt("NO"));
                product.setUserNo(rs.getInt("USER_NO"));
                product.setProductNo(rs.getInt("PRODUCT_NO"));
                product.setStartingPrice(rs.getInt("STARTING_PRICE"));
                product.setNowPrice(rs.getInt("NOW_PRICE"));
                product.setSalesStatus(rs.getInt("SALES_STATUS"));
                product.setRegdate(rs.getString("REGDATE"));
                product.setGrade(rs.getString("GRADE").charAt(0));
                list.add(product);
            }
        } finally {
            DbUtil.dbClose(conn, ps, rs);
        }
        return list;
    }

    /*
    UPDATE USERS_SALES SET SALES_STATUS = 1 WHERE NO = 1;
     */
    @Override
    public int updateSalesStatus(int salesNo, int salesStatus, int price) throws SQLException {
        String sql = "UPDATE USERS_SALES SET SALES_STATUS=?,REGDATE=DATE_ADD(NOW(),INTERVAL 7 DAY) WHERE NO=?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, salesStatus);
            ps.setInt(2, salesNo);
            bidDAO.insertAdminAccount(conn, salesNo, price);
            return ps.executeUpdate();
        } finally {
            DbUtil.dbClose(conn, ps);
        }
    }

    /*
    UPDATE USERS_SALES SET GRADE = 'A' WHERE NO = 1;
     */
    @Override
    public int updateSalesGrade(int salesNo, char grade) throws SQLException {
        String sql = "UPDATE USERS_SALES SET GRADE=? WHERE NO=?";
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(grade));
            ps.setInt(2, salesNo);
            return ps.executeUpdate();
        } finally {
            DbUtil.dbClose(conn, ps);
        }
    }

    /*
    INSERT INTO SURVEY (USER_NO, CATEGORY, BRAND, COLOR, PRICE) VALUES (1, 'Shoes', 'Nike', 'Red', 100);
     */
    @Override
    public int submitSurvey(SurveyDTO surveyData) throws SQLException {
        String sql = "INSERT INTO SURVEY (USER_NO, CATEGORY, BRAND, COLOR, PRICE) VALUES (?, ?, ?, ?, ?)";
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

    /*
    SELECT KOR_NAME FROM PRODUCT WHERE NO = 1;
     */
    @Override
    public Map<String, String> getProductName(int productNo) throws SQLException {
        String sql = "SELECT KOR_NAME FROM PRODUCT WHERE NO = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<String, String> result = new HashMap<>();

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productNo);
            rs = ps.executeQuery();

            if (rs.next()) {
                result.put("productName", rs.getString("KOR_NAME"));
            }
        } finally {
            DbUtil.dbClose(conn, ps, rs);
        }

        return result;
    }

    @Override
    public boolean hasUserCompletedSurvey(String userId) throws SQLException {
        String sql="SELECT COUNT(*) FROM SURVEY WHERE USER_ID = ?";
        Connection conn =null;
        PreparedStatement ps =null;
        ResultSet rs = null;

        try
        {
            conn=DbUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            if(rs.next())
            {
                return rs.getInt(1)>0;
            }
        }
        finally
        {
            DbUtil.dbClose(conn, ps,rs);
        }
        return false;
    }

    @Override
    public boolean isAdmin(String adminId) throws SQLException {
        String sql="SELECT COUNT(*) FROM ADMIN WHERE ADMIN_ID = ?";
        Connection conn =null;
        PreparedStatement ps =null;
        ResultSet rs = null;

        try
        {
            conn=DbUtil.getConnection();
            ps=conn.prepareStatement(sql);
            ps.setString(1, adminId);
            rs = ps.executeQuery();

            if(rs.next())
            {
                return rs.getInt(1)>0; // 관리자 아이디라면 참을 반환
            }
        }
        finally
        {
            DbUtil.dbClose(conn, ps, rs);
        }
        return false; // 관리자 아이디가 아니므로 거짓을 반환
    }

//    @Override
//    public ProductDTO getProduct(int categoryNo, int brandNo, int colorNo, int releasePrice) throws SQLException {
////        String sql = "SELECT p.*, b.NO AS BRAND_NO, b.BRAND, pi.FILE_PATH, pi.FILE_SIZE, pi.REGDATE " +
////                "FROM PRODUCT p " +
////                "JOIN BRAND b ON p.BRAND_NO = b.NO " +
////                "JOIN PRODUCT_IMG pi ON p.NO = pi.PRODUCT_NO AND p.COLOR_NO = pi.COLOR_NO " +
////                "WHERE p.CATEGORY_NO = ? " +
////                "AND p.BRAND_NO = ? " +
////                "AND p.COLOR_NO = ? " +
////                "AND p.RELEASE_PRICE <= ? " +
////                "ORDER BY p.RELEASE_PRICE DESC " +
////                "LIMIT 1";
//        String sql = "SELECT p.*, b.NO AS BRAND_NO, b.BRAND, pi.FILE_PATH, pi.FILE_SIZE, pi.REGDATE " +
//                "FROM PRODUCT p " +
//                "JOIN BRAND b ON p.BRAND_NO = b.NO " +
//                "JOIN PRODUCT_IMG pi ON p.NO = pi.PRODUCT_NO AND p.COLOR_NO = pi.COLOR_NO " +
//                "WHERE p.CATEGORY_NO = 111 " + // 고정된 값
//                "AND p.BRAND_NO = 1000 " +     // 고정된 값
//                "AND p.COLOR_NO = 101 " +      // 고정된 값
//                "AND p.RELEASE_PRICE <= 1000000 " + // 고정된 값
//                "ORDER BY p.RELEASE_PRICE DESC " +
//                "LIMIT 1";
//
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        try {
//            conn = DbUtil.getConnection();
//            ps = conn.prepareStatement(sql);
////            ps.setInt(1, categoryNo);
////            ps.setInt(2, brandNo);
////            ps.setInt(3, colorNo);
////            ps.setInt(4, releasePrice);
//
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                BrandDTO brand = new BrandDTO();
//                brand.setNo(rs.getInt("b.NO"));
//                brand.setBrand(rs.getString("b.BRAND"));
//
//                ProductImgDTO productImg = new ProductImgDTO();
//                productImg.setFilePath(rs.getString("pi.FILE_PATH"));
//                productImg.setFileSize(rs.getString("pi.FILE_SIZE"));
//                productImg.setRegdate(rs.getString("pi.REGDATE"));
//
//                ProductDTO product = new ProductDTO();
//                product.setNo(rs.getInt("p.NO"));
//                product.setBrandNo(rs.getInt("p.BRAND_NO"));
//                product.setCategoryNo(rs.getInt("p.CATEGORY_NO"));
//                product.setColorNo(rs.getInt("p.COLOR_NO"));
//                product.setEngName(rs.getString("p.ENG_NAME"));
//                product.setKorName(rs.getString("p.KOR_NAME"));
//                product.setRelease(rs.getString("p.RELEASE"));
//                product.setReleasePrice(rs.getInt("p.RELEASE_PRICE"));
//                product.setModelNumber(rs.getString("p.MODEL_NUMBER"));
//                product.setRegdate(rs.getString("p.REGDATE"));
//                product.setSalesQuantity(rs.getInt("p.SALES_QUANTITY"));
//                product.setProductImg(productImg);
//                product.setBrandName(brand);
//
//                return product;
//            }
//        } finally {
//            DbUtil.dbClose(conn, ps, rs);
//        }
//
//        return null;
//    }

    @Override
    public ProductDTO getProduct(int categoryNo, int brandNo, int colorNo, int releasePrice) throws SQLException {
        // 고정된 값으로 SQL 쿼리 생성
        String sql = "SELECT p.*, b.NO AS BRAND_NO, b.BRAND, pi.FILE_PATH, pi.FILE_SIZE, pi.REGDATE " +
                "FROM PRODUCT p " +
                "JOIN BRAND b ON p.BRAND_NO = b.NO " +
                "JOIN PRODUCT_IMG pi ON p.NO = pi.PRODUCT_NO AND p.COLOR_NO = pi.COLOR_NO " +
                "WHERE p.CATEGORY_NO = 111 " + // 고정된 값
                "AND p.BRAND_NO = 1000 " +     // 고정된 값
                "AND p.COLOR_NO = 101 " +      // 고정된 값
                "AND p.RELEASE_PRICE <= 1000000 " + // 고정된 값
                "ORDER BY p.RELEASE_PRICE DESC " +
                "LIMIT 1";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DbUtil.getConnection();
            ps = conn.prepareStatement(sql);

            // 고정된 값 사용 시 파라미터 설정 불필요
            // ps.setInt(1, categoryNo);
            // ps.setInt(2, brandNo);
            // ps.setInt(3, colorNo);
            // ps.setInt(4, releasePrice);

            rs = ps.executeQuery();

            if (rs.next()) {
                BrandDTO brand = new BrandDTO();
                brand.setNo(rs.getInt("BRAND_NO")); // "b.NO" 대신 "BRAND_NO" 사용
                brand.setBrand(rs.getString("BRAND")); // "b.BRAND" 대신 "BRAND" 사용

                ProductImgDTO productImg = new ProductImgDTO();
                productImg.setFilePath(rs.getString("FILE_PATH")); // "pi.FILE_PATH" 대신 사용
                productImg.setFileSize(rs.getString("FILE_SIZE")); // "pi.FILE_SIZE" 대신 사용
                productImg.setRegdate(rs.getString("REGDATE")); // "pi.REGDATE" 대신 사용

                ProductDTO product = new ProductDTO();
                product.setNo(rs.getInt("NO")); // "p.NO" 대신 "NO" 사용
                product.setBrandNo(rs.getInt("BRAND_NO")); // "p.BRAND_NO" 대신 사용
                product.setCategoryNo(rs.getInt("CATEGORY_NO")); // "p.CATEGORY_NO" 대신 사용
                product.setColorNo(rs.getInt("COLOR_NO")); // "p.COLOR_NO" 대신 사용
                product.setEngName(rs.getString("ENG_NAME")); // "p.ENG_NAME" 대신 사용
                product.setKorName(rs.getString("KOR_NAME")); // "p.KOR_NAME" 대신 사용
                product.setRelease(rs.getString("RELEASE")); // "p.RELEASE" 대신 사용
                product.setReleasePrice(rs.getInt("RELEASE_PRICE")); // "p.RELEASE_PRICE" 대신 사용
                product.setModelNumber(rs.getString("MODELNUMBER")); // "p.MODELNUMBER" 대신 사용
                product.setRegdate(rs.getString("REGDATE")); // "p.REGDATE" 대신 사용
                product.setSalesQuantity(rs.getInt("SALES_QUANTITY")); // "p.SALES_QUANTITY" 대신 사용
                product.setProductImg(productImg);
                product.setBrandName(brand);

                return product;
            }
        } finally {
            DbUtil.dbClose(conn, ps, rs);
        }

        return null;
    }






}
