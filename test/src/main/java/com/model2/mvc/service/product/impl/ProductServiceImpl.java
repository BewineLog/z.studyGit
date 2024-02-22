package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDao;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class ProductServiceImpl implements ProductService {
	ProductDao productDao = new ProductDao();
	
	public void addProduct(ProductVO productVO) throws Exception{
		productDao.addProduct(productVO);
	}
	
	
	public ProductVO getProduct(int prodNo) throws Exception {
		return productDao.getProduct(prodNo);
	}
	
	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {
		
		return productDao.getProductList(searchVO);
	}
	
	public HashMap<String, Object> getProductList(SearchVO searchVO, List<Integer> list) throws Exception {
		
		return productDao.getProductList(searchVO, list);
	}
	
	public void updateProduct(ProductVO productVO) throws Exception{
		productDao.updateProduct(productVO);
	}
	
	
}
