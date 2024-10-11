package com.cream.service;

import java.sql.SQLException;

import com.cream.dao.ProductDAO;
import com.cream.dao.ProductDAOImpl;
import com.cream.dto.ProductDTO;

public class ProductServiceImpl {
	ProductDAO dao = new ProductDAOImpl();
	public ProductDTO detail(int productNo) throws SQLException {
		ProductDTO product = dao.detail(productNo);
		
		if(product==null)
			throw new SQLException("해당 상품이 존재하지 않습니다.");
		return product;
	}

}
