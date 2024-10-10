package com.cream.service;

import java.sql.SQLException;
import java.util.List;

import com.cream.dao.ProductDAOImpl;
import com.cream.dto.ProductDTO;

public class ProductServiceImpl implements ProductService {
	ProductDAOImpl productDao = new ProductDAOImpl();

	@Override
	public List<ProductDTO> selectAllProduct() throws SQLException {
		//전체 상품 검색
		List<ProductDTO> productList = productDao.selectAllProduct();
		
		return productList;
	}

	@Override
	public List<ProductDTO> searchProductKor(String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductDTO> searchProductEng(String keyword) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
