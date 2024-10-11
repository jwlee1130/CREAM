package com.cream.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cream.dao.ProductDAOImpl;
import com.cream.dto.ProductDTO;

public class ProductServiceImpl implements ProductService {
	ProductDAOImpl productDao = new ProductDAOImpl();
	List<ProductDTO> productList = new ArrayList<ProductDTO>();
	
	@Override
	public List<ProductDTO> selectAllProduct() throws SQLException {
		//전체 상품 검색
		productList = productDao.selectAllProduct();
		return productList;
	}
	
	@Override
	public ProductDTO searchByProductId(int productId) throws SQLException {
		// 상품 ID로 검색
		ProductDTO product = productDao.searchByProductId(productId);
		return product;
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
