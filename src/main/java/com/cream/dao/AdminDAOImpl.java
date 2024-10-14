package com.cream.dao;

import com.cream.dto.SalesDTO;
import com.cream.dto.SurveyDTO;
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
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtil.getConnection();
            conn.setAutoCommit(false);


            String deletePurchasesSql = "DELETE FROM PURCHASE WHERE SALES_NO=?";
            ps = conn.prepareStatement(deletePurchasesSql);
            ps.setInt(1, salesNo);
            int deletedPurchases = ps.executeUpdate();
            ps.close();


            String deleteSalesSql = "DELETE FROM USERS_SALES WHERE NO=?";
            ps = conn.prepareStatement(deleteSalesSql);
            ps.setInt(1, salesNo);
            int deletedSales = ps.executeUpdate();
            ps.close();

            conn.commit();
            return deletedSales;
        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback();
            }
            throw e;
        } finally {
            DbUtil.dbClose(conn, ps, null);
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
        String sql = "UPDATE USERS_SALES SET SALES_STATUS=? WHERE NO=?";
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
}
