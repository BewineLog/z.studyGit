package com.model2.mvc.service.product;

import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.domain.Product;

public interface ProductService{
	
	public void addProduct(Product productVO) throws Exception;
	
	public Product getProduct(int prodNo) throws Exception;
	
	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception;
	
	public void updateProduct(Product productVO) throws Exception;
	
	
}