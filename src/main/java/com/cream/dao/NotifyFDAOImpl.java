package com.cream.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.cream.dto.NotifyDTO;
import com.cream.util.DbUtil;

public class NotifyFDAOImpl implements NotifyDAO {
		private Properties proFile = new Properties();
	
		public NotifyFDAOImpl() {
			try {
				//dbQuery를 준비한 ~.properties파일을 로딩해서 Properties 자료구조에 저장한다.

				//현재 프로젝트가 런타임(실행)될때, 즉 서버가 실행될때 classes폴더의 위치를
				//동적으로 가져와서 경로를 설정해야한다.
				InputStream is = getClass().getClassLoader().getResourceAsStream("dbQuery.properties");
				proFile.load(is);

				System.out.println("query.selectAllProduct = " +proFile.getProperty("query.selectAllProduct"));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	@Override
	public int insertNotify(Connection con,NotifyDTO notify) throws SQLException {
		PreparedStatement ps=null;
		int result=0;
		String sql = proFile.getProperty("query.insertNotify") ;
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
		String sql = proFile.getProperty("query.getNotifyList");
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
		// TODO Auto-generated method stub
		return 0;
	}

}
