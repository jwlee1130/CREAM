package com.cream.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cream.dto.NotifyDTO;
import com.cream.util.DbUtil;

public class NotifyFDAOImpl implements NotifyDAO {

	@Override
	public int insertNotify(Connection con,NotifyDTO notify) throws SQLException {
		PreparedStatement ps=null;
		int result=0;
		String sql = "INSERT INTO NOTIFY(`USER_NO`,`SALES_NO`,`PRODUCT_NO`,`MSG`) VALUES(?,?,?,?)";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, notify.getUserNo());
			ps.setInt(2, notify.getSalesNo());
			ps.setInt(3, notify.getProductNo());
			ps.setString(4, notify.getMsg());
			
			result  = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(ps);
		}
			
		return result;
	}

	@Override
	public List<NotifyDTO> sendNotify(int user_no) throws SQLException {
		List<NotifyDTO> list = new ArrayList<NotifyDTO>();
		Connection con = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		String sql = "SELECT * FROM NOTIFY WHERE USER_NO = ?";
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_no);
			rs= ps.executeQuery();
			
			while(rs.next()) {
				NotifyDTO notify = new NotifyDTO(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7));
				list.add(notify);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, ps,rs);
		}
		
		return list;
	}

	@Override
	public int deleteNotify(int user_no) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNotify(int user_no, int notify_no) throws SQLException {
		Connection con = null;
		PreparedStatement ps=null;
		int result=0;
		String sql = "UPDATE NOTIFY SET IS_READ = 1 WHERE USER_NO=? AND NO = ?";
		try {
			con=DbUtil.getConnection();
			ps=con.prepareStatement(sql);
			ps.setInt(1, user_no);
			ps.setInt(2, notify_no);
			
			result = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			DbUtil.dbClose(con, ps);
		}
			
		return result;
	}

}
